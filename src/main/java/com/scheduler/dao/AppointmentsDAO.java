package com.scheduler.dao;

import com.scheduler.app.Config;
import com.scheduler.app.FileLogger;
import com.scheduler.pojo.Appointment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class AppointmentsDAO {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    public Appointment getByID(int ID) throws Exception {
        Appointment appointment = null;
        final String sql = "select Title,Description,Location,Type, Start, End, Last_Update,Last_Updated_By, "
                + "Customer_ID,User_ID, Contact_ID from appointments where Appointment_ID =?";

        try {

            Class.forName(Config.getDBDriver());
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, String.valueOf(ID));

            // execute query
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                appointment = new Appointment();
                appointment.setTitle(resultSet.getString(1));
                appointment.setDescription(resultSet.getString(2));
                appointment.setLocation(resultSet.getString(3));
                appointment.setType(resultSet.getString(4));
                appointment.setLast_update(resultSet.getTimestamp(5));
                appointment.setLast_updated_by(resultSet.getString(6));
                appointment.setCustomer_id(resultSet.getInt(7));
                appointment.setUser_id(resultSet.getInt(8));
                appointment.setContact_id(resultSet.getInt(9));
            }

        } catch (Exception e) {
            FileLogger.getInstance().warning(e.getMessage());
        } finally {

            // close everything
            if (resultSet != null)
                resultSet.close();

            if (preparedStatement != null)
                preparedStatement.close();

            if (connect != null)
                connect.close();

            // return the dataset or value (or nothing)
            return appointment;
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
            Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

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

    public int update(int Appointment_ID, String Title, String Description, String Location,
                      String Type, Date Start, Date End, String Created_By, Timestamp Last_Update,
                      String Last_Updated_By, int Customer_ID, int user_ID, int Contact_ID ) throws Exception {

        int count = 0;
        AppointmentsDAO appointmentsDAO= new AppointmentsDAO();
        Appointment appointment = new Appointment();
        appointment = getByID(Appointment_ID);


        final String sql = "update appointments set Title=?,Description=?, Location=?,Type=?, Start=?, End=?," +
                " Created_By=?, Last_Update=?, Last_Updated_By=?, Customer_ID=?, User_ID=?, Contact_ID=?" +
                " where Appointment_ID=? ";




        try {

            Calendar cal = Calendar.getInstance();


            Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
            Date date = new Date(timestamp.getTime());
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, Title);
            preparedStatement.setString(2, Description);
            preparedStatement.setString(3,Location);
            preparedStatement.setString(4, Type);
            preparedStatement.setDate(5,date);
            preparedStatement.setDate(6,date);
            preparedStatement.setString(7, Created_By);
            preparedStatement.setTimestamp(8, timestamp);
            preparedStatement.setString(9,Last_Updated_By);
            preparedStatement.setInt(10, Customer_ID);
            preparedStatement.setInt(11,user_ID);
            preparedStatement.setInt(12,Contact_ID );
            preparedStatement.setInt(13,appointment.getAppointment_ID() );


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
