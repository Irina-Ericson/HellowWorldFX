package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User_Controller {
    @FXML private TextField nameField;
    @FXML private TextField surnameField;
    @FXML private TextField telefonnummer;
    @FXML private TextField emailField;
    @FXML private TextField postnummer;
    @FXML private TextField streetField;
    @FXML private TextField cityField;
    @FXML private TextField passwordField;
    @FXML private ChoiceBox catChoice;
    @FXML private Button registerButton;
    @FXML private Label varnLabel;


        public void RegisButtonOnAction (ActionEvent event) throws Exception{
        if (surnameField.getText().isBlank() == false && telefonnummer.getText().isBlank() == false &&
                emailField.getText().isBlank() == false && postnummer.getText().isBlank() == false &&
                streetField.getText().isBlank() == false && cityField.getText().isBlank() == false &&
                passwordField.getText().isBlank() == false && catChoice.getItems().isEmpty() == false &&
                nameField.getText().isBlank() == false) {
            registerUser();


        } else {
            varnLabel.setText("Kontrollera att alla fält är ifyllda");
        }
    }

    public void registerUser() {
        sample.DatabaseConnection connectNow = new sample.DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String insertUser = "insert into user_account_2 (Name,Surname, telefonnummer, password, postnummer, street, city, email, ID_Låntag_kat)\n" +
                "values('" + nameField.getText() + "','" + surnameField.getText() + "','" + telefonnummer.getText() + "','" + passwordField.getText() + "','" + postnummer.getText() + "','" + streetField.getText() + "','" + cityField.getText() + "', '" + emailField.getText() + "','" + catChoice.getValue() + "')";

        try {
            Statement statement = connectDB.createStatement();
            int rows=statement.executeUpdate(insertUser);
            varnLabel.setText("Nu är du registrerad");


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    }







