package com.tgs.platform.aaa.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	String app_name;
	@Id
	String email;
	String name;
	String password;
	String approved;
	String hash;
	
	public User() {
		super();
	}
	
	public User( String app_name
			   , String email
			   , String name
			   , String password
			   , String approved
			   , String hash
			   ) 
	{
		super();
		this.app_name = app_name;
		this.email = email;
		this.name = name;
		this.password = password;
		this.approved = approved;
		this.hash = hash;
	}
	
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
}
