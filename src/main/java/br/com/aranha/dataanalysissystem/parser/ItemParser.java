package br.com.aranha.dataanalysissystem.parser;

import br.com.aranha.dataanalysissystem.domain.input.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemParser {
    public Item parseItem(String line) {
        String [] itemSplitData = line.split("-");
        String id = itemSplitData[0];
        int quantity = Integer.parseInt(itemSplitData[1]);
        double price = Double.parseDouble(itemSplitData[2]);
        return new Item(id, quantity, price);
    }
}
