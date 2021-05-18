package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.stage.Stage;

public class Controller  {

    public Button Login;
    public Menu Kontakta_oss;
    public Menu Oppetider;
    public Menu Databaser;
    public Menu Start;
    @FXML
    private Label showUserNameLabel;
    public void connectButton(ActionEvent event){
        sample.DatabaseConnection connectNow = new sample.DatabaseConnection();
        Connection connectDB=connectNow.getConnection();

        String connectQuery="SELECT fFörfattare FROM artikel WHERE eFörfattare='Thomas'";

        try {
            Statement statement=connectDB.createStatement();
            ResultSet queryOutput=statement.executeQuery(connectQuery);
        while (queryOutput.next()){
            showUserNameLabel.setText(queryOutput.getString("fFörfattare"));
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;


    public void loginButtonOnAction(ActionEvent event){
        if(usernameTextField.getText().isBlank()==false && enterPasswordField.getText().isBlank()==false){
            validateLogin();
    }else{
            loginMessageLabel.setText("Please enter username and password");
        }
    }
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    public void validateLogin() {
        sample.DatabaseConnection connectNow = new sample.DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(3) FROM user_account_1 WHERE username ='" + usernameTextField.getText() + "'AND password ='" + enterPasswordField.getText()+"'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Congratulations!");
                } else {
                    loginMessageLabel.setText("Invalid login. Please try again");
                }
            }
        }
                catch(Exception e){
                    e.printStackTrace();
                    e.getCause();
                }

            }
        }
