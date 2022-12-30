package com.scheduler.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.scheduler.pojo.*;


public class CountryDAO {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private static final String DBSERVER= "jdbc:mysql://localhost/scheduler";
    private static final String DBUSER = "scheduleruser";

    private static final String DBPASS = "freddy!@";

    public Country getCountry(int ID) throws Exception {
        Country country = new Country();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(DBSERVER, DBUSER, DBPASS);
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("select Country from Countries where Country_ID=?");
            preparedStatement.setString(1, String.valueOf(ID));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                country.setCountry(resultSet.getString(1));

                System.out.println("Country = " + country.getCountry());
            }
            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            throw e;
        } finally {
            //close();

            return country;
        }
    }

    public void Insert(Country country) throws Exception {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(DBSERVER, DBUSER, DBPASS);
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("insert into Countries " +
                    "(Country,Create_Date, Created_By, Last_Update, Last_Updated_By)" +
                    "values (?,?,?,?,?");
            preparedStatement.setString(1, country.getCountry());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                country.setCountry(resultSet.getString(1));

                System.out.println("Country = " + country.getCountry());
            }
            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            throw e;
        } finally {
            //close();

            return;
        }
    }
}