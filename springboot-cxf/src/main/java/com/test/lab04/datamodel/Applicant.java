package com.test.lab04.datamodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Represents a mortgage Applicant")
public class Applicant  implements java.io.Serializable {

static final long serialVersionUID = 1L;
    
    private Integer creditScore;
    private Integer income;
    private String name;
    private Integer ssn;

    public Applicant() {
    }

    public Applicant(String name, Integer ssn, Integer income, Integer creditScore) {
        this.name = name;
        this.ssn = ssn;
        this.income = income;
        this.creditScore = creditScore;
    }

    public Integer getCreditScore() {
        return this.creditScore;
    }
    
    @ApiModelProperty(value = "The credit Score of the Applicant", required = true)
    public void setCreditScore(  Integer creditScore ) {
        this.creditScore = creditScore;
    }
    
    public Integer getIncome() {
        return this.income;
    }

    public void setIncome(  Integer income ) {
        this.income = income;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(  String name ) {
        this.name = name;
    }
    
    public Integer getSsn() {
        return this.ssn;
    }

    public void setSsn(  Integer ssn ) {
        this.ssn = ssn;
    }

	@Override
	public String toString()
	{
		return "Applicant [creditScore=" + creditScore + ", income=" + income + ", name=" + name + ", ssn=" + ssn + "]";
	}




}