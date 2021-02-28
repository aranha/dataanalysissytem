package br.com.aranha.dataanalysissystem.service;

import br.com.aranha.dataanalysissystem.domain.input.Salesman;
import br.com.aranha.dataanalysissystem.parser.SalesmanParser;
import br.com.aranha.dataanalysissystem.repository.SalesmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesmanService implements RowDataService{

    @Autowired
    private SalesmanParser salesmanParser;

    @Autowired
    private SalesmanRepository salesmanRepository;

    @Override
    public void processNewRowData(String parsedLine) {
        Salesman salesman = salesmanParser.parseSalesman(parsedLine);
        salesmanRepository.save(salesman);
    }
}
