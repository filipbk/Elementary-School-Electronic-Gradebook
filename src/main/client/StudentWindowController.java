package client;

import java.sql.Date;
import java.sql.ResultSet;

import database.DBClass;
import database.DBGrade;
import database.DBStudent;
import database.DBSubject;
import database.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

public class StudentWindowController {
	
	private String userID;
	private DatabaseConnector connector;
	private int subject;
	private TableColumn<DBSubject, Integer> subjectID;
	private TableColumn<DBSubject, String> subjectName;
	private TableColumn<DBSubject, Integer> subjectYear;
	private TableColumn<DBGrade, Integer> gradeID;
	private TableColumn<DBGrade, Date> gradeDate;
	private TableColumn<DBGrade, Integer> gradeVal;
	private TableColumn<DBGrade, String> gradeFinal;
	private TableColumn<DBGrade, String> gradeSurname;
	private TableColumn<DBGrade, String> gradeInfo;
	
	@FXML
	private TableView<DBSubject> subjects;
	@FXML
	private TableView<DBGrade> grades;
	
	@FXML
	private void initialize() {
		subject = -1;
		
		
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
					setUpGrades(subject);
				}
			});
			return row;
		});
		
		gradeID = new TableColumn<>("Grade ID");
		gradeID.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
		gradeDate = new TableColumn<>("Date");
		gradeDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		gradeVal = new TableColumn<>("Grade");
		gradeVal.setCellValueFactory(cellData -> cellData.getValue().gradeProperty().asObject());
		gradeFinal = new TableColumn<>("Is final");
		gradeFinal.setCellValueFactory(cellData -> cellData.getValue().finalProperty());
		gradeSurname = new TableColumn<>("Surname");
		gradeSurname.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
		gradeInfo = new TableColumn<>("Note");
		gradeInfo.setCellValueFactory(cellData -> cellData.getValue().noteProperty());
		grades.getColumns().addAll(gradeID, gradeDate, gradeVal, gradeFinal, gradeSurname, gradeInfo);
		
	}
	
	private void setUpSubjects() {
		ResultSet resultSet = connector.listStudentsSubjects(userID);
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
	
	private void setUpGrades( int subject) {
		
		ResultSet resultSet = connector.listStudentsGradesForSubject(userID, subject);
		grades.getItems().removeAll(grades.getItems());
		if(resultSet == null) {
			return;
		}
		try {
			while(resultSet.next()) {
				DBGrade dbGrade = new DBGrade(resultSet.getInt(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				grades.getItems().add(dbGrade);
			}
		} catch (Exception e) {
		}
	}
	
	public void setData(String userID, DatabaseConnector connector) {
		this.userID = userID;
		this.connector = connector;
		setUpSubjects();
	}
}
