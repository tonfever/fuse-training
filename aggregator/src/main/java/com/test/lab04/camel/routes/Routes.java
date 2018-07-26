package com.test.lab04.camel.routes;

import com.test.lab04.aggregators.PropertyAggregator;
import com.test.lab04.datamodel.Applicant;
import com.test.lab04.datamodel.MortgageApplication;
import com.test.lab04.datamodel.Property;
import com.test.lab04.services.ApplicantsService;
import com.test.lab04.services.PropertiesService;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Routes extends RouteBuilder {

    @Override
    public void configure() {

        from("direct:testAggregator")
            .recipientList(constant("direct:serviceA,direct:serviceB,direct:serviceC")).aggregationStrategy(new PropertyAggregator())
            .log(LoggingLevel.INFO, "testAggregator", "body:${body}")
            .to("mock:xx");

        from("direct:serviceA")
            .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    exchange.getOut().setBody(new Property("Road A", 4000000));
                }
            })
            .log(LoggingLevel.INFO, "serviceA", "body:${body}")
            .to("mock:x");

        from("direct:serviceB")
            .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    exchange.getOut().setBody(new Property("Road B", 2500000));
                }
            })
            .log(LoggingLevel.INFO, "serviceB", "body:${body}")
            .to("mock:y");

        from("direct:serviceC")
            .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    exchange.getOut().setBody(new Property("Road C", 300000));
                }
            })
            .log(LoggingLevel.INFO, "serviceC", "body:${body}")
            .to("mock:z");
    }

}
