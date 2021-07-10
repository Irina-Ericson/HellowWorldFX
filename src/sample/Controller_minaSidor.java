package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Controller_minaSidor {
    @FXML
    private Button backButton;
    @FXML
    private Button minProfilButton;
    @FXML
    private Button minaReservationerButton;
    @FXML private Button manageArticleButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchField;
    @FXML
    private TableView searchTable;

    @FXML
    private TableColumn<Book, String> colTitle;
    @FXML
    private TableColumn<Book, String> colName;
    @FXML
    private TableColumn<Book, String> colSurname;
    @FXML
    private TableColumn colBorrow;
    @FXML
    Button borrowButton;


    public void searchButtonOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {


        sample.DatabaseConnection connectNow = new sample.DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String searchResource = "select Titel,eFörfattare,fFörfattare from artikel\n" +
                "where Titel like '" + searchField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(searchResource);
            ObservableList<Book> bookList = FXCollections.observableArrayList();
            {
                while (queryResult.next()) {
                    Book book1 = new Book();
                    bookList.add(book1);
                    book1.setTitel(queryResult.getString("Titel"));
                    book1.setENamn(queryResult.getString("eFörfattare"));
                    book1.setFNamn(queryResult.getString("fFörfattare"));

                    colName.setCellValueFactory(new PropertyValueFactory<Book, String>("fNamn"));
                    colTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("titel"));
                    colSurname.setCellValueFactory(new PropertyValueFactory<Book, String>("eNamn"));

                    //create a cell factory to insert a button in every row
                    Callback<TableColumn<Book, String>, TableCell<Book, String>> cellFactory = (param) -> {
                        final TableCell<Book, String> cell = new TableCell<Book, String>() {
                            //override updateItem method
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                //ensure that cell is created only on non-empty rows
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {

                                    //Now we can create the action button
                                    final Button borrowButton = new Button("Låna");
                                    //attach listener on button, what to do when clicked
                                    borrowButton.setOnAction(event -> {
                                        //Make a DB connection

                                        sample.DatabaseConnection connectNow = new sample.DatabaseConnection();
                                        Connection connectDB = connectNow.getConnection();
                                        LocalDate date = LocalDate.now();
                                        LocalDate returnDate = LocalDate.now().plusMonths(1);
                                        Book c = getTableView().getItems().get(getIndex());
                                        //int barcode=c.getIdArtikelBarcode();
                                        String getBarcode = "Select idArtikelBarcode from Artikel where Titel='" + c.getTitel() + "'";
                                        //extract the clicked Book object
                                        try {
                                            Statement st = connectDB.createStatement();
                                            ResultSet rs = st.executeQuery(getBarcode);
                                            while (rs.next()) {

                                                //String str = rs.getString("idArtikelBarcode");
                                                int barcode = rs.getInt("idArtikelBarcode");
                                                String borrowBook = "insert into lånetillfälle(Lånedatum,idArtikelBarcode,Lånekortsnummer,Returdatum)" +
                                                        "values('" + date + "','" + barcode + "','5','" + returnDate + "')";
                                                Statement stat = connectDB.createStatement();
                                                int rows = stat.executeUpdate(borrowBook);
                                            }

                                        } catch (SQLException throwables) {
                                            throwables.printStackTrace();
                                        }


                                        Book b = getTableView().getItems().get(getIndex());
                                        //lets show which item has been selected
                                        LocalDate backDate = LocalDate.now().plusMonths(1);
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setContentText("Boken \n " + b.getTitel() + " av författare \n " + b.getENamn() + " " + b.getFNamn() +
                                                " är nu utlånad till " + backDate);
                                        alert.show();

                                    });
                                    //set the button
                                    setGraphic(borrowButton);
                                    setText(null);
                                }

                            }

                        };
                        return cell;
                    };
//set the custom factory to action column
                    colBorrow.setCellFactory(cellFactory);
                    searchTable.setItems(bookList);
                    searchTable.setVisible(true);

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void goBackButtonOnAction(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root, 600, 475));
        window.show();


    }

    public void minProfilButtonOnAction(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("minProfil.fxml"));
        Stage window = (Stage) minProfilButton.getScene().getWindow();
        window.setScene(new Scene(root, 600, 475));
        window.show();
    }

    public void minaReservationerOnAction(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("minaReservationer.fxml"));
        Stage window = (Stage) minaReservationerButton.getScene().getWindow();
        window.setScene(new Scene(root, 600, 475));
        window.show();
    }

    public void manageArticleButtonOnAction(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Artikel.fxml"));
        Stage window = (Stage) manageArticleButton.getScene().getWindow();
        window.setScene(new Scene(root, 800, 675));
        window.show();

    }

    public void borrowButtonOnAction(ActionEvent event) throws Exception {
    }

    public void logOutButtonOnAction (ActionEvent event) throws Exception {
    }


    // String borrowBook = "update lånetillfälle set idArtikelBarcode=5 where Lånekortsnummer=1";

}