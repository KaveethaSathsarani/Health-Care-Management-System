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

import model.Appoinment;
@Path("/Appoinment")
public class AppoinmentService {
	Appoinment AppObj = new Appoinment();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readdoctor()
	{
		return AppObj.readAppoinment();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(@FormParam("PName") String PName,
			@FormParam("DName") String DName,
			@FormParam("RoomNo") String RoomNo,
			@FormParam("Date") String Date,
			@FormParam("Time") String Time,
			@FormParam("ID") String ID)
			
	{
		String output = AppObj.insertAppoinment(PName, DName, RoomNo, Date, Time, ID);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppoinment(String appoinmentData)
	{
		//Convert the input string to a JSON object
		JsonObject appoinmentobject = new JsonParser().parse(appoinmentData).getAsJsonObject();
		//Read the values from the JSON object
		String AID = appoinmentobject.get("AID").getAsString();
		String PName = appoinmentobject.get("PName").getAsString();
		String DName = appoinmentobject.get("DName").getAsString();
		String RoomNo = appoinmentobject.get("RoomNo").getAsString();
		String Date = appoinmentobject.get("Date").getAsString();
		String Time = appoinmentobject.get("Time").getAsString();
		String ID = appoinmentobject.get("ID").getAsString();
		
		

		String output = AppObj.updateAppoinment(AID,PName,DName,RoomNo,Date,Time,ID);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppoinment(String appoinmentData)
	{
		//Convert the input string to an XML document
		Document app = Jsoup.parse(appoinmentData, "", Parser.xmlParser());

		//Read the value from the element <DocID>
		String AID = app.select("AID").text();
		String output = AppObj.deleteAppoinment(AID);
		return output;
	}
}
