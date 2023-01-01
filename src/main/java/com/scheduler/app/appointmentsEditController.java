package com.scheduler.app;

import com.scheduler.dao.AppointmentsDAO;
import com.scheduler.dao.ContactsDAO;
import com.scheduler.dao.CountryDAO;
import com.scheduler.pojo.Appointment;
import com.scheduler.pojo.Contact;
import com.scheduler.pojo.Country;
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
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;


public class appointmentsEditController implements Initializable {


    @FXML
    private TableView<Appointment> appointmentsTableView;
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

        cboContacts.getItems().clear();

        ContactsDAO contactsDAO = new ContactsDAO();
        ObservableList<Contact> contacts = null;
        try {
            contacts = contactsDAO.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        cboContacts.setItems(contacts);

        try {
            refreshTableViewData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void initTableView() {

        TableColumn<Appointment, Integer> apptIDCol       = new TableColumn<Appointment, Integer>("Appointment ID");
        apptIDCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("Appointment_ID"));

        TableColumn<Appointment, String> titleCol     = new TableColumn<Appointment, String>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("Title"));

        TableColumn<Appointment, String> descCol  = new TableColumn<Appointment, String>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("Description"));

        TableColumn<Appointment, String> locationCol   = new TableColumn<Appointment,String>("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("Location"));

        TableColumn<Appointment, String> contactCol    = new TableColumn<Appointment, String>("Contact");
        contactCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("Contact_ID"));

        TableColumn<Appointment, Integer> startCol = new TableColumn<Appointment, Integer>("Start Date/Time");
        startCol.setCellValueFactory(new PropertyValueFactory<Appointment,Integer>("Start"));

        TableColumn<Appointment, String> endCol    = new TableColumn<Appointment, String>("End Date/Time");
        endCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("End"));

        TableColumn<Appointment, Integer> customerIDCol = new TableColumn<Appointment, Integer>("Customer ID");
        customerIDCol.setCellValueFactory(new PropertyValueFactory<Appointment,Integer>("Customer_ID"));

        TableColumn<Appointment, String> userIDCol    = new TableColumn<Appointment, String>("User ID");
        userIDCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("User_ID"));

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

        AppointmentsDAO apptDAO = new AppointmentsDAO();
        ObservableList<Appointment> appointments = apptDAO.getAll();

        appointmentsTableView.getItems().addAll(appointments);

    }

    public void onAppointmentTableViewSelectedRow() throws Exception {

        if (appointmentsTableView.getSelectionModel().getSelectedItem() != null) {

            // Get selected row
            Appointment selectedRow = appointmentsTableView.getSelectionModel().getSelectedItem();

            // populate the UI fields
            lblAppointmentID.setText(String.valueOf(selectedRow.getAppointment_ID()));
            txtTitle.setText(selectedRow.getTitle());
            txtDescription.setText(selectedRow.getDescription());
            txtLocation.setText(selectedRow.getLocation());
            txtCustomerID.setText(String.valueOf(selectedRow.getCustomer_ID()));
            txtType.setText(selectedRow.getType());
            txtStart.setText(convertDateToString((Date) selectedRow.getStart()));
            txtEnd.setText(convertDateToString((Date) selectedRow.getEnd()));
            txtUserID.setText(String.valueOf(selectedRow.getUser_ID()));

            ;
            // set the comboboxes
            int contactId = selectedRow.getContact_ID();
            ContactsDAO contactsDAO = new ContactsDAO();
            Contact contact = contactsDAO.getByID(contactId);
            cboContacts.setValue(contact);
        }
    }

    void clearFormData() throws Exception {

        lblAppointmentID.setText("New");
        txtTitle.setText("");
        txtDescription.setText("");
        txtLocation.setText("");
        txtCustomerID.setText("");
        txtType.setText("");
        txtStart.setText("");
        txtEnd.setText("");
        txtUserID.setText("");

        cboContacts.valueProperty().set(null);

    }
    void saveAppointment() throws Exception {

        if (validateInput() == false)
            return;

        AppointmentsDAO apptDAO = new AppointmentsDAO();

        Contact contact = (Contact) cboContacts.getSelectionModel().getSelectedItem();
        int contactId = 0;

        if(contact != null) {
            try {
                contactId = contact.getContact_ID();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if(lblAppointmentID.getText().equals("New")) {
            // this is an insert
            apptDAO.insert(txtTitle.getText(),
                    txtDescription.getText(),
                    txtLocation.getText(),
                    txtType.getText(),
                    convertStringtoDate(txtStart.getText()),
                    convertStringtoDate(txtEnd.getText()),
                    Integer.parseInt(txtCustomerID.getText()),
                    Integer.parseInt(txtUserID.getText()),
                    contactId,
                    App.loggedInUser.getUser_name());
        }
        else {
            // this is an update
            apptDAO.update(Integer.parseInt(lblAppointmentID.getText()),txtTitle.getText(), txtDescription.getText(),
                    txtLocation.getText(), txtType.getText(), convertStringtoDate(txtStart.getText()),
                    convertStringtoDate(txtEnd.getText()),Integer.parseInt(txtCustomerID.getText()),
                    Integer.parseInt(txtUserID.getText()), contactId,
                    App.loggedInUser.getUser_name());
        }
        refreshTableViewData();
        clearFormData();
    }

    void deleteAppointment() throws Exception {

        if (!lblAppointmentID.getText().equals("New")) {

            AppointmentsDAO apptDAO = new AppointmentsDAO();
            int count = apptDAO.delete(Integer.parseInt(lblAppointmentID.getText()));

            if(count == 1)
                lblMessage.setText("Appointment record deleted");

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

        try {
            convertStringtoDate(txtStart.getText());
        } catch (ParseException e) {
            lblMessage.setText("Error: Incorrect date format (MM/dd/yyyy)");
            return false;
        }

        try {
            convertStringtoDate(txtEnd.getText());
        } catch (ParseException e) {
            lblMessage.setText("Error: Incorrect date format (MM/dd/yyyy)");
            return false;
        }

        lblMessage.setText("");
        return true;

    }

    public String convertDateToString(Date indate)
    {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("MM/dd/yyyy");

        try{
            dateString = sdfr.format( indate );
        }catch (Exception ex ){
            throw ex;
        }
        return dateString;
    }

    public Date convertStringtoDate(String mydate) throws ParseException {

        java.util.Date date = (java.util.Date) new SimpleDateFormat("MM/dd/yyyy").parse(mydate);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }
}
