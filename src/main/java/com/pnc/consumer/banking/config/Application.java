package com.pnc.consumer.banking.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.pnc.consumer.banking.rest.ConsumerController;
import com.pnc.consumer.banking.security.AuthenticationFilter;


@SpringBootApplication
@ComponentScan(basePackages={"com.pnc.consumer.banking.security","com.pnc.consumer.banking.rest",
		"com.pnc.consumer.banking.exception","com.pnc.consumer.banking.service",
		"com.pnc.consumer.banking.model"
		}, basePackageClasses=ConsumerController.class)
@EnableWebMvc
@PropertySource("classpath:application.properties")
public class Application {
	
	public static void main(String args[]){
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
    public FilterRegistrationBean authenticationFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthenticationFilter());
        registration.addUrlPatterns("/consumer/*");
        return registration;
    }


}
