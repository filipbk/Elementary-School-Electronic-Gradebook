package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		return "Type in your login and password";
	}
	
	public ResultSet executeQuery(String query) {
		Statement statement = null;
        ResultSet resultSet = null;
        
        try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return resultSet;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public String addAdmin(String pesel, String password, String firstName, String secondName, String surname, Date dateOfBirth, String email,
							  String contactPhone, String postalCode, String city, String street, int houseNumber, int flatNumber) {
		ResultSet resultSet = null;
		String call = "{call addAdmin(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		
		try {
			PreparedStatement statement = connection.prepareCall(call);
			statement.setString(1,pesel);
			statement.setString(2, password);
			statement.setString(3, firstName);
			statement.setString(4, secondName);
			statement.setString(5, surname);
			statement.setDate(6, dateOfBirth);
			statement.setString(7, email);
			statement.setString(8, contactPhone);
			statement.setString(9, postalCode);
			statement.setString(10, city);
			statement.setString(11, street);
			statement.setInt(12, houseNumber);
			statement.setInt(13, flatNumber);
			
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getString(1);
			} else {
				return "Invalid data";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Adding user failed";
		}
		
	}
	
	public String addTeacher(String pesel, String password, String firstName, String secondName, String surname, Date dateOfBirth, String email,
				  String contactPhone, String postalCode, String city, String street, int houseNumber, int flatNumber) {
		ResultSet resultSet = null;
		String call = "{call addTeacher(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		
		try {
			PreparedStatement statement = connection.prepareCall(call);
			statement.setString(1,pesel);
			statement.setString(2, password);
			statement.setString(3, firstName);
			statement.setString(4, secondName);
			statement.setString(5, surname);
			statement.setDate(6, dateOfBirth);
			statement.setString(7, email);
			statement.setString(8, contactPhone);
			statement.setString(9, postalCode);
			statement.setString(10, city);
			statement.setString(11, street);
			statement.setInt(12, houseNumber);
			statement.setInt(13, flatNumber);
			
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getString(1);
			} else {
				return "Invalid data";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Adding user failed";
		}
	
	}
	
	
	
	public String login(String login, String password) {
		String call = "{call Login(?, ?)}";
		ResultSet resultSet = null;
		
		try {
			PreparedStatement statement = connection.prepareCall(call);
			statement.setString(1,login);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				return resultSet.getString(1);
			} else {
				return "Invalid login or password";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "Exception";
		}
	}

}
