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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;


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
    @FXML private Button searchButtonOnAction;

    @FXML
    private TableView searchTable;

    @FXML
    private TableColumn<Book, String> colTitle;
    @FXML
    private TableColumn<Book, String> colName;
    @FXML
    private TableColumn<Book, String> colSurname;
    @FXML private TableColumn colBorrow;
    @FXML Button borrowButton;
    @FXML Button regButton;

    public void regButtonOnAction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("User.fxml"));
        Stage window = (Stage) regButton.getScene().getWindow();
        window.setScene(new Scene(root, 600, 475));
        window.show();
    }

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

@FXML private void borrowButtonOnAction(ActionEvent event){}

    @FXML
    private void searchButtonOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {


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
                    book1.setIdArtikelBarcode(queryResult.getInt("idArtikelBarcode"));

                    colName.setCellValueFactory(new PropertyValueFactory<Book, String>("fNamn"));
                    colTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("titel"));
                    colSurname.setCellValueFactory(new PropertyValueFactory<Book, String>("eNamn"));

                    //create a cell factory to insert a button in every row
                   /* Callback<TableColumn<Book, String>, TableCell<Book, String>> cellFactory =(param)->{
                        final TableCell<Book,String> cell=new TableCell<Book,String>(){
                        //override updateItem method
                            @Override
                            public void updateItem(String item, boolean empty){
                                super.updateItem(item, empty);
                                //ensure that cell is creaated only on non-empty rows
                                if(empty){
                                    setGraphic(null);
                                    setText(null);
                                }
                                else{
                                    //Now we can create the action button
                                    final Button borrowButton=new Button("Låna");
                                    //attach listener on button, what to do when clicked
                                    borrowButton.setOnAction(event ->{
                                        //extract the clicked Book object
                                        Book b=getTableView().getItems().get(getIndex());
                                        //lets show which item has been selected
                                        Alert alert=new Alert(Alert.AlertType.INFORMATION);
                                        alert.setContentText("You have clicked \n " +b.getTitel()+"with författare \n "+b.getFNamn());
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
                    colBorrow.setCellFactory(cellFactory);*/
                    searchTable.setItems(bookList);
                    searchTable.setVisible(true);



                }

            }}
       catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
            throw e;
        }}}












