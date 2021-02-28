package br.com.aranha.dataanalysissystem.io.input;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FilesReader {
    public List<String> readFile(List<String> filesAlreadyReadList) throws IOException {
        List<File> files = getFilesToRead(filesAlreadyReadList);
        List<String> lines = new ArrayList<>();
        files.forEach(file -> {
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    lines.add(line);
                }
            }catch (IOException e){
                e.getCause();
            }
        });
        return lines;
    }

    private List<File> getFilesToRead(List<String> filesAlreadyReadList) throws IOException {
        final String homePathIn = System.getProperty("user.home") + "/data/in";

        final Stream<Path> pathStream = Files.walk(Paths.get(homePathIn));

        return pathStream
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .filter(file -> filesAlreadyReadList.stream().noneMatch(fileAlreadyRead ->
                        file.getName().equalsIgnoreCase(fileAlreadyRead)))
                .filter(file -> file.getName().endsWith(".dat"))
                .collect(Collectors.toList());
    }
}
