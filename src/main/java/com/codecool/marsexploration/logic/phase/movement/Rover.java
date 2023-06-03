package com.codecool.marsexploration.logic.phase.movement;

import com.codecool.marsexploration.data.Coordinate;
import com.codecool.marsexploration.data.rover.Routine;

import java.util.ArrayList;
import java.util.List;

public class Rover {
    private final int id;
    private Coordinate position;
    private final int sight;
    private Routine routine;
    private final List<Coordinate> previousSteps;
    private final MovementEngine movementEngine = new MovementEngine();
    private final ControlPanel controlPanel = new ControlPanel(this, movementEngine);

    public Rover(int id, Coordinate position, int sight, Routine routine) {
        this.id = id;
        this.position = position;
        this.sight = sight;
        this.routine = routine;
        this.previousSteps = new ArrayList<>();
    }

    public Coordinate perform() {
        controlPanel.stepToNextField();
        return position;
    }

    public Coordinate getPosition() {
        return position;
    }

    public int[] getPositionsArray() {
        return new int[]{position.x(), position.y()};
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public List<Coordinate> getPreviousSteps() {
        return previousSteps;
    }

    public int getSight() {
        return sight;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    public int getId() {
        return id;
    }
}
