package br.com.aranha.dataanalysissystem.domain.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Salesman {
    @Id
    private String cpf;
    private String name;
    private double salary;
}
