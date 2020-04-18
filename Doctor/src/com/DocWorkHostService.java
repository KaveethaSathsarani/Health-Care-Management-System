package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDocWorkHost(String DocWorkHostData)
	{
		//Convert the input string to a JSON object
				JsonObject DocWorkHostobject = new JsonParser().parse(DocWorkHostData).getAsJsonObject();
				//Read the values from the JSON object
				String DocID = DocWorkHostobject.get("DocID").getAsString();
				String HosID = DocWorkHostobject.get("HosID").getAsString();
				String HosPhone = DocWorkHostobject.get("HosPhone").getAsString();

		String output = DocWorkHostObj.insertDocWorkHost(DocID, HosID, HosPhone);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDocWorkHost(String DocWorkHostData)
	{
		//Convert the input string to a JSON object
		JsonObject DocWorkHostobject = new JsonParser().parse(DocWorkHostData).getAsJsonObject();
		//Read the values from the JSON object

		//Read the value from the element <DocID>
		String DocID = DocWorkHostobject.get("DocID").getAsString();
		String output = DocWorkHostObj.deleteDocWorkHost(DocID);
		return output;
	}
	
}
