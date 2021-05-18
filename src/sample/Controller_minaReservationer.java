package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller_minaReservationer {
    @FXML
    private Label eNamn;
    @FXML
    private Label fNamn;
    @FXML
    private Label titel;
    @FXML
    private Button showReservationerButton;
    @FXML
    private Button backToMinProfilButton;
    public void showReservationerButtonOnAction(ActionEvent event) {
        sample.DatabaseConnection connectNow = new sample.DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "select eFörfattare, fFörfattare, Titel, Utgivningsår\n" +
                "from reservation a,artikel b, user_account_2 c\n" +
                "where a.ISBN=b.ISBN and a.Lånekortsnummer=c.Lånekortsnummer";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);
            while (queryOutput.next()) {
                // String lastName=queryOutput.getString("eFörfattare");
                //  String firstName=queryOutput.getString("fFörfattare");
                // String titel=queryOutput.getString("Titel");
                //  String year=queryOutput.getString("Utgivningsår");
                eNamn.setText(queryOutput.getString("eFörfattare"));
                fNamn.setText(queryOutput.getString("fFörfattare"));
                titel.setText(queryOutput.getString("Titel"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public void backToMinProfilButtonOnAction (ActionEvent event) throws Exception{

            Parent root = FXMLLoader.load(getClass().getResource("minProfil.fxml"));
            Stage window = (Stage) backToMinProfilButton.getScene().getWindow();
            window.setScene(new Scene(root, 600, 475));
            window.show();


        }

    }

