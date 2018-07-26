package com.test.lab04.services;

import com.test.lab04.datamodel.Applicant;
import org.apache.camel.Exchange;


public class ApplicantsService {

	
    public ApplicantsService() {
    }

    public void getApplicant(Exchange exchange) {
    	Applicant applicant = new Applicant("Joao", 23143, 1000, 85);
        exchange.getIn().setBody(applicant);
    }

   
}