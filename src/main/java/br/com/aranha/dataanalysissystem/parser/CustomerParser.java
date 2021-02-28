package br.com.aranha.dataanalysissystem.parser;

import br.com.aranha.dataanalysissystem.domain.input.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerParser {
    public Customer parseCustomer(String lineCustomer) {
        String[] customerSplitData = lineCustomer.split("ç");
        String cnpj = customerSplitData[1];
        String name = customerSplitData[2];
        String businessArea = customerSplitData[3];
        return new Customer(cnpj, name, businessArea);
    }
}
