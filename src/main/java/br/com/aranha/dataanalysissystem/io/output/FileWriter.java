package br.com.aranha.dataanalysissystem.io.output;

import br.com.aranha.dataanalysissystem.domain.output.Report;
import br.com.aranha.dataanalysissystem.io.FileWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileWriter {
    private static final Logger log = LoggerFactory.getLogger(FileWatcher.class);

    public void writeReport(Report report) {
        try {
            String homePathOut = System.getProperty("user.home") + "/data/out/";
            String outputFileName = "report.done.dat";
            Path path = Paths.get(homePathOut, outputFileName);
            Files.write(path, report.generateReport().getBytes());
        } catch (IOException e) {
           log.error("an error occurred in the try to save a new report", e.getCause());
        }
    }
}
