package br.com.aranha.dataanalysissystem.service;

import br.com.aranha.dataanalysissystem.enums.DataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LineInputService {

    private static final Logger log = LoggerFactory.getLogger(LineInputService.class);

    @Autowired
    private SalesmanService salesmanService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SaleService saleService;

    public void saveLine(String line) {
        String idDataType = line.substring(0, 3);
        DataType dataType = DataType.getByLineTypeId(idDataType);
        switch (dataType) {
            case SALESMAN:
                salesmanService.processNewRowData(line);
                break;
            case CUSTOMER:
                customerService.processNewRowData(line);
                break;
            case SALES:
                saleService.processNewRowData(line);
                break;
            default:
                log.info("data type not valid: {}", line);
        }
    }
}
