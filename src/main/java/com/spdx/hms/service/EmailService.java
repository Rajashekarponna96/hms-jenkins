package com.spdx.hms.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spdx.hms.util.CommonsUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {
		
    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private String port;

    @Value("${spring.mail.username}")
    private String from;
    
    @Value("${spring.mail.password}")
    private String password;

	public void sendForgotEmail(String string, String html, String toEmail) throws AddressException, MessagingException {
		
		//get the system properties
		Properties properties = System.getProperties();
		
		//setting important information to properties object
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {				
				return new PasswordAuthentication(from, password);
			}		
					
		});
		
		session.setDebug(true);
		
		MimeMessage m = new MimeMessage(session);

		m.setFrom(from);
		
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		
		m.setSubject(string);
	
//		m.setText(html);
		m.setContent(html,"text/html");

		Transport.send(m);

	}
	
	/*public void sendTextMessage(String subject,String body,String toEmail) {
		MandrillMessage message = new MandrillMessage();
        message.setSubject(subject);
        message.setText(body);
        message.setAutoText(true);
        message.setFromEmail(emailFrom);
        message.setTo(null);
        try {
			mandrillApi.messages().send(message, false);
		} catch (MandrillApiError e) {
			log.error("Mail Error while sending :{}",CommonsUtil.getErrorStacktrace(e));
		} catch (IOException e) {
			log.error("Mail Error while sending :{}",CommonsUtil.getErrorStacktrace(e));
		}

	}
	
	public void sendHtmlMessage(String subject,String body,String toEmail) {
		MandrillMessage message = new MandrillMessage();
        message.setSubject(subject);
        message.setHtml(body);
        message.setAutoHtml(true);
        message.setFromEmail(emailFrom);
       // message.setFromName(emailMessage.getFromName());
        List<MandrillMessage.Recipient> recipients = new ArrayList<>();
        MandrillMessage.Recipient recipient = new MandrillMessage.Recipient();
        recipient.setEmail(toEmail);
        recipient.setType(MandrillMessage.Recipient.Type.TO);
        recipients.add(recipient);
        message.setTo(recipients);
        try {
			mandrillApi.messages().send(message, false);
        } catch (MandrillApiError e) {
			log.error("Mail Error while sending :{}",CommonsUtil.getErrorStacktrace(e));
		} catch (IOException e) {
			log.error("Mail Error while sending :{}",CommonsUtil.getErrorStacktrace(e));
		}

	}
	
	public void sendAlertHtmlMessage(String subject,String body,String[] toEmails,String ccEmail) {
		MandrillMessage message = new MandrillMessage();
        message.setSubject(subject);
        message.setHtml(body);
        message.setAutoHtml(true);
        message.setFromEmail(emailFrom);
       // message.setFromName(emailMessage.getFromName());
        List<MandrillMessage.Recipient> recipients = new ArrayList<>();
        for(String toEmail:toEmails) {
        	MandrillMessage.Recipient recipient = new MandrillMessage.Recipient();
        	recipient.setEmail(toEmail);
        	recipient.setType(MandrillMessage.Recipient.Type.TO);
        	recipients.add(recipient);
        }
        MandrillMessage.Recipient recipient = new MandrillMessage.Recipient();
    	recipient.setEmail(ccEmail);
    	recipient.setType(MandrillMessage.Recipient.Type.CC);
    	recipients.add(recipient);
        message.setTo(recipients);
        try {
			mandrillApi.messages().send(message, false);
        } catch (MandrillApiError e) {
			log.error("Mail Error while sending :{}",CommonsUtil.getErrorStacktrace(e));
		} catch (IOException e) {
			log.error("Mail Error while sending :{}",CommonsUtil.getErrorStacktrace(e));
		}

	}*/

}
