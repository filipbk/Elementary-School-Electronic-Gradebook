package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
	
	private Connection connection;
	private static final String DB_URL = "jdbc:mysql://localhost/electronic_gradebook";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	public String connect() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			return "Unable to connect to database";
		}
		
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			return "Unable to connect to database";
		}
		
		
		return "Type in tour login and password";
	}

}
