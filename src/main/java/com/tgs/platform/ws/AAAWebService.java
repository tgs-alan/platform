package com.tgs.platform.ws;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.tgs.platform.aaa.service.ApplicationService;
import com.tgs.platform.aaa.service.UserService;
import com.tgs.platform.aaa.vo.Application;
import com.tgs.platform.util.ErrorUtil;

@Path("/aaa")
public class AAAWebService {
	
	@POST
    @Path("/create_application")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
	public Response createApplication( @FormParam("app_name"             ) String app_name   
                                     , @FormParam("app_comment"          ) String app_comment
                                     , @FormParam("primary_mail_account" ) String primary_mail_account
                                     , @FormParam("primary_mail_password") String primary_mail_password
                                     , @FormParam("mail_server"          ) String mail_server         
			                         ) throws JSONException, IOException 
	{
		//JSONObject rslt = new JSONObject();		
		ApplicationService appService = new ApplicationService();
		
		Application app = appService.createApplication( app_name             
                                                      , app_comment          				                                  
				                                      , primary_mail_account 
				                                      , primary_mail_password
		                                              , mail_server
		                                              );
		//rslt.put("app_id", app.getApp_id());
		return Response.status(200).entity("").build();
	}
	
    @POST
    @Path("/create_user")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response createUser( @FormParam("app_name") String app_name
    		                  , @FormParam("email"   ) String email
    		                  , @FormParam("password") String password
    		                  , @FormParam("name"    ) String name
    		                  , @FormParam("sender"  ) String sender
    		                  )
    {
    	// assert where application_id exists
    	Application app = new ApplicationService().getApplication(app_name); 
    	if(app == null){
    		JSONObject rslt = new ErrorUtil().getJSONErrorObject("XXXX", "the application doesn't exist on platform");
    		
    		return Response.status(204).entity(rslt.toString()).build();
    	}
    	
    	// create user
    	UserService users = new UserService();
        
    	users.createUser( email                    // email                          
    			        , password                 // password   
    			        , name                     // name       
    			        , app                      // application
    			        , sender                   // sender     
    			        );
    	
    	return Response.status(200).build();
    }
    
    @GET
    @Path("/approved")
    @Produces("text/html")
    public Response approved( @QueryParam("link_hash") String link_hash   
    		                ) throws JSONException
    {
    	System.out.println("list_hash : " + link_hash); 
    	
    	
    	
    	return Response.status(200).entity(link_hash).build();
    }
}
