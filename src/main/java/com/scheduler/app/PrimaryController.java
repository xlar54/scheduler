package com.scheduler.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.sql.*;

import java.io.IOException;
import java.util.TimeZone;
import java.sql.Connection;
import java.sql.ResultSet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;

import javafx.util.Callback;

import com.scheduler.pojo.*;
import com.scheduler.dao.UserDAO;

public class PrimaryController {
    @FXML
    Button signIn;
    @FXML
    TextField userNameField;
    @FXML
    TextField passwordField;
    static final String DB_URL = "jdbc:mysql://localhost:3306/scheduler";
    static final String USER = "scheduleruser";
    static final String PASS = "freddy!@";
    @FXML
    Label zoneID = new Label();
    @FXML
    Label userNameLabel = new Label();
    @FXML
    Label passWordLabel = new Label();
    @FXML
    Label CustomerLogin = new Label();
    Boolean french = false;



    @FXML
    TableView customerData = new TableView();

    @FXML
    ObservableList<ObservableList> data;
    @FXML
    Label col1Row1 = new Label();
    @FXML
    Label col1Row2 = new Label();
    @FXML
    Label col1Row3 = new Label();
    @FXML
    Label col1Row4 = new Label();
    @FXML
    Label col1Row5 = new Label();
    @FXML
    Label col1Row6 = new Label();
    @FXML
    Label col1Row7 = new Label();

    @FXML
    Label col1Row8 = new Label();



    @FXML
    Label col2Row1 = new Label();
    @FXML
    Label col2Row2 = new Label();
    @FXML
    Label col2Row3 = new Label();
    @FXML
    Label col2Row4 = new Label();
    @FXML
    Label col2Row5 = new Label();
    @FXML
    Label col2Row6 = new Label();
    @FXML
    Label col2Row7 = new Label();
    @FXML
    Label col2Row8 = new Label();



    @FXML
    TextField c1R1 = new TextField();
    @FXML
    TextField c1R2 = new TextField();
    @FXML
    TextField c1R3= new TextField();
    @FXML
    TextField c1R4 = new TextField();
    @FXML
    TextField c1R5 = new TextField();
    @FXML
    TextField c1R6 = new TextField();
    @FXML
    TextField c1R7 = new TextField();

    @FXML
    TextField c1R8 = new TextField();
    @FXML
    TextField c2R1 = new TextField();
    @FXML
    TextField c2R2 = new TextField();
    @FXML
    TextField c2R3 = new TextField();
    @FXML
    TextField c2R4 = new TextField();
    @FXML
    TextField c2R5 = new TextField();
    @FXML
    TextField c2R6 = new TextField();
    @FXML
    TextField c2R7 = new TextField();
    @FXML
    TextField c2R8 = new TextField();




    @FXML
    Button addButton = new Button();
    @FXML
    Button updateButton =new Button();
    @FXML
    Button deleteButton =new Button();


    int setLanguage()
    {
        String x = System.getProperty("user.language");
        int y =0;
        if (x.equals("fr"))
        {

            signIn.setText("Se connecter");
            userNameLabel.setText("Nom d'utilisateur");
            passWordLabel.setText("le mot de passe");
            CustomerLogin.setText("Connexion client");
            french = true; 
            y= 1;

        }
        return y;
    }

    @FXML 
    void signInHandler(ActionEvent event) throws Exception {

        String enteredUsername = userNameField.getText();
        String enteredPassword = passwordField.getText();

        User user = new User();
        UserDAO userDAO = new UserDAO();
        user = userDAO.getUserByUsernamePassword(enteredUsername, enteredPassword);

        if (user != null && french)
        {

            Alert a = new Alert(AlertType.CONFIRMATION);
            TimeZone obj = TimeZone.getDefault();
            String name = obj.getID();
            zoneID.setText(name);
            a.setContentText("connexion réussie");
            Translation trans = new Translation();
            String text = trans.getText(Translation.Language.FRENCH, Translation.LanguageKey.LOGIN_FORM_HEADER);
            a.showAndWait();

        }
        else if (user != null)
        {

            Alert a = new Alert(AlertType.CONFIRMATION);
            TimeZone obj = TimeZone.getDefault();
            String name = obj.getID();
            zoneID.setText(name);
            a.setContentText("Login Successful");
            a.showAndWait();

        }
        else
        {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Username or password not found. Try again.");
            a.showAndWait();
        }
        Window window = signIn.getScene().getWindow();
        Stage stage = (Stage) signIn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/secondary.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 640);
        stage.setTitle("Schedule Application");

        stage.setScene(scene);
        buildData();
    }

    public void buildData(){

        data = FXCollections.observableArrayList();
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from appointments";
            //ResultSet
            ResultSet rs = conn.createStatement().executeQuery(SQL);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                customerData.getColumns().addAll(col);
               // System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
              //  System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            customerData.setItems(data);
        }catch(Exception e){
            e.printStackTrace();
            //System.out.println("Error on Building Data");
        }
    }

    @FXML 
    void signUpHandler(ActionEvent event)
    {
        String passWord = passwordField.getText();
        String userName = userNameField.getText();
    }
}
    