package com.scheduler.app;

import com.scheduler.dao.CustomerDAO;
import com.scheduler.pojo.Customer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;




public class customerEditController implements Initializable {
    public ComboBox cboDivision;
    public ComboBox cboCountry;
    public TextField txtCustomerName;
    public Label lblCustomerID;
    public TextField txtAddress;
    public TextField txtPostalCode;
    public TextField txtPhoneNumber;
    public Button btnSave;
    public Button btnDelete;
    public Label lblMessage;
    @FXML
    private TableView<Customer> customersTableView;


    @Override
    @SuppressWarnings("unchecked")
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn<Customer, Integer> customerIDCol       = new TableColumn<Customer, Integer>("ID");
        customerIDCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("Customer_ID"));

        TableColumn<Customer, String> customerNameCol     = new TableColumn<Customer, String>("Name");
        customerNameCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("Customer_name"));

        TableColumn<Customer, String> customerAddressCol  = new TableColumn<>("Address");
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("Address"));

        TableColumn<Customer, String> customerPostalCol   = new TableColumn<>("Postal Code");
        customerPostalCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("Postal_code"));

        TableColumn<Customer, String> customerPhoneCol    = new TableColumn<Customer, String>("Phone");
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
