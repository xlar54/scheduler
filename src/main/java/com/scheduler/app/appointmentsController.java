package com.scheduler.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * appointments controller is a controller class
 * that creates the FXML for the appointments
 * in the application
 */
public class appointmentsController implements Initializable {

    @FXML
    private TableView appointmentsTableView;

    /**
     * initialize sets up the tablecolumn objects
     * for the tableview to display
     * @param location  javafx parameter
     * @param resources javafx parameter
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn appointmentIdCol    = new TableColumn("ID");
        TableColumn titleCol            = new TableColumn("Title");
        TableColumn descriptionCol      = new TableColumn("Description");

        appointmentsTableView.getColumns().addAll(appointmentIdCol,titleCol,descriptionCol);
        appointmentsTableView.refresh();

    }
}