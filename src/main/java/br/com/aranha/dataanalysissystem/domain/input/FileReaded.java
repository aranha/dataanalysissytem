package br.com.aranha.dataanalysissystem.domain.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class FileReaded {
    @Id
    private String name;
}
