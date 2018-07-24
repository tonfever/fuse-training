package com.test.mycompany;

import org.apache.camel.Exchange;
import com.test.mycompany.Stocktrading;

public class StockEnricher {
    public void setUpStock(Exchange exchange) throws Exception {
        Stocktrading stocktrading = exchange.getIn().getBody(com.test.mycompany.Stocktrading.class);
        stocktrading.setCost("1000");
    }
}
