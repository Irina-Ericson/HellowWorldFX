package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.Statement;

public class Controller_Artikel {
    @FXML private TextField titel;
    @FXML private TextField stkod;
    @FXML private TextField författare;
    @FXML private TextField reg;
    @FXML private TextField isbn;
    @FXML private TextField klass;
    @FXML private TextField genre;
    @FXML private TextField proland;
    @FXML private TextField aldergr;
    @FXML private TextField actor;
    @FXML private TextField hyplnr;
    @FXML private ChoiceBox artikelCat;
    @FXML private Button sparabutton;
    @FXML private Button avbrytbutton;
    @FXML private Label varnLabel;

    public void Artiklar(ActionEvent event) throws Exception{
       /* if (surnameField.getText().isBlank() == false && telefonnummer.getText().isBlank() == false &&
                emailField.getText().isBlank() == false && postnummer.getText().isBlank() == false &&
                streetField.getText().isBlank() == false && cityField.getText().isBlank() == false &&
                passwordField.getText().isBlank() == false && catChoice.getItems().isEmpty() == false &&
                nameField.getText().isBlank() == false) {*/
            registerArtikel();


       /* } else {
            varnLabel.setText("Kontrollera att alla fält är ifyllda");
        }*/
    }

    public void registerArtikel() {
        sample.DatabaseConnection connectNow = new sample.DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String insertArtikel = "insert into artikel (Titel,fFörfattare, eFörfattare, ISBN, " +
                "Nyckelord, idArtikelKategori, tillgänglig, placering, Produktionsland, " +
                "Skådespelare, Kategori, Streckkod, regissör, genre, ageLimit) values " +
                "('" + titel.getText() + "','0','" + författare.getText() + "','" + isbn.getText() + "', " +
                "'" + klass.getText() + "',0, 0,'" + hyplnr.getText() + "','" + proland.getText() + "'," +
                " " + "'" + actor.getText() + "','" + artikelCat.getValue() + "', '" + stkod.getText() + "' , " +
                "'" + reg.getText() + "', '" + genre.getText() + "' , '" + aldergr.getText() + "')";

        try {
            Statement statement = connectDB.createStatement();
            int rows=statement.executeUpdate(insertArtikel);
            varnLabel.setText("Nu är artikeln registrerad");


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }





    public void avbryt(ActionEvent event) throws Exception{
        varnLabel.setText("Nu är registreringen avbruten");
    }
}
