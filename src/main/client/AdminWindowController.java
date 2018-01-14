package client;

import java.io.IOException;
import java.io.InputStream;

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
	private Button backup;

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
		controller.setData(userID, connector);
		stage.show();
	}
	
	@FXML
	private void handleBaackup(ActionEvent event) {
		Process exec = null;
		try {
			exec = Runtime.getRuntime().exec(new String[]{"cmd.exe","/c","E:\\Pobrane\\mariadb-10.2.10-winx64\\mariadb-10.2.10-winx64\\bin\\mysqldump -u root electronic_gradebook > E:\\backup.sql"});
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			if(exec.waitFor()==0)
			{
			    InputStream inputStream = exec.getInputStream();
			    byte[] buffer = new byte[inputStream.available()];
			    inputStream.read(buffer);

			    String str = new String(buffer);
			    System.out.println(str);
			}
			else
			{
			    InputStream errorStream = exec.getErrorStream();
			    byte[] buffer = new byte[errorStream.available()];
			    errorStream.read(buffer);

			    String str = new String(buffer);
			    System.out.println(str);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
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
		controller.setData(userID, connector);
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
		controller.setData(userID, connector);
		stage.show();
	}
	
	public void setData(String userID, DatabaseConnector connector) {
		this.userID = userID;
		this.connector = connector;
	}

}
