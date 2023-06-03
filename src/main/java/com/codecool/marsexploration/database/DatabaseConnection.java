package com.codecool.marsexploration.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final String URL;
    private final String username;
    private final String password;

    public DatabaseConnection(String URL, String username, String password) {
        this.URL = URL;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, username, password);
        } catch (SQLException e) {
            System.err.println("Could not create a database connection");
            throw new RuntimeException(e);
        }
    }
}
