package com.codecool.marsexploration.logic.analyzer;

import com.codecool.marsexploration.data.Outcome;
import com.codecool.marsexploration.data.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class Analyze implements IAnalyzer {
    List<Symbol> foundWaterList = new ArrayList<>();
    List<Symbol> foundMineralList = new ArrayList<>();
    int resourceThresholdForColonization = 6;

    @Override
    public Optional<Outcome> analyzeTimeout(int timeout, int steps) {
        return getTimeout(timeout, steps) <= 0 ? Optional.of(Outcome.TIMEOUT) : Optional.empty();
    }

    @Override
    public Optional<Outcome> analyzeSuccess(Queue<Symbol> foundResources) {
        //TODO: Follow single response
        divideFoundResourcesIntoMineralsAndWater(foundResources, foundWaterList, foundMineralList);
        return checkIfColonizationIsPossible() ? Optional.of(Outcome.COLONIZABLE) : Optional.empty();
    }

    @Override
    public Optional<Outcome> analyzeLackOfResources(Queue<Symbol> foundResources, int timeout, int steps) {
        //TODO: follow single response
        divideFoundResourcesIntoMineralsAndWater(foundResources, foundWaterList, foundMineralList);
        return !checkIfColonizationIsPossible() && getTimeout(timeout, steps) == timeout / 2 ?
                Optional.of(Outcome.NOT_COLONIZABLE_RESOURCES) : Optional.empty();
    }

    private int getTimeout(int timeout, int steps) {
        return timeout - steps;
    }

    private void divideFoundResourcesIntoMineralsAndWater(Queue<Symbol> foundResources, List<Symbol> foundWaterList, List<Symbol> foundMineralList) {
        boolean foundMineral = foundResources.peek().equals(Symbol.MINERAL);
        boolean foundWater = foundResources.peek().equals(Symbol.WATER);
        Symbol polledElement = foundResources.poll();
        if (foundMineral) {
            foundMineralList.add(polledElement);
        }
        if (foundWater) {
            foundWaterList.add(polledElement);
        }
    }

    private boolean checkIfColonizationIsPossible() {
        return foundWaterList.size() >= resourceThresholdForColonization &&
                foundMineralList.size() >= resourceThresholdForColonization;
    }
}