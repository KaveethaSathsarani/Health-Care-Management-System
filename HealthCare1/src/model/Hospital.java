package model;

import java.sql.*;

public class Hospital { // A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/HealthCare1?useTimezone=true&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertHospital(String HosName, String HosCity, int Rooms, String Address, int ContactNum) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into Hospital(`HosID`,`HosName`,`HosCity`,`Rooms`,`Address`,`ContactNum`)"
					+ " values (?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, HosName);
			preparedStmt.setString(3, HosCity);
			preparedStmt.setint(4, Integer.parseInt(Rooms));
			preparedStmt.setString(5, Address);
			preparedStmt.setint(6, Integer.parseInt(ContactNum));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

public String readHospital()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output = "<table border=\"1\"><tr><th>HosID</th><th>HosName</th><th>HosCity</th><th>Rooms</th><th>Address</th><th>ContactNum</th><th>Update</th><th>Remove</th></tr>";
 String query = "select * from items";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String HosID = Integer.toString(rs.getInt("HosID"));
 String HosName = rs.getString("HosName");
 String HosCity = rs.getString("HosCity");
 String Rooms = Integer.toString(rs.getInt("Rooms"));
 String Address = rs.getString("Address");
 String ContactNum = Integer.toString(rs.getInt("ContactNum"));
 // Add into the html table
 output += "<tr><td>" + HosID + "</td>";
 output += "<td>" + HosName + "</td>";
 output += "<td>" + HosCity + "</td>";
 output += "<td>" + Rooms + "</td>";
 output += "<td>" + Address + "</td>";
 output += "<td>" + ContactNum + "</td>";
 // buttons
 output += "<td><input name=\"btnUpdate\" type=\"button\"
 value=\"Update\" class=\"btn btn-secondary\"></td>"
 + "<td><form method=\"post\" action=\"Hospital.jsp\">"
 + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"
 class=\"btn btn-danger\">"
 + "<input name=\"HosID\" type=\"hidden\" value=\"" + HosID
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

	public String updateHospital(String HosName, String HosCity, int Rooms, String Address, int ContactNum) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE Hospital SET HosName=?,HosCity=?,Rooms=?,Address=?,ContactNum=? WHERE HosID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, HosName);
			preparedStmt.setString(3, HosCity);
			preparedStmt.setint(4, Integer.parseInt(Rooms));
			preparedStmt.setString(5, Address);
			preparedStmt.setint(6, Integer.parseInt(ContactNum));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteHospital(String HosID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from Hospital where HosID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(HosID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}