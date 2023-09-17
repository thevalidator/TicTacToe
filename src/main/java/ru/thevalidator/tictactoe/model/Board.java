/*
 * Copyright (C) 2023 thevalidator
 */
package ru.thevalidator.tictactoe.model;

/**
 * @author thevalidator
 */
public class Board {

    public static final int BOARD_SIZE = 3;
    private final int[][] board;

    public Board() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
    }

    public int getboxValue(int x, int y) {
        return board[x][y];
    }
    
    public void setBoxValue(int x, int y, Role role) {
        board[x][y] = role.getValue();
    }

}
