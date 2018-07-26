package com.test.lab04.datamodel;

import org.apache.camel.Exchange;

public class MortgageApplication  implements java.io.Serializable {

static final long serialVersionUID = 1L;
    
  
    private Double apr;
    private Integer mortgageAmount;
    private Integer amortization;
    private Integer downPayment;
    
    private Applicant applicant; 
    private Property property;
    
    public MortgageApplication() {
    }

    public MortgageApplication(Applicant applicant, Property property, Integer downPayment, Integer amortization, Integer mortgageAmount, Double apr) {
        this.applicant = applicant;
        this.property = property;
        this.downPayment = downPayment;
        this.amortization = amortization;
        this.mortgageAmount = mortgageAmount;
        this.apr = apr;
    }


    
  

    
    public Double getApr() {
        return this.apr;
    }

    public void setApr(  Double apr ) {
        this.apr = apr;
    }
    
    public Integer getMortgageAmount() {
        return this.mortgageAmount;
    }

    public void setMortgageAmount(  Integer mortgageAmount ) {
        this.mortgageAmount = mortgageAmount;
    }
    
    public Applicant getApplicant() {
        return this.applicant;
    }

    public void setApplicant( Applicant applicant ) {
        this.applicant = applicant;
    }
    
    public Integer getAmortization() {
        return this.amortization;
    }

    public void setAmortization(  Integer amortization ) {
        this.amortization = amortization;
    }
    
    public Integer getDownPayment() {
        return this.downPayment;
    }

    public void setDownPayment(  Integer downPayment ) {
        this.downPayment = downPayment;
    }
    
    public Property getProperty() {
        return this.property;
    }

    public void setProperty(Property property ) {
        this.property = property;
    }
    
    public void getMortgageApplicationSample(Exchange exchange) {
    	MortgageApplication mortgage = new MortgageApplication();
  		mortgage.setApr(8.00);
  		mortgage.setAmortization(30);
  		mortgage.setDownPayment(4000);
  		mortgage.setMortgageAmount(40000);
	    exchange.getIn().setBody(mortgage);
    }
   

	@Override
	public String toString()
	{
		return "Application [apr=" + apr + ", mortgageAmount=" + mortgageAmount + ", applicant="
				+ applicant + ", amortization=" + amortization + ", downPayment=" + downPayment + ", property=" + property 
				 + "]";
	}




}