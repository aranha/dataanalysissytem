package br.com.aranha.dataanalysissystem.enums;

import br.com.aranha.dataanalysissystem.service.CustomerService;
import br.com.aranha.dataanalysissystem.service.RowDataService;
import br.com.aranha.dataanalysissystem.service.SaleService;
import br.com.aranha.dataanalysissystem.service.SalesmanService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum DataType {
    SALESMAN("001", new SalesmanService()),
    CUSTOMER("002", new CustomerService()),
    SALES("003", new SaleService());

    private final String lineTypeId;
    private final RowDataService rowDataService;

    public static DataType getByLineTypeId(String lineTypeId) {
        return Stream.of(values())
                .filter(id -> id.lineTypeId.equalsIgnoreCase(lineTypeId))
                .findFirst()
                .orElse(null);
    }
}
