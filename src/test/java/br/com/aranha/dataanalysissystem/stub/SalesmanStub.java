package br.com.aranha.dataanalysissystem.stub;

import br.com.aranha.dataanalysissystem.domain.input.Salesman;

public class SalesmanStub {
    public static Salesman createSalesman() {
        return new Salesman("1234567891234", "Diego", 50000);
    }
}
