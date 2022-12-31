package com.scheduler.dao;

import com.scheduler.app.Config;
import com.scheduler.app.FileLogger;
import com.scheduler.pojo.FirstLevelDivision;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class FirstLevelDivisionDAO {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    public FirstLevelDivision getByID(int ID) throws Exception {
        FirstLevelDivision firstLevelDivision = null;
        final String sql = "select Division_ID, Division, Create_Date,Created_By, Last_Update,last_Updated_By, " +
                "Country_ID from firstlevel_divisions where Division_ID =?";

        try {

            Class.forName(Config.getDBDriver());
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, String.valueOf(ID));

            // execute query
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next() ) {
                firstLevelDivision = new FirstLevelDivision();
                firstLevelDivision.setDivision_ID(resultSet.getInt(1));
                firstLevelDivision.setDivision(resultSet.getString(2));
                firstLevelDivision.setCreate_Date(resultSet.getDate(3));
                firstLevelDivision.setCreated_by(resultSet.getString(4));
                firstLevelDivision.setLast_update(resultSet.getDate(5));
                firstLevelDivision.setLast_updated_by(resultSet.getString(6));
                firstLevelDivision.setCountry_id(resultSet.getInt(7));
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
            return firstLevelDivision;
        }
    }

    public ArrayList<FirstLevelDivision> getByCountryName(String countryName) throws Exception {

        ArrayList<FirstLevelDivision> fldList = new ArrayList<>();

        final String sql = "select fld.Division_ID, fld.Division, fld.Create_Date, fld.Created_By, fld.Last_Update, " +
                "fld.last_Updated_By, " +
                "fld.Country_ID from firstlevel_divisions fld " +
                "inner join Countries co ON co.Country_ID = fld.Country_ID and co.Country=?";

        try {

            Class.forName(Config.getDBDriver());
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, String.valueOf(countryName));

            // execute query
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next() ) {
                FirstLevelDivision firstLevelDivision = new FirstLevelDivision();

                firstLevelDivision.setDivision_ID(resultSet.getInt(1));
                firstLevelDivision.setDivision(resultSet.getString(2));
                firstLevelDivision.setCreate_Date(resultSet.getDate(3));
                firstLevelDivision.setCreated_by(resultSet.getString(4));
                firstLevelDivision.setLast_update(resultSet.getDate(5));
                firstLevelDivision.setLast_updated_by(resultSet.getString(6));
                firstLevelDivision.setCountry_id(resultSet.getInt(7));

                fldList.add(firstLevelDivision);
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
            return fldList;
        }
    }

    public int insert(String division, String created_by, String last_Updated_By, int countryID ) throws Exception {

        int count = 0;
        final String sql = "insert into firstlevel_divisions " +
                "(division, create_date,created_By, last_Update,last_Updated_By, country_ID) " +
                "values (?,?,?,?,?,?)";
//
        try {

            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, division);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setString(3,created_by);
            preparedStatement.setTimestamp(4,timestamp);
            preparedStatement.setString(5,last_Updated_By);
            preparedStatement.setInt(6,countryID);

            //need to make function check for previous created by timestamp to ensure accuracy



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

    public int update(int division_ID, String division, String created_by, String last_Updated_By, int countryID ) throws Exception {

        int count = 0;
        FirstLevelDivisionDAO firstLevelDivisionDAO= new FirstLevelDivisionDAO();
        FirstLevelDivision firstLevelDivision = new FirstLevelDivision();
        firstLevelDivision = getByID(division_ID);
        division_ID = firstLevelDivision.getDivision_ID();
        final String sql = "update firstlevel_divisions set division=?,created_By=?, last_Update=?,last_Updated_By=?, country_ID=? where division_ID=? ";




        try {

            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, division);
            preparedStatement.setString(2, created_by);
            preparedStatement.setTimestamp(3,timestamp);
            preparedStatement.setString(4, last_Updated_By);
            preparedStatement.setInt(5,countryID);
            preparedStatement.setInt(6,division_ID);

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
        final String sql = "delete from firstlevel_Divisions where Division_ID=?";

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
