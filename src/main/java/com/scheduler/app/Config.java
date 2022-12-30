package com.scheduler.app;

public class Config {

    private static String dbdriver = "com.mysql.cj.jdbc.Driver";
    private static String database = "jdbc:mysql://localhost/scheduler";
    private static String dbuser = "scheduleruser";
    private static String dbpassword = "freddy!@";

    public static String getDBDriver() { return  dbdriver; }
    public static String getDatabase() {
        return database;
    }

    public static String getDBUser() {
        return dbuser;
    }

    public static String getDBPassword() {
        return dbpassword;
    }

}
