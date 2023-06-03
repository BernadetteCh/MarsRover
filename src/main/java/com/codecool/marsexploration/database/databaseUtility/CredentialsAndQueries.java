package com.codecool.marsexploration.database.databaseUtility;

public final class CredentialsAndQueries {
    private CredentialsAndQueries() {
    }

    public static String dbName = "mars-exploration";
    public static final String username = "bernadette";
    public static final String password = "bernadette";
    public static final String URL = "jdbc:postgresql://localhost:5432/" + dbName;
    public static final String dropTableMarsExploration = "DROP TABLE IF EXISTS marsExploration";
    public static final String createTableForLog = """
             (
                roverID INTEGER,
                step INTEGER,
                foundResource TEXT,
                outcome TEXT,
                PRIMARY KEY (step)
            )
            """;
    public static final String dropTableForBase = "DROP TABLE IF EXISTS base?";
    public static String createTableForBase = """
                (
                baseID INTEGER PRIMARY KEY,
                roverForBase INTEGER REFERENCES marsExploration?(step),
                resourcesFoundByBaseRover INTEGER,
                resourcesNeededForBuilding INTEGER,
                resourcesInStock INTEGER
            )
            """;
    public static final String getSumOfLogResources = "SELECT COUNT(foundresource) FROM marsExploration?";
}
