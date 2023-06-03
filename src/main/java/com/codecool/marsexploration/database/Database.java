package com.codecool.marsexploration.database;

import com.codecool.marsexploration.database.databaseUtility.CredentialsAndQueries;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection connection;
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_RED    = "\u001B[31m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE  = "\u001B[37m";
    final String ANSI_BLUE = "\u001B[34m";

    public Connection connectWithDatabase() throws SQLException {
        DatabaseConnection database = new DatabaseConnection(
                CredentialsAndQueries.URL,
                CredentialsAndQueries.username,
                CredentialsAndQueries.password
        );
        connection = database.getConnection();
        return connection;
    }

    public void initTables(int roverId){
        dropTableForBase(roverId);
        dropTableForLog(roverId);
        createTableForLog(roverId);
        createTableForBase(roverId);
    }

    public void dropTableForLog(int roverId) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(
                    CredentialsAndQueries.dropTableMarsExploration
                            + roverId
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableForLog(int roverId) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS marsExploration"
                            + roverId
                            + CredentialsAndQueries.createTableForLog
            );

            System.out.println(ANSI_RED +"Created log for rover " +ANSI_PURPLE+ roverId + ANSI_CYAN +" in given database, if exists...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableForBase(int roverId) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS base"
                            + roverId
                            + CredentialsAndQueries
                            .createTableForBase
                            .replace("?", String.valueOf(roverId))
            );
            System.out.println(ANSI_PURPLE +"Created Base " +ANSI_RED+ roverId +ANSI_BLUE+ " in given database, if exists...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTableForBase(int roverId) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(
                    CredentialsAndQueries
                            .dropTableForBase
                            .replace("?", String.valueOf(roverId))
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
