package br.com.aranha.dataanalysissystem.parser;

import br.com.aranha.dataanalysissystem.domain.input.Salesman;
import br.com.aranha.dataanalysissystem.properties.ParserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalesmanParser {
    @Autowired
    private ParserProperties parserProperties;

    public Salesman parseSalesman(String lineSalesman) {
        String[] customerSplitData = lineSalesman.split(parserProperties.getRowDataSplitter());
        String cpf = customerSplitData[parserProperties.getSalesmanCpfIndex()];
        String name = customerSplitData[parserProperties.getSalesmanNameIndex()];
        double salary = Double.parseDouble(customerSplitData[parserProperties.getSalesmanSalaryIndex()]);
        return new Salesman(cpf, name, salary);
    }
}
