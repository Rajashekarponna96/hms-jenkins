package com.spdx.hms.util;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.spdx.hms.service.EmailService;

@Component
public class EmailSenderUtil {

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Autowired
	private EmailService emailService;

	public void sendMessage(String name, String password,String toEmail ) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userId", name);
		model.put("password", password);
		Context context = new Context();
		context.setVariables(model);

		String html = templateEngine.process("Registration", context);
		//emailService.sendHtmlMessage("Login Instructions for Connected Warehouse", html,toEmail);
	}
	
	public void sendForgotMessage(String userId, String password,String toEmail) throws AddressException, MessagingException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userId", userId);
		model.put("password", password);
		Context context = new Context();
		context.setVariables(model);

		String html = templateEngine.process("forgotpassword", context);
	//	emailService.sendHtmlMessage("ForgotPassword Login Instructions for Connected Warehouse", html,toEmail);
		emailService.sendForgotEmail("ForgotPassword Login Instructions for Connected Warehouse", html,toEmail);

	}
	

}
