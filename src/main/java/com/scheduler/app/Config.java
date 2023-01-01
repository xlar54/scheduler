package com.scheduler.app;

/**
 * config class encapsulates all connection data for the application
 */
public class Config {

    private static String dbdriver = "com.mysql.cj.jdbc.Driver";
    private static String database = "jdbc:mysql://localhost/scheduler";
    private static String dbuser = "scheduleruser";
    private static String dbpassword = "freddy!@";

    /**
     * getter for dbdriver string
     * @return
     */
    public static String getDBDriver() { return  dbdriver; }

    /**
     * getter for database
     * @return
     */
    public static String getDatabase() {
        return database;
    }
    /**
     * getter for dbuser
     * @return
     */
    public static String getDBUser() {
        return dbuser;
    }
    /**
     * getter for dbpassword
     */
    public static String getDBPassword() {
        return dbpassword;
    }

}
