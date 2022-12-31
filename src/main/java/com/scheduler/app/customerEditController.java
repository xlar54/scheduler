package com.scheduler.app;

import com.scheduler.dao.CountryDAO;
import com.scheduler.dao.CustomerDAO;
import com.scheduler.dao.FirstLevelDivisionDAO;
import com.scheduler.pojo.Country;
import com.scheduler.pojo.CustomerRow;
import com.scheduler.pojo.FirstLevelDivision;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
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
        public void initialize(URL location, ResourceBundle resources) {

        initTableView();

        try {
            initCountryComboBox();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            refreshTableViewData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @SuppressWarnings("unchecked")
    private void initTableView() {

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

        // Here is a lambda method as requested on document
        customersTableView.setOnMouseClicked((MouseEvent event) -> {
            onCustomerTableViewSelectedRow();
        });
    }

    /// Initializes the Country combo box from SQl and
    /// sets the listeners for change events
    private void initCountryComboBox() throws Exception {

        cboCountry.getItems().clear();

        CountryDAO countryDAO = new CountryDAO();
        ArrayList<Country> countries = countryDAO.getAll();

        for (Country country : countries) cboCountry.getItems().add(country.getCountry());

        // create handler for change to country combobox
        cboCountry.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String selectedCountry = (String) cboCountry.getValue();
                try {
                    onCountryComboBoxChange(selectedCountry);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void onCountryComboBoxChange(String selectedCountry) throws Exception {

        // loads the divisions when the country combobox is changed
        cboDivision.getItems().clear();

        FirstLevelDivisionDAO fldDAO = new FirstLevelDivisionDAO();
        ArrayList<FirstLevelDivision> flds = fldDAO.getByCountryName(selectedCountry);

        for (FirstLevelDivision fld : flds) cboDivision.getItems().add(fld.getDivision());

    }

    private void refreshTableViewData() throws Exception {

        customersTableView.getItems().clear();

        CustomerDAO customerDAO = new CustomerDAO();
        ObservableList<CustomerRow> customers = customerDAO.getCustomerRows();

        customersTableView.getItems().addAll(customers);

    }

    public void onCustomerTableViewSelectedRow() {

        if (customersTableView.getSelectionModel().getSelectedItem() != null) {

            // Get selected row
            CustomerRow selectedRow = customersTableView.getSelectionModel().getSelectedItem();

            // populate the UI fields
            lblCustomerID.setText(String.valueOf(selectedRow.getCustomer_ID()));
            txtCustomerName.setText(selectedRow.getCustomer_name());
            txtAddress.setText(selectedRow.getAddress());
            txtPhoneNumber.setText(selectedRow.getPhone());
            txtPostalCode.setText(selectedRow.getPostal_code());

            // set the comboboxes
            cboCountry.setValue((String)selectedRow.getCountry());
            cboDivision.setValue((String)selectedRow.getDivision());
        }
    }
}
