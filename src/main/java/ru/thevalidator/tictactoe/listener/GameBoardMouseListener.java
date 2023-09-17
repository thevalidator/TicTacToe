/*
 * Copyright (C) 2023 thevalidator
 */
package ru.thevalidator.tictactoe.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 * @author thevalidator
 */
public class GameBoardMouseListener extends MouseAdapter {

    private final JPanel panel;
    private final int[][] board;

    public GameBoardMouseListener(JPanel panel, int[][] board) {
        this.panel = panel;
        this.board = board;
        for (int[] e: board) {
            for (int i: e) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //super.mouseClicked(e);

        int x = e.getX();
        int y = e.getY();
        System.out.printf("x=%d y=%d", x, y);

        int rowNumber = getRowNumber(x);
        int columnNumber = getColumnNumber(y);

        System.out.println(">> " + board[columnNumber][rowNumber]);

    }

    private int getRowNumber(int position) {
        return position / (panel.getSize().width / board.length);
    }

    private int getColumnNumber(int position) {
        return position / (panel.getSize().height / board[0].length);
    }
}
