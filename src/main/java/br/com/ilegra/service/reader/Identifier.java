package br.com.ilegra.service.reader;

import br.com.ilegra.service.data.CustomerHandler;
import br.com.ilegra.service.data.SaleHandler;
import br.com.ilegra.service.data.SellerHandler;

import java.util.function.Function;

public enum Identifier {
    SELLER("001", SellerHandler::apply),
    CUSTOMER("002", CustomerHandler::apply),
    SALE("003", SaleHandler::apply);

    private final String id;
    private final Function<String[], Object> handler;

    private Identifier(String id, Function<String[], Object> handler) {
        this.id = id;
        this.handler = handler;
    }

    public String getId() {
        return id;
    }

    public boolean canHandle(String id) {
        return id.equals(this.id);
    }

    public Object handle(String[] line) {
        return this.handler.apply(line);
    }
}
