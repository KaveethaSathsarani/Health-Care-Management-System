package model;

import java.sql.*;

public class Patient
{
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare?useTimezone=true&serverTimezone=UTC", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	} 

	//insert data
	public String insertItem(String Name, String Address, String  Age, String NIC,String Phone)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = " insert into patient(`ID`,`Name`,`Address`,`Age`,`NIC`,`Phone`)"
					+ " values (?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Name);
			preparedStmt.setString(3, Address);
			preparedStmt.setInt(4, Integer.parseInt(Age));
			preparedStmt.setString(5, NIC); 
			preparedStmt.setInt(6, Integer.parseInt(Phone));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Read data
	public String readItems()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Name</th><th>Address</th><th>Age</th><th>NIC</th><th>Phone</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from patient";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String ID = Integer.toString(rs.getInt("ID"));
				String Name = rs.getString("Name");
				String Address = rs.getString("Address");
				String Age = Integer.toString(rs.getInt("Age"));
				String NIC = rs.getString("NIC");
				String Phone = Integer.toString(rs.getInt("Phone"));
				// Add into the html table
				output += "<tr><td>" + Name + "</td>";
				output += "<td>" + Address + "</td>";
				output += "<td>" + Age + "</td>";
				output += "<td>" + NIC + "</td>";
				output += "<td>" + Phone + "</td>";
				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + ID
						+ "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//update 
	public String updateItem(int  ID, String Name, String Address, int Age,String NIC,int Phone)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE patient SET Name=?,Address=?,Age=?,NIC=?,Phone=? WHERE ID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, Name);
	 preparedStmt.setString(2, Address);
	 preparedStmt.setInt(3, (Age));
	 preparedStmt.setString(4, NIC);
	 preparedStmt.setInt(5, (Phone));
	 preparedStmt.setInt(6, (ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 

	//delete
	public String deleteItem(String ID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from patient where ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}