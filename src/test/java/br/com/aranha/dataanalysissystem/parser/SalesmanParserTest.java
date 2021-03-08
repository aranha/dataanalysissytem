package br.com.aranha.dataanalysissystem.parser;

import br.com.aranha.dataanalysissystem.domain.input.Salesman;
import br.com.aranha.dataanalysissystem.properties.ParserProperties;
import br.com.aranha.dataanalysissystem.stub.SalesmanStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SalesmanParserTest {

    @InjectMocks
    private SalesmanParser salesmanParser;

    @Mock
    private ParserProperties parserProperties;

    @Test
    public void shouldCreateNewSalesman() {
        when(parserProperties.getRowDataSplitter()).thenReturn("ç");
        when(parserProperties.getSalesmanCpfIndex()).thenReturn(1);
        when(parserProperties.getSalesmanNameIndex()).thenReturn(2);
        when(parserProperties.getSalesmanSalaryIndex()).thenReturn(3);

        String salesmanLine = "001ç1234567891234çDiegoç50000";

        Salesman actual = salesmanParser.parseSalesman(salesmanLine);
        Salesman expected = SalesmanStub.createSalesman();

        Assertions.assertEquals(actual, expected);
    }

}