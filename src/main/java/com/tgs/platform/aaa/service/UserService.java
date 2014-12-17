package com.tgs.platform.aaa.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.tgs.platform.aaa.vo.Application;
import com.tgs.platform.aaa.vo.User;
import com.tgs.platform.util.ConfigUtil;
import com.tgs.platform.util.HashUtil;
import com.tgs.platform.util.MailUtil;


public class UserService {
	
	SessionFactory sessionFactory;
	
	/**
	 * 
	 */
	public UserService() {
		super();
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	}
	
	/**
	 * @param email
	 * @return
	 */
	public User getUser(String app_name, String email)
    {
		Session session = sessionFactory.openSession();
		
		try {
			return (User)session.load(User.class, email);
		} catch (ObjectNotFoundException e) {
			return null;
		}
    }

	
	/**
	 * @param email
	 * @param password
	 * @param name
	 * @param application
	 * @param sender
	 */
	public void createUser( String      email
			              , String      password
			              , String      name
			              , Application app
			              , String      sender
			              )
	{
		// checking where the account exist already 
		/*
		if(getUser(email) != null){
			
		}
		*/
		ConfigUtil config = new ConfigUtil();
		
		try {
			String hash      = new HashUtil().getHash(email + app.getApp_name() + (new Date( )).toString(), "SHA-1");
			String link_hash = config.getSystemConfig().getProperty("backend_api_root")
					         + "aaa/approved?" 
					         + "link_hash=" + hash
					         ;
						
			// making invitation mail
			VelocityEngine ve = new VelocityEngine();
			ve.init();
			Template t = ve.getTemplate(config.getMailTemplateDirectory() + "create_user.vm");

			VelocityContext context = new VelocityContext();
			context.put("name"       , name             );
			context.put("application", app.getApp_name());
			context.put("link"       , link_hash        );

			StringWriter writer = new StringWriter();
			t.merge( context, writer );
			
			// store user
			User user = new User( app.getApp_name()
					            , email         
					            , name          
					            , password      
					            , "N"      
					            , hash          
					            );
			
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			
			// sending invitation mail
			MailUtil mail = new MailUtil();
			mail.sendMail( app.getMail_server()
					     , app.getPrimary_mail_account()
						 , app.getPrimary_mail_password()
						 , email
						 , "Please verify your email address"
						 , writer.toString()
						 );
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
	}
	
	public void approveAccount( String      email
            				  , String      hash
                              )
	{
		//User user = getAccount(email);
		//
		//user.setApproved("Y");
		//
		//SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		//Session session = sessionFactory.openSession();
		//session.beginTransaction();
		//session.save(user);
		//session.getTransaction().commit();
	}
}
