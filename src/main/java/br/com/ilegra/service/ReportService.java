package br.com.ilegra.service;

import br.com.ilegra.domain.SalesData;

public interface ReportService {
    void setSalesData(SalesData salesData);
    int getNumberSellers();
    int getNumberCustomers();
    String getExpensiveSaleId();
    String getWorstSeller();
}
