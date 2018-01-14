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
			//e.printStackTrace();
			return "Adding user failed";
		}
		
	}
	
	public String addStaff(String pesel, String password, String firstName, String secondName, String surname, Date dateOfBirth, String email,
				  String contactPhone, String postalCode, String city, String street, int houseNumber, int flatNumber) {
		ResultSet resultSet = null;
		String call = "{call addStaff(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		
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
	
	public String addStudent(String pesel, String password, String firstName, String secondName, String surname, Date dateOfBirth, String email,
			  String contactPhone, String postalCode, String city, String street, int houseNumber, int flatNumber, String parentPhone, String classID) {
		ResultSet resultSet = null;
		String call = "{call addStudent(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		
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
			statement.setString(14, parentPhone);
			statement.setString(15, classID);
			
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
	
	public ResultSet login(String login, String password) {
		String call = "{call login(?, ?)}";
		ResultSet resultSet = null;
		
		try {
			PreparedStatement statement = connection.prepareCall(call);
			statement.setString(1,login);
			statement.setString(2, password);
			resultSet = statement.executeQuery();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public String addClass(String id, String tutor, int year) {
		String call = "{call addClass(?, ?, ?)}";
		ResultSet resultSet = null;
		
		try {
			PreparedStatement statement = connection.prepareCall(call);
			statement.setString(1, id);
			statement.setString(2, tutor);
			statement.setInt(3, year);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getString(1);
			} else {
				return "Invalid data";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Adding class failed";
		}
	}
	
	public String addSubject(String name, int classYear) {
		String call = "{call addSubject(?, ?)}";
		ResultSet resultSet = null;
		
		try {
			PreparedStatement statement = connection.prepareCall(call);
			statement.setString(1, name);
			statement.setInt(2, classYear);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getString(1);
			} else {
				return "Invalid data";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Adding subject failed";
		}
	}
	
	public String addLessonSet(int subjectID, String teacherID, String classID) {
		String call = "{call addLessonSet(?, ?, ?)}";
		ResultSet resultSet = null;
		
		try {
			PreparedStatement statement = connection.prepareCall(call);
			statement.setInt(1, subjectID);
			statement.setString(2, teacherID);
			statement.setString(3, classID);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getString(1);
			} else {
				return "Invalid data";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Adding subject failed";
		}
	}
	
	public ResultSet listAllClasses() {
		String call = "{call listAllClasses()}";
		ResultSet resultSet = null;
		
		try {
			PreparedStatement statement = connection.prepareCall(call);
			resultSet = statement.executeQuery();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public ResultSet listAllSubjectsForClass(String classID) {
		String call = "{call listAllSubjectsForClass(?)}";
		ResultSet resultSet = null;
		
		try {
			PreparedStatement statement = connection.prepareCall(call);
			statement.setString(1, classID);
			resultSet = statement.executeQuery();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

}
