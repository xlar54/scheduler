package com.scheduler.dao;

import com.scheduler.app.Config;
import com.scheduler.app.FileLogger;
import com.scheduler.pojo.Contact;
import com.scheduler.pojo.Country;

import java.sql.*;
import java.util.Calendar;

/**
 * /**
 *  * all data access objects are created to encapsulate logic, members,
 *  * and functions
 *  * of the data that need not be in the plain old java objects classes
 *  */
public class ContactsDAO {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /**
     * Gets a contact from the database based on the given input
     *
     * @param ID used to get the contactID from the database
     * @return return a contact object filled with database data
     * based on the contact ID
     * @throws Exception
     */
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
    /**
     * insert inserts a new contact into the database
     * @param Contact_Name  contacts name field
     * @param Email email name field
     * @return returns any lines changed
     * @throws Exception
     */

    public int insert(String Contact_Name, String Email ) throws Exception {

        int count = 0;
        final String sql = "insert into Contacts" +
                "(Contact_Name, Email)" +
                "values (?,?)";

        try {

            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, Contact_Name);
            preparedStatement.setString(2, Email);


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

    /**
     * update will update an existing contact in the application
     * @param Contact_ID ID used to identify which contact
     * @param Contact_Name name field
     * @param Email email field
     * @return
     * @throws Exception
     */
    public int update(int Contact_ID, String Contact_Name, String Email) throws Exception {

        int count = 0;
        final String sql = "update Contacts " +
                "set Contact_Name=?, Email=?"+
                "where Contact_ID=?";

        try {

            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, Contact_Name);
            preparedStatement.setString(2, Email);
            preparedStatement.setInt(3,Contact_ID);

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

    /**
     * delete removes a contact object from the database
     * @param ID iD used to identify the specific contact to
     *           delete
     * @return return a count of lines changed, if any
     * @throws Exception
     */
    public int delete(int ID) throws Exception {

        int count = 0;
        final String sql = "delete from Contacts where Contact_ID=?";

        try {

            Calendar cal = Calendar.getInstance();
            Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

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

