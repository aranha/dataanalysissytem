package br.com.aranha.dataanalysissystem.domain.output;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Report {
    private int amountClients;
    private int amountSalesman;
    private String mostExpensiveSaleId;
    private String worstSalesmanEverName;

    public String generateReport() {
        return new StringBuilder()
                .append("Amount of clients: ").append(amountClients)
                .append("\nAmount of salesman: ").append(amountSalesman)
                .append("\nMost expensive sale id: ").append(mostExpensiveSaleId)
                .append("\nWorst salesman ever: ").append(worstSalesmanEverName)
                .toString();
    }
}
