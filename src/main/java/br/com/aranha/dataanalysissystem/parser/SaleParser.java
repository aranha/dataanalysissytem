package br.com.aranha.dataanalysissystem.parser;

import br.com.aranha.dataanalysissystem.domain.input.Item;
import br.com.aranha.dataanalysissystem.domain.input.Sale;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleParser {
    public Sale parseSale(String lineSale) {
        String[] customerSplitData = lineSale.split("ç");
        String id = customerSplitData[0];
        List<Item> items = buildItems(customerSplitData[1]);
        String name = customerSplitData[2];
        return new Sale(id, items, name);
    }

    private List<Item> buildItems(String lineItems) {
        ItemParser itemParser = new ItemParser();
        return Arrays
                .stream(lineItems.split(","))
                .collect(Collectors.toList())
                .stream()
                .map(itemParser::parseItem)
                .collect(Collectors.toList());
    }
}
