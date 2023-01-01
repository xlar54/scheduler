package com.scheduler.dao;

import com.scheduler.app.Config;
import com.scheduler.app.FileLogger;
import com.scheduler.pojo.User;

import java.sql.*;
import java.util.Calendar;

/**
 * all data access objects are created to encapsulate logic, members,
 * and functions
 * of the data that need not be in the plain old java objects classes
 */
public class UserDAO {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /**
     * get user record from database based on user input
     * @param ID field which specifys the record
     * @return return user object
     * @throws Exception
     */
    public User getUserByID(int ID) throws Exception {
        User user = new User();

        try {

            Class.forName(Config.getDBDriver());
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(
                    "select User_ID, User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By" +
                    "from Users where User_ID=?");

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, String.valueOf(ID));

            // execute query
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setUser_ID(resultSet.getInt(1));
                user.setUser_name(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setCreate_date(resultSet.getDate(4));
                user.setCreated_by(resultSet.getString(5));
                user.setLast_update(resultSet.getDate(6));
                user.setLast_updated_by(resultSet.getString(7));
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
            return user;
        }
    }

    /**
     * getuserbyusernamepassword gets the user record from the database based on user input
     * @param userName field which specifys the record
     * @param password field which specifys the record
     * @return return user object
     * @throws Exception
     */
    public User getUserByUsernamePassword(String userName, String password) throws Exception {
        User user = null;

        try {

            Class.forName(Config.getDBDriver());
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(
                    "select User_ID, User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By " +
                            "from Users where User_Name=? and Password=?");

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            // execute query
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next() ) {

                user = new User();

                user.setUser_ID(resultSet.getInt(1));
                user.setUser_name(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setCreate_date(resultSet.getDate(4));
                user.setCreated_by(resultSet.getString(5));
                user.setLast_update(resultSet.getDate(6));
                user.setLast_updated_by(resultSet.getString(7));
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
            return user;
        }
    }

    /**
     * insert inserts a user record into the database using user input
     * @param userName first username
     * @param password password
     * @param username second username
     * @return return count of records affected, if any
     * @throws Exception
     */
    public int insert(String userName, String password, String username) throws Exception {

        int count = 0;
        final String sql = "insert into Users " +
                "(User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By) " +
                "values (?,?,?,?,?,?)";

        try {

            Calendar cal = Calendar.getInstance();
            Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

            Class.forName(Config.getDBDriver());
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.setString(4, username);
            preparedStatement.setTimestamp(5, timestamp);
            preparedStatement.setString(6, username);

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
     * update updates the existing user records based on user input
     * @param ID id specifying which record
     * @param userName first username field
     * @param password password field
     * @param username second username field
     * @return return count of lines changed, if any
     * @throws Exception
     */
    public int update(int ID, String userName, String password, String username) throws Exception {

        int count = 0;
        final String sql = "update Users " +
                "set User_Name=?, Password=?, Last_Update=?," +
                "Last_Updated_By=? " +
                "where User_ID=?";

        try {

            Calendar cal = Calendar.getInstance();
            Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

            Class.forName(Config.getDBDriver());
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.setString(4, username);
            preparedStatement.setInt(5, ID);

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
     * delete deletes a user record based on user input
     * @param ID field used to specify the record
     * @return return a count of lines changed, if any
     * @throws Exception
     */
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