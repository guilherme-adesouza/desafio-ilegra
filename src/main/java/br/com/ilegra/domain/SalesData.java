package br.com.ilegra.domain;

import br.com.ilegra.domain.customer.Customer;
import br.com.ilegra.domain.sale.Sale;
import br.com.ilegra.domain.seller.Seller;

import java.util.ArrayList;
import java.util.List;

public class SalesData {
    private List<Customer> customers;
    private List<Sale> sales;
    private List<Seller> sellers;

    public SalesData() {
        this.customers = new ArrayList<>();
        this.sales = new ArrayList<>();
        this.sellers = new ArrayList<>();
    }

    public SalesData(List<Customer> customers, List<Sale> sales, List<Seller> sellers) {
        this.customers = customers;
        this.sales = sales;
        this.sellers = sellers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void addSeller(Seller seller) {
        this.sellers.add(seller);
    }

    public void addSale(Sale sale) {
        this.sales.add(sale);
    }
}
