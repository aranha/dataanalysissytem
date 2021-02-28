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
        log.info("startedaranha");
        try {
            monitoringDirectoryAndWriteReport();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void monitoringDirectoryAndWriteReport() throws IOException, InterruptedException {
        Path pathInput = Paths.get("/data/in/");
        Path pathOutput = Paths.get("/data/out/");
        createFoldersIfNecessary(pathInput, pathOutput);
        saveNewFiles();
        writeReport();
        WatchService watchService = FileSystems.getDefault().newWatchService();
        pathInput.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        while (true) {
            WatchKey watchKey = watchService.take();
            Optional<WatchEvent<?>> watchEvent = watchKey.pollEvents().stream().findFirst();
            Path directory = (Path) watchEvent.get().context();
            if(directory.endsWith(".dat")) {
                saveNewFiles();
                writeReport();
            }
        }
    }

    private void saveNewFiles() throws IOException {
        List<FileReaded> fileReadedList = fileRepository.findAll();

        List<String> filesNameRead = Objects.requireNonNull(fileReadedList).stream()
                .map(FileReaded::getName)
                .collect(Collectors.toList());

        List<String> linesRead = filesReader.readFile(filesNameRead);

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

    private void createFoldersIfNecessary(Path pathInput, Path pathOutput) {
        new File(System.getProperty("user.home"), pathInput.toString()).mkdir();
        new File(System.getProperty("user.home"), pathOutput.toString()).mkdir();
    }
}
