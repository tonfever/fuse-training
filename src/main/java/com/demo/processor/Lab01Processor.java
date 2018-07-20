package com.demo.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Lab01Processor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Lab01Processor.class);

    public void process(Exchange exchange) throws Exception {
        Map<String,Object> headers = exchange.getIn().getHeaders();

        for(String headerName : headers.keySet()){
            LOGGER.info("headerName:{"+headerName+"]");
        }
        Message originalMessage = exchange.getUnitOfWork().getOriginalInMessage();
        LOGGER.info("Original Message:{"+originalMessage+"]");
        exchange.getOut().setHeader("SchoolNum", exchange.getIn().getBody());
        exchange.getOut().setBody(originalMessage.getBody());
    }
}
