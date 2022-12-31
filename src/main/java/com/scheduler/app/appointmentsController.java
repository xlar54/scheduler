package com.scheduler.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class appointmentsController implements Initializable {

    @FXML
    private TableView appointmentsTableView;

    @FXML
    private void switchToPrimary() throws IOException {
        //App.setRoot("primary");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn appointmentIdCol    = new TableColumn("ID");
        TableColumn titleCol            = new TableColumn("Title");
        TableColumn descriptionCol      = new TableColumn("Description");

        appointmentsTableView.getColumns().addAll(appointmentIdCol,titleCol,descriptionCol);
        appointmentsTableView.refresh();

    }
}