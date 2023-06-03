package com.codecool.marsexploration.logic.phase.movement;

import com.codecool.marsexploration.data.Coordinate;
import com.codecool.marsexploration.data.rover.Direction;

public class ControlPanel {
    private final Rover rover;
    private final MovementEngine movementEngineImpl;
    private final int NUMBER_OF_DIRECTIONS;
    private int currentDistanceTraveledInOneDirection;
    private int lastDistanceTraveledInOneDirection;
    private int nextDirectionIndex;

    public ControlPanel(Rover rover, MovementEngine movementEngineImpl) {
        this.rover = rover;
        this.movementEngineImpl = movementEngineImpl;
        NUMBER_OF_DIRECTIONS = Direction.values().length;
        currentDistanceTraveledInOneDirection = 0;
        lastDistanceTraveledInOneDirection = 0;
        nextDirectionIndex = 0;
    }

    public void stepToNextField() {
        move(getNextDirection());
        increaseDistanceTraveledInOneDirection();
        saveStepToMovementHistory(rover.getPosition());
    }

    private void move(Direction direction) {
        movementEngineImpl.move(rover, direction);
    }

    private void increaseDistanceTraveledInOneDirection() {
        currentDistanceTraveledInOneDirection++;
    }

    private void saveStepToMovementHistory(Coordinate newPosition) {
        rover.getPreviousSteps().add(newPosition);
    }


    private Direction getNextDirection() {
        if (!roverTraveledEnoughInCurrentDirection())
            return Direction.values()[nextDirectionIndex];

        adjustDistanceCounters();
        return getNextDirectionIndex();
    }

    private boolean roverTraveledEnoughInCurrentDirection() {
        return lastDistanceTraveledInOneDirection < currentDistanceTraveledInOneDirection;
    }

    private void adjustDistanceCounters() {
        lastDistanceTraveledInOneDirection = (currentDistanceTraveledInOneDirection + rover.getSight());
        currentDistanceTraveledInOneDirection = 0;
    }

    private Direction getNextDirectionIndex() {
        nextDirectionIndex = (++nextDirectionIndex % NUMBER_OF_DIRECTIONS);
        return Direction.values()[nextDirectionIndex];
    }
}
