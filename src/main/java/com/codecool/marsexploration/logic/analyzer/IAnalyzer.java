package com.codecool.marsexploration.logic.analyzer;

import com.codecool.marsexploration.data.Outcome;
import com.codecool.marsexploration.data.Symbol;

import java.util.Optional;
import java.util.Queue;

public interface IAnalyzer {
    Optional<Outcome> analyzeTimeout(int timeout, int steps);

    Optional<Outcome> analyzeSuccess(Queue<Symbol> foundResources);

    Optional<Outcome> analyzeLackOfResources(Queue<Symbol> foundResources, int timeout, int steps);
}
