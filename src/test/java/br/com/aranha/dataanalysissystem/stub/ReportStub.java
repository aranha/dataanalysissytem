package br.com.aranha.dataanalysissystem.stub;

import br.com.aranha.dataanalysissystem.domain.output.Report;

public class ReportStub {
    public static Report createReport() {
        return new Report(2, 2, "10", "Diego");
    }
}
