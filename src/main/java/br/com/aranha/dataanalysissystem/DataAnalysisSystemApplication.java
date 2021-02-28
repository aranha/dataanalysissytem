package br.com.aranha.dataanalysissystem;

import br.com.aranha.dataanalysissystem.io.FileWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DataAnalysisSystemApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DataAnalysisSystemApplication.class);

    @Autowired
    private FileWatcher fileWatcher;

    public static void main(String[] args) {
        SpringApplication.run(DataAnalysisSystemApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("test");
        new Thread(fileWatcher, "file-watcher").start();
    }
}