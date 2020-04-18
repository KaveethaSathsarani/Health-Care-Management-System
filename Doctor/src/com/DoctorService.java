package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

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
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(@FormParam("DocName") String DocName,
			@FormParam("DocNIC") String DocNIC,
			@FormParam("Gender") String Gender,
			@FormParam("ReqNo") String ReqNo,
			@FormParam("Specialized") String Specialized,
			@FormParam("Email") String Email,
			@FormParam("DocCharges") String DocCharges)
	{
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
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletedoctor(String doctorData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(doctorData, "", Parser.xmlParser());

		//Read the value from the element <DocID>
		String DocID = doc.select("DocID").text();
		String output = docObj.deletedoctor(DocID);
		return output;
	}

}
