package br.com.aranha.dataanalysissystem.parser;


import br.com.aranha.dataanalysissystem.domain.input.Customer;
import br.com.aranha.dataanalysissystem.properties.ParserProperties;
import br.com.aranha.dataanalysissystem.stub.CustomerStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CustomerParserTest {

    @InjectMocks
    private CustomerParser customerParser;

    @Mock
    private ParserProperties parserProperties;

    @Test
    public void shouldCreateNewCustomer() {
        when(parserProperties.getRowDataSplitter()).thenReturn("ç");
        when(parserProperties.getCustomerCnpjIndex()).thenReturn(1);
        when(parserProperties.getCustomerNameIndex()).thenReturn(2);
        when(parserProperties.getCustomerBusinessAreaIndex()).thenReturn(3);

        String customerLine = "002ç2345675434544345çJosedaSilvaçRural";

        Customer actual = customerParser.parseCustomer(customerLine);
        Customer expected = CustomerStub.createCustomer();

        Assertions.assertEquals(actual, expected);
    }

}