package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.app.modal.Users;


@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(Users user) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		simpleMailMessage.setFrom("sonuhits047@gmail.com");
		
		simpleMailMessage.setTo(user.getEmail());	
		
		simpleMailMessage.setSubject(user.getMailHeading());
		
		simpleMailMessage.setText("Hello Greetings  "+user.getUsername() +",\n\n" + user.getMailBody());

		javaMailSender.send(simpleMailMessage);

	}
}
