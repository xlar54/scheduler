package com.scheduler.dao;

import com.scheduler.app.Config;
import com.scheduler.app.FileLogger;
import com.scheduler.pojo.User;

import java.sql.*;
import java.util.Calendar;


public class UserDAO {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

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