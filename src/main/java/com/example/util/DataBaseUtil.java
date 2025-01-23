package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtil {
    private Connection connection;

    // in constructor initializing the connection for database postgre

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/farmEase", "postgres","Soham@14"
                    );
        } catch (SQLException e) {
            showError("Error During PostGreConnection", e.getMessage());
        }
        return connection;
    }

    // closing the connection after use

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            showError("Error During PostGreClosing", e.getMessage());
        }
    }

    // funtion for showing error if any during the postgre connection

    private void showError(String header, String message) {
        System.out.println(header + "  Message :   " + message);
    }

}
