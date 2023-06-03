package com.codecool.marsexploration.io;

import com.codecool.marsexploration.data.Coordinate;
import com.codecool.marsexploration.data.Outcome;
import com.codecool.marsexploration.data.SimulationInput;
import com.codecool.marsexploration.data.Symbol;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LogWriter {
    final String ANSI_WHITE  = "\u001B[37m";
    List<String> log = new ArrayList<>();

    //TODO: Make an object for parameter
    public List<String> createLog(
            int steps,
            int id,
            Queue<Coordinate> logAllCoordinates,
            Queue<Symbol> logAllSymbols,
            Optional<Outcome> outcome
    ) {
        for (int i = 0; i < steps; i++) {
            String parametersForAddingLog = String.format(
                    "Coordinates %s Rover %d found Symbol %s",
                    logAllCoordinates.poll(),
                    id,
                    logAllSymbols.poll()
            );
            log.add(parametersForAddingLog);
        }
        log.add("Outcome of the exploration: " + outcome);
        log.add("Bases created: " + id);
        return log;
    }

    public void writeLog(List<String> log, SimulationInput input) {
        final String outputFilePath = input.logPath();
        File file = new File(outputFilePath);

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))) {
            for (String record : log) {
                fileWriter.write(record);
                fileWriter.newLine();
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ANSI_WHITE +"Saved log: " + input.logPath() + "\n");
    }
}
