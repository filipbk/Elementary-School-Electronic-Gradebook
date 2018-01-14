package database;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DBSubject {
	
	private IntegerProperty id;
	private StringProperty name;
	private IntegerProperty year;
	
	public DBSubject() {
		this.id = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty();
		this.year = new SimpleIntegerProperty();
	}
	
	public DBSubject(int id, String name, int year) {
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.year = new SimpleIntegerProperty(year);
	}
	
	public void setID(int id) {
		this.id.set(id);
	}
	
	public int getID() {
		return id.get();
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getName() {
		return name.get();
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	
	public void setYear(int year) {
		this.year.set(year);
	}
	
	public int getYear() {
		return year.get();
	}
	
	public IntegerProperty yearProperty() {
		return year;
	}

}
