package com.spdx.hms.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailSenderConfig {
	
	@Value("${spring.mail.username}")
	private String userName;

	@Value("${spring.mail.password}")
	private String password;

	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.zoho.in");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername(userName);
	    mailSender.setPassword(password);
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
	
	public static void main(String sr[]) {
		/* JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		    mailSender.setHost("smtp.gmail.com");
		    mailSender.setPort(587);
		    
		    mailSender.setUsername("support@spidex.io");
		    mailSender.setPassword("ujnmjepoerotokyx");
		    
		    Properties props = mailSender.getJavaMailProperties();
		    props.put("mail.transport.protocol", "smtp");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.debug", "true");
		    
		    SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("noreply@spidex.com");
	        message.setTo("pvharanadh@gmail.com"); 
	        message.setSubject("password reset"); 
	        message.setText("fdgfgfd");
	        mailSender.send(message);*/
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.zoho.in");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("noreply@joinhotelmanagement.com");
	    mailSender.setPassword("@Nt122342");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("noreply@joinhotelmanagement.com");
        message.setTo("pvharanadh@gmail.com"); 
        message.setSubject("password reset"); 
        message.setText("fdgfgfd");
        mailSender.send(message);
	}

}
