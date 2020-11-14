package br.com.ilegra.domain.sale;

import java.util.List;
import java.util.stream.Collectors;

public class Sale {

    private final String id;
    private final List<ItemSale> itemSaleList;
    private final String sellerName;

    public Sale(String id, List<ItemSale> itemSaleList, String sellerName) {
        this.id = id;
        this.itemSaleList = itemSaleList;
        this.sellerName = sellerName;
    }

    public String getId() {
        return id;
    }

    public List<ItemSale> getItemSaleList() {
        return itemSaleList;
    }

    public String getSellerName() {
        return sellerName;
    }

    public double getSalePrice() {
        return itemSaleList.stream()
                .map(ItemSale::getTotalPrice)
                .collect(Collectors.summingDouble(Double::doubleValue));
    }
}
