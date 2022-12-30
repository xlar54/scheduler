package com.scheduler.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import com.scheduler.pojo.*;
import com.scheduler.dao.*;
import com.scheduler.dao.ContactsDAO;

public class App extends Application {


    public void start(Stage stage) throws IOException
    {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/primary.fxml"));
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

    }



        public static void main (String[]args) throws Exception {

            nickstestcode();
            dadstestcode();
            launch();
        }

        public static void dadstestcode() throws Exception {

            // this is just a temp function to test code.  delete when done

            //CountryDAO countryDAO = new CountryDAO();
            //countryDAO.insert("Spain", "scott");

            /*Countries country = new Countries();
            CountryDAO countryDAO = new CountryDAO();
            country = countryDAO.getCountry(1);

            System.out.println(country.getCountry());*/

            FileLogger f = FileLogger.getInstance();
            f.info("hello");

            //UserDAO userDAO = new UserDAO();
            //int count = userDAO.delete(3);
            //System.out.println(count);
        }

    public static void nickstestcode() throws Exception {

        ContactsDAO contactsDAO = new ContactsDAO();
        contactsDAO.update(3, "nickybae", "email.address@.com");

    }
}