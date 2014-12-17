package com.tgs.platform.ws;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;

@Path("/media")
public class MediaWebService {

	@POST
    @Path("/get_metadata")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
	public Response createApplication( @FormParam("app_name" ) String app_name
			                         , @FormParam("file_type") String file_type
                                     , @FormParam("path"     ) String path
			                         ) throws JSONException, IOException 
	{
		
		return null;
	}
	
	@POST
    @Path("/make_thumbnail")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
	public Response makeThumbnail( @FormParam("app_name" ) String app_name
                                 , @FormParam("path"     ) String path
			                     ) throws JSONException, IOException 
	{
		
		return null;
	}
}
