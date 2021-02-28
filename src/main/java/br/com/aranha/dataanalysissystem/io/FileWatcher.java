package br.com.aranha.dataanalysissystem.io;

import br.com.aranha.dataanalysissystem.domain.input.FileReaded;
import br.com.aranha.dataanalysissystem.domain.output.Report;
import br.com.aranha.dataanalysissystem.io.input.FilesReader;
import br.com.aranha.dataanalysissystem.io.output.FileWriter;
import br.com.aranha.dataanalysissystem.repository.CustomerRepository;
import br.com.aranha.dataanalysissystem.repository.FileRepository;
import br.com.aranha.dataanalysissystem.repository.SaleRepository;
import br.com.aranha.dataanalysissystem.repository.SalesmanRepository;
import br.com.aranha.dataanalysissystem.service.LineInputService;
import br.com.aranha.dataanalysissystem.service.SalesmanService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Data
public class FileWatcher implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(FileWatcher.class);

    private final LineInputService lineInputService;

    private final FileRepository fileRepository;

    private final CustomerRepository customerRepository;

    private final SalesmanRepository salesmanRepository;

    private final SaleRepository saleRepository;

    private final FilesReader filesReader;

    private final FileWriter fileWriter;

    private final SalesmanService salesmanService;

    private final Path pathInput = Paths.get(System.getProperty("user.home"),"/data/in/");

    private final Path pathOutput = Paths.get(System.getProperty("user.home"), "/data/out/");


    public FileWatcher(LineInputService lineInputService, FileRepository fileRepository, CustomerRepository customerRepository, SalesmanRepository salesmanRepository, SaleRepository saleRepository, FilesReader filesReader, FileWriter fileWriter, SalesmanService salesmanService) {
        this.lineInputService = lineInputService;
        this.fileRepository = fileRepository;
        this.customerRepository = customerRepository;
        this.salesmanRepository = salesmanRepository;
        this.saleRepository = saleRepository;
        this.filesReader = filesReader;
        this.fileWriter = fileWriter;
        this.salesmanService = salesmanService;
    }

    @Override
    public void run() {
        try {
            monitoringDirectoryAndWriteReport();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void monitoringDirectoryAndWriteReport() throws IOException, InterruptedException {
        createFoldersIfNecessary();

        saveNewFiles();
        writeReport();

        WatchService watchService = register();

        detectNewFileInFolder(watchService);
    }

    private void saveNewFiles() throws IOException {
        List<FileReaded> fileReadedList = fileRepository.findAll();

        List<String> filesNameRead = Objects.requireNonNull(fileReadedList).stream()
                .map(FileReaded::getName)
                .collect(Collectors.toList());

        List<String> linesRead = filesReader.readFile(filesNameRead, fileRepository);

        linesRead.forEach(lineInputService::saveLine);
    }

    private void writeReport() {
        int amountClients = Math.toIntExact(customerRepository.count());

        int amountSalesman = Math.toIntExact(salesmanRepository.count());

        String mostExpensiveSaleId = "as";

        String worstSalesmanEverName = "paulo";

        Report report = new Report(amountClients, amountSalesman, mostExpensiveSaleId, worstSalesmanEverName);

        fileWriter.writeReport(report);
    }

    private void createFoldersIfNecessary() {
        File fileInput = new File(pathInput.toString());
        File fileOutput = new File(pathOutput.toString());

        if(!fileInput.exists()) {
            fileInput.mkdirs();
        }

        if(!fileOutput.exists()) {
            fileOutput.mkdirs();
        }
    }

    private WatchService register() {
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
            if(directory.toString().endsWith(".dat")) {
                log.info("new file detected, name {}", directory.toString());
                saveNewFiles();
                writeReport();
                FileReaded fileReaded = new FileReaded(directory.toString());
                fileRepository.save(fileReaded);
            }
        }
    }
}
