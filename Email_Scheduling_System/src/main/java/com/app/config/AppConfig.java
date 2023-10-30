package com.app.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class AppConfig {

	
	@Bean
	 SecurityFilterChain securityFilterChain(
			HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
		   .authorizeHttpRequests(auth->{
			   auth
			   .requestMatchers(HttpMethod.POST,"/register").permitAll()
			   .requestMatchers(HttpMethod.POST,"/test").permitAll()
			       .anyRequest()
			       .authenticated();
		   })
		   .csrf(csrf->csrf.disable())
		   .formLogin(Customizer.withDefaults())
		   .httpBasic(Customizer.withDefaults())
		;
		return httpSecurity.build();
	}
	
	@Bean
	 PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	JavaMailSender javaMailSender() {
		
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		
		
		mailSenderImpl.setHost("smtp-relay.brevo.com");
		
		mailSenderImpl.setPort(587); 	
		
		mailSenderImpl.setUsername("sonuhits047@gmail.com");
		mailSenderImpl.setPassword("BXvq3YRxpPfhIH74");
		
		Properties props = mailSenderImpl.getJavaMailProperties();
		    props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        
	        System.out.println(mailSenderImpl);
	        
		return mailSenderImpl;
	}
	
}
