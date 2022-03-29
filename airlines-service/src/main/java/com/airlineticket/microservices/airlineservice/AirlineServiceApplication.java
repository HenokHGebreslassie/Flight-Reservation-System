package com.airlineticket.microservices.airlineservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.MessageSourceAccessor;

@EnableFeignClients
@SpringBootApplication
public class AirlineServiceApplication {

	@Autowired
	private MessageSource messageSource;

	public static void main(String[] args) {
		SpringApplication.run(AirlineServiceApplication.class, args);
	}

	@Bean
	public MessageSourceAccessor messageSourceAccessor() {
		return new MessageSourceAccessor(messageSource);
	}
}
