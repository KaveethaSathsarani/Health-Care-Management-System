package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor {
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
	public String insertDoctor(String name, String docNIC,String gender,String reqNo,String specialized,String email, String docCharge)
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
			String query = " insert into doctor(`DocID`, `DocName`, `DocNIC`, `Gender`, `ReqNo`, `Specialized`, `Email`, `DocCharges`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, docNIC);
			preparedStmt.setString(4, gender);
			preparedStmt.setString(5, reqNo);
			preparedStmt.setString(6, specialized);
			preparedStmt.setString(7, email);
			preparedStmt.setDouble(8, Double.parseDouble(docCharge));


			//execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readdoctor()
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
			output = "<table border=\"1\"><tr><th>Doctor Name</th><th>Doctor NIC</th><th>Gender</th><th>ReqNo</th><th>Specialized</th><th>Email</th><th>DocCharges</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from doctor";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next())
			{
				String DocID = Integer.toString(rs.getInt("DocID"));
				String DocName = rs.getString("DocName");
				String DocNIC = rs.getString("DocNIC");
				String Gender = rs.getString("Gender");
				String ReqNo = Integer.toString(rs.getInt("ReqNo"));
				String Specialized = rs.getString("Specialized");
				String Email = rs.getString("Email");
				String DocCharges = Double.toString(rs.getDouble("DocCharges"));


				// Add into the html table

				output += "<tr><td>" + DocName + "</td>";
				output += "<td>" + DocNIC + "</td>";
				output += "<td>" + Gender + "</td>";
				output += "<td>" + ReqNo + "</td>";
				output += "<td>" + Specialized + "</td>";
				output += "<td>" + Email + "</td>";
				output += "<td>" + DocCharges + "</td>";
				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"doctor.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"+ "<input name=\"DocID\" type=\"hidden\" value=\"" + DocID+ "\">" + "</form></td></tr>";

			}
			con.close();

			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	} 

	public String updatedoctor(String Docid, String name, String docNIC, String gender, String reqNo,String specialized,String email, String docCharge)
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

			String query = "UPDATE doctor SET DocName=?,DocNIC=?,Gender=?,ReqNo=?,Specialized=?,Email=?,DocCharges=? WHERE DocID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, docNIC);
			preparedStmt.setString(3, gender);
			preparedStmt.setString(4, reqNo);
			preparedStmt.setString(5, specialized);
			preparedStmt.setString(6, email);
			preparedStmt.setDouble(7, Double.parseDouble(docCharge));
			preparedStmt.setInt(8, Integer.parseInt(Docid));

			// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletedoctor(String DocID)
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
