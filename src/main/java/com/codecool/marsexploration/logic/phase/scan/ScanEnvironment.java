package com.codecool.marsexploration.logic.phase.scan;

import com.codecool.marsexploration.data.Coordinate;
import com.codecool.marsexploration.data.Environment;
import com.codecool.marsexploration.data.Symbol;

import java.util.Arrays;

public class ScanEnvironment {
    private final char[][] mapOfMars;
    private int sight;

    public ScanEnvironment(char[][] mapOfMars, int sight) {
        this.mapOfMars = mapOfMars;
        this.sight = sight;
    }

    public Environment scan(Coordinate coordinate) {
        if (!roverIsOnMap(coordinate)) return null;
        int coordinateX = coordinate.x() + sight;
        int coordinateY = coordinate.y() + sight;
        String foundResource = String.valueOf(mapOfMars[coordinateX][coordinateY]);
        Symbol[] enumSymbol = Symbol.values();
        Symbol symbol = Arrays.stream(enumSymbol)
                .filter(symb -> foundResource.equals(symb.getSymbol()))
                .findFirst()
                .orElse(null);
        return symbol != null ? new Environment(symbol, coordinate) : null;
    }

    private boolean roverIsOnMap(Coordinate coordinate) {
        return (coordinate.x() > -1 && coordinate.x() < mapOfMars.length - sight)
                && (coordinate.y() > -1 && coordinate.y() < mapOfMars.length - sight);
    }
}
