package com.scheduler.app;

import com.scheduler.dao.AppointmentsDAO;
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
import java.util.ResourceBundle;


public class appointmentsEditController implements Initializable {


    @FXML
    private TableView<CustomerRow> appointmentsTableView;
    @FXML
    Button btnSave;
    @FXML
    Button btnDelete;
    @FXML
    Button btnNew;
    @FXML
    Label lblAppointmentID;
    @FXML
    TextField txtTitle;
    @FXML
    TextField txtDescription;
    @FXML
    TextField txtLocation;
    @FXML
    TextField txtCustomerID;
    @FXML
    ComboBox cboContacts;
    @FXML
    TextField txtType;
    @FXML
    TextField txtStart;
    @FXML
    TextField txtEnd;
    @FXML
    TextField txtUserID;
    @FXML
    Label lblMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lblMessage.setText("");

        initTableView();

        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    saveAppointment();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    deleteAppointment();
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


        try {
            refreshTableViewData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void initTableView() {

        TableColumn<CustomerRow, Integer> apptIDCol       = new TableColumn<CustomerRow, Integer>("Appointment ID");
        apptIDCol.setCellValueFactory(new PropertyValueFactory<CustomerRow, Integer>("Appointment_ID"));

        TableColumn<CustomerRow, String> titleCol     = new TableColumn<CustomerRow, String>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,String>("Title"));

        TableColumn<CustomerRow, String> descCol  = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,String>("Description"));

        TableColumn<CustomerRow, String> locationCol   = new TableColumn<>("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,String>("Location"));

        TableColumn<CustomerRow, String> contactCol    = new TableColumn<CustomerRow, String>("Contact");
        contactCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,String>("Contact"));

        TableColumn<CustomerRow, Integer> startCol = new TableColumn<CustomerRow, Integer>("Start Date/Time");
        startCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,Integer>("Start"));

        TableColumn<CustomerRow, String> endCol    = new TableColumn<CustomerRow, String>("End Date/Time");
        endCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,String>("End"));

        TableColumn<CustomerRow, Integer> customerIDCol = new TableColumn<CustomerRow, Integer>("Customer ID");
        customerIDCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,Integer>("Customer_ID"));

        TableColumn<CustomerRow, String> userIDCol    = new TableColumn<CustomerRow, String>("User ID");
        userIDCol.setCellValueFactory(new PropertyValueFactory<CustomerRow,String>("User_ID"));

        appointmentsTableView.getColumns().addAll(apptIDCol,titleCol,descCol,
                locationCol, contactCol, startCol, endCol, customerIDCol, userIDCol );

        // Here is a lambda method as requested on document
        appointmentsTableView.setOnMouseClicked((MouseEvent event) -> {
            try {
                onAppointmentTableViewSelectedRow();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void refreshTableViewData() throws Exception {

        appointmentsTableView.getItems().clear();

        //CustomerDAO customerDAO = new CustomerDAO();
        //ObservableList<CustomerRow> customers = customerDAO.getCustomerRows();

        //appointmentsTableView.getItems().addAll(customers);

    }

    public void onAppointmentTableViewSelectedRow() throws Exception {
/*
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
        }*/
    }

    void clearFormData() throws Exception {

        lblAppointmentID.setText("New");
        txtTitle.setText("");
        txtDescription.setText("");
        txtLocation.setText("");
        txtCustomerID.setText("");

        //initContactComboBox();
        cboContacts.getItems().clear();
        cboContacts.valueProperty().set(null);

    }
    void saveAppointment() throws Exception {

        if (validateInput() == false)
            return;

        AppointmentsDAO apptDAO = new AppointmentsDAO();

        if(lblAppointmentID.getText().equals("New")) {
            // this is an insert
            //apptDAO.insert(txtCustomerName.getText(), txtAddress.getText(),
            //        txtPostalCode.getText(), txtPhoneNumber.getText(), divisionId, App.loggedInUser.getUser_name());
        }
        else {
            // this is an update
            //apptDAO.update(Integer.parseInt(lblAppointmentID.getText()),txtTitle.getText(), txtDescription.getText(),
            //        txtLocation.getText(), txtType.getText(), txtStart.getText(), txtEnd.getText(), App.loggedInUser.getUser_name());
        }
        refreshTableViewData();
        clearFormData();
    }

    void deleteAppointment() throws Exception {

        if (!lblAppointmentID.getText().equals("New")) {

            AppointmentsDAO apptDAO = new AppointmentsDAO();
            int count = apptDAO.delete(Integer.parseInt(lblAppointmentID.getText()));

            if(count == 1)
                lblMessage.setText("Customer record deleted");

            refreshTableViewData();
            clearFormData();
        }
    }

    boolean validateInput() {

        if(txtTitle.getText().equals("")) {
            lblMessage.setText("Error: Title is required.");
            return false;
        }

        if(txtDescription.getText().equals("")) {
            lblMessage.setText("Error: Description is required.");
            return false;
        }

        if(txtLocation.getText().equals("")) {
            lblMessage.setText("Error: Location is required.");
            return false;
        }

        if(txtCustomerID.getText().equals("")) {
            lblMessage.setText("Error: Customer ID is required.");
            return false;
        }

        if(txtUserID.getText().equals("")) {
            lblMessage.setText("Error: User ID is required.");
            return false;
        }

        lblMessage.setText("");
        return true;

    }
}
