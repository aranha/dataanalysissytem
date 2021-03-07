package br.com.aranha.dataanalysissystem.domain.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document("salesman")
public class Salesman {
    @Id
    private String cpf;
    private String name;
    private double salary;
}
