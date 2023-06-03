package com.codecool.marsexploration.logic.analyzer;

import com.codecool.marsexploration.data.Environment;
import com.codecool.marsexploration.data.Outcome;
import com.codecool.marsexploration.data.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzeTest {
    Analyze analyze = new Analyze();
    @Test
    void analyzeTimeout() {
        int timeout = 20;
        int steps = 20;
        Optional<Outcome> outcome1 = analyze.analyzeTimeout(timeout, steps);
        Optional<Outcome> expectedOutcome = Optional.of(Outcome.TIMEOUT);
        Assertions.assertEquals(expectedOutcome, outcome1);

        timeout = 40;
        steps = 20;
        Optional<Outcome> outcome2 = analyze.analyzeTimeout(timeout, steps);
        Optional<Outcome> expectedOutcome2 = Optional.empty();
        Assertions.assertEquals(expectedOutcome2, outcome2);

        timeout = 0;
        steps = 30;
        Optional<Outcome> outcome3 = analyze.analyzeTimeout(timeout, steps);
        Optional<Outcome> expectedOutcome3 = Optional.of(Outcome.TIMEOUT);
        Assertions.assertEquals(expectedOutcome3, outcome3);
    }

    @Test
    void analyzeSuccess() {
        Queue<String> foundResources = new LinkedList();
        foundResources.add("*");
        foundResources.add("~");
        foundResources.add("*");
        foundResources.add("~");
    }

    @Test
    void analyzeLackOfResources() {
    }
}