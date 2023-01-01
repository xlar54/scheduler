package com.scheduler.app;

import com.scheduler.dao.AppointmentsDAO;
import com.scheduler.pojo.Appointment;
import com.scheduler.pojo.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;

public class App extends Application {

    public static User loggedInUser = null;

    public static void main (String[]args) throws Exception {

        nickstestcode();
        dadstestcode();
        launch();
    }
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

        public static void dadstestcode() throws Exception {


        }

    public static void nickstestcode() throws Exception{


    }
}