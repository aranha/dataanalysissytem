package br.com.aranha.dataanalysissystem.service;

import br.com.aranha.dataanalysissystem.domain.input.Sale;
import br.com.aranha.dataanalysissystem.parser.SaleParser;
import br.com.aranha.dataanalysissystem.repository.SaleRepository;
import br.com.aranha.dataanalysissystem.stub.SaleStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SaleServiceTest {

    @InjectMocks
    private SaleService saleService;

    @Mock
    private SaleParser saleParser;

    @Spy
    private SaleRepository saleRepository;

    @Test
    public void shouldProcessSaleLine() {
        String customerLine = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";

        Sale sale = SaleStub.createSale();

        when(saleParser.parseSale(eq(customerLine))).thenReturn(sale);

        saleService.processNewRowData(customerLine);

        verify(saleRepository).save(sale);
    }
}