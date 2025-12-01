package com.techhub.dbconfig;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DBInitialize {

  
    protected Connection conn;
    protected PreparedStatement stmt;
    protected ResultSet rs;

    public DBInitialize() {
        try {
            // Get project root path
            File f = new File("");
            String rootPath = f.getAbsolutePath() + "\\src\\main\\resources\\db.Properties";

            // Load the properties file (contains DB username, password, URL, driver)
            FileInputStream fin = new FileInputStream(rootPath);
            Properties p = new Properties();
            p.load(fin);

            // Read database connection values from the properties file
            String username = p.getProperty("username");
            String password = p.getProperty("password");
            String url = p.getProperty("url");
            String driverClass = p.getProperty("driver");

            System.out.println("Username Loaded: " + username);

            // Load the JDBC driver class
            Class.forName(driverClass);

            // Establish the connection to the database
            conn = DriverManager.getConnection(url, username, password);

            // Check connection status
            if (conn != null) {
                System.out.println("Database is connected");
            } else {
                System.out.println("Database Not connected");
            }

        } catch (Exception ex) {
            System.out.println("Error is: " + ex);
        }
    }

    public static void main(String[] args) {
        new DBInitialize();
    }
}
