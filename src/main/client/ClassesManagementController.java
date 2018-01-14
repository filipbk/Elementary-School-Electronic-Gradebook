package client;

import java.io.IOException;

import database.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ClassesManagementController {
	
	private String userID;
	private DatabaseConnector connector;
	
	@FXML
	private TextField subjectName;

	@FXML
	private TextField subjectYear;

	@FXML
	private TextField classID;

	@FXML
	private TextField classTeacher;
	
	@FXML
	private TextField classYear;

	@FXML
	private TextField lessonSubject;

	@FXML
	private TextField lessonTeacher;

	@FXML
	private TextField lessonClass;
	
	@FXML
	private TextField message;
	
	@FXML
	private Button addSubject;

	@FXML
	private Button addClass;

	@FXML
	private Button addLesson;
	
	@FXML
	private Button back;
	
	@FXML
	private void handleAddSubject(ActionEvent event) {
		String name = subjectName.getText();
		int year = 0;
		try {
			year = Integer.parseInt(subjectYear.getText());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			message.setText("Invalid subject year");
			return;
		}
		String msg = connector.addSubject(name, year);
		message.setText(msg);
	}

	@FXML
	private void handleAddClass(ActionEvent event) {
		String id = classID.getText();
		String tutor = classTeacher.getText();
		int year = 0;
		try {
			year = Integer.parseInt(classYear.getText());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			message.setText("Invalid class year");
			return;
		}
		String msg = connector.addClass(id, tutor, year);
		message.setText(msg);
	}

	@FXML
	private void handleAddLesson(ActionEvent event) {
		String teacher = lessonTeacher.getText();
		String classID = lessonClass.getText();
		int subjectID = 0;
		try {
			subjectID = Integer.parseInt(lessonSubject.getText());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			message.setText("Invalid subject ID");
			return;
		}
		String msg = connector.addLessonSet(subjectID, teacher, classID);
		message.setText(msg);
	}
	
	@FXML
	private void handleBack(ActionEvent event) {
		Stage stage = (Stage)back.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminWindow.FXML"));
		
		Scene scene = null;
		try {
			scene = new Scene((Pane)loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.setScene(scene);
		AdminWindowController controller = loader.<AdminWindowController>getController();
		controller.setData(userID, connector);
		stage.show();
	}
	
	public void setData(String userID, DatabaseConnector connector) {
		this.userID = userID;
		this.connector = connector;
	}
	

}
