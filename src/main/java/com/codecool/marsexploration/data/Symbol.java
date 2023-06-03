package com.codecool.marsexploration.data;

public enum Symbol {
    EMPTY(" "),
    MOUNTAIN("^"),
    PIT("#"),
    MINERAL("*"),
    WATER("~"),
    ALIEN("A");

    private final String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String  getSymbol() {
        return symbol;
    }
}
