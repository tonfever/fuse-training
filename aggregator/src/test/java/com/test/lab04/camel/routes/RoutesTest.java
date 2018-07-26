package com.test.lab04.camel.routes;

import com.test.lab04.datamodel.Property;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoutesTest extends CamelTestSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoutesTest.class);

    @Produce(uri = "direct:testAggregator")
    protected ProducerTemplate inputEndpoint;

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new Routes();
    }

    @Test
    public void testRecipientList() throws InterruptedException {
        Property property = new Property();

        Map headers = new HashMap<String,String>();

        inputEndpoint.sendBodyAndHeaders("{}", headers);

        getMockEndpoint("mock:x").expectedMessageCount(1);
        getMockEndpoint("mock:y").expectedMessageCount(1);
        getMockEndpoint("mock:z").expectedMessageCount(1);

        MockEndpoint mockXX = getMockEndpoint("mock:xx");
        mockXX.expectedMessageCount(1);

        List results = mockXX.getExchanges().get(0).getIn().getBody(List.class);
        int totalPrice = (Integer) mockXX.getExchanges().get(0).getProperty("totalPrice");

        LOGGER.info("List::"+ results);
        LOGGER.info("totalPrice::"+ totalPrice);

        assertEquals(3, results.size());
        assertEquals(6800000, totalPrice);

        assertMockEndpointsSatisfied();
    }

}




