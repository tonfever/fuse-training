package com.demo.processor;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lab01Bean {

    private static final Logger LOGGER = LoggerFactory.getLogger(Lab01Bean.class);


    public void printExchangeMsg(Exchange exchange) {
        LOGGER.info("exchange properties --> " + exchange.getProperties());
        LOGGER.info("in.header ---> " + exchange.getIn().getHeaders());
        LOGGER.info("in.body ---> " + exchange.getIn().getBody());
    }
}
