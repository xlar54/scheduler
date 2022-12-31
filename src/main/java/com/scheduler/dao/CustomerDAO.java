package com.scheduler.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.scheduler.app.Config;
import com.scheduler.pojo.Customer;
import com.scheduler.app.FileLogger;
import com.scheduler.pojo.CustomerRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CustomerDAO {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public ObservableList<Customer> getCustomerList() throws Exception {

        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        try {

            Class.forName(Config.getDBDriver());
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(
                    "select Customer_ID, Customer_Name, Address, Postal_Code,"+
                            "Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID " +
                            "from Customers");

            // setting the SQL parameters (one for each ?)
            //preparedStatement.setString(1, String.valueOf(ID));

            // execute query
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Customer customer = new Customer();
                customer.setCustomer_ID(resultSet.getInt(1));
                customer.setCustomer_name(resultSet.getString(2));
                customer.setAddress(resultSet.getString(3));
                customer.setPostal_code(resultSet.getString(4));
                customer.setPhone(resultSet.getString(5));
                customer.setCreated_by(resultSet.getString(6));
                customer.setLast_update(resultSet.getDate(8));
                customer.setLast_updated_by(resultSet.getString(9));
                customer.setDivision_id(resultSet.getInt(10));

                customerList.add(customer);

            }

        } catch (Exception e) {
            FileLogger.getInstance().warning(e.getMessage());
        } finally {

            // close everything
            if(resultSet != null)
                resultSet.close();

            if(preparedStatement != null)
                preparedStatement.close();

            if(connect != null)
                connect.close();

            // return the dataset or value (or nothing)
            return customerList;
        }
    }

    public ObservableList<CustomerRow> getCustomerRows() throws Exception {

        ObservableList<CustomerRow> customerRows = FXCollections.observableArrayList();

        try {

            Class.forName(Config.getDBDriver());
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(
                    "select c.Customer_ID, c.Customer_Name, c.Address, c.Postal_Code, c.Phone, fld.Division, co.Country " +
                            "from Customers c " +
                            "inner join firstlevel_divisions fld " +
                            "on c.Division_ID = fld.Division_ID " +
                            "inner join countries co " +
                            "on fld.Country_ID = co.Country_ID");

            // setting the SQL parameters (one for each ?)
            //preparedStatement.setString(1, String.valueOf(ID));

            // execute query
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                CustomerRow customerRow = new CustomerRow();
                customerRow.setCustomer_ID(resultSet.getInt(1));
                customerRow.setCustomer_name(resultSet.getString(2));
                customerRow.setAddress(resultSet.getString(3));
                customerRow.setPostal_code(resultSet.getString(4));
                customerRow.setPhone(resultSet.getString(5));
                customerRow.setDivision(resultSet.getString(6));
                customerRow.setCountry(resultSet.getString(7));

                customerRows.add(customerRow);

            }

        } catch (Exception e) {
            FileLogger.getInstance().warning(e.getMessage());
        } finally {

            // close everything
            if(resultSet != null)
                resultSet.close();

            if(preparedStatement != null)
                preparedStatement.close();

            if(connect != null)
                connect.close();

            // return the dataset or value (or nothing)
            return customerRows;
        }
    }

    public Customer getCustomerByID(int ID) throws Exception {
        Customer customer = new Customer();

        try {

            Class.forName(Config.getDBDriver());
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(
                    "select Customer_ID, Customer_Name, Address, Postal_Code,"+
                    "Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID" +
                    "from Customer where Customer_ID=?");

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, String.valueOf(ID));

            // execute query
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer.setCustomer_name(resultSet.getString(2));
                customer.setAddress(resultSet.getString(3));
                customer.setPostal_code(resultSet.getString(4));
                customer.setPhone(resultSet.getString(5));
                customer.setCreated_by(resultSet.getString(6));
                customer.setLast_update(resultSet.getTimestamp(8));
                customer.setLast_updated_by(resultSet.getString(9));
                customer.setDivision_id(resultSet.getInt(10));

            }

        } catch (Exception e) {
            FileLogger.getInstance().warning(e.getMessage());
        } finally {

            // close everything
            if(resultSet != null)
                resultSet.close();

            if(preparedStatement != null)
                preparedStatement.close();

            if(connect != null)
                connect.close();

            // return the dataset or value (or nothing)
            return customer;
        }
    }


    public int insert(String customerName, String address, String postalCode, String phone, int divisionId,
                      String username) throws Exception {

        int count = 0;
        final String sql = "insert into Customers " +
                "(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update," +
                "Last_Updated_By, Division_ID)" +
                "values (?,?,?,?,?,?,?,?,?)";

        try {

            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());

            Class.forName(Config.getDBDriver());
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, postalCode);
            preparedStatement.setString(4, phone);
            preparedStatement.setTimestamp(5, timestamp);
            preparedStatement.setString(6, username);
            preparedStatement.setTimestamp(7, timestamp);
            preparedStatement.setString(8, username);
            preparedStatement.setInt(9, divisionId);

            // execute query
            count = preparedStatement.executeUpdate();

        } catch (Exception e) {
            FileLogger.getInstance().warning(e.getMessage());
        } finally {

            // close everything
            if(resultSet != null)
                resultSet.close();

            if(preparedStatement != null)
                preparedStatement.close();

            if(connect != null)
                connect.close();

            // return the dataset or value (or nothing)
            return count;
        }
    }

    public int update(int ID, String customerName, String address, String postalCode, String phone, int divisionId,
                      String username) throws Exception {

        int count = 0;
        final String sql = "update Customers " +
                "set Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Last_Update=?," +
                "Last_Updated_By=?, Division_ID=? " +
                "where Customer_ID=?";

        try {

            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());

            Class.forName(Config.getDBDriver());
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, postalCode);
            preparedStatement.setString(4, phone);
            preparedStatement.setTimestamp(5, timestamp);
            preparedStatement.setString(6, username);
            preparedStatement.setInt(7, divisionId);
            preparedStatement.setInt(8, ID);

            // execute query
            count = preparedStatement.executeUpdate();

        } catch (Exception e) {
            FileLogger.getInstance().warning(e.getMessage());
        } finally {

            // close everything
            if(resultSet != null)
                resultSet.close();

            if(preparedStatement != null)
                preparedStatement.close();

            if(connect != null)
                connect.close();

            // return the dataset or value (or nothing)
            return count;
        }
    }

    public int delete(int ID) throws Exception {

        int count = 0;
        final String sql = "delete from Customers where Customer_ID=?";

        try {

            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());

            Class.forName(Config.getDBDriver());
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setInt(1, ID);

            // execute query
            count = preparedStatement.executeUpdate();

        } catch (Exception e) {
            FileLogger.getInstance().warning(e.getMessage());
        } finally {

            // close everything
            if(resultSet != null)
                resultSet.close();

            if(preparedStatement != null)
                preparedStatement.close();

            if(connect != null)
                connect.close();

            // return the dataset or value (or nothing)
            return count;
        }
    }
}