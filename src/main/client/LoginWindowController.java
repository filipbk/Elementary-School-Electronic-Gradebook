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
		String call = "{call Login(?, ?)}";
		ResultSet resultSet = null;
		// TODO Will be moved to DatabaseConnector
		/*try {
			PreparedStatement statement = connector.getConnection().prepareCall(call);
			statement.setString(1, login.getText());
			statement.setString(2, password.getText());
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				userID = resultSet.getString(1);
			} else {
				setMessage("Invalid login or password");
				return;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}*/
		
		Stage stage = (Stage)confirm.getScene().getWindow();
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
		controller.setData("A", connector);
		stage.show();
		
	}
	
	public void setMessage(String message) {
		this.message.setText(message);
	}

}
