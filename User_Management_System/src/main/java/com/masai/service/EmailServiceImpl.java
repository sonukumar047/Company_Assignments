package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.masai.exception.EmailSendingException;
import com.masai.exception.MessagingException;

import lombok.Data;


@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail; // Sender's email address

//    public EmailServiceImpl(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }

    @Override
    public void sendWelcomeEmail(String email, String username) {
        jakarta.mail.internet.MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(email);
            helper.setSubject("Welcome to Our Application");
            helper.setText("Hello " + username + ",\n\nWelcome to our application! Thank you for joining us.");

            // Set the sender email address
            helper.setFrom(senderEmail);

            // Send the email
            javaMailSender.send(message);
        } catch (MessagingException e) {
            // Handle exceptions (e.g., cannot connect to the mail server)
            e.printStackTrace();
            throw new EmailSendingException("Failed to send welcome email.", e);
        } catch (jakarta.mail.MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
