package br.com.aranha.dataanalysissystem.service;

import br.com.aranha.dataanalysissystem.domain.output.Report;
import br.com.aranha.dataanalysissystem.repository.CustomerRepository;
import br.com.aranha.dataanalysissystem.repository.SaleRepository;
import br.com.aranha.dataanalysissystem.repository.SalesmanRepository;
import br.com.aranha.dataanalysissystem.stub.ReportStub;
import br.com.aranha.dataanalysissystem.stub.SaleStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ReportServiceTest {

    @InjectMocks
    private ReportService reportService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private SalesmanRepository salesmanRepository;

    @Mock
    private SaleRepository saleRepository;

    @Test
    public void shouldCreateNewReport() {
        when(saleRepository.findAll()).thenReturn(SaleStub.createSaleList());
        when(customerRepository.count()).thenReturn(2L);
        when(salesmanRepository.count()).thenReturn(2L);

        Report actual = reportService.buildReport();

        Report expected = ReportStub.createReport();

        Assertions.assertEquals(actual, expected);
    }
}