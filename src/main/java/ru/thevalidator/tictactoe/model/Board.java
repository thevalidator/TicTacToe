/*
 * Copyright (C) 2023 thevalidator
 */
package ru.thevalidator.tictactoe.model;

import javax.swing.JPanel;

/**
 * @author thevalidator
 */
public class Board {

    public static final int BOARD_SIZE = 3;
    private final int[][] board;
    private JPanel gamePanel;

    public Board() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
    }

    public void setPanel(JPanel panel) {
        this.gamePanel = panel;
    }

    public int getboxValue(int x, int y) {
        return board[y][x];
    }
    
    public void setBoxValue(int x, int y, Role role) {
        board[y][x] = role.getValue();
    }
    
    public int getVerticalSize() {
        return board.length;
    }
    
    public int getHorisontalSize() {
        return board[0].length;
    }
    
    public void clearBoard() {
        clearValues();
        gamePanel.repaint();
    }

    private void clearValues() {
        for (int[] row: board) {
            for (int i = 0; i < row.length; i++) {
                row[i] = 0;
            }
        }
    }

}
