package sample;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller_minaSidor {
    @FXML
    private Button backButton;
    @FXML
    private Button minProfilButton;
    @FXML
    private Button minaReservationerButton;

    public void goBackButtonOnAction(ActionEvent event) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root, 600, 475));
        window.show();


    }

    public void minProfilButtonOnAction(ActionEvent event) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("minProfil.fxml"));
        Stage window = (Stage) minProfilButton.getScene().getWindow();
        window.setScene(new Scene(root, 600, 475));
        window.show();
    }

    public void minaReservationerOnAction(ActionEvent event) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("minaReservationer.fxml"));
        Stage window = (Stage) minaReservationerButton.getScene().getWindow();
        window.setScene(new Scene(root, 600, 475));
        window.show();
    }
}
