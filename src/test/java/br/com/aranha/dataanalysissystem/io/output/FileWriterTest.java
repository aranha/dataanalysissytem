package br.com.aranha.dataanalysissystem.io.output;

import br.com.aranha.dataanalysissystem.domain.output.Report;
import br.com.aranha.dataanalysissystem.properties.IOProperties;
import br.com.aranha.dataanalysissystem.stub.ReportStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class FileWriterTest {

    @InjectMocks
    private FileWriter fileWriter;

    @Mock
    private IOProperties ioProperties;

    @Test
    public void shouldWriteReport() {
        String systemHome = System.getProperty("user.home");

        when(ioProperties.getOutputDirectory()).thenReturn(systemHome + "/data/out/");
        when(ioProperties.getReportFileName()).thenReturn("report");
        when(ioProperties.getOutputFileExtension()).thenReturn(".done.dat");

        Path pathOutput = Paths.get(ioProperties.getOutputDirectory());

        createOutputFolderIfNecessary(pathOutput);

        Report report = ReportStub.createReport();

        File file = new File(ioProperties.getOutputDirectory() + ioProperties.getReportFileName()
                + ioProperties.getOutputFileExtension());

        fileWriter.writeReport(report);

        Assertions.assertTrue(file.exists());
    }

    private void createOutputFolderIfNecessary(Path pathOutput) {
        File fileOutput = new File(pathOutput.toString());

        if(!fileOutput.exists()) {
            fileOutput.mkdirs();
        }
    }
}