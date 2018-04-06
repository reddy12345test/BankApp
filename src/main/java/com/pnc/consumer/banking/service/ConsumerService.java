package com.pnc.consumer.banking.service;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pnc.consumer.banking.model.CreditCardResponse;
import com.pnc.consumer.banking.model.CreditLimitResponse;

@Service
public class ConsumerService {
	
	private static final Log logger = LogFactory.getLog(ConsumerService.class);
	
	private static final String SUCCESS="SUCCESS";
	private static final String FAILURE="FAILURE";
	private static final String ELIGIBLE="Eligible for credit limit increase";
	private static final String NOT_ELIGIBLE="Not eligible for credit limit increase";
	
	private static final String URI="http://localhost:8090/creditcard/";
	
	private CreditLimitResponse creditLimtResponse;
	
	public CreditLimitResponse getCreditInfo(String ssn){
		
		ResponseEntity<CreditCardResponse> response = null;
		
		RestTemplate restTemplate = new RestTemplate();
		
		try{
		
		response =  restTemplate.exchange(URI+ssn,HttpMethod.GET , new HttpEntity<CreditCardResponse>(createHeaders()), CreditCardResponse.class);
		
		}
		
		catch(Exception ex){
			
			creditLimtResponse = new CreditLimitResponse();
			
			if(ex.getMessage()!=null && ex.getMessage().equals("400 null")){
				
				creditLimtResponse.setStatus(FAILURE);
				creditLimtResponse.setData("");
				creditLimtResponse.setError("Invalid CreditCard");
				return creditLimtResponse;
				
			}
			else{
				
				creditLimtResponse.setStatus(FAILURE);
				creditLimtResponse.setData("");
				creditLimtResponse.setError(ex.getMessage());
				return creditLimtResponse;
				
			}
		}
		
		return mapResponse(response);
		
	}
	
	private CreditLimitResponse mapResponse(ResponseEntity<CreditCardResponse> response){
		
		creditLimtResponse = new CreditLimitResponse();
		
		creditLimtResponse.setStatus(response.getBody().getStatus());
		creditLimtResponse.setData(mapDataMessage(response.getBody().getStatus(),response.getBody().getData()));
		creditLimtResponse.setError(response.getBody().getError());
		
		return creditLimtResponse;
		
	}
	
	private String mapDataMessage(String status, String data){
		
		if(status.equalsIgnoreCase(SUCCESS) && data.equalsIgnoreCase(ELIGIBLE))
			return "Credit card limit raised by 1000 USD";
		else if(status.equalsIgnoreCase(SUCCESS) && data.equalsIgnoreCase(NOT_ELIGIBLE))
			return "Not eligible for CC Limit raise , please try after some days.";
		else
			return "";
		
	}
	
	private HttpHeaders createHeaders(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer123456");
		headers.add("X-Request-ID", "bankApp");
		return headers;

}
}