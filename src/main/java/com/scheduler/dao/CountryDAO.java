package com.scheduler.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import com.scheduler.app.Config;
import com.scheduler.pojo.*;
import com.scheduler.app.FileLogger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * /**
 *  * all data access objects are created to encapsulate logic, members,
 *  * and functions
 *  * of the data that need not be in the plain old java objects classes
 *  */

public class CountryDAO {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /**
     * get an observable list object populated with
     * country data from the database
     * @return return observable list
     * @throws Exception
     */
    public ObservableList<Country> getAll() throws Exception {

        ObservableList<Country> countries = FXCollections.observableArrayList();

        final String sql = "select Country_ID, Country, Create_Date, Created_By," +
                "Last_Update, Last_Updated_By from Countries";

        try {

            Class.forName(Config.getDBDriver());
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            //preparedStatement.setString(1, String.valueOf(ID));

            // execute query
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Country country = new Country();
                country.setCountry_ID(resultSet.getInt(1));
                country.setCountry(resultSet.getString(2));
                country.setCreate_Date(resultSet.getDate(3));
                country.setCreated_by(resultSet.getString(4));
                country.setLast_update(resultSet.getDate(5));
                country.setLast_updated_by(resultSet.getString(6));
                countries.add(country);
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
            return countries;
        }
    }

    /**
     * getbyID gets the country object from the database
     * @param ID used to get the countryID from the database
     * @return return country object
     * @throws Exception
     */
    public Country getByID(int ID) throws Exception {
        Country country = new Country();
        final String sql = "select Country from Countries where Country_ID=?";

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
                country.setCountry(resultSet.getString(1));
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
            return country;
        }
    }

    /**
     * insert inserts a new country into the database
     * @param country the country
     * @param username username of the user who made the change
     * @return return count of any lines changed
     * @throws Exception
     */
    public int insert(String country, String username) throws Exception {

        int count = 0;
        final String sql = "insert into Countries " +
                "(Country,Create_Date, Created_By, Last_Update, Last_Updated_By)" +
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

    /**
     * update will update an existing country in the application
     * @param countryId ID used to identify country
     * @param country name field for country
     * @param username name field for user who made the change
     * @return
     * @throws Exception
     */
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

    /**
     * delete deletes a country from the database
     * @param ID field used to identify country
     * @return return count of records changed if any
     * @throws Exception
     */
    public int delete(int ID) throws Exception {

        int count = 0;
        final String sql = "delete from Countries where Country_ID=?";

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