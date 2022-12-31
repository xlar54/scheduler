package com.scheduler.app;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javafx.scene.control.TableView;

import com.scheduler.pojo.*;
import com.scheduler.dao.UserDAO;

public class loginController implements Initializable {
    @FXML
    Button signInButton;
    @FXML
    TextField userNameTextField;
    @FXML
    TextField passwordTextField;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    int setLanguage()
    {
        String x = System.getProperty("user.language");
        int y =0;
        if (x.equals("fr"))
        {
            french = true;

            CustomerLogin.setText(Translation.getInstance()
                    .getText(Translation.Language.FRENCH, Translation.LanguageKey.LOGIN_FORM_HEADER));
            userNameLabel.setText(Translation.getInstance()
                    .getText(Translation.Language.FRENCH, Translation.LanguageKey.USERNAME_LABEL));
            passWordLabel.setText(Translation.getInstance()
                    .getText(Translation.Language.FRENCH, Translation.LanguageKey.PASSWORD_LABEL));
            signInButton.setText(Translation.getInstance()
                    .getText(Translation.Language.FRENCH, Translation.LanguageKey.SIGN_IN_BUTTON));

            y= 1;

        }
        return y;
    }

    @FXML 
    void signInHandler(ActionEvent event) throws Exception {

        String enteredUsername = userNameTextField.getText();
        String enteredPassword = passwordTextField.getText();

        User user = new User();
        UserDAO userDAO = new UserDAO();
        user = userDAO.getUserByUsernamePassword(enteredUsername, enteredPassword);

        if (user != null)
        {

            Alert a = new Alert(AlertType.CONFIRMATION);
            TimeZone obj = TimeZone.getDefault();
            String name = obj.getID();
            zoneID.setText(name);

            if(french)
                a.setContentText(Translation.getInstance()
                        .getText(Translation.Language.FRENCH, Translation.LanguageKey.LOGIN_SUCCESSFUL));
            else
                a.setContentText(Translation.getInstance()
                        .getText(Translation.Language.ENGLISH, Translation.LanguageKey.LOGIN_SUCCESSFUL));

            a.showAndWait();

        }
        else
        {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText(Translation.getInstance()
                    .getText(Translation.Language.ENGLISH, Translation.LanguageKey.LOGIN_TRY_AGAIN));
            a.showAndWait();
        }

        Stage stage = (Stage) signInButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/customerEdit.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 500, 640);
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Schedule Application");

        // center on screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.setScene(scene);

    }



    @FXML 
    void signUpHandler(ActionEvent event)
    {
        String passWord = passwordTextField.getText();
        String userName = userNameTextField.getText();
    }
}
    