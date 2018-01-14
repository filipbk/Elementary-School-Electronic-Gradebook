package database;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DBClass {
	
	private StringProperty id;
	private IntegerProperty year;
	
	public DBClass() {
		this.id = new SimpleStringProperty();
		this.year = new SimpleIntegerProperty();
	}
	
	public DBClass(String id, int year) {
		this.id = new SimpleStringProperty(id);
		this.year = new SimpleIntegerProperty(year);
	}
	
	public void setID(String id) {
		this.id.set(id);
	}
	
	public String getID() {
		return id.get();
	}
	
	public StringProperty idProperty() {
		return id;
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
