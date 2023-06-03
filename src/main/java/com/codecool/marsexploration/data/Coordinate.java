package com.codecool.marsexploration.data;

public record Coordinate(int x, int y) {
    @Override
    public String toString() {
        return "[%d, %d]".formatted(x, y);
    }
}
