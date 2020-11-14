package br.com.ilegra.domain.sale;

public class ItemSale {
    private final String id;
    private final Integer quantity;
    private final double price;

    public ItemSale(String id, Integer quantity, double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return quantity * price;
    }
}
