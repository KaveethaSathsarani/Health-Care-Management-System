package com;

import java.sql.Date;
import java.sql.Time;

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

import model.Appointment;
@Path("/Appointment")
public class AppointmentService {
	Appointment AppObj = new Appointment();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointment()
	{
		return AppObj.readAppointment();
	} 

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("PName") String PName,
			@FormParam("DName") String DName,
			@FormParam("RoomNo") Integer RoomNo,
			@FormParam("ReqNo") String ReqNo,
			@FormParam("Date") Date Date,
			@FormParam("Time") Time Time,
			@FormParam("ID") Integer ID)
	{
		String output = AppObj.insertAppointment(PName, DName, RoomNo, Date, Time,ID);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String AppointmentData)
	{
		//Convert the input string to a JSON object
		JsonObject Appointmentobject = new JsonParser().parse(AppointmentData).getAsJsonObject();
		//Read the values from the JSON object
		String AID = Appointmentobject.get("AID").getAsString();
		String PName = Appointmentobject.get("PName").getAsString();
		String DName = Appointmentobject.get("DName").getAsString();
		Integer RoomNo = Appointmentobject.get("RoomNo").getAsInt();
		String Date = Appointmentobject.get("Date").getAsString();
		String Time = Appointmentobject.get("Time").getAsString();
		Integer ID = Appointmentobject.get("ID").getAsInt();
		

		String output = AppObj.updateAppointment(AID, PName, DName, RoomNo, Date, Time , ID);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(String AppointmentData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(AppointmentData, "", Parser.xmlParser());

		//Read the value from the element <DocID>
		String AID = doc.select("AID").text();
		String output = AppObj.deleteAppointment(AID);
		return output;
	}

}
