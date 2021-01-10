package br.com.aranha.dataanalysissystem.service;

import br.com.aranha.dataanalysissystem.domain.input.Customer;
import br.com.aranha.dataanalysissystem.parser.CustomerParser;
import br.com.aranha.dataanalysissystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements RowDataService{
    @Autowired
    private CustomerParser customerParser;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void processNewRowData(String parsedLine) {
        Customer customer = customerParser.parseCustomer(parsedLine);
        customerRepository.save(customer);
    }
}
