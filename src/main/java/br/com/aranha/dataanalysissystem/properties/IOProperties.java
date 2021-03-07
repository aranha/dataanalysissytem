package br.com.aranha.dataanalysissystem.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class IOProperties {
    @Value("${application.data.io.extension.input-file-extension}")
    private String inputFileExtension;

    @Value("${application.data.io.extension.output-file-extension}")
    private String outputFileExtension;

    @Value("${application.data.io.directory.input}")
    private String inputDirectory;

    @Value("${application.data.io.directory.output}")
    private String outputDirectory;

    @Value("${application.data.io.file-name.report}")
    private String reportFileName;
}
