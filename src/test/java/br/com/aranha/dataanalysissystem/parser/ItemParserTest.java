package br.com.aranha.dataanalysissystem.parser;

import br.com.aranha.dataanalysissystem.domain.input.Item;
import br.com.aranha.dataanalysissystem.properties.ParserProperties;
import br.com.aranha.dataanalysissystem.stub.ItemStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ItemParserTest {

    @InjectMocks
    private ItemParser customerParser;

    @Mock
    private ParserProperties parserProperties;

    @Test
    public void shouldCreateNewItem() {
        when(parserProperties.getItemIdIndex()).thenReturn(0);
        when(parserProperties.getItemQuantityIndex()).thenReturn(1);
        when(parserProperties.getItemPriceIndex()).thenReturn(2);
        when(parserProperties.getItemSplitter()).thenReturn("-");
        when(parserProperties.getItemStarter()).thenReturn("[");
        when(parserProperties.getItemFinisher()).thenReturn("]");

        String itemLine = "1-10-100";

        Item actual = customerParser.parseItem(itemLine);
        Item expected = ItemStub.createItem1();

        Assertions.assertEquals(actual, expected);
    }

}