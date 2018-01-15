package database;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DBGrade {
	
	private IntegerProperty id;
	private SimpleObjectProperty<Date> assesmentDate;
	private IntegerProperty gradeValue;
	private StringProperty finalGrade;
	private StringProperty surname;
	private StringProperty note;
	
	public DBGrade() {
		this.id = new SimpleIntegerProperty();
		this.assesmentDate = new SimpleObjectProperty<>();
		this.gradeValue = new SimpleIntegerProperty();
		this.finalGrade = new SimpleStringProperty();
		this.surname = new SimpleStringProperty();
		this.note = new SimpleStringProperty();
	}
	
	public DBGrade(int id, Date date, int value, String finalGrade, String surname, String note) {
		this.id = new SimpleIntegerProperty(id);
		this.assesmentDate = new SimpleObjectProperty<>(date);
		this.gradeValue = new SimpleIntegerProperty(value);
		this.finalGrade = new SimpleStringProperty(finalGrade);
		this.surname = new SimpleStringProperty(surname);
		this.note = new SimpleStringProperty(note);
	}
	
	public void setID(int ID) {
		this.id.set(ID);
	}
	
	public int getID() {
		return id.get();
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	
	public void setDate(Date date) {
		this.assesmentDate.set(date);
	}
	
	
	public SimpleObjectProperty<Date> dateProperty() {
		return assesmentDate;
	}
	
	public void setSurame(String surname) {
		this.surname.set(surname);
	}
	
	public String getSurame() {
		return surname.get();
	}
	
	public StringProperty surnameProperty() {
		return surname;
	}
	
	public void setGrade(int value) {
		this.gradeValue.set(value);
	}
	
	public int getGrade() {
		return gradeValue.get();
	}
	
	public IntegerProperty gradeProperty() {
		return gradeValue;
	}
	
	public void setFinal(String finalGrade) {
		this.finalGrade.set(finalGrade);
	}
	
	public String getFinalt() {
		return finalGrade.get();
	}
	
	public StringProperty finalProperty() {
		return finalGrade;
	}
	
	public void setNote(String note) {
		this.note.set(note);
	}
	
	public String getNote() {
		return note.get();
	}
	
	public StringProperty noteProperty() {
		return note;
	}
}
