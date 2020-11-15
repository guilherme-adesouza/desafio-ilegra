package br.com.ilegra.service.data;

import br.com.ilegra.domain.seller.Seller;

public class SellerHandler {
    public static Seller apply(String[] data) {
        if (data.length != 4) {
            throw new RuntimeException("Unable to parse Seller data. Required 4 arguments, received [" + data.length + "]");
        }
        final String cpf = data[1];
        final String name = data[2];
        final double salary = Double.parseDouble(data[3]);
        return new Seller(cpf, name, salary);
    }
}
