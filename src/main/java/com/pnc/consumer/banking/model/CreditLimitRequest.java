package com.pnc.consumer.banking.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, creatorVisibility=Visibility.ANY)
public class CreditLimitRequest {
	
	private String firstName;
	
	private String lastName;
	
	private String zipCode;
	
	private String ssn;
	
	private String creditCardNumber;
	
	

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public CreditLimitRequest(String firstName, String lastName, String zipCode, String ssn,String creditCardNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.zipCode = zipCode;
		this.ssn = ssn;
		this.creditCardNumber = creditCardNumber;
	}
	
	public CreditLimitRequest(){
		
	}

}
