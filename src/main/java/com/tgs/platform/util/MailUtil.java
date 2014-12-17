package com.tgs.platform.util;

//File Name SendEmail.java
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import com.tgs.platform.aaa.vo.Application;

public class MailUtil {

	Properties config;
	
	/**
	 * 
	 */
	public MailUtil() {
		super();
		
		try {
			config = new Properties();
			FileInputStream in = new FileInputStream("configs/config.properties");
			config.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param sender
	 * @param receiver
	 * @param content
	 */
	public void sendMail( String       host
			            , final String sender
			            , final String passwd
			            , String       user_mail
			            , String       subject
			            , String       content
			            )
	{   		
		Properties mail_prop = System.getProperties();
		Session    session   = null;
		
		if(host.equals("smtp.gmail.com")){
			// using google smtp server
			mail_prop.setProperty("mail.smtp.auth"           , "true");
			mail_prop.setProperty("mail.smtp.starttls.enable", "true");
			mail_prop.setProperty("mail.smtp.host"           , host  );
			mail_prop.setProperty("mail.smtp.port"           , "587" );
			
			session = Session.getInstance( mail_prop 
					  					 , new javax.mail.Authenticator() {
						                       protected PasswordAuthentication getPasswordAuthentication() {
							                       return new PasswordAuthentication(sender, passwd);
						                       }
					                       }
			                             );
		} else {
			// using own smtp server
			;
		}
		
		// sending mail
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.addRecipient( Message.RecipientType.TO
					            , new InternetAddress(user_mail)
			                    );
			message.setSubject(subject);
			message.setText(content, "utf-8", "html");

			Transport.send(message);
		}catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
