package br.com.aranha.dataanalysissystem.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class LineInputServiceTest {

    @InjectMocks
    private LineInputService lineInputService;

    @Mock
    private SalesmanService salesmanService;

    @Mock
    private CustomerService customerService;

    @Mock
    private SaleService saleService;

    @Test
    public void shouldVerifySalesmanProcessLine() {

    }

    @Test
    public void shouldVerifyCustomerProcessLine() {

    }

    @Test
    public void shouldVerifySaleProcessLine() {

    }

    @Test
    public void shouldVerifyNoOneProcessLineCalled() {

    }
}