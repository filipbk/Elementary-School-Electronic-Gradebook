package client;

import database.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginWindowController {
	
	private DatabaseConnector connector;
	
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
		
	}
	
	public void setMessage(String message) {
		this.message.setText(message);
	}

}
