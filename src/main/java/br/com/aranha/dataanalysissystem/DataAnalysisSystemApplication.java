package br.com.aranha.dataanalysissystem;

import br.com.aranha.dataanalysissystem.service.FileWatcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataAnalysisSystemApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DataAnalysisSystemApplication.class);

    @Autowired
    private FileWatcherService fileWatcherService;

    public static void main(String[] args) {
        SpringApplication.run(DataAnalysisSystemApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("starting application");
        new Thread(fileWatcherService, "file-watcher").start();
    }
}