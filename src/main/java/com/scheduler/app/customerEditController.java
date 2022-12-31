package com.scheduler.app;

import com.scheduler.dao.CustomerDAO;
import com.scheduler.pojo.Customer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;




public class customerEditController implements Initializable {
    @FXML
    private TableView customersTableView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn customerIDCol       = new TableColumn("ID");
        customerIDCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("Customer_ID"));

        TableColumn customerNameCol     = new TableColumn("Name");
        customerNameCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("Customer_name"));

        TableColumn customerAddressCol  = new TableColumn("Address");
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("Address"));

        TableColumn customerPostalCol   = new TableColumn("Postal Code");
        customerPostalCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("Postal_code"));

        TableColumn customerPhoneCol    = new TableColumn("Phone");
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("Phone"));

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
        ObservableList<Customer> customers = customerDAO.getCustomerList();

        customersTableView.getItems().addAll(customers);

    }
}
