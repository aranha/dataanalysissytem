package br.com.aranha.dataanalysissystem.parser;

import br.com.aranha.dataanalysissystem.domain.input.Item;
import br.com.aranha.dataanalysissystem.properties.ParserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemParser {
    @Autowired
    private ParserProperties parserProperties;

    public Item parseItem(String line) {
        line = line.replace("[", "").replace("]", "");
        String [] itemSplitData = line.split(parserProperties.getItemSplitter());
        String id = itemSplitData[parserProperties.getItemIdIndex()];
        int quantity = Integer.parseInt(itemSplitData[parserProperties.getItemQuantityIndex()]);
        double price = Double.parseDouble(itemSplitData[parserProperties.getItemPriceIndex()]);
        return new Item(id, quantity, price);
    }
}
