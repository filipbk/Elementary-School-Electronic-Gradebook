package client;

import java.io.IOException;

import database.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminWindowController {
	
	private String userID;
	private DatabaseConnector connector;
	
	@FXML
	private Button addAdmin;
	
	@FXML
	private Button addTeacher;

	@FXML
	private Button addStudent;
	
	@FXML
	private Button manageClasses;
	
	@FXML
	private void handleAddAdmin(ActionEvent event) {
		Stage stage = (Stage)addAdmin.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAdmin.FXML"));
		
		Scene scene = null;
		try {
			scene = new Scene((Pane)loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.setScene(scene);
		AddAdminController controller = loader.<AddAdminController>getController();
		controller.setData("A", connector);
		stage.show();
	}
	
	@FXML
	private void handleAddTeacher(ActionEvent event) {
		
	}
	
	@FXML
	private void handleAddStudent(ActionEvent event) {
		Stage stage = (Stage)addStudent.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddStudent.FXML"));
		
		Scene scene = null;
		try {
			scene = new Scene((Pane)loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.setScene(scene);
		AddStudentController controller = loader.<AddStudentController>getController();
		controller.setData("A", connector);
		stage.show();
	}
	
	@FXML
	private void handleManageClasses(ActionEvent event) {
		Stage stage = (Stage)manageClasses.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ClassesManagement.FXML"));
		
		Scene scene = null;
		try {
			scene = new Scene((Pane)loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.setScene(scene);
		ClassesManagementController controller = loader.<ClassesManagementController>getController();
		controller.setData("A", connector);
		stage.show();
	}
	
	public void setData(String userID, DatabaseConnector connector) {
		this.userID = userID;
		this.connector = connector;
	}

}
