package com.scheduler.app;

import com.scheduler.dao.AppointmentsDAO;
import com.scheduler.pojo.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;

/**
 * Represents the main application class
 * this class contains main, start, and testcode methods
 *
 */
public class App extends Application {

    public static User loggedInUser = null;

    /**
     * main method used to initialize the testcode function
     * if there is any code to test
     * @param args arguments for the compiler at runtime
     * @throws Exception
     */
    public static void main (String[]args) throws Exception {
    testcode();
    }

    /**
     * start function is the javafx function that initializes the scene
     * also calls function setlanguage which changes based on user language setting
     * @param stage the javafx stage object
     * @throws IOException
     */
    public void start(Stage stage) throws IOException
    {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 250);
        stage.setTitle("Schedule Application");
        stage.setScene(scene);

        loginController controller = fxmlLoader.getController();
        int x = controller.setLanguage();

        if (x==1)
        {
            stage.setTitle("demande de calendrier");
        }
        stage.show();

    }

    /**
     * testcode method was used in the development of the project to
     * test one component at a time
     * @throws Exception generic exception in case testing errors for syntax, null pointer, etc
     */
    public static void testcode() throws Exception{


    }
}