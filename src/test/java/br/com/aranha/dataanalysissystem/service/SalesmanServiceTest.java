package br.com.aranha.dataanalysissystem.service;

import br.com.aranha.dataanalysissystem.domain.input.Sale;
import br.com.aranha.dataanalysissystem.domain.input.Salesman;
import br.com.aranha.dataanalysissystem.parser.SaleParser;
import br.com.aranha.dataanalysissystem.parser.SalesmanParser;
import br.com.aranha.dataanalysissystem.repository.SaleRepository;
import br.com.aranha.dataanalysissystem.repository.SalesmanRepository;
import br.com.aranha.dataanalysissystem.stub.SaleStub;
import br.com.aranha.dataanalysissystem.stub.SalesmanStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SalesmanServiceTest {

    @InjectMocks
    private SalesmanService salesmanService;

    @Mock
    private SalesmanParser salesmanParser;

    @Spy
    private SalesmanRepository salesmanRepository;

    @Test
    public void shouldProcessSalesmanLine() {
        String salesmanLine = "001ç1234567891234çDiegoç50000";

        Salesman salesman = SalesmanStub.createSalesman();

        when(salesmanParser.parseSalesman(eq(salesmanLine))).thenReturn(salesman);

        salesmanService.processNewRowData(salesmanLine);

        verify(salesmanRepository).save(salesman);
    }
}