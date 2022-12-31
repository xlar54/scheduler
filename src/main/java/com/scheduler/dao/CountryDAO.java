package com.scheduler.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import com.scheduler.app.Config;
import com.scheduler.pojo.*;
import com.scheduler.app.FileLogger;


public class CountryDAO {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public ArrayList<Country> getAll() throws Exception {

        ArrayList<Country> countries = new ArrayList<Country>();

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
    public int delete(int ID) throws Exception {

        int count = 0;
        final String sql = "delete from Users where User_ID=?";

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