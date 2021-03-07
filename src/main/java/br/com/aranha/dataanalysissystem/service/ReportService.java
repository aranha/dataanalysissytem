package br.com.aranha.dataanalysissystem.service;

import br.com.aranha.dataanalysissystem.domain.input.Sale;
import br.com.aranha.dataanalysissystem.domain.output.Report;
import br.com.aranha.dataanalysissystem.repository.CustomerRepository;
import br.com.aranha.dataanalysissystem.repository.SaleRepository;
import br.com.aranha.dataanalysissystem.repository.SalesmanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private static final Logger log = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SalesmanRepository salesmanRepository;

    @Autowired
    private SaleRepository saleRepository;

    public Report buildReport() {
        log.info("starting building report");

        List<Sale> allSales = saleRepository.findAll();

        int amountClients = getAmountClients();

        int amountSalesman = getAmountSalesman();

        String mostExpensiveSaleId = getSmallerSaleId(allSales);

        String worstSalesmanEverName = getWorstSalesman(allSales);

        log.info("report built");

        return new Report(amountClients, amountSalesman, mostExpensiveSaleId, worstSalesmanEverName);
    }

    private int getAmountClients() {
        return Math.toIntExact(customerRepository.count());
    }

    private int getAmountSalesman() {
        return Math.toIntExact(salesmanRepository.count());
    }

    private String getSmallerSaleId(List<Sale> allSales) {
        Sale smallerSale = allSales.stream()
                .min(Comparator.comparing(Sale::getSalePriceAllItems))
                .orElse(null);

        if(Objects.isNull(smallerSale)) {
            return "there's no sales saved";
        }

        return smallerSale.getId();
    }

    private String getWorstSalesman(List<Sale> allSales) {
        if(allSales.isEmpty()) {
            return "there's no sales saved";
        }

        Map<String, List<Sale>> nameSalesmanWithAllSales = allSales.stream()
                .collect(Collectors.groupingBy(Sale::getSalesmanName));

        Map<String, Double> stringDoubleMap = new HashMap<>();

        nameSalesmanWithAllSales.forEach((nameSalesman, sales) -> {
            double priceAllItems = sales.stream().mapToDouble(Sale::getSalePriceAllItems).sum();
            stringDoubleMap.put(nameSalesman, priceAllItems);
        });

        return Collections.min(stringDoubleMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
