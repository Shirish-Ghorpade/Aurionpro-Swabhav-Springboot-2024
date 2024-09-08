package com.aurionpro.model.controller;

import java.io.File;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.model.entity.EmailData;

import jakarta.mail.internet.MimeMessage;

@RestController
public class EmailController {

	private final JavaMailSender mailSender;

	public EmailController(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

///////////////////////////////////////////////////////////
//	To send the simple mail
//	@GetMapping("/sendemail")
//	public String sendEmail() {
//		try {
//			SimpleMailMessage message = new SimpleMailMessage();
//
//			message.setFrom("shirish9191@gmail.com");
//			message.setTo("shirishghorpade11@gmail.com");
//			message.setSubject("Assignment is completed !!!");
//			message.setText("Assignment of email sending is completed");
//
//			mailSender.send(message);
//			return "Success";
//		} catch (Exception e) {
//			return e.getMessage();
//		}
//	}
	
	
	
///////////////////////////////////////////////////
//	Send Email with attachment
	@GetMapping("/sendemail")
	public String sendEmail(@RequestBody EmailData emailData) {
		try {
//			Mimemessage from jakarta
			MimeMessage message = mailSender.createMimeMessage();
//			It is imp to pass true otherwise it does not consider the attachment
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
			helper.setFrom(emailData.getFrom());
			helper.setTo(emailData.getTo());
			helper.setSubject(emailData.getSubject());
			helper.setText(emailData.getTextBody());
			
			helper.addAttachment("home.png", new File("C:\\Users\\shirish.ghorpade\\Desktop\\Project\\home.png"));
			helper.addAttachment("test.txt", new File("C:\\Users\\shirish.ghorpade\\Desktop\\Project\\test.txt"));

			mailSender.send(message);
			return "Success";
			
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
