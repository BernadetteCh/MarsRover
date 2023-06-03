package com.codecool.marsexploration.logic.phase.scan;

import com.codecool.marsexploration.data.Coordinate;
import com.codecool.marsexploration.data.Environment;
import com.codecool.marsexploration.data.Symbol;
import com.codecool.marsexploration.io.ReadFile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScanEnvironmentTest {

    ReadFile readFile = new ReadFile("src/test/fileReaderTest");

    char[][] map = readFile.readFile();

    Coordinate coordinate = new Coordinate(0,0);
    ScanEnvironment scanEnvironment = new ScanEnvironment(map, 0);

    @Test
    void scan() {
        assertEquals(new Environment(Symbol.MINERAL,coordinate), scanEnvironment.scan(coordinate));
    }
}