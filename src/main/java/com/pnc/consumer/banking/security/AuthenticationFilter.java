package com.pnc.consumer.banking.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

@Configuration
public class AuthenticationFilter extends GenericFilterBean {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain arg2)
			throws IOException, ServletException {
		
		String token = ((HttpServletRequest) req).getHeader("Authorization");
		
		if(token!=null && token.equals("Bearer123456"))
		
		     arg2.doFilter(req, res);
		
		else
			unauthorized((HttpServletResponse)res, "Invalid authentication token");
		
	}
	
	private void unauthorized(HttpServletResponse response, String message) throws IOException {

	    response.setContentType("application/json");
	    response.sendError(401, message);

	  }

}
