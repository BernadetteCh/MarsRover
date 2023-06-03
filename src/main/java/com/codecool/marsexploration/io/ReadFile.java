package com.codecool.marsexploration.io;

import com.codecool.marsexploration.logic.fileConverter.MapConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadFile {
    private final String path;

    public ReadFile(String path) {
        this.path = path;
    }

    public char[][] readFile() {
        try {
            return doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private char[][] doRead() throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get(path));
        MapConverter mapConverter = new MapConverter();
        return mapConverter.convertFileToTwoDimensionalArray(allLines);
    }
}
