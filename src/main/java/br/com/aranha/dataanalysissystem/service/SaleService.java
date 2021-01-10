package br.com.aranha.dataanalysissystem.service;

import br.com.aranha.dataanalysissystem.domain.input.Sale;
import br.com.aranha.dataanalysissystem.parser.SaleParser;
import br.com.aranha.dataanalysissystem.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService implements RowDataService{
    @Autowired
    private SaleParser saleParser;

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public void processNewRowData(String parsedLine) {
        Sale sale = saleParser.parseSale(parsedLine);
        saleRepository.save(sale);
    }
}
