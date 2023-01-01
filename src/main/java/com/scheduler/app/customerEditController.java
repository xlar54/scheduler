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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * customerEditController the controller class for editing customer information
 *
 */
public class customerEditController implements Initializable {
    public ComboBox<FirstLevelDivision> cboDivision;
    public ComboBox<Country> cboCountry;
    public TextField txtCustomerName;
    public Label lblCustomerID;
    public TextField txtAddress;
    public TextField txtPostalCode;
    public TextField txtPhoneNumber;
    public Button btnSave;
    public Button btnDelete;
    public Button btnNew;
    public Label lblMessage;

    int countryId, divisionId = 0;
    @FXML
    private TableView<CustomerRow> customersTableView;

    ChangeListener<Country> countryComboBoxChangeListener;
    ChangeListener<FirstLevelDivision> divisionComboBoxChangeListener;

    /**
     * initialize method uses the javafx api to create the scene
     * @param location javafx
     * @param resources javafx
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lblMessage.setText("");

        initTableView();

        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    saveCustomer();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    deleteCustomer();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    clearFormData();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        divisionComboBoxChangeListener = new ChangeListener<FirstLevelDivision>() {
            /**
             * changed function just gets division ID
             * @param observable value can be seen in gui
             * @param oldValue old value
             * @param newValue new value
             */
            @Override
            public void changed(ObservableValue<? extends FirstLevelDivision> observable, FirstLevelDivision oldValue, FirstLevelDivision newValue) {
                FirstLevelDivision firstLevelDivision = (FirstLevelDivision) cboDivision.getSelectionModel().getSelectedItem();

                if(firstLevelDivision != null) {
                    try {
                        divisionId = firstLevelDivision.getDivision_ID();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        cboDivision.valueProperty().addListener(divisionComboBoxChangeListener);

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

    /**
     * inittableview creates the table view
     * by adding a list of columns using factory methods
     */
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

        TableColumn<CustomerRow, Integer> customerDivisionIDCol = new TableColumn<CustomerRow, Integer>("Division ID");
        customerDivisionIDCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,Integer>("Division_ID"));

        TableColumn<CustomerRow, String> customerDivisionCol    = new TableColumn<CustomerRow, String>("Division");
        customerDivisionCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,String>("Division"));

        TableColumn<CustomerRow, Integer> customerCountryIDCol = new TableColumn<CustomerRow, Integer>("Country ID");
        customerCountryIDCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,Integer>("CountryID"));

