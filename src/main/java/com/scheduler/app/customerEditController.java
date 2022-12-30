package com.scheduler.app;

import com.scheduler.dao.CustomerDAO;
import com.scheduler.pojo.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;




public class customerEditController implements Initializable {
    @FXML
    private TableView customersTableView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn customerIDCol       = new TableColumn("ID");
        TableColumn customerNameCol     = new TableColumn("Name");
        TableColumn customerAddressCol  = new TableColumn("Address");
        TableColumn customerPostalCol   = new TableColumn("Postal Code");
        TableColumn customerPhoneCol    = new TableColumn("Phone");

        customersTableView.getColumns().addAll(customerIDCol,customerNameCol,customerAddressCol,
                customerPostalCol, customerPhoneCol);

        try {
            refreshTableViewData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void refreshTableViewData() throws Exception {

        CustomerDAO customerDAO = new CustomerDAO();
        ArrayList<Customer> customers = customerDAO.getCustomerList();

    }
}
