package com.scheduler.app;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TimeZone;

import com.scheduler.pojo.*;
import com.scheduler.dao.UserDAO;
import javafx.stage.WindowEvent;

/***
 *  The main login controller and menu selection form
 */
public class loginController implements Initializable {
    @FXML
    Button btnSignIn;
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtPassword;
    @FXML
    Label zoneID = new Label();
    @FXML
    Label userNameLabel = new Label();
    @FXML
    Label passWordLabel = new Label();
    @FXML
    Label CustomerLogin = new Label();

    @FXML
    Button btnEditCustomers;
    @FXML
    Button btnEditAppointments;
    Boolean french = false;

    /***
     * The override function which gets called when the controller starts
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(App.loggedInUser != null)
            enableMenu();
        else
            enableLogin();

        btnSignIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    btnSignInHandler(event);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnEditCustomers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    openCustomerEditForm();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnEditAppointments.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    openAppointmentsEditForm();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    /***
     * Code which sets the text label values based on the users locale
     * @return
     */
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
            btnSignIn.setText(Translation.getInstance()
                    .getText(Translation.Language.FRENCH, Translation.LanguageKey.SIGN_IN_BUTTON));

            y= 1;

        }
        return y;
    }

    /***
     * The sign in button handler
     * It will check the user login info against the database Users table,
     * set the global logged in user, and address the french/english requirement
     * based on the users machines locale
     * @param event
     * @throws Exception
     */
    @FXML 
    void btnSignInHandler(ActionEvent event) throws Exception {

        String enteredUsername = txtUsername.getText();
        String enteredPassword = txtPassword.getText();

        User user = new User();
        UserDAO userDAO = new UserDAO();
        user = userDAO.getUserByUsernamePassword(enteredUsername, enteredPassword);

        if (user != null)
        {
            // establish global logged in user
            App.loggedInUser = user;

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

            btnEditCustomers.setVisible(true);
            btnEditAppointments.setVisible(true);

            userNameLabel.setVisible(false);
            passWordLabel.setVisible(false);
            txtUsername.setVisible(false);
            txtPassword.setVisible(false);
            btnSignIn.setVisible(false);

        }
        else
        {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText(Translation.getInstance()
                    .getText(Translation.Language.ENGLISH, Translation.LanguageKey.LOGIN_TRY_AGAIN));
            a.showAndWait();
        }
    }

    /***
     * The code called when the user presses the Edit Customers button
     * @throws IOException
     */
    void openCustomerEditForm() throws IOException {

        Stage stage = (Stage) btnSignIn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/customerEdit.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 500, 640);
        Scene scene = new Scene(fxmlLoader.load(), 800, 550);
        stage.setTitle("Schedule Application");

        // Code to catch closing the window and return to main menu
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
                    returnToLoginForm(we);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        // center on screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.setScene(scene);

    }

    void openAppointmentsEditForm() throws IOException {

        Stage stage = (Stage) btnSignIn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/appointmentsEdit.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 550);
        stage.setTitle("Schedule Application");

        // Code to catch closing the window and return to main menu
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
                    returnToLoginForm(we);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        // center on screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.setScene(scene);

    }

    /**
     * this code is attached to the other form Close Window events,
     * and will return to the main menu (login) form
     * @param event
     * @throws Exception
     */
    private void returnToLoginForm(Event event) throws Exception {

        Stage stage, stage1;
        String eventstring;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));
        stage = (Stage) btnEditCustomers.getScene().getWindow();

        // Execution Block
        eventstring = event.getEventType().toString();
        if ("ACTION".equals(eventstring)) {

            stage.close();
        } else if ("WINDOW_CLOSE_REQUEST".equals(eventstring)) {
            event.consume();
            stage = (Stage) event.getSource();
            stage.close();
        }

        Scene scene = new Scene(fxmlLoader.load(), 600, 250);

        stage1 = new Stage();
        stage1.setScene(scene);
        stage1.setTitle("Schedule Application");
        stage1.show();
    }

    /**
     *  Enables the menu buttons (disables the login form)
     */
    void enableMenu() {

        btnEditCustomers.setVisible(true);
        btnEditAppointments.setVisible(true);

        userNameLabel.setVisible(false);
        passWordLabel.setVisible(false);
        txtUsername.setVisible(false);
        txtPassword.setVisible(false);
        btnSignIn.setVisible(false);

    }

    /**
     *  Enables the login form (disables menu buttons)
     */
    void enableLogin() {

        btnEditCustomers.setVisible(false);
        btnEditAppointments.setVisible(false);

        userNameLabel.setVisible(true);
        passWordLabel.setVisible(true);
        txtUsername.setVisible(true);
        txtPassword.setVisible(true);
        btnSignIn.setVisible(true);

    }
}
    