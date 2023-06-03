package com.codecool.marsexploration.data.rover;

public enum Direction {
    N(AxisOperation.INCREASE, Axis.Y),
    E(AxisOperation.INCREASE, Axis.X),
    S(AxisOperation.DECREASE, Axis.Y),
    W(AxisOperation.DECREASE, Axis.X);

    final AxisOperation axisOperation;
    final Axis axis;

    Direction(AxisOperation axisOperation, Axis axis) {
        this.axisOperation = axisOperation;
        this.axis = axis;
    }

    public AxisOperation getAxisOperation() { return axisOperation; }
    public Axis getAxis() { return axis; }

}
