package br.com.aranha.dataanalysissystem.io.output;

import br.com.aranha.dataanalysissystem.domain.output.Report;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;

@Component
public class FileWriter {
    public void writeReport(Report report) {
        String homePathOut = System.getProperty("user.home") + "/data/out/";
        String outputFileName = "report.done.dat";
        try {
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(homePathOut + outputFileName, false));
            writer.write(report.generateReport());
        } catch (IOException e) {
            System.err.println("Cannot write a file, cause happened an error + " + e.getCause());
        }
    }
}
