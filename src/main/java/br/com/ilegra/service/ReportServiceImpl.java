package br.com.ilegra.service;

import br.com.ilegra.domain.SalesData;
import br.com.ilegra.domain.sale.Sale;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class ReportServiceImpl implements ReportService {

    private final SalesData salesData;

    public ReportServiceImpl(SalesData salesData) {
        this.salesData = salesData;
    }

    @Override
    public int getNumberSellers() {
        return salesData.getSellers().size();
    }

    @Override
    public int getNumberCustomers() {
        return salesData.getCustomers().size();
    }

    @Override
    public String getExpensiveSaleId() {
        final Sale expensiveSale = salesData.getSales().stream()
                .max(Comparator.comparing(Sale::getSalePrice))
                .orElseThrow(NoSuchElementException::new);
        return expensiveSale.getId();
    }

    @Override
    public String getWorstSeller() {
        final Map<String, Double> groupedSellers = salesData.getSales().stream()
                .collect(groupingBy(Sale::getSellerName, Collectors.summingDouble(Sale::getSalePrice)));
        final Map.Entry<String, Double> sortedSellers = this.getLowerValue(groupedSellers);
        return sortedSellers.getKey();
    }

    private Map.Entry<String, Double> getLowerValue(Map<String, Double> unsortedMap) {
        List<Map.Entry<String, Double>> list = new LinkedList<>(unsortedMap.entrySet());
        list.sort(Map.Entry.comparingByValue());
        return list.get(0);
    }
}
