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

    public Countries getCountry(int ID) throws Exception {
        Countries country = new Countries();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/scheduler", "scheduleruser", "freddy!@");


            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("select Country from Countries where Country_ID=?");
            preparedStatement.setString(1, String.valueOf(ID));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                country.setCountry(resultSet.getString(1));

                System.out.println("Country = " + country.getCountry());
                // Print the column values
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
}