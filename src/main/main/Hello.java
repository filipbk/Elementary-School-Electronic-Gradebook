package main;

import database.DatabaseConnector;

public class Hello {
	
	public static void main(String[] args) {
		System.out.println("Hello database adventure");
		DatabaseConnector dbc = new DatabaseConnector();
		dbc.rebuildDatabase();
	}

}
