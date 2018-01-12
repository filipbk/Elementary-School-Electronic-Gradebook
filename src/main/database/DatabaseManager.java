package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
	
	private final String buildScriptPath = "\\databaseBuildScript.txt"; 
	private final String host = "jdbc:mariadb://localhost:3306";
	private final String user = "root";
	private final String passwd = "";
	
	public static void main(String[] args) {
		DatabaseManager dbm = new DatabaseManager();
		dbm.setupDatabase();
	}
	
	public void setupDatabase() {
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
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
		System.out.println(buildScript.get(0));
		try {
			Connection connection = DriverManager.getConnection(host,user,passwd);
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
	 * Reads a list of statements fom
	 * @param file file, from which we read a string
	 * @return string - text from the file
	 * @throws FileNotFoundException if no file was found
	 * @throws IOException if there was an IO problem while reading from a file
	 */
	private List<String> readFile(File file) throws FileNotFoundException, IOException {
		List<String> output = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuilder sb = new StringBuilder();
		try {	
			String line = br.readLine();
			
			while(line != null) {
				if(!line.isEmpty() && !line.startsWith("-- ")) {
					if(line.endsWith(";")) output.add(line);
					else {
						String statement = line;
						do {
							line = br.readLine();
							if(line.isEmpty() || line.startsWith("-- ")) continue;
							statement += line;
						} while(line != null && !line.endsWith(";"));
						output.add(statement);
					}
				}
				line = br.readLine();
			}
		} finally {
		br.close();
		}
		return output;
	}
}