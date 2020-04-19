package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Appoinment {

	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertAppoinment(String pname, String dname,String roomNo,String date,String time,String id)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into appoinment(`AID`, `PName`, `DName`, `RoomNo`, `Date`, `Time`, `ID`) "
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pname);
			preparedStmt.setString(3, dname);
			preparedStmt.setString(4, roomNo);
			preparedStmt.setString(5, date);
			preparedStmt.setString(6, time);
			preparedStmt.setString(7, id);
			


			//execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the appoinment.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readAppoinment()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Patient Name </th><th>Doctor Name</th><th>Room No</th><th>Date</th><th>Time</th><th>ID</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from appoinment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the resRult set
			while (rs.next())
			{
				String AID = Integer.toString(rs.getInt("AID"));
				String PName=rs.getString("PName");
				String DName = rs.getString("DName");
				String RoomNo = Integer.toString(rs.getInt("RoomNo"));
				String Date = rs.getString("Date");
				String Time = rs.getString("Time");
				String ID = Integer.toString(rs.getInt("ID"));


				// Add into the html table

				output += "<tr><td>" + PName + "</td>";
				output += "<td>" + DName + "</td>";
				output += "<td>" + RoomNo + "</td>";
				output += "<td>" + Date + "</td>";
				output += "<td>" + Time + "</td>";
				output += "<td>" + ID + "</td>";


				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"appoinment.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"+ "<input name=\"AID\" type=\"hidden\" value=\"" + AID+ "\">" + "</form></td></tr>";

			}
			con.close();

			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading .";
			System.err.println(e.getMessage());
		}
		return output;
	} 
	
	public String updateAppoinment(String Aid, String pname, String dname, String roomNo, String date,String time,String id)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement

			String query = "UPDATE appoinment SET PName=?,DName=?,RoomNo=?,Date=?,Time=?,ID=? WHERE AID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, pname);
			preparedStmt.setString(2, dname);
			preparedStmt.setString(3, roomNo);
			preparedStmt.setString(4, date);
			preparedStmt.setString(5, time);
			preparedStmt.setString(6, id);
			preparedStmt.setInt(7, Integer.parseInt(Aid));

			// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteAppoinment(String AID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";

			}

			//create a prepared statement

			String query = "delete from appoinment where AID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			//binding values

			preparedStmt.setInt(1, Integer.parseInt(AID));

			//execute the statement

			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the .";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
}
