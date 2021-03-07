package br.com.aranha.dataanalysissystem.domain.input;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document("customer")
public class Customer {
    @Id
    private String cnpj;
    private String name;
    private String businessArea;
}
