package br.com.aranha.dataanalysissystem.io.input;

import br.com.aranha.dataanalysissystem.io.output.FileWriter;
import br.com.aranha.dataanalysissystem.properties.IOProperties;
import br.com.aranha.dataanalysissystem.repository.FileRepository;
import br.com.aranha.dataanalysissystem.stub.LineStringStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class FilesReaderTest {

    @InjectMocks
    private FilesReader filesReader;

    @Mock
    private IOProperties ioProperties;

    @Mock
    private FileRepository fileRepository;

    @Test
    public void shouldEvaluateFileReaderAndSavedFileRead() throws IOException {

        String file = "001ç1234567891234çDiegoç50000\n" +
                "001ç3245678865434çRenatoç40000.99\n" +
                "002ç2345675434544345çJosedaSilvaçRural\n" +
                "002ç2345675433444345çEduardoPereiraçRural\n" +
                "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego\n" +
                "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato";

        Files.write(Paths.get(System.getProperty("user.home") + "/data/in/test.dat"), file.getBytes());

        when(ioProperties.getInputDirectory()).thenReturn(System.getProperty("user.home") + "/data/in/test.dat");
        when(ioProperties.getInputFileExtension()).thenReturn(".dat");

        List<String> actual = filesReader.readFile(new ArrayList<>(), fileRepository);

        List<String> expected = LineStringStub.createLinesListString();

        Assertions.assertEquals(actual, expected);

        verify(fileRepository).save(any());
    }
}