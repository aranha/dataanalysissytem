package br.com.aranha.dataanalysissystem.parser;

import br.com.aranha.dataanalysissystem.domain.input.Item;
import br.com.aranha.dataanalysissystem.domain.input.Sale;
import br.com.aranha.dataanalysissystem.properties.ParserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleParser {
    @Autowired
    private ParserProperties parserProperties;

    public Sale parseSale(String lineSale) {
        String[] customerSplitData = lineSale.split(parserProperties.getRowDataSplitter());
        String id = customerSplitData[parserProperties.getSaleIdIndex()];
        List<Item> items = buildItems(customerSplitData[parserProperties.getSaleItemsIndex()]);
        String name = customerSplitData[parserProperties.getSaleNameSalesmanIndex()];
        return new Sale(id, items, name);
    }

    private List<Item> buildItems(String lineItems) {
        ItemParser itemParser = new ItemParser();
        return Arrays
                .stream(lineItems.split(parserProperties.getItemListSplitter()))
                .collect(Collectors.toList())
                .stream()
                .map(itemParser::parseItem)
                .collect(Collectors.toList());
    }
}
