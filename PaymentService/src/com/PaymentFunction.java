package com;

import java.sql.Date;

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

import model.Payment;



@Path("/Payment")
public class PaymentFunction {
	
	Payment payObj=new Payment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		
		return payObj.readPayment();
		
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(String paymentmData)
	{
		//Convert the input string to a JSON object
		JsonObject payObject = new JsonParser().parse(paymentmData).getAsJsonObject();
		//Read the values from the JSON object
	 
		Double DocCharge = payObject.get("DocCharge").getAsDouble();
		Double HosCharge = payObject.get("HosCharge").getAsDouble();
		Double AppoCharge = payObject.get("AppoCharge").getAsDouble();
		//Double Total = payObject.get("Total").getAsDouble();
		String PayType = payObject.get("PayType").getAsString();
		String CardNo = payObject.get("CardNo").getAsString();
		String CardExpiryDate = payObject.get("CardExpiryDate").getAsString();
		String Card_CVNo =payObject.get("Card_CVNo").getAsString();
		Integer AID = payObject.get("AID").getAsInt();
		Integer DocID = payObject.get("DocID").getAsInt();
	 
		String output = payObj.insertPayment(DocCharge, HosCharge, AppoCharge, PayType, CardNo, CardExpiryDate, Card_CVNo, AID, DocID);
	 
		return output;
	
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentmData)
	{
		//Convert the input string to a JSON object
		JsonObject payObject = new JsonParser().parse(paymentmData).getAsJsonObject();
		//Read the values from the JSON object
	 
		Integer PayID = payObject.get("PayID").getAsInt();
		Double DocCharge = payObject.get("DocCharge").getAsDouble();
		Double HosCharge = payObject.get("HosCharge").getAsDouble();
		Double AppoCharge = payObject.get("AppoCharge").getAsDouble();
		//Double Total = payObject.get("Total").getAsDouble();
		String PayType = payObject.get("PayType").getAsString();
		String CardNo = payObject.get("CardNo").getAsString();
		String CardExpiryDate = payObject.get("CardExpiryDate").getAsString();
		String Card_CVNo =payObject.get("Card_CVNo").getAsString();
		Integer AID = payObject.get("AID").getAsInt();
		Integer DocID = payObject.get("DocID").getAsInt();
	 
		String output = payObj.updatePayment(PayID,DocCharge, HosCharge, AppoCharge, PayType, CardNo, CardExpiryDate, Card_CVNo, AID, DocID);
	 
		return output;
	
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentmData)
	{
		//Convert the input string to a JSON object
		JsonObject payObject = new JsonParser().parse(paymentmData).getAsJsonObject();
		//Read the values from the JSON object
	 
		Integer PayID = payObject.get("PayID").getAsInt();
	 
		String output = payObj.deletePayment(PayID);
	 
		return output;
	
	}
	
	
	

}
