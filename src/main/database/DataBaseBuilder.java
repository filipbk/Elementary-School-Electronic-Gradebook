package database;

import java.sql.*;
/**
 * Probably a builder designed to build a clear database.
 * @author pawemix
 *
 */
public class DataBaseBuilder {

	/** XXX JDBC
	 * DriverManager - matches connection requests with proper Driver, depending on a database we have;
	 * Connection - interface we will be using; contains methods to contact a database;
	 * Statement - object to send to the DB (sometimes with arguments);
	 * ResultSet - iterable result returned from the DB after sending a Statement object;
	 * SQLException - if there's a problem with our DB
	 * 
	 * PRIMARY PACKAGES: java.sql, javax.sql; contain all needed classes
	 */
	
	/**
	 * At the moment this method tests connection with database server as root and performs basic operations
	 * in order to get some data from the database.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		try {
			
			Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "root", "");
			Statement statement = connection.createStatement();
			System.out.println("### DATABASES ###");
			ResultSet result = statement.executeQuery("SHOW DATABASES");
			while(result.next()) {
				System.out.println(result.getString(1));
			}
			result = statement.executeQuery("USE people");
			System.out.println("### TABLES IN people ###");
			result = statement.executeQuery("SHOW TABLES");
			while(result.next()) {
				System.out.println(result.getString(1));
			}
			result = statement.executeQuery("SELECT * FROM ludzie LIMIT 10");
			System.out.println("### LUDZIE LIMIT 10 ###");
			while(result.next()) {
				System.out.println(result.getString(2) + " " + result.getString(3));
			}
			connection.close();
			
		} catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
}
