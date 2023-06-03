package com.codecool.marsexploration.logic;

import com.codecool.marsexploration.data.*;
import com.codecool.marsexploration.data.rover.Routine;
import com.codecool.marsexploration.database.DataManipulator;
import com.codecool.marsexploration.io.LogWriter;
import com.codecool.marsexploration.logic.analyzer.Analyze;
import com.codecool.marsexploration.logic.phase.movement.Rover;
import com.codecool.marsexploration.logic.phase.scan.ScanEnvironment;

import java.util.*;

public class ExplorationSimulator {
    private Queue<Symbol> ListForSuccessAnalyzer;
    private Queue<Symbol> ListForLackOfResourcesAnalyzer;
    private final DataManipulator dataManipulator;

    public ExplorationSimulator(DataManipulator dataManipulator) {
        this.dataManipulator = dataManipulator;
    }

    public Coordinate simulate(SimulationInput input, char[][] map, Rover rover) {
        Coordinate currentRoverPosition;
        ScanEnvironment scanEnvironment = new ScanEnvironment(map, rover.getSight());
        Analyze analyze = new Analyze();
        Optional analyzedOutcome = Optional.empty();
        Queue<Symbol> foundResources = new LinkedList<>();
        int steps = 0;
        boolean simulationIsRunning = true;
        Queue<Coordinate> logAllCoordinates = new LinkedList<>();
        Queue<Symbol> listOfAllFoundSymbols = new LinkedList<>();
        while (simulationIsRunning) {
            currentRoverPosition = rover.perform();
            if (scanEnvironment.scan(currentRoverPosition) == null) continue;
            logAllCoordinates.offer(scanEnvironment.scan(currentRoverPosition).coordinate());
            foundResources.offer(scanEnvironment.scan(currentRoverPosition).resourceType());
            listOfAllFoundSymbols.offer(scanEnvironment.scan(currentRoverPosition).resourceType());
            dataManipulator.insertDataRowLog(rover.getId(), steps, foundResources.poll().getSymbol(), analyzedOutcome);
            if (analyzedOutcome != Optional.empty()) {
                LogWriter logWriter = new LogWriter();
                List<String> log = logWriter.createLog(steps, rover.getId(), logAllCoordinates, foundResources, analyzedOutcome);
                logWriter.writeLog(log, input);
                simulationIsRunning = false;
                if (Optional.of(Outcome.COLONIZABLE).equals(analyzedOutcome)) {
                    rover.setRoutine(Routine.CONSTRUCT);
                    dataManipulator.getSumOfRoverResource(rover.getId());
                    dataManipulator.insertDataRowBase(rover.getId());
                    return new Coordinate(currentRoverPosition.x(), currentRoverPosition.y());
                }
            }
            analyzedOutcome = analyze(input, analyze, steps, listOfAllFoundSymbols);
            steps++;
        }
        return new Coordinate(0, 0);
    }

    private Optional analyze(SimulationInput input, Analyze analyze, int steps, Queue<Symbol> foundResources) {
        ListForSuccessAnalyzer = new LinkedList<>(foundResources);
        ListForLackOfResourcesAnalyzer = new LinkedList<>(foundResources);
        foundResources.poll();
        Optional outcomeFromAnalyzeTimeout = analyze.analyzeTimeout((int) input.timeout(), steps);
        if (outcomeFromAnalyzeTimeout != Optional.empty()) {
            return outcomeFromAnalyzeTimeout;
        }

        Optional outcomeFromAnalyzeSuccess = analyze.analyzeSuccess(ListForSuccessAnalyzer);
        if (outcomeFromAnalyzeSuccess != Optional.empty()) {
            return outcomeFromAnalyzeSuccess;
        }

        Optional outcomeFromAnalyzeLackOfResources = analyze.analyzeLackOfResources(ListForLackOfResourcesAnalyzer, (int) input.timeout(), steps);
        if (outcomeFromAnalyzeLackOfResources != Optional.empty()) {
            return outcomeFromAnalyzeLackOfResources;
        }
        return Optional.empty();
    }
}
