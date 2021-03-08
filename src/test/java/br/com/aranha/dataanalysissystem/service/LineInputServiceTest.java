package br.com.aranha.dataanalysissystem.service;

import br.com.aranha.dataanalysissystem.enums.DataType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class LineInputServiceTest {

    @InjectMocks
    private LineInputService lineInputService;

    @Spy
    private SalesmanService salesmanService;

    @Spy
    private CustomerService customerService;

    @Spy
    private SaleService saleService;

    @Test
    public void shouldVerifySalesmanProcessLine() {
        String salesmanLine = "001ç1234567891234çDiegoç50000";

        doNothing().when(salesmanService).processNewRowData(salesmanLine);

        lineInputService.saveLine(salesmanLine);

        verify(salesmanService).processNewRowData(salesmanLine);
        verify(customerService, times(0)).processNewRowData(salesmanLine);
        verify(saleService, times(0)).processNewRowData(salesmanLine);
    }

    @Test
    public void shouldVerifyCustomerProcessLine() {
        String salesmanLine = "002ç2345675434544345çJosedaSilvaçRural";

        doNothing().when(customerService).processNewRowData(salesmanLine);

        lineInputService.saveLine(salesmanLine);

        verify(salesmanService, times(0)).processNewRowData(salesmanLine);
        verify(customerService).processNewRowData(salesmanLine);
        verify(saleService, times(0)).processNewRowData(salesmanLine);

    }

    @Test
    public void shouldVerifySaleProcessLine() {
        String salesmanLine = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";

        doNothing().when(saleService).processNewRowData(salesmanLine);

        lineInputService.saveLine(salesmanLine);

        verify(salesmanService, times(0)).processNewRowData(salesmanLine);
        verify(customerService, times(0)).processNewRowData(salesmanLine);
        verify(saleService).processNewRowData(salesmanLine);

    }

    @Test
    public void shouldVerifyNoOneProcessLineCalled() {
        String salesmanLine = "004ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";

        doNothing().when(saleService).processNewRowData(salesmanLine);

        lineInputService.saveLine(salesmanLine);

        verify(salesmanService, times(0)).processNewRowData(salesmanLine);
        verify(customerService, times(0)).processNewRowData(salesmanLine);
        verify(saleService, times(0)).processNewRowData(salesmanLine);

    }
}