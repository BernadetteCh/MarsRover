package com.codecool.marsexploration.logic.phase.movement;

import com.codecool.marsexploration.data.Coordinate;
import com.codecool.marsexploration.data.rover.Axis;
import com.codecool.marsexploration.data.rover.AxisOperation;
import com.codecool.marsexploration.data.rover.Direction;

import java.util.HashMap;

public class MovementEngine {
    private final HashMap<Axis, Integer> POSITION;
    private final int STEP_SIZE_FROM_ROVER = 1;

    public MovementEngine() {
        this.POSITION = new HashMap<>();
    }

    public void move(Rover rover, Direction direction) {
        readOldPosition(rover);
        Axis axis = direction.getAxis();
        AxisOperation axisOperation = direction.getAxisOperation();
        changePosition(POSITION, axis, axisOperation);
        Coordinate newPosition = new Coordinate(POSITION.get(Axis.X), POSITION.get(Axis.Y));
        rover.setPosition(newPosition);
    }

    private void changePosition(HashMap<Axis, Integer> position, Axis axis, AxisOperation axisOperation) {
        switch (axisOperation) {
            case INCREASE -> position.put(axis, position.get(axis) + STEP_SIZE_FROM_ROVER);
            case DECREASE -> position.put(axis, position.get(axis) - STEP_SIZE_FROM_ROVER);
        }
    }

    private void readOldPosition(Rover rover) {
        int i = 0;
        for (Axis axis : Axis.values()) {
            POSITION.put(axis, rover.getPositionsArray()[i]);
            i++;
        }
    }
}
