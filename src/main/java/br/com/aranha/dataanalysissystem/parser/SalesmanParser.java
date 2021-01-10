package br.com.aranha.dataanalysissystem.parser;

import br.com.aranha.dataanalysissystem.domain.input.Salesman;
import org.springframework.stereotype.Component;

@Component
public class SalesmanParser {
    public Salesman parseSalesman(String lineSalesman) {
        String[] customerSplitData = lineSalesman.split("ç");
        String cpf = customerSplitData[0];
        String name = customerSplitData[1];
        double salary = Double.parseDouble(customerSplitData[2]);
        return new Salesman(cpf, name, salary);
    }
}
