package br.com.aranha.dataanalysissystem.parser;

import br.com.aranha.dataanalysissystem.domain.input.Salesman;
import org.springframework.stereotype.Component;

@Component
public class SalesmanParser {
    public Salesman parseSalesman(String lineSalesman) {
        String[] customerSplitData = lineSalesman.split("ç");
        String cpf = customerSplitData[1];
        String name = customerSplitData[2];
        double salary = Double.parseDouble(customerSplitData[3]);
        return new Salesman(cpf, name, salary);
    }
}
