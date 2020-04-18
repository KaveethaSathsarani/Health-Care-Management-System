package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {
	
	private static Connection connection;

	private DBHandler() {
		
	}

	public static Connection connect() throws SQLException, ClassNotFoundException {
		
		if (connection == null || connection.isClosed()) {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			//give database url ,username,password
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare","root", "root");
		}
		
		return connection;

	}
	

}
