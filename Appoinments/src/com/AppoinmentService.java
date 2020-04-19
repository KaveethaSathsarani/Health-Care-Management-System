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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppoinment(String doctorappoinment)
	{
		//Convert the input string to a JSON object
		JsonObject appoinmentobject = new JsonParser().parse(doctorappoinment).getAsJsonObject();
		//Read the values from the JSON object
		String PName = appoinmentobject.get("PName").getAsString();
		String DName = appoinmentobject.get("DName").getAsString();
		String RoomNo = appoinmentobject.get("RoomNo").getAsString();
		String Date = appoinmentobject.get("Date").getAsString();
		String Time = appoinmentobject.get("Time").getAsString();
		String ID = appoinmentobject.get("ID").getAsString();
		
	
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppoinment(String appoinmentData)
	{
		//Convert the input string to a JSON object
		JsonObject app =  new JsonParser().parse(appoinmentData).getAsJsonObject();

		//Read the value from the element <DocID>
		String AID = app.get("AID").getAsString();
		String output = AppObj.deleteAppoinment(AID);
		return output;
	}
}
