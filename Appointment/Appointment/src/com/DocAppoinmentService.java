package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.DocAppoinment;
@Path("/DocAppoinment")
public class DocAppoinmentService {
	DocAppoinment DocAppObj = new DocAppoinment();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDocAppoinment()
	{
		return DocAppObj.readDocAppoinment();
	} 

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDocAppoinment(@FormParam("DocID") String DocID,
			@FormParam("AID") String AID,
			@FormParam("DocPhone") String DocPhone)
	{
		String output = DocAppObj.insertDocAppoinment(DocID, AID, DocPhone);
		return output;
	}

}