        TableColumn<CustomerRow, String> customerCountryCol    = new TableColumn<CustomerRow, String>("Country");
        customerCountryCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,String>("Country"));

        customersTableView.getColumns().addAll(customerIDCol,customerNameCol,customerAddressCol,
                customerPostalCol, customerPhoneCol, customerDivisionCol, customerCountryCol);

        /**
         * @see lambda expression to capture mouse click
         */
        customersTableView.setOnMouseClicked((MouseEvent event) -> {
            try {
                onCustomerTableViewSelectedRow();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /// Initializes the Country combo box from SQl and
    /// sets the listeners for change events

    /**
     * initCountryComboBox sets up the combobox for the countries in the application
     * @throws Exception
     */
    private void initCountryComboBox() throws Exception {

        cboCountry.getItems().clear();

        CountryDAO countryDAO = new CountryDAO();
        ObservableList<Country> countries = countryDAO.getAll();
        cboCountry.setItems(countries);

        if(countryComboBoxChangeListener != null)
            cboCountry.valueProperty().removeListener(countryComboBoxChangeListener);

        this.countryComboBoxChangeListener = new ChangeListener<Country>() {
            @Override
            public void changed(ObservableValue<? extends Country> observable, Country oldValue, Country newValue) {
                Country selectedCountry = (Country) cboCountry.getSelectionModel().getSelectedItem();

                try {
                    // loads the divisions when the country combobox is changed
                    cboDivision.getItems().clear();

                    if(selectedCountry != null) {
                        FirstLevelDivisionDAO fldDAO = new FirstLevelDivisionDAO();
                        ObservableList<FirstLevelDivision> flds = fldDAO.getByCountryName(selectedCountry.getCountry());

                        cboDivision.setItems(flds);
                        cboDivision.valueProperty().set(null);
                        divisionId=0;
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        cboCountry.valueProperty().addListener(countryComboBoxChangeListener);
    }

    /**
     * refresh tableview takes the already created
     * tableview object and isolates the clear function
     * @throws Exception
     */
    private void refreshTableViewData() throws Exception {

        customersTableView.getItems().clear();

        CustomerDAO customerDAO = new CustomerDAO();
        ObservableList<CustomerRow> customers = customerDAO.getCustomerRows();

        customersTableView.getItems().addAll(customers);

    }

    /**
     * onCustomerTableViewSelectedRow allows the user to select
     * a row from the tableview and controls the behavior of the
     * ui fields and comboboxes
     *
     * @throws Exception
     */

    public void onCustomerTableViewSelectedRow() throws Exception {

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
            countryId = selectedRow.getCountryID();
            CountryDAO countryDAO = new CountryDAO();
            Country country = countryDAO.getByID(countryId);
            cboCountry.setValue(country);

            divisionId = selectedRow.getDivisionID();
            FirstLevelDivisionDAO flddao = new FirstLevelDivisionDAO();
            FirstLevelDivision fld = flddao.getByID(divisionId);
            cboDivision.setValue(fld);
        }
    }

    /**
     * clearFormData takes all label objects and
     * textfield objects and clears their text to isolate
     * the clear function
     * @throws Exception
     */
    void clearFormData() throws Exception {

        lblCustomerID.setText("New");
        txtCustomerName.setText("");
        txtAddress.setText("");
        txtPostalCode.setText("");
        txtPhoneNumber.setText("");

        initCountryComboBox();
        cboDivision.getItems().clear();
        cboDivision.valueProperty().set(null);

        divisionId = 0;
        countryId = 0;

    }

    /**
     * saveCustomer saves the customer in the database based
     * on user input whether new customer is created or
     * existing customer is updated
     * @throws Exception
     */
    void saveCustomer() throws Exception {

        if (validateInput() == false)
            return;

        CustomerDAO customerDAO = new CustomerDAO();

        if(lblCustomerID.getText().equals("New")) {
            // this is an insert
            customerDAO.insert(txtCustomerName.getText(), txtAddress.getText(),
                    txtPostalCode.getText(), txtPhoneNumber.getText(), divisionId, App.loggedInUser.getUser_name());
        }
        else {
            // this is an update
            customerDAO.update(Integer.parseInt(lblCustomerID.getText()),txtCustomerName.getText(), txtAddress.getText(),
                    txtPostalCode.getText(), txtPhoneNumber.getText(), divisionId, App.loggedInUser.getUser_name());
        }
        refreshTableViewData();
        clearFormData();
    }

    /**
     * deleteCustomer deletes the customer object from the database
     * and application
     * @throws Exception
     */
    void deleteCustomer() throws Exception {

        if (!lblCustomerID.getText().equals("New")) {

            CustomerDAO customerDAO = new CustomerDAO();
            int count = customerDAO.delete(Integer.parseInt(lblCustomerID.getText()));

            if(count == 1)
                lblMessage.setText("Customer record deleted");

            refreshTableViewData();
            clearFormData();

            //TODO: delete appointments as well for this customer
        }
    }

    /**
     * validateInput checks the textfields and labels to
     * make sure that user input is valid according to
     * the rubric requirements
     * @return this return is used to return the error messages
     * based on unhappy path user input
     */

    boolean validateInput() {

        if(txtCustomerName.getText().equals("")) {
            lblMessage.setText("Error: Customer Name is required.");
            return false;
        }

        if(txtAddress.getText().equals("")) {
            lblMessage.setText("Error: Address is required.");
            return false;
        }

        if(txtPhoneNumber.getText().equals("")) {
            lblMessage.setText("Error: Phone number is required.");
            return false;
        }

        if(txtPostalCode.getText().equals("")) {
            lblMessage.setText("Error: Postal code is required.");
            return false;
        }

        if(divisionId == 0) {
            lblMessage.setText("Error: Country/Division is required.");
            return false;
        }

        lblMessage.setText("");
        return true;

    }
}
