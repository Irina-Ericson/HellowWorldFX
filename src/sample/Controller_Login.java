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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Controller_Login {
    @FXML
    private Button login2;
    @FXML
    private Button backButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField EmailField;
    @FXML
    private TextField searchField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Button searchButton;
    @FXML
    private Label searchMessageLabel;
    @FXML
    private Label eNamn1;
    @FXML
    private Label fNamn1;
    @FXML
    private Label titel1;

    @FXML
    private Label eNamn2;
    @FXML
    private Label fNamn2;
    @FXML
    private Label titel2;




    public void login2ButtonOnAction(ActionEvent event) throws Exception {
        if (EmailField.getText().isBlank() == false && PasswordField.getText().isBlank() == false) {
            validateLogin();


        } else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    public void validateLogin() {
        sample.DatabaseConnection connectNow = new sample.DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_account_2 WHERE email ='" + EmailField.getText() + "'AND password ='" + PasswordField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    Parent root = FXMLLoader.load(getClass().getResource("minaSidor.fxml"));
                    Stage window = (Stage) login2.getScene().getWindow();
                    window.setScene(new Scene(root, 600, 475));
                    window.show();
                } else {
                    loginMessageLabel.setText("Invalid login. Please try again");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
        public void goBackButtonOnAction(ActionEvent event) throws Exception{

            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                    Stage window = (Stage) backButton.getScene().getWindow();
                    window.setScene(new Scene(root, 600, 475));
                    window.show();


        }
        public void searchButtonOnAction(ActionEvent event) throws Exception{
            sample.DatabaseConnection connectNow = new sample.DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String searchResource = "select Titel,eFörfattare,fFörfattare from artikel\n" +
                    "where Titel like '"+searchField.getText()+"'";


            try {
                Statement statement = connectDB.createStatement();
                ResultSet queryResult = statement.executeQuery(searchResource);

                while (queryResult.next()) {

                        eNamn1.setText(queryResult.getString("eFörfattare"));
                        fNamn1.setText(queryResult.getString("fFörfattare"));
                        titel1.setText(queryResult.getString("Titel"));

                        eNamn2.setText(queryResult.getString("eFörfattare"));
                        fNamn2.setText(queryResult.getString("fFörfattare"));
                        titel2.setText(queryResult.getString("Titel"));}


            //  searchMessageLabel.setText("Invalid search. Please try again");}


            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

        }

    }

