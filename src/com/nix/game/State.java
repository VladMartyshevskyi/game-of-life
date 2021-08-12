package com.nix.game;

/**
 * Represents possible states of cells
 */
public enum State {
    ALIVE("| "), DEAD("|X");

    private final String displayValue;

    private State(String displayValue) {
        this.displayValue = displayValue;
    }

    public String displayValue() {
        return displayValue;
    }
}
