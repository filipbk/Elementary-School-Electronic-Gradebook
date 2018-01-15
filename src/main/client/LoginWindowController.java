package client;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginWindowController {
	
	private DatabaseConnector connector;
	private String userID;
	
	@FXML
	private TextField login;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Button confirm;
	
	@FXML
	private TextField message;
	
	@FXML
	private void initialize() {
		connector = new DatabaseConnector();
		setMessage(connector.connect());
	}
	
	@FXML
	private void handleConfirm(ActionEvent event) {
		ResultSet resultSet = null;
		String permission = "";
		resultSet = connector.login(login.getText(), password.getText());
		
		try {
			if(resultSet == null) {
				message.setText("Null");
				return;
			} else if(resultSet.next()) {
				userID = resultSet.getString(1);
				permission = resultSet.getString(2);
			} else {
				message.setText("Invalid data");
			}
		} catch (Exception e) {
			message.setText("Unable to log in");
			//e.printStackTrace();
			return;
		}
		
		Stage stage = (Stage)confirm.getScene().getWindow();
		FXMLLoader loader = null;
		
		if(permission.equals("ADMIN")) {
			loader = new FXMLLoader(getClass().getResource("AdminWindow.FXML"));
		} else if(permission.equals("TEACHER")) {
			loader = new FXMLLoader(getClass().getResource("TeacherWindow.FXML"));
		} else if(permission.equals("STUDENT")) {
			loader = new FXMLLoader(getClass().getResource("StudentWindow.FXML"));
		}
		
		Scene scene = null;
		try {
			scene = new Scene((Pane)loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.setScene(scene);
		if(permission.equals("ADMIN")) {
			AdminWindowController controller = loader.<AdminWindowController>getController();
			controller.setData(userID, connector);
		} else if(permission.equals("TEACHER")) {
			TeacherWindowController controller = loader.<TeacherWindowController>getController();
			controller.setData(userID, connector);
		} else if(permission.equals("STUDENT")) {
			StudentWindowController controller = loader.<StudentWindowController>getController();
			controller.setData(userID, connector);
		}
		stage.show();
		
	}
	
	public void setMessage(String message) {
		this.message.setText(message);
	}

}
