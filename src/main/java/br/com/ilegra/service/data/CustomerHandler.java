package br.com.ilegra.service.data;

import br.com.ilegra.domain.customer.Customer;

public class CustomerHandler {

    public static Object apply(String[] data) {
        if (data.length != 4) {
            throw new RuntimeException("Unable to parse Customer data. Required 4 arguments, received [" + data.length + "]");
        }
        final String cnpj = data[1];
        final String name = data[2];
        final String businessArea = data[3];
        return new Customer(cnpj, name, businessArea);
    }
}
