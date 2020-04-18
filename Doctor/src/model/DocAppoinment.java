package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DocAppoinment {
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
	public String insertDocAppoinment(String docID, String aID,String Phone)
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
			String query = " insert into `docappoinments`(`DocID`, `AID`, `DocPhone`)"
					+ " values (?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, docID);
			preparedStmt.setString(2, aID);
			preparedStmt.setString(3, Phone);


			//execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readDocAppoinment()
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
			output = "<table border=\"1\"><tr><th>Doctor ID</th><th>Appoinment ID</th><th>Phone Number</th><th>Remove</th></tr>";
			String query = "select * from docappoinments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next())
			{
				String DocID = Integer.toString(rs.getInt("DocID"));
				String AID = Integer.toString(rs.getInt("AID"));
				String DocPhone = Integer.toString(rs.getInt("DocPhone"));



				// Add into the html table

				output += "<tr><td>" + DocID + "</td>";
				output += "<td>" + AID + "</td>";
				output += "<td>" + DocPhone + "</td>";

				// buttons
				output += "<td><form method=\"post\" action=\"doctor.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"+ "<input name=\"DocID\" type=\"hidden\" value=\"" + DocID+ "\">" + "</form></td></tr>";
				
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
	
	public String deleteDocAppoinment(String DocID)
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

			String query = "delete from doctor where DocID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			//binding values

			preparedStmt.setInt(1, Integer.parseInt(DocID));

			//execute the statement

			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
