package com.pnc.consumer.banking.rest;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pnc.consumer.banking.model.CreditLimitRequest;
import com.pnc.consumer.banking.model.CreditLimitResponse;
import com.pnc.consumer.banking.service.ConsumerService;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {
	
	private static final Log logger = LogFactory.getLog(ConsumerController.class);
	
	@Autowired
	private ConsumerService consumerService;
	
	@RequestMapping( method=RequestMethod.POST, value="/creditcard", produces="application/json")
	@ResponseBody
	public ResponseEntity<CreditLimitResponse> getUserInfo(  @RequestBody CreditLimitRequest creditLimitReq, HttpServletRequest request){
		
		
		HttpHeaders responseHeaders = new HttpHeaders();
		
		CreditLimitResponse ccLimitResponse = null;
		
		responseHeaders.add("X-Request-ID", request.getHeader("X-Request-ID"));
		
		ccLimitResponse = consumerService.getCreditInfo(creditLimitReq.getSsn());
		
	    return new ResponseEntity<CreditLimitResponse>(ccLimitResponse,responseHeaders, HttpStatus.OK);
			
		
	}


}
