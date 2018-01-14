package client;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import database.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AdminWindowController {
	
	private String userID;
	private DatabaseConnector connector;
	
	@FXML
	private Button addAdmin;
	
	@FXML
	private Button backup;
	
	@FXML
	private Button restore;

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
		DirectoryChooser directoryChooser = new DirectoryChooser();
	    directoryChooser.setTitle("Choose directory of mysqldump");
	    File mysqldumpFolder = directoryChooser.showDialog(backup.getScene().getWindow());
	    String mysqldump = "";
	    if (mysqldumpFolder != null) {
	        mysqldump = mysqldumpFolder.getPath();
	    }
	    directoryChooser.setTitle("Choose destination directory");
	    File destination = directoryChooser.showDialog(backup.getScene().getWindow());
	    String dest = "";
	    if (destination != null) {
	        dest = destination.getPath();
	    }
	    
		Process exec = null;
		try {
			System.out.println(dest);
			//exec = Runtime.getRuntime().exec(new String[]{"cmd.exe","/c","E:\\Pobrane\\mariadb-10.2.10-winx64\\mariadb-10.2.10-winx64\\bin\\mysqldump -u root electronic_gradebook > E:\\backup.sql"});
			exec = Runtime.getRuntime().exec(new String[]{"cmd.exe","/c", mysqldump + "\\mysqldump -u root electronic_gradebook > " + dest + "\\backup.sql"});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			if(exec.waitFor()==0)
			{
			    InputStream inputStream = exec.getInputStream();
			    byte[] buffer = new byte[inputStream.available()];
			    inputStream.read(buffer);
			    Alert alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("Backup");
			    alert.setContentText("Backup done");
			    alert.showAndWait();
			}
			else
			{
			    InputStream errorStream = exec.getErrorStream();
			    byte[] buffer = new byte[errorStream.available()];
			    errorStream.read(buffer);
			    Alert alert = new Alert(AlertType.ERROR);
			    alert.setTitle("Backup");
			    alert.setContentText("Error: backup failed");
			    alert.showAndWait();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@FXML
	private void handleRestore(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
	    directoryChooser.setTitle("Choose directory of mysqldump");
	    File mysqldumpFolder = directoryChooser.showDialog(backup.getScene().getWindow());
	    String mysqldump = "";
	    if (mysqldumpFolder != null) {
	        mysqldump = mysqldumpFolder.getPath();
	    }
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Choose file to restore");
	    File src = fileChooser.showOpenDialog(restore.getScene().getWindow());
	    String source = "";
	    if (src != null) {
	    	source = src.getPath();
	    }
	    
		Process exec = null;
		try {
			System.out.println(source);
			//exec = Runtime.getRuntime().exec(new String[]{"cmd.exe","/c","E:\\Pobrane\\mariadb-10.2.10-winx64\\mariadb-10.2.10-winx64\\bin\\mysqldump -u root electronic_gradebook > E:\\backup.sql"});
			exec = Runtime.getRuntime().exec(new String[]{"cmd.exe","/c", mysqldump + "\\mysql -u root electronic_gradebook < " + source});
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			if(exec.waitFor()==0)
			{
			    InputStream inputStream = exec.getInputStream();
			    byte[] buffer = new byte[inputStream.available()];
			    inputStream.read(buffer);
			    Alert alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("Restore");
			    alert.setContentText("Restore done");
			    alert.showAndWait();
			}
			else
			{
			    InputStream errorStream = exec.getErrorStream();
			    byte[] buffer = new byte[errorStream.available()];
			    errorStream.read(buffer);
			    Alert alert = new Alert(AlertType.ERROR);
			    alert.setTitle("Restore");
			    alert.setContentText("Error: restore failed");
			    alert.showAndWait();
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
