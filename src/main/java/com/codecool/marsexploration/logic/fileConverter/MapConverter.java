package com.codecool.marsexploration.logic.fileConverter;

import java.util.List;

public final class MapConverter {
    public char[][] convertFileToTwoDimensionalArray(List<String> allLines) {
        int fileSize = allLines.size();
        char[][] mapOfMars = new char[fileSize][fileSize];
        for (int i = 0; i < allLines.size(); i++) {
            mapOfMars[i] = allLines.get(i).toCharArray();
        }
        return mapOfMars;
    }
}
