package com.nix.game;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * The Grid for Game of Life
 */
public class Grid {

    private final List<Pair<Integer, Integer>> NEIGHBOURS = Arrays.asList(
            new Pair<Integer, Integer>(-1, -1),
            new Pair<Integer, Integer>(-1, 0),
            new Pair<Integer, Integer>(-1, 1),
            new Pair<Integer, Integer>(0, -1),
            new Pair<Integer, Integer>(0, 1),
            new Pair<Integer, Integer>(1, -1),
            new Pair<Integer, Integer>(1, 0),
            new Pair<Integer, Integer>(1, 1)
    );

    private final int ROWS;
    private final int COLUMNS;
    private final Cell[][] grid;

    /**
     * Creates a grid with specified number of rows and columns
     * @param rows the number of rows
     * @param columns the number of columns
     */
    public Grid(int rows, int columns) {
        this.ROWS = rows;
        this.COLUMNS = columns;
        grid = initGrid(rows, columns);
    }

    /**
     * Makes cell with coordinates alive
     * @param row the cell row
     * @param column the cell column
     */
    public void makeCellAlive(int row, int column) {
        if (cellExists(row, column)) {
            grid[row][column].setCurrentState(State.ALIVE);
        } else {
            System.out.println("Wrong cell coordinates");
        }
    }

    /**
     * Prints the grid
     */
    public void printGrid() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(grid[i][j].getCurrentState().displayValue());
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------");
    }

    /**
     * Executes one tick in the game, calculates and prints next generation
     */
    public void tick() {
        nextGeneration();
        rotateStates();
        printGrid();
    }

    /**
     * Rotates state of cells - move next state to current
     */
    private void rotateStates() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Cell cell = grid[i][j];
                cell.setCurrentState(Optional.ofNullable(grid[i][j].getNextState()).orElse(cell.getCurrentState()));
            }
        }
    }

    /**
     * Calculates next generation for cells
     */
    private void nextGeneration() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Cell cell = grid[i][j];
                int aliveNeighbors = activeNeighbours(i, j);
                if (cell.getCurrentState() == State.ALIVE) {
                    if (aliveNeighbors < 2 || aliveNeighbors > 3) {
                        cell.setNextState(State.DEAD);
                    }
                } else if (aliveNeighbors == 3){
                    cell.setNextState(State.ALIVE);
                }
            }
        }
    }

    /**
     * Finds active neighbours by coordinates
     * @param row the row
     * @param column the column
     * @return count of active neighbours
     */
    private int activeNeighbours(final int row, final int column) {
        return NEIGHBOURS.stream().mapToInt(neighbor -> {
            int neighbourRow  = row + neighbor.getKey();
            int neighbourColumn = column + neighbor.getValue();
            return cellExists(neighbourRow, neighbourColumn) &&
                    grid[neighbourRow][neighbourColumn].getCurrentState() == State.ALIVE ? 1 : 0;
        }).sum();
    }

    /**
     * Initializes a grid by default cells
     * @param rows count of rows
     * @param columns count of columns
     * @return the grid with cells
     */
    private Cell[][] initGrid(int rows, int columns) {
        Cell[][] grid = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = new Cell();
            }
        }
        return grid;
    }

    /**
     * Checks if the cell exists
     * @param row the row of the cell
     * @param column the column of the cell
     * @return true, if the cell exists, false otherwise
     */
    private boolean cellExists(int row, int column) {
        return row >= 0 && row < ROWS && column >= 0 && column < COLUMNS;
    }
}
