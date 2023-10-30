package com.masai;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Bean
	JavaMailSender javaMailSender() {
		
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		return mailSenderImpl;
	}
	
}