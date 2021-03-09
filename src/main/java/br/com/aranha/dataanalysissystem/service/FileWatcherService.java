package br.com.aranha.dataanalysissystem.service;

import br.com.aranha.dataanalysissystem.domain.input.FileReaded;
import br.com.aranha.dataanalysissystem.io.input.FilesReader;
import br.com.aranha.dataanalysissystem.io.output.FileWriter;
import br.com.aranha.dataanalysissystem.properties.IOProperties;
import br.com.aranha.dataanalysissystem.properties.ParserProperties;
import br.com.aranha.dataanalysissystem.repository.CustomerRepository;
import br.com.aranha.dataanalysissystem.repository.FileRepository;
import br.com.aranha.dataanalysissystem.repository.SaleRepository;
import br.com.aranha.dataanalysissystem.repository.SalesmanRepository;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class FileWatcherService implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(FileWatcherService.class);

    private final LineInputService lineInputService;

    private final FileRepository fileRepository;

    private final CustomerRepository customerRepository;

    private final SalesmanRepository salesmanRepository;

    private final SaleRepository saleRepository;

    private final FilesReader fileReader;

    private final FileWriter fileWriter;

    private final SalesmanService salesmanService;

    private final ReportService reportService;

    private final IOProperties ioProperties;

    private final ParserProperties parserProperties;

    public FileWatcherService(LineInputService lineInputService, FileRepository fileRepository,
                              CustomerRepository customerRepository, SalesmanRepository salesmanRepository,
                              SaleRepository saleRepository, FilesReader fileReader, FileWriter fileWriter,
                              SalesmanService salesmanService, ReportService reportService,
                              IOProperties ioProperties, ParserProperties parserProperties) {

        this.lineInputService = lineInputService;
        this.fileRepository = fileRepository;
        this.customerRepository = customerRepository;
        this.salesmanRepository = salesmanRepository;
        this.saleRepository = saleRepository;
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
        this.salesmanService = salesmanService;
        this.reportService = reportService;
        this.ioProperties = ioProperties;
        this.parserProperties = parserProperties;
    }

    @Override
    public void run() {
        try {
            Path pathInput = Paths.get(ioProperties.getInputDirectory());
            Path pathOutput = Paths.get(ioProperties.getOutputDirectory());

            monitoringDirectoryAndWriteReport(pathInput, pathOutput);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void monitoringDirectoryAndWriteReport(Path pathInput, Path pathOutput) throws IOException, InterruptedException {
        log.info("starting monitoring directory");
        createFoldersIfNecessary(pathInput, pathOutput);

        saveNewFiles();
        writeReport();

        WatchService watchService = register(pathInput);

        detectNewFileInFolder(watchService);
    }

    private void saveNewFiles() throws IOException {
        log.info("saving new files");
        List<FileReaded> fileReadList = fileRepository.findAll();

        List<String> filesNameRead = Objects.requireNonNull(fileReadList).stream()
                .map(FileReaded::getName)
                .collect(Collectors.toList());

        List<String> linesRead = fileReader.readFile(filesNameRead, fileRepository);

        linesRead.forEach(lineInputService::saveLine);
    }

    private void writeReport() {
        fileWriter.writeReport(reportService.buildReport());
    }

    private void createFoldersIfNecessary(Path pathInput, Path pathOutput) {
        File fileInput = new File(pathInput.toString());
        File fileOutput = new File(pathOutput.toString());

        if(!fileInput.exists()) {
            log.info("creating path input");
            fileInput.mkdirs();
        }

        if(!fileOutput.exists()) {
            log.info("creating path output");
            fileOutput.mkdirs();
        }
    }

    private WatchService register(Path pathInput) {
        WatchService watchService = null;
        try {
            watchService = FileSystems.getDefault().newWatchService();
            pathInput.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        } catch (IOException e) {
            log.error("an error occurred when try to register new path to be monitored", e.getCause());
        }
        return watchService;
    }

    private void detectNewFileInFolder(WatchService watchService) throws InterruptedException, IOException {
        while (true) {
            WatchKey watchKey = watchService.take();
            Optional<WatchEvent<?>> watchEvent = watchKey.pollEvents().stream().findFirst();
            Path directory = (Path) watchEvent.get().context();
            if(directory.toString().endsWith(ioProperties.getInputFileExtension())) {
                log.info("new file detected, name {}", directory.toString());
                saveNewFiles();
                writeReport();
                FileReaded fileReaded = new FileReaded(directory.toString());
                fileRepository.save(fileReaded);
            }
        }
    }
}
