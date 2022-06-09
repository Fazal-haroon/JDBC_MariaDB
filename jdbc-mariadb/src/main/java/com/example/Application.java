package com.example;

import java.sql.*;

public class Application {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        try{
        openDatabaseConnection();
        readData();
        } finally {
        closeDatabaseConnection();

        }
    }

    private static void readData() throws SQLException{
        System.out.println("Reading Data...");
        try (PreparedStatement statement = connection.prepareStatement("select REPORT_GENERATION_ID,REPORT_PDF_FILE_NAME from REPORT_GENERATION")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int num = resultSet.getInt(1);
                String name = resultSet.getString(2);
                System.out.println(num+": done"+name);
            }
        }
    }

    private static void openDatabaseConnection() throws SQLException {
        System.out.println("Connecting to the database...");
        connection = DriverManager.getConnection("jdbc:mariadb://10.10.10.10:3306/Tiger_02152022_ST","fharoon","password_fharoon");
//        if(true) throw new RuntimeException("Simulated error!");
        System.out.println("Connection valid: "+connection.isValid(5));
    }

    private static void closeDatabaseConnection() throws SQLException {
        System.out.println("Closing database connection...");
        connection.close();
        System.out.println("Connection valid: "+connection.isValid(5));
    }

}
