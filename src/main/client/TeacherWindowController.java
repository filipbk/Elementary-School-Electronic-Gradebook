package client;

import java.sql.Date;
import java.sql.ResultSet;

import database.DBClass;
import database.DBGrade;
import database.DBSubject;
import database.DBStudent;
import database.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;

public class TeacherWindowController {
	
	private TableColumn<DBClass, String> classID;
	private TableColumn<DBClass, Integer> classYear;
	private TableColumn<DBSubject, Integer> subjectID;
	private TableColumn<DBSubject, String> subjectName;
	private TableColumn<DBSubject, Integer> subjectYear;
	private TableColumn<DBStudent, String> studentID;
	private TableColumn<DBStudent, String> studentName;
	private TableColumn<DBStudent, String> studentSurname;
	private TableColumn<DBStudent, String> studentEmail;
	private TableColumn<DBStudent, String> studentContact;
	private TableColumn<DBStudent, String> studentParent;
	private TableColumn<DBGrade, Integer> gradeID;
	private TableColumn<DBGrade, Date> gradeDate;
	private TableColumn<DBGrade, Integer> gradeVal;
	private TableColumn<DBGrade, String> gradeFinal;
	private TableColumn<DBGrade, String> gradeSurname;
	private TableColumn<DBGrade, String> gradeInfo;
	private String userID;
	private DatabaseConnector connector;
	private int subject;
	private String student;
	private String className;
	private int grade;
	private String note;
	
	@FXML
	private TableView<DBClass> classes;
	@FXML
	private TableView<DBSubject> subjects;
	@FXML
	private TableView<DBStudent> students;
	@FXML
	private TableView<DBGrade> grades;
	@FXML
	private TextField message;
	@FXML
	private TextField gradeValue;
	@FXML
	private TextField gradeNote;
	@FXML
	private Button addGrade;
	@FXML
	private Button deleteGrade;
	
	@FXML
	private void initialize() {
		className = null;
		subject = -1;
		student = null;
		classID = new TableColumn<DBClass, String>("Class ID");
		classID.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		classYear = new TableColumn<>("Class year");
		classYear.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
		classes.getColumns().add(classID);
		classes.getColumns().add(classYear);
		classes.setRowFactory(tv -> {
			TableRow<DBClass> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				DBClass rowData = row.getItem();
				if(rowData != null) {
					subject = -1;
					student = null;
					className = rowData.getID();
					grade = -1;
					setUpSubjects(className);
					setUpStudents(className);
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
		
		studentID = new TableColumn<>("Student ID");
		studentID.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		studentName = new TableColumn<>("Name");
		studentName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		studentSurname = new TableColumn<>("Surname");
		studentSurname.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
		studentEmail = new TableColumn<>("Email");
		studentEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		studentContact = new TableColumn<>("Contact phone");
		studentContact.setCellValueFactory(cellData -> cellData.getValue().contactProperty());
		studentParent = new TableColumn<>("Parent phone");
		studentParent.setCellValueFactory(cellData -> cellData.getValue().parentProperty());
		students.getColumns().addAll(studentID, studentName, studentSurname, studentEmail, studentContact, studentParent);
		students.setRowFactory(tv -> {
			TableRow<DBStudent> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				DBStudent rowData = row.getItem();
				if(rowData != null) {
					student = rowData.getID();
					setUpGrades(student, subject);
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
		grades.setRowFactory(tv -> {
			TableRow<DBGrade> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				DBGrade rowData = row.getItem();
				if(rowData != null) {
					grade = rowData.getID();
				}
			});
			return row;
		});
	}
	
	@FXML
	private void handleAddGrade(ActionEvent event) {
		if(className == null || subject == -1 || student == null) {
			message.setText("Choose class, subject and student");
			return;
		}
		int grade = 0;
		
		try {
			grade = Integer.parseInt(gradeValue.getText());
		} catch (Exception e) {
			message.setText("Invalid grade format");
			return;
		}
		
		String msg = connector.addGrade(grade, student, userID, subject, 0, gradeNote.getText());
		message.setText(msg);
	}
	
	@FXML
	private void deleteGrade(ActionEvent event) {
		if (grade == -1) {
			message.setText("Choose a grade");
			return;
		}
		connector.deleteGrade(grade);
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
		}
	}
	
	private void setUpStudents(String classID) {
		
		ResultSet resultSet = connector.listAllStudentsForClass(classID);
		students.getItems().removeAll(students.getItems());
		if(resultSet == null) {
			return;
		}
		try {
			while(resultSet.next()) {
				DBStudent dbStudent = new DBStudent(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				students.getItems().add(dbStudent);
			}
		} catch (Exception e) {
		}
	}
	
	private void setUpGrades(String student, int subject) {
		if (student != null && subject != -1) {
			
		}
		ResultSet resultSet = connector.listStudentsGradesForSubject(student, subject);
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
		setUpClasses();
	}

}
