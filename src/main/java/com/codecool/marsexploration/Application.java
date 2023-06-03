package com.codecool.marsexploration;

import com.codecool.marsexploration.data.Coordinate;
import com.codecool.marsexploration.data.rover.Routine;
import com.codecool.marsexploration.data.SimulationInput;
import com.codecool.marsexploration.database.DataManipulator;
import com.codecool.marsexploration.database.Database;
import com.codecool.marsexploration.io.ReadFile;
import com.codecool.marsexploration.logic.ExplorationSimulator;
import com.codecool.marsexploration.logic.phase.movement.Rover;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        int roverId = 1;
        DataManipulator dataManipulator = new DataManipulator(roverId);
        ReadFile readFile = new ReadFile("src/main/resources/MarsMap.txt");
        char[][] mapOfMars = readFile.readFile();
        SimulationInput input = new SimulationInput(
                "src/main/resources/exploration-1.map",
                new Coordinate(12, 12),
                1_000,
                "src/main/resources/exploration-mars.log");
        Database database = new Database();
        database.connectWithDatabase();
        database.initTables(roverId);
        Rover rover = new Rover(roverId, input.landing(), 2, Routine.EXPLORE);
        ExplorationSimulator simulator = new ExplorationSimulator(dataManipulator);
        Coordinate currentCoordinates = simulator.simulate(input, mapOfMars, rover);

        String outputPath;

        while ((currentCoordinates.x() != 0 || currentCoordinates.y() != 0)) {
            roverId++;
            database.initTables(roverId);
            Rover nextRover = new Rover(roverId, currentCoordinates, 4, Routine.EXPLORE);
            outputPath = String.format("src/main/resources/baseStation-mars%d.log", roverId);
            SimulationInput input2 = new SimulationInput(
                    new Coordinate(currentCoordinates.x(), currentCoordinates.y()),
                    1_000,
                    outputPath);
            simulator = new ExplorationSimulator(dataManipulator);
            currentCoordinates = simulator.simulate(input2, mapOfMars, nextRover);
            int maxBaseRovers = 5;
            if (roverId >= maxBaseRovers) {
                break;
            }
        }
    }
}
