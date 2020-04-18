package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Doctor;
@Path("/Doctor")
public class DoctorService {
	Doctor docObj = new Doctor();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readdoctor()
	{
		return docObj.readdoctor();
	} 

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(String doctorData)
	{
		//Convert the input string to a JSON object
		JsonObject doctorobject = new JsonParser().parse(doctorData).getAsJsonObject();
		//Read the values from the JSON object
		String DocName = doctorobject.get("DocName").getAsString();
		String DocNIC = doctorobject.get("DocNIC").getAsString();
		String Gender = doctorobject.get("Gender").getAsString();
		String ReqNo = doctorobject.get("ReqNo").getAsString();
		String Specialized = doctorobject.get("Specialized").getAsString();
		String Email = doctorobject.get("Email").getAsString();
		String DocCharges = doctorobject.get("DocCharges").getAsString();

		String output = docObj.insertDoctor(DocName, DocNIC, Gender, ReqNo, Specialized, Email, DocCharges);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatedoctor(String doctorData)
	{
		//Convert the input string to a JSON object
		JsonObject doctorobject = new JsonParser().parse(doctorData).getAsJsonObject();
		//Read the values from the JSON object
		String DocID = doctorobject.get("DocID").getAsString();
		String DocName = doctorobject.get("DocName").getAsString();
		String DocNIC = doctorobject.get("DocNIC").getAsString();
		String Gender = doctorobject.get("Gender").getAsString();
		String ReqNo = doctorobject.get("ReqNo").getAsString();
		String Specialized = doctorobject.get("Specialized").getAsString();
		String Email = doctorobject.get("Email").getAsString();
		String DocCharges = doctorobject.get("DocCharges").getAsString();

		String output = docObj.updatedoctor(DocID, DocName, DocNIC, Gender, ReqNo, Specialized, Email, DocCharges);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletedoctor(String doctorData)
	{
		//Convert the input string to a JSON object
		JsonObject doctorobject = new JsonParser().parse(doctorData).getAsJsonObject();
		//Read the values from the JSON object

		//Read the value from the element <DocID>
		String DocID = doctorobject.get("DocID").getAsString();
		String output = docObj.deletedoctor(DocID);
		return output;
	}

}
