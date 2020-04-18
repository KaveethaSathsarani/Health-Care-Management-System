package com;

import javax.ws.rs.Consumes;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import model.DocWorkHost;


@Path("/DocWorkHost")
public class DocWorkHostService {
	DocWorkHost DocWorkHostObj = new DocWorkHost();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDocWorkHost()
	{
		return DocWorkHostObj.readDocWorkHost();
	} 

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDocWorkHost(@FormParam("DocID") String DocID,
			@FormParam("HosID") String HosID,
			@FormParam("HosPhone") String HosPhone)
	{
		String output = DocWorkHostObj.insertDocWorkHost(DocID, HosID, HosPhone);
		return output;
	}


	
}
