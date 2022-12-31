package com.scheduler.app;

import com.scheduler.dao.CustomerDAO;
import com.scheduler.pojo.CustomerRow;
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
    private TableView<CustomerRow> customersTableView;


    @Override
    @SuppressWarnings("unchecked")
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn<CustomerRow, Integer> customerIDCol       = new TableColumn<CustomerRow, Integer>("ID");
        customerIDCol.setCellValueFactory(new PropertyValueFactory<CustomerRow, Integer>("Customer_ID"));

        TableColumn<CustomerRow, String> customerNameCol     = new TableColumn<CustomerRow, String>("Name");
        customerNameCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,String>("Customer_name"));

        TableColumn<CustomerRow, String> customerAddressCol  = new TableColumn<>("Address");
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,String>("Address"));

        TableColumn<CustomerRow, String> customerPostalCol   = new TableColumn<>("Postal Code");
        customerPostalCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,String>("Postal_code"));

        TableColumn<CustomerRow, String> customerPhoneCol    = new TableColumn<CustomerRow, String>("Phone");
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,String>("Phone"));

        TableColumn<CustomerRow, String> customerDivisionCol    = new TableColumn<CustomerRow, String>("Division");
        customerDivisionCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,String>("Division"));

        TableColumn<CustomerRow, String> customerCountryCol    = new TableColumn<CustomerRow, String>("Country");
        customerCountryCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,String>("Country"));


        customersTableView.getColumns().addAll(customerIDCol,customerNameCol,customerAddressCol,
                customerPostalCol, customerPhoneCol, customerDivisionCol, customerCountryCol);

        try {
            refreshTableViewData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void refreshTableViewData() throws Exception {

        CustomerDAO customerDAO = new CustomerDAO();
        ObservableList<CustomerRow> customers = customerDAO.getCustomerRows();

        customersTableView.getItems().addAll(customers);

    }
}
