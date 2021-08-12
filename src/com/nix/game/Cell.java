package com.nix.game;

/**
 * Cell representation for game of life. Holds current and next states.
 */
public class Cell {

    private State currentState = State.DEAD; // default state

    private State nextState;

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }
}
