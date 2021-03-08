package br.com.aranha.dataanalysissystem.service;

import br.com.aranha.dataanalysissystem.domain.input.Customer;
import br.com.aranha.dataanalysissystem.parser.CustomerParser;
import br.com.aranha.dataanalysissystem.repository.CustomerRepository;
import br.com.aranha.dataanalysissystem.stub.CustomerStub;
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
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerParser customerParser;

    @Spy
    private CustomerRepository customerRepository;

    @Test
    public void shouldProcessCustomerLine() {
        String customerLine = "002ç2345675434544345çJosedaSilvaçRural";

        Customer customer = CustomerStub.createCustomer();

        when(customerParser.parseCustomer(eq(customerLine))).thenReturn(customer);

        customerService.processNewRowData(customerLine);

        verify(customerRepository).save(customer);
    }
}