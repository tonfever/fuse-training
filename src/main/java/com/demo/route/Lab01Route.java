package com.demo.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class Lab01Route extends RouteBuilder {
    public void configure() throws Exception {
        from("{{TEST.FILE.ENDPOINT}}?delete=true")
            .routeId("test-route-1")
            .log(LoggingLevel.INFO, "com.demo.route", "${in.header.CamelFileName} with Content -> ${body}")
            .setHeader("CamelFileName", simple("${date:now:yyyyMMddhhmmss}-read.xml"))
            .to("ftp://localhost:21/data/app/upload?username=jboss&password=jboss#1!");
    }
}
