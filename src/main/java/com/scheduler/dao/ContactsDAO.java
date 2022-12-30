package com.scheduler.dao;

import com.scheduler.app.Config;
import com.scheduler.app.FileLogger;
import com.scheduler.pojo.Contact;
import com.scheduler.pojo.Country;

import java.sql.*;
import java.util.Calendar;

public class ContactsDAO {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    public Contact getByID(int ID) throws Exception {
        Contact contact = new Contact();
        final String sql = "select Contact_ID, Contact_Name, Email from Contacts where Contact_ID=?";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, String.valueOf(ID));

            // execute query
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                contact.setContact_ID(resultSet.getInt(1));
                contact.setContact_name(resultSet.getString(2));
                contact.setEmail(resultSet.getString(3));
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
            return contact;
        }
    }

    public int insert(String contact, String username) throws Exception {

        int count = 0;
        final String sql = "insert into Contacts" +
                "(Contacts)" +
                "values (?,?,?,?,?)";

        try {

            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, country);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setString(3, username);
            preparedStatement.setTimestamp(4, timestamp);
            preparedStatement.setString(5, username);

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

    public int update(int countryId, String country, String username) throws Exception {

        int count = 0;
        final String sql = "update Countries " +
                "set Country=?, Last_Update=?, Last_Updated_By=?" +
                "where Country_ID=?";

        try {

            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, country);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setString(3, username);
            preparedStatement.setInt(4, countryId);

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

