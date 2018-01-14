package client;

import java.io.IOException;
import java.sql.Date;

import database.DatabaseConnector;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddStudentController {
	
	private String userID;
	private DatabaseConnector connector;
	private ToggleGroup group;
	private String type;
	
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
	private TextField day;
	@FXML
	private TextField month;
	@FXML
	private TextField year;
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
	private TextField parentPhone;
	@FXML
	private TextField classID;
	@FXML
	private TextField message;
	@FXML
	private Button add;
	@FXML
	private Button back;
	
	@FXML
	private void handleAdd(ActionEvent event) {
		String peselS = pesel.getText();
		String passwordS = password.getText();
		String firstS = firstName.getText();
		String secondS = secondName.getText();
		String surnameS = surname.getText();
		String dayS = day.getText();
		String monthS = month.getText();
		String yearS = year.getText();
		String dateS = yearS + "-" + monthS + "-" + dayS;
		String emailS = email.getText();
		String phoneS = contactPhone.getText();
		String postalS = postalCode.getText();
		String cityS = city.getText();
		String streetS = street.getText();
		String parentS = parentPhone.getText();
		String classS = classID.getText();
		Date date = null;
		int house;
		int flat;
		try {
			date = Date.valueOf(dateS);
			house = Integer.parseInt(houseNumber.getText());
			if (flatNumber.getText().equals("")) {
				flat = 0;
			} else {
				flat = Integer.parseInt(flatNumber.getText());
			}
		} catch (NumberFormatException e) {
			message.setText("House and flat number must be a number");
			return;
		} catch (IllegalArgumentException e1) {
			message.setText("Invalid date");
			return;
		}
		
		String msg = connector.addStudent(peselS, passwordS, firstS, secondS, surnameS, date, emailS, phoneS, postalS, cityS, streetS, house, flat, parentS, classS);
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
