package com.codecool.marsexploration.io;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
class ReadFileTest {
ReadFile readFile = new ReadFile("src/test/fileReaderTest");

    @Test
    void readFile() {
        char[][] result = readFile.readFile();
        char[][] expected = {{'*', '*'}};

        for (int i = 0; i > expected.length; i++) {
            assertTrue(Arrays.equals(expected[i], result[i]));
        }
    }
}