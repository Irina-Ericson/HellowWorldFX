package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.*;
import javafx.collections.ObservableArray;


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
    @FXML private TableView searchTable;
    @FXML private ObservableList<Book>getBook;
    private Object Book;
    @FXML private TableColumn <Book,String> colTitle;
    @FXML private TableColumn <Book,String> colName;
    @FXML private TableColumn <Book,String> colSurname;


    public class Book extends Object{
        String fNamn;
        String eNamn;
        String titel;

        public void setAll(TableColumn<Book, String> nameColumn, TableColumn<Book, String> surnameColumn, TableColumn<Book, String> titelColumn) {
        }

        public String getfNamn() {
            return fNamn;
        }

        public void setfNamn(String fNamn) {
            this.fNamn = fNamn;
        }

        public String geteNamn() {
            return eNamn;
        }

        public void seteNamn(String eNamn) {
            this.eNamn = eNamn;
        }

        public String getTitel() {
            return titel;
        }

        public void setTitel(String titel) {
            this.titel = titel;
        }
    }

    @FXML public TableView<Book> searchResultTable;

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

    public void goBackButtonOnAction(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root, 600, 475));
        window.show();


    }

    public void searchButtonOnAction(ActionEvent event) throws Exception {
        sample.DatabaseConnection connectNow = new sample.DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String searchResource = "select Titel,eFörfattare,fFörfattare from artikel\n" +
                "where Titel like '" + searchField.getText() + "'";
        private void populateTable(ObservableList<Book>bookList){
            searchTable.setItems(booklist);

        }

        private void initialize() throw Exception{
            colTitle.setCellValueFactory(cellData ->cellData.getValue().getTitel.asObject());
            colName.setCellValueFactory(cellData ->cellData.getValue().getfNamn.asObject());
            colSurname.setCellValueFactory(cellData ->cellData.getValue().geteNamn.asObject());
            ObservableList<Book>bookList=Book.getAllRecords();
            populateTable(bookList);
        }

            try {
                Statement statement = connectDB.createStatement();
                ResultSet queryResult = statement.executeQuery(searchResource);
                ObservableList<Book> bookList = FXCollections.observableArrayList();

                while (queryResult.next()) {
                    Book book=new Book();
                    book.setTitel(queryResult.getString("Titel"));
                    book.setfNamn(queryResult.getString("Namn"));
                    book.seteNamn(queryResult.getString("Efternamn"));
                    bookList.add(book);}
                return bookList;}
                    //TableColumn<Book, String> nameColumn = new TableColumn<> ("Namn");
                   //nameColumn.setCellValueFactory(new PropertyValueFactory <>("Namn"));
                   //TableColumn<Book, String> surnameColumn= new TableColumn<> ("Efternamn");
                   //surnameColumn.setCellValueFactory(new PropertyValueFactory <>("Efternamn"));
                   //TableColumn<Book, String> titelColumn= new TableColumn<> ("Titel");
                   //titelColumn.setCellValueFactory(new PropertyValueFactory <>("Titel"));
                   // searchTable.getColumns().setAll(nameColumn, surnameColumn, titelColumn);
                    //FilteredList<Book>filteredData=new FilteredList<>(dataList, b ->true);

                   // searchField.textProperty().addListener((observable,oldValue,newValue)->{
                       // filteredData.setPredicate(book1 -> {
                          //  if (newValue == null || newValue.isEmpty()) {
                            //    return true;
                          //  }
                         /*   String lowerCaseFilter=newValue.toLowerCase();
                            if(book1.getfNamn().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                                return true;
                            }
                            if(book1.geteNamn().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                                return true;}
                            if(book1.getTitel().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                                return true;}
                            else
                        return false;
                        });
                    });

                  nameColumn.setText(queryResult.getString("eFörfattare"));
                  surnameColumn.setText(queryResult.getString("fFörfattare"));
                  titelColumn.setText(queryResult.getString("Titel"));

            SortedList<Book>searchData=new SortedList<>(filteredData);
            searchData.comparatorProperty().bind(searchTable.comparatorProperty());
            searchTable.setItems(searchData);}*/}}

            catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

        }}








