package br.com.aranha.dataanalysissystem.parser;

import br.com.aranha.dataanalysissystem.domain.input.Customer;
import br.com.aranha.dataanalysissystem.properties.ParserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerParser {
    @Autowired
    private ParserProperties parserProperties;

    public Customer parseCustomer(String lineCustomer) {
        String[] customerSplitData = lineCustomer.split(parserProperties.getRowDataSplitter());
        String cnpj = customerSplitData[parserProperties.getCustomerCnpjIndex()];
        String name = customerSplitData[parserProperties.getCustomerNameIndex()];
        String businessArea = customerSplitData[parserProperties.getCustomerBusinessAreaIndex()];
        return new Customer(cnpj, name, businessArea);
    }
}
