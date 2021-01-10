package br.com.aranha.dataanalysissystem.domain.input;

import lombok.*;

import java.math.BigDecimal;

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
