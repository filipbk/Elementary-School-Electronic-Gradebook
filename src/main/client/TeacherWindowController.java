package client;

import java.sql.ResultSet;

import database.DBClass;
import database.DBSubject;
import database.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;

public class TeacherWindowController {
	
	private TableColumn<DBClass, String> classID;
	private TableColumn<DBClass, Integer> classYear;
	private TableColumn<DBSubject, Integer> subjectID;
	private TableColumn<DBSubject, String> subjectName;
	private TableColumn<DBSubject, Integer> subjectYear;
	private String userID;
	private DatabaseConnector connector;
	private int subject;
	private String student;
	
	@FXML
	private TableView<DBClass> classes;
	@FXML
	private TableView<DBSubject> subjects;
	
	@FXML
	private void initialize() {
		classID = new TableColumn<DBClass, String>("Class ID");
		classID.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		classYear = new TableColumn<>("Class year");
		classYear.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
		classes.getColumns().add(classID);
		classes.getColumns().add(classYear);
		classes.getItems().add(new DBClass("324234234", 3));
		classes.setRowFactory(tv -> {
			TableRow<DBClass> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				DBClass rowData = row.getItem();
				if(rowData != null) {
					System.out.println("click on: " + rowData.getID());
					setUpSubjects(rowData.getID());
				}
			});
			return row;
		});
		
		subjectID = new TableColumn<DBSubject, Integer>("Subject ID");
		subjectID.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
		subjectName = new TableColumn<>("Subject");
		subjectName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		subjectYear = new TableColumn<>("Subject year");
		subjectYear.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
		subjects.getColumns().addAll(subjectID, subjectName, subjectYear);
		subjects.setRowFactory(tv -> {
			TableRow<DBSubject> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				DBSubject rowData = row.getItem();
				if(rowData != null) {
					subject = rowData.getID();
				}
			});
			return row;
		});
	}
	
	private void setUpClasses() {
		ResultSet resultSet = connector.listAllClasses();
		if(resultSet == null) {
			return;
		}
		try {
			while(resultSet.next()) {
				DBClass dbClass = new DBClass(resultSet.getString(1), resultSet.getInt(2));
				classes.getItems().add(dbClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setUpSubjects(String classID) {
		ResultSet resultSet = connector.listAllSubjectsForClass(classID);
		subjects.getItems().removeAll(subjects.getItems());
		if(resultSet == null) {
			return;
		}
		try {
			while(resultSet.next()) {
				DBSubject dbSubject = new DBSubject(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
				subjects.getItems().add(dbSubject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setData(String userID, DatabaseConnector connector) {
		this.userID = userID;
		this.connector = connector;
		setUpClasses();
	}

}
