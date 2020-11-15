package br.com.ilegra.service.report;

import br.com.ilegra.domain.SalesData;

public interface ReportService {
    void setSalesData(SalesData salesData);
    int getNumberSellers();
    int getNumberCustomers();
    String getExpensiveSaleId();
    String getWorstSeller();
}
