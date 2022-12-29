package com.scheduler.app;

import java.sql.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import com.scheduler.pojo.*;
import com.scheduler.dao.*;

public class App extends Application {


    static final String DB_URL = "jdbc:mysql://localhost:3306/schedular";
    static final String USER = "nicholas";
    static final String PASS = "Freddy12!@";
    static final String QUERY = "desc appointments";
    public void start(Stage stage) throws IOException
    {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("com.scheduler.primary.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 640);
        stage.setTitle("Schedule Application");
        stage.setScene(scene);
        PrimaryController controller = fxmlLoader.getController();
        int x = controller.setLanguage();

        if (x==1)
        {
            stage.setTitle("demande de calendrier");
        }
        stage.show();
        //connection to database
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);) {

             System.out.println("connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(System.getProperty("user.language"));

    }



        public static void main (String[]args) throws Exception {


            /*Countries country = new Countries();
            CountryDAO countryDAO = new CountryDAO();
            country = countryDAO.getCountry(1);

            System.out.println(country.getCountry());*/
            launch();
        }
    }