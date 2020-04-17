package com;

import model.Hospital;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Hospitals")
public class HealthCare1 {
	Hospital HospitalObj = new Hospital();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readHospital() {
		return HospitalObj.readHospital();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospital(@FormParam("HosName") String HosName, @FormParam("HosCity") String HosCity,
			@FormParam("Rooms") String Rooms, @FormParam("Address") String Address,
			@FormParam("ContactNum") String ContactNum) {
		String output = HospitalObj.insertHospital(HosName, HosCity, Rooms, Address, ContactNum);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospital(String itemData) {
//Convert the input string to a JSON object
		JsonObject HospitalObject = new JsonParser().parse(itemData).getAsJsonObject();
//Read the values from the JSON object

		String HosName = HospitalObject.get("HosName").getAsString();
		String HosCity = HospitalObject.get("HosCity").getAsString();
		int Rooms = HospitalObject.get("Rooms").getAsInt();
		String Address = HospitalObject.get("Address").getAsString();
		int ContactNum = HospitalObject.get("ContactNum").getAsInt();
		String output = HospitalObj.updateHospital(HosName, HosCity, Rooms, Address, ContactNum);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospital(String itemData) {
//Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

//Read the value from the element <itemID>
		String HosID = doc.select("HosID").text();
		String output = HospitalObj.deleteHospital(HosID);
		return output;
	}

}