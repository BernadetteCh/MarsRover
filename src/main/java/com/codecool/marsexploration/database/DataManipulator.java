package com.codecool.marsexploration.database;

import com.codecool.marsexploration.database.databaseUtility.CredentialsAndQueries;

import java.sql.*;
import java.util.Optional;

public class DataManipulator {
    private final Database database;
    private final Connection connection;
    private int sum;

    public DataManipulator(int roverId) throws SQLException {
        this.database = new Database();
        this.connection = database.connectWithDatabase();
        this.sum = getSumOfRoverResource(roverId);
    }

    public void insertDataRowLog(int roverID, int step, String foundResource, Optional outcome) {
        String tableName = "marsexploration" + roverID;
        String insertQuery = "INSERT INTO "
                                    + tableName
                                    + " (roverid, step, foundresource, outcome)" +
                "             VALUES (?,?,?,?)";
        try (
                Connection connection = database.connectWithDatabase();
                PreparedStatement pst = connection.prepareStatement(insertQuery)
        ) {
            pst.setInt(1, roverID);
            pst.setInt(2, step);
            pst.setString(3, foundResource);
            pst.setString(4, outcome.toString());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDataRowBase(int roverId) {
        String tableName = "base" + roverId;
        String insertQuery = "INSERT INTO "
                                    + tableName
                                    + " (baseid, roverforbase, resourcesfoundbybaserover, resourcesneededforbuilding, resourcesinstock)" +
                "             VALUES (?, ?, ?, ?, ?)";
        try (
                Connection connection2 = database.connectWithDatabase();
                PreparedStatement pst = connection2.prepareStatement(insertQuery)
        ) {
            pst.setInt(1, roverId);
            pst.setInt(2, roverId);
            pst.setInt(3, sum);
            pst.setInt(4, (sum / 2));
            pst.setInt(5, (sum / 2));
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSumOfRoverResource(int roverId) {
        String query = CredentialsAndQueries
                            .getSumOfLogResources
                            .replace("?", String.valueOf(roverId));
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                sum = ((Number) rs.getObject(1)).intValue();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }
}
