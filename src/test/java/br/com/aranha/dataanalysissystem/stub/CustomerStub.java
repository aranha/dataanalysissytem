package br.com.aranha.dataanalysissystem.stub;

import br.com.aranha.dataanalysissystem.domain.input.Customer;

public class CustomerStub {
    public static Customer createCustomer() {
        return new Customer("2345675434544345", "JosedaSilva", "Rural");
    }
}
