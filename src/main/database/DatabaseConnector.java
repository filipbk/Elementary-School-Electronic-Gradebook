package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DatabaseConnector {
	
	private Connection connection;
	private static final String DB_URL = "jdbc:mysql://localhost/electronic_gradebook";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private static final String buildScriptPath = "\\databaseBuildScript.txt"; 
	
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
	/**
	 * A method that receives text from databaseBuildScript.txt located in project directory,
	 * formats it into a series of SQL statements, and then executes them one by one.
	 * DatabaseBuildScript.txt's function is to build the database's framework from scratch,
	 * in SQL, all the tables, procedures, etc. 
	 * @author pawemix
	 */
	public void rebuildDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Could not retrieve database driver.");
			return;
		}
		
		List<String> buildScript;
		
		try {
		File buildFile = new File("");
		buildFile = new File(buildFile.getAbsolutePath() + buildScriptPath);
		buildScript = readFile(buildFile);
		} catch(FileNotFoundException e1) {
			System.out.println("Could not retrieve a build script text file from project directory.");
			return;
		} catch(IOException e2) {
			System.out.println("IO Error while reading from the build script text file.");
			return;
		}
		try {
			Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
			Statement statement = connection.createStatement();
			for(String command : buildScript) statement.execute(command);
			connection.close();
		} catch(SQLException e) {
			System.out.println("Error while connecting to the database/executing statements.");
			e.printStackTrace();
			return;
		}
		System.out.println("Successfully set up a clear electronic gradebook database.");
	}
	
	/**
	 * Reads a raw string from a file.
	 * @param file file, from which we read a string
	 * @return raw string
	 * @throws FileNotFoundException if no file was found
	 * @throws IOException if there was an IO problem while reading from a file
	 */
	private List<String> readFile(File file) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuilder sb = new StringBuilder();

		String line = br.readLine();
		while(line != null) {
			if(!line.matches("^([ ]|\\t)*-- .*$")) sb.append(line + " ");
			line = br.readLine();
		}
		br.close();
		return divideIntoSQLStatements(sb.toString());
	}
	/**
	 * @param string a raw string symbolizing concatenated list of SQL statements
	 * @return Java List of strings; clear, formatted SQL statements
	 */
	private List<String> divideIntoSQLStatements(String string) {
		String[] chunks = string.split("\\bDELIMITER ");
		List<String> result = new ArrayList<String>();
		chunks[0] = "; " + chunks[0];
		for(String chunk : chunks) {
			String delimiter = chunk.trim().split("\\s")[0];
			delimiter = delimiter.replace("$", "\\$"); //XXX obs³uguje tylko $, innych kluczowych znaków w regexie nie
			String[] commands = chunk.split(delimiter);
			for(String command : commands) result.add(command);
		}
		Predicate<String> empty = new Predicate<String>() {
			public boolean test(String string) {
				return string.matches("^\\s*$");
			}
		};
		result.removeIf(empty);
		return result;
	}
}
