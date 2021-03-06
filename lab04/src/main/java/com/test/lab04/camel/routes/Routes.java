package com.test.lab04.camel.routes;

import com.test.lab04.aggregators.MortgageAggregator;
import com.test.lab04.datamodel.Applicant;
import com.test.lab04.datamodel.MortgageApplication;
import com.test.lab04.datamodel.Property;
import com.test.lab04.services.ApplicantsService;
import com.test.lab04.services.PropertiesService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class Routes extends RouteBuilder {

    @Override
    public void configure() {
        //Rest DSL Configuration
        restConfiguration()
            .component("restlet")
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true")
            .contextPath("/").port(8081)
            .apiContextPath("/api-doc")
            .apiProperty("api.title", "User API")
            .apiProperty("api.version", "1.2.3")
            .apiProperty("cors", "true");

        //Rest Services Definition
        rest("/mortgages/")
            .get("/getMortgageApplication")
            .produces("application/json")
            .outType(MortgageApplication.class)
            .to("direct:getMortgageApplication");

        rest("/applicants/")
            .get("/getApplicant")
            .produces("application/json")
            .outType(Applicant.class)
            .to("direct:getApplicant");

        rest("/properties/")
            .get("/getProperty")
            .produces("application/json")
            .outType(Property.class)
            .to("direct:getProperty");

        from("direct:getApplicant")
            .bean(ApplicantsService.class, "getApplicant");

        from("direct:getProperty")
            .bean(PropertiesService.class, "getProperty");


        from("direct:getMortgageApplication")

            .multicast(new MortgageAggregator())
            //Get a populated mortgageApplication Example (Inline or Bean)
            .bean(MortgageApplication.class, "getMortgageApplicationSample")
            /*.process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    mortgageApplication mortgage = new mortgageApplication();
                    mortgage.setApr(8.00);
                    mortgage.setAmortization(30);
                    mortgage.setDownPayment(4000);
                    mortgage.setMortgageAmount(40000);
                    exchange.getIn().setBody(mortgage);
                 }
                })*/
            .parallelProcessing()
            .to("direct:callPropertyService", "direct:callApplicantService")
            .end();


        from("direct:callPropertyService")
            .removeHeaders("CamelHttp*")
            .enrich("http://localhost:8081/properties/getProperty")
            .unmarshal().json(JsonLibrary.Jackson, Property.class);


        from("direct:callApplicantService")
            .removeHeaders("CamelHttp*")
            .enrich("http://localhost:8081/applicants/getApplicant")
            .unmarshal().json(JsonLibrary.Jackson, Applicant.class);

    }
}
