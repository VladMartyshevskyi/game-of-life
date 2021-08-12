package com.nix.game;

public class Main {

    private static final int TICKS = 10;


    public static void main(String[] args) {
        Grid grid = new Grid(25, 25);
        addGlider(grid);
        grid.printGrid();
        for (int i = 0; i < TICKS; i++) {
            grid.tick();
        }

    }

    private static void addGlider(Grid grid) {
        grid.makeCellAlive(10,10);
        grid.makeCellAlive(10,11);
        grid.makeCellAlive(10,12);
        grid.makeCellAlive(9,12);
        grid.makeCellAlive(8,11);
    }
}
