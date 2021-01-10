package br.com.aranha.dataanalysissystem.domain.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@Document("sales")
public class Sale {
    @Id
    private String Id;
    private List<Item> items;
    private String salesmanName;

    public double getSalePrinceAllItems() {
        return items.stream()
                .mapToDouble(Item::getSalePriceItem)
                .sum();
    }
}
