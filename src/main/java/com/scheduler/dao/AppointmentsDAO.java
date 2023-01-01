package com.scheduler.dao;

import com.scheduler.app.Config;
import com.scheduler.app.FileLogger;
import com.scheduler.pojo.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * all data access objects are created to encapsulate logic, members,
 * and functions
 * of the data that need not be in the plain old java objects classes
 */
public class AppointmentsDAO {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /**
     * Gets an appointment from the database based on the given input
     * appointment
     * @param ID used to get the appointment ID from the database
     * @return return an appointment object filled with database data
     * based on the appointment ID
     * @throws Exception
     */

    public ObservableList<Appointment> getAll() throws Exception {

        ObservableList<Appointment> apptList = FXCollections.observableArrayList();

        final String sql = "select Appointment_ID, Title,Description,Location,Type, Start, End, Last_Update,"
                + "Last_Updated_By,Customer_ID,User_ID, Contact_ID from appointments";

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
                Appointment appointment = new Appointment();
                appointment.setAppointment_ID(resultSet.getInt(1));
                appointment.setTitle(resultSet.getString(2));
                appointment.setDescription(resultSet.getString(3));
                appointment.setLocation(resultSet.getString(4));
                appointment.setType(resultSet.getString(5));
                appointment.setStart(resultSet.getDate(6));
                appointment.setEnd(resultSet.getDate(7));
                appointment.setLast_update(resultSet.getTimestamp(8));
                appointment.setLast_updated_by(resultSet.getString(9));
                appointment.setCustomer_ID(resultSet.getInt(10));
                appointment.setUser_ID(resultSet.getInt(11));
                appointment.setContact_ID(resultSet.getInt(12));
                apptList.add(appointment);
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
            return apptList;
        }
    }


    public Appointment getByID(int ID) throws Exception {
        Appointment appointment = null;
        final String sql = "select Appointment_ID, Title,Description,Location,Type, Start, End, Last_Update,"
                + "Last_Updated_By,Customer_ID,User_ID, Contact_ID from appointments where Appointment_ID =?";

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
                appointment.setAppointment_ID(resultSet.getInt(1));
                appointment.setTitle(resultSet.getString(2));
                appointment.setDescription(resultSet.getString(3));
                appointment.setLocation(resultSet.getString(4));
                appointment.setType(resultSet.getString(5));
                appointment.setStart(resultSet.getDate(6));
                appointment.setEnd(resultSet.getDate(7));
                appointment.setLast_update(resultSet.getTimestamp(8));
                appointment.setLast_updated_by(resultSet.getString(9));
                appointment.setCustomer_ID(resultSet.getInt(10));
                appointment.setUser_ID(resultSet.getInt(11));
                appointment.setContact_ID(resultSet.getInt(12));
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

    /**
     * insert inserts a new appointment into the database
     * @param division  division of country
     * @param created_by created by user
     * @param last_Updated_By lastupdatedby user
     * @param countryID used to find country
     * @return returns any lines changed
     * @throws Exception
     */

    public int insert(String title, String description, String location, String type, Date start, Date end,
                      int customerId, int userId, int contactId, String username) throws Exception {

        int count = 0;
        final String sql = "insert into Appointments " +
                "(Title,Description,Location,Type, Start, End, Last_Update," +
                "Last_Updated_By, Create_Date, Created_By, Customer_ID, User_ID, Contact_ID) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {

            Calendar cal = Calendar.getInstance();
            Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, location);
            preparedStatement.setString(4, type);
            preparedStatement.setDate(5, start);
            preparedStatement.setDate(6, end);
            preparedStatement.setTimestamp(7, timestamp);
            preparedStatement.setString(8, username);
            preparedStatement.setTimestamp(9, timestamp);
            preparedStatement.setString(10, username);
            preparedStatement.setInt(11,customerId);
            preparedStatement.setInt(12,userId);
            preparedStatement.setInt(13,contactId);

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
     * update will update an existing appointment in the application
     * @param Appointment_ID field used to identify appointment
     * @param Title appt title
     * @param Description field used to describe purpose of appt
     * @param Location where is the appt
     * @param Type type of appt
     * @param Start appt start
     * @param End appt end
     * @param Created_By user created appt
     * @param Last_Update timestamp of last update
     * @param Last_Updated_By user last updated
     * @param Customer_ID field used to identify customer
     * @param user_ID  foreign key for users table in database
     * @param Contact_ID foreign key for contact table
     * @return
     * @throws Exception
     */
    public int update(int Appointment_ID, String Title, String Description, String Location,
                      String Type, Date Start, Date End, int Customer_ID, int user_ID, int Contact_ID,
                      String username) throws Exception {

        int count = 0;

        final String sql = "update appointments set Title=?,Description=?, Location=?,Type=?, Start=?, End=?," +
                "Last_Update=?, Last_Updated_By=?, Customer_ID=?, User_ID=?, Contact_ID=?" +
                " where Appointment_ID=? ";

        try {

            Calendar cal = Calendar.getInstance();


            Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
            Date date = new Date(timestamp.getTime());
            Class.forName(Config.getDBDriver());
            connect = DriverManager.getConnection(Config.getDatabase(), Config.getDBUser(), Config.getDBPassword());

            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement(sql);

            // setting the SQL parameters (one for each ?)

            preparedStatement.setString(1, Title);
            preparedStatement.setString(2, Description);
            preparedStatement.setString(3,Location);
            preparedStatement.setString(4, Type);
            preparedStatement.setDate(5,Start);
            preparedStatement.setDate(6,End);
            preparedStatement.setTimestamp(7, timestamp);
            preparedStatement.setString(8, username);
            preparedStatement.setInt(9, Customer_ID);
            preparedStatement.setInt(10,user_ID);
            preparedStatement.setInt(11,Contact_ID );
            preparedStatement.setInt(12,Appointment_ID );

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
     * delete an existing appointment from the application and database
     * @param ID appointment ID used to identify which record
     * @return return number of lines changed, if any
     * @throws Exception
     */
    public int delete(int ID) throws Exception {

        int count = 0;

        final String sql = "delete from Appointments where Appointment_ID=?";


        try {
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
