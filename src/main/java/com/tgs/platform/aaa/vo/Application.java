package com.tgs.platform.aaa.vo;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * @author Administrator
 *
 */
@Entity
public class Application {
	@Id
	String app_name;
	String app_comment;
	String primary_mail_account;
	String primary_mail_password;
	String mail_server;
	
	public Application() {
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getApp_comment() {
		return app_comment;
	}

	public void setApp_comment(String app_comment) {
		this.app_comment = app_comment;
	}

	public String getPrimary_mail_account() {
		return primary_mail_account;
	}

	public void setPrimary_mail_account(String primary_mail_account) {
		this.primary_mail_account = primary_mail_account;
	}

	public String getPrimary_mail_password() {
		return primary_mail_password;
	}

	public void setPrimary_mail_password(String primary_mail_password) {
		this.primary_mail_password = primary_mail_password;
	}

	public String getMail_server() {
		return mail_server;
	}

	public void setMail_server(String mail_server) {
		this.mail_server = mail_server;
	}
	
}
