package com.test.lab04.aggregators;

import com.test.lab04.datamodel.Applicant;
import com.test.lab04.datamodel.MortgageApplication;
import com.test.lab04.datamodel.Property;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MortgageAggregator implements AggregationStrategy {

    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

    	 if (oldExchange == null) {
             System.out.println("oldExchange is null -> Returning new exchange");
             return newExchange;
         }
                
        if( newExchange.getIn().getBody() instanceof Applicant){
        	 Applicant applicant = newExchange.getIn().getBody(Applicant.class);
        	 MortgageApplication mortgage = oldExchange.getIn().getBody(MortgageApplication.class);
        	 mortgage.setApplicant(applicant);
        }
        else {
        	Property property = newExchange.getIn().getBody(Property.class);
	       	MortgageApplication mortgage = oldExchange.getIn().getBody(MortgageApplication.class);
	       	mortgage.setProperty(property);       	
        }
   
        return oldExchange;
    }

}