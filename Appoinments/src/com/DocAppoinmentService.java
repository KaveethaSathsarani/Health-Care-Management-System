package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDocAppoinment(String doctorAppoinmentData)
	{
		//Convert the input string to a JSON object
		JsonObject doctorAppoinmentobject = new JsonParser().parse(doctorAppoinmentData).getAsJsonObject();
		//Read the values from the JSON object
		String DocID = doctorAppoinmentobject.get("DocID").getAsString();
		String AID = doctorAppoinmentobject.get("AID").getAsString();
		String DocPhone = doctorAppoinmentobject.get("DocPhone").getAsString();

		String output = DocAppObj.insertDocAppoinment(DocID, AID, DocPhone);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppoinment(String doctorAppoinmentData)
	{
		//Convert the input string to a JSON object
		JsonObject doctorAppoinmentobject = new JsonParser().parse(doctorAppoinmentData).getAsJsonObject();
		//Read the values from the JSON object

		//Read the value from the element <DocID>
		String DocID = doctorAppoinmentobject.get("DocID").getAsString();
		String output = DocAppObj.deleteDocAppoinment(DocID);
		return output;
	}

}
