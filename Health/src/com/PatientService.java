package com;

import model.Patient;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Patients")
public class PatientService
{
Patient patientObj = new Patient();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readItems()
{
return patientObj.readItems();
}

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String insertItem(@FormParam("Name") String Name,
 @FormParam("Address") String Address,
 @FormParam("Age") String Age,
 @FormParam("NIC") String NIC,
 @FormParam("Phone") String Phone)
{
 String output = patientObj.insertItem(Name, Address, Age, NIC, Phone);
return output;
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateItem(String itemData)
{
//Convert the input string to a JSON object
 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
//Read the values from the JSON object
 int ID = itemObject.get("ID").getAsInt();
 String Name = itemObject.get("Name").getAsString();
 String Address = itemObject.get("Address").getAsString();
 int Age = itemObject.get("Age").getAsInt();
 String NIC = itemObject.get("NIC").getAsString();
 int Phone = itemObject.get("Phone").getAsInt();
 String output = patientObj.updateItem(ID,Name, Address,Age,NIC,Phone);
return output;
}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String deleteItem(String itemData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String ID = doc.select("ID").text();
 String output = patientObj.deleteItem(ID);
return output;
}


}