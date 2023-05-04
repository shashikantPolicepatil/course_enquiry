package com.enquiry.demo.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailUtils {

	 public static boolean sendEmail(JavaMailSender mailSender, String to, String subject, String body) throws Exception {
		 try {
			 SimpleMailMessage message = new SimpleMailMessage();
		        message.setTo(to);
		        message.setSubject(subject);
		        message.setText(body);
		        mailSender.send(message);
		        return true; 
		 } catch(Exception ex){
			 throw new Exception(ex.getMessage());
		 }
	    }
}
