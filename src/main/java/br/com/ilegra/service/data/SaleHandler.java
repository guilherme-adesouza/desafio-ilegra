package br.com.ilegra.service.data;

import br.com.ilegra.domain.sale.ItemSale;
import br.com.ilegra.domain.sale.Sale;

import java.util.ArrayList;
import java.util.List;

public class SaleHandler {
    public static Sale apply(String[] data) {
        if (data.length != 4) {
            throw new RuntimeException("Unable to parse Sale data. Required 4 arguments, received [" + data.length + "]");
        }
        final String id = data[1];

        List<ItemSale> itemSaleList = new ArrayList<>();
        final String[] itemsData = data[2]
                .replace("[", "")
                .replace("]", "")
                .split(",");
        for (String itemData : itemsData) {
            final String[] parsedItemData = itemData.split("-");
            if (parsedItemData.length != 3) {
                throw new RuntimeException("Unable to parse data. Required 3 arguments, received [" + data.length + "]");
            }
            final String itemId = parsedItemData[0];
            final int itemQuantity = Integer.parseInt(parsedItemData[1]);
            final double itemPrice = Double.parseDouble(parsedItemData[2]);
            itemSaleList.add(new ItemSale(itemId, itemQuantity, itemPrice));
        }

        final String sellerName = data[3];
        return new Sale(id, itemSaleList, sellerName);
    }
}
