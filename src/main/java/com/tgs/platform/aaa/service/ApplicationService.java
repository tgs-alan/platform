package com.tgs.platform.aaa.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.tgs.platform.aaa.vo.Application;
//import org.hibernate.ObjectNotFoundException;
import org.hibernate.ObjectNotFoundException;

public class ApplicationService {

	SessionFactory sessionFactory;
	
	/**
	 * 
	 */
	public ApplicationService() {
		super();
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	}
	
	public Application createApplication( String app_name             
			                            , String app_comment          
			                            , String primary_mail_account 
			                            , String primary_mail_password
			                            , String mail_server          
			                            )
	{
		Session session = sessionFactory.openSession();
		
		// check if already exists
		//Application app = (Application)session.load(Application.class, app_name);
		//if(app != null){
		//	return null;
		//}
				
		Application appVo = new Application();
		
		appVo.setApp_name             (app_name             );
		appVo.setApp_comment          (app_comment          );
		appVo.setPrimary_mail_account (primary_mail_account );
		appVo.setPrimary_mail_password(primary_mail_password);
		appVo.setMail_server          (mail_server          );
		
		session.beginTransaction();
		session.save(appVo);
		session.getTransaction().commit();
		
		return appVo;
	}
	
	/**
	 * @param 
	 */
	public Application getApplication(String app_name)
	{
		Session session = sessionFactory.openSession();
		
		try {
			return (Application)session.load(Application.class, app_name);
		} catch (ObjectNotFoundException e) {
			return null;
		}
	}

}
