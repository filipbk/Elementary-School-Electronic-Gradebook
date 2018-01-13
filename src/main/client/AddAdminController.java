package client;

import java.io.IOException;

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

public class AddAdminController {
	
	private String userID;
	private DatabaseConnector connector;
	
	@FXML
	private TextField pesel;
	@FXML
	private PasswordField password;
	@FXML
	private TextField firstName;
	@FXML
	private TextField secondName;
	@FXML
	private TextField surname;
	@FXML
	private TextField dateOfBirth;
	@FXML
	private TextField email;
	@FXML
	private TextField contactPhone;
	@FXML
	private TextField postalCode;
	@FXML
	private TextField city;
	@FXML
	private TextField street;
	@FXML
	private TextField houseNumber;
	@FXML
	private TextField flatNumber;
	@FXML
	private TextField message;
	@FXML
	private Button add;
	@FXML
	private Button back;
	
	@FXML
	private void handleAdd(ActionEvent event) {
		
	}

	@FXML
	private void handleBack(ActionEvent event) {
		Stage stage = (Stage)back.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminWindow.FXML"));
		
		Scene scene = null;
		try {
			scene = new Scene((Pane)loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.setScene(scene);
		AdminWindowController controller = loader.<AdminWindowController>getController();
		controller.setData("A", connector);
		stage.show();
	}
	
	public void setData(String userID, DatabaseConnector connector) {
		this.userID = userID;
		this.connector = connector;
	}
	
}
