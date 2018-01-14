package database;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DBStudent {
	
	private StringProperty id;
	private StringProperty name;
	private StringProperty surname;
	private StringProperty email;
	private StringProperty contact;
	private StringProperty parent;
	
	public DBStudent() {
		this.id = new SimpleStringProperty();
		this.name = new SimpleStringProperty();
		this.surname = new SimpleStringProperty();
		this.email = new SimpleStringProperty();
		this.contact = new SimpleStringProperty();
		this.parent = new SimpleStringProperty();
	}
	
	public DBStudent(String id, String name, String surname, String email, String contact, String parent) {
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);
		this.email = new SimpleStringProperty(email);
		this.contact = new SimpleStringProperty(contact);
		this.parent = new SimpleStringProperty(parent);
	}
	
	public void setID(String ID) {
		this.id.set(ID);
	}
	
	public String getID() {
		return id.get();
	}
	
	public StringProperty idProperty() {
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
	
	public void setSurame(String surname) {
		this.surname.set(surname);
	}
	
	public String getSurame() {
		return surname.get();
	}
	
	public StringProperty surnameProperty() {
		return surname;
	}
	
	public void setEmail(String email) {
		this.email.set(email);
	}
	
	public String getEmail() {
		return email.get();
	}
	
	public StringProperty emailProperty() {
		return email;
	}
	
	public void setContact(String contact) {
		this.contact.set(contact);
	}
	
	public String getContact() {
		return contact.get();
	}
	
	public StringProperty contactProperty() {
		return contact;
	}
	
	public void setParent(String parent) {
		this.parent.set(parent);
	}
	
	public String getParent() {
		return parent.get();
	}
	
	public StringProperty parentProperty() {
		return parent;
	}

}
