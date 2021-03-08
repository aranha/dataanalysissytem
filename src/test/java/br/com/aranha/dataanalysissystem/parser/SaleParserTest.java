package br.com.aranha.dataanalysissystem.parser;

import br.com.aranha.dataanalysissystem.domain.input.Sale;
import br.com.aranha.dataanalysissystem.properties.ParserProperties;
import br.com.aranha.dataanalysissystem.stub.ItemStub;
import br.com.aranha.dataanalysissystem.stub.SaleStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SaleParserTest {

    @InjectMocks
    private SaleParser saleParser;

    @Mock
    private ItemParser itemParser;

    @Mock
    private ParserProperties parserProperties;

    @Test
    public void shouldCreateSale() {
        when(itemParser.parseItem(eq("[1-10-100"))).thenReturn(ItemStub.createItem1());
        when(itemParser.parseItem(eq("2-30-2.50"))).thenReturn(ItemStub.createItem2());
        when(itemParser.parseItem(eq("3-40-3.10]"))).thenReturn(ItemStub.createItem3());

        when(parserProperties.getRowDataSplitter()).thenReturn("ç");
        when(parserProperties.getSaleIdIndex()).thenReturn(1);
        when(parserProperties.getSaleItemsIndex()).thenReturn(2);
        when(parserProperties.getSaleNameSalesmanIndex()).thenReturn(3);
        when(parserProperties.getItemListSplitter()).thenReturn(",");

        String saleLine = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";

        Sale actual = saleParser.parseSale(saleLine);

        Sale expected = SaleStub.createSale();

        Assertions.assertEquals(actual, expected);
    }
}