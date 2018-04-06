package com.pnc.consumer.banking.model;

import org.springframework.stereotype.Component;

@Component
public class CreditCardResponse {
	
	private String status;
	private String data;
	private String error;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public CreditCardResponse(String status, String data, String error) {
		super();
		this.status = status;
		this.data = data;
		this.error = error;
	}
	
	public CreditCardResponse(){
		
	}


}
