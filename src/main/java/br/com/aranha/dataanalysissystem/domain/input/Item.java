package br.com.aranha.dataanalysissystem.domain.input;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private String id;
    private int quantity;
    private double price;

    public double getSalePriceItem() {
        return price*quantity;
    }
}
