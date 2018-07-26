package com.test.lab04.datamodel;

public class Property  implements java.io.Serializable {

static final long serialVersionUID = 1L;
    
    private Integer price;
    
    private String address;

    public Property() {
    }

    public Property(String address, Integer price) {
        this.address = address;
        this.price = price;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(  Integer price ) {
        this.price = price;
    }
    
    public String getAddress() {
        return this.address;
    }

    public void setAddress(  String address ) {
        this.address = address;
    }

	@Override
	public String toString()
	{
		return "Property [price=" + price + ", address=" + address + "]";
	}




}