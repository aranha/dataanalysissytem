package br.com.aranha.dataanalysissystem.domain.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@Document("sale")
public class Sale {
    @Id
    private String id;
    private List<Item> items;
    private String salesmanName;

    public double getSalePriceAllItems() {
        return Objects.requireNonNull(items).stream()
                .mapToDouble(Item::getSalePriceItem)
                .sum();
    }
}
