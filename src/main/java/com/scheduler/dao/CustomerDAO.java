package com.scheduler.dao;

import com.scheduler.pojo.Customer;

import java.sql.*;


public class CustomerDAO {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public Customer getCustomer(int ID) throws Exception {
        Customer customer = new Customer();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/scheduler", "scheduleruser", "freddy!@");
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(
                    "select Customer_ID, Customer_Name, Address, Postal_Code,"+
                    "Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID" +
                    "from Customer where Customer_ID=?");
            preparedStatement.setString(1, String.valueOf(ID));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer.setCustomer_name(resultSet.getString(2));
                customer.setAddress(resultSet.getString(3));
                customer.setPostal_code(resultSet.getString(4));
                customer.setPhone(resultSet.getString(5));
                customer.setCreated_by(resultSet.getString(6));
                customer.setLast_update(resultSet.getDate(8));
                customer.setLast_updated_by(resultSet.getString(9));
                customer.setDivision_id(resultSet.getInt(10));

            }
            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            throw e;
        } finally {
            //close();

            return customer;
        }
    }
}