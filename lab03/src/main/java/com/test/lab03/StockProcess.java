package com.test.lab03;

import com.test.lab03.model.Stocktrading;
import org.apache.camel.Exchange;

public class StockProcess {
    public void updateVIP(Exchange exchange) throws Exception{
        Stocktrading stocktrading = exchange.getIn().getBody(Stocktrading.class);
        stocktrading.setCost("99999");
    }
    public void updateNonVIP(Exchange exchange) throws Exception{
        Stocktrading stocktrading = exchange.getIn().getBody(Stocktrading.class);
        stocktrading.setCost("111");
    }
}
