package com.test;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class RouteTest extends CamelBlueprintTestSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteTest.class);

    private static final String BLUEPRINT_DESCRIPTOR = "OSGI-INF/blueprint/blueprint-camel.xml";

    @Override
    protected String getBlueprintDescriptor() {
        return BLUEPRINT_DESCRIPTOR;
    }

    @Override
    public boolean isUseAdviceWith() {
        return true;
    }

    @Override
    protected Properties useOverridePropertiesWithPropertiesComponent() {
        final Properties properties = System.getProperties();

        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("META-INF/etc/lab03.cfg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {

        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                String cxfEndpoint =  getContext().resolvePropertyPlaceholders("{{fuse.host.url}}") + "/api/v1/test";

                from("direct:start")
                    .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                    .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                    .setHeader(Exchange.ACCEPT_CONTENT_TYPE, constant("application/json"))
                    .to(cxfEndpoint)
                    .to("log:lab03-1-route?showAll=true&multiline=true");

            }
        };
    }

    @Test
    public void testCxfCall_whenValidRequest_thenReturnSuccess() throws Exception {

        final RouteDefinition route = context.getRouteDefinition("lab03-1-route");

        route.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() {
                weaveAddLast().process(new Processor() {
                    public void process(Exchange exchange) {
                        com.test.lab03.common.ResponseWrapper res = (com.test.lab03.common.ResponseWrapper) exchange.getIn().getBody();
                        LOGGER.info("response status - " + res.getErrorCode());
                    }
                }).to("mock:output");
            }
        });


        String requestBody = getTestFile("testdata/File1.json");
        template.requestBody("direct:start", requestBody);

        getMockEndpoint("mock:output").expectedMessageCount(1);

        assertMockEndpointsSatisfied();
    }

    public String getTestFile(String path) throws IOException {
        return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(path));
    }
}
