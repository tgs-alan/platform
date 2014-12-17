package com.tgs.platform.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
	
	String pwd;
	
	public ConfigUtil() {
		super();
		
		String os_name = System.getProperty("os.name");

		if(os_name.contains("Windows"))
			pwd = "C:/Users/Administrator/workspace/platform/src/main/webapp/";
		else
			pwd = "/var/lib/tomcat7/webapps/platform";
	}

	public Properties getSystemConfig() throws IOException
	{
		Properties config = new Properties();
		FileInputStream in = new FileInputStream(pwd + "configs/config.properties");
		config.load(in);
		in.close();
		
		return config;
	}
	
	public String getWorkingDirectory()
	{
		return pwd;
	}
	
	public String getMailTemplateDirectory()
	{
		return pwd + "mail_templates/";
	}
}
