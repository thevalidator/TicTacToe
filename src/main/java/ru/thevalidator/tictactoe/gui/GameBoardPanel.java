/*
 * Copyright (C) 2023 thevalidator
 */
package ru.thevalidator.tictactoe.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static ru.thevalidator.tictactoe.gui.Settings.BOX_SIZE;
import static ru.thevalidator.tictactoe.gui.Settings.MAIN_WINDOW_WIDTH;
import static ru.thevalidator.tictactoe.gui.Settings.MARGIN;
import ru.thevalidator.tictactoe.model.Board;
import ru.thevalidator.tictactoe.model.Role;
import ru.thevalidator.tictactoe.model.StatusData;

/**
 *
 * @author thevalidator
 */
public class GameBoardPanel extends javax.swing.JPanel implements MouseListener {

    private StatusData status;
    private final Board board;
    private boolean isCrosses;
    private int actionsNumber;
    private Image cross;
    private Image circle;
    private Image crossWin;
    private Image circleWin;

    /**
     * Creates new form GameBoardPanel
     */
    public GameBoardPanel() {
        loadImages();
        resetActions();
        this.board = new Board();
        initComponents();
    }

    public void setStatus(StatusData status) {
        this.status = status;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 153, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private int getRowNumber(int y) {
        return y / (getSize().height / board.getVerticalSize());
    }

    private int getColumnNumber(int x) {
        return x / ((getSize().width / board.getHorisontalSize()));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xClickPos = e.getX();
        int yClickPos = e.getY();
        if (gamingAreaClicked(xClickPos, yClickPos)) {
            int x = getColumnNumber(xClickPos);
            int y = getRowNumber(yClickPos);
            if (board.getboxValue(x, y) == 0) {
                Role role = isCrosses ? Role.CROSS : Role.NOUGHT;
                board.setBoxValue(x, y, role);
                actionsNumber++;
            }
            isCrosses = !isCrosses;
            if (isFinished()) {
                status.setStatus("FINISHED");
                // TO DO: crossing line if win
            } else {
                status.setStatus(isCrosses ? "CROSSES TURN" : "NOUGHTS TURN");
            }
            repaint();

            System.out.printf("x=%d y=%d\n", xClickPos, yClickPos);
            System.out.printf("POINT[x=%d:y=%d] value=%d  -  Step: %d\n", x, y, board.getboxValue(x, y), actionsNumber);
        }

    }

    private boolean gamingAreaClicked(int x, int y) {
        // TODO: fix gaming area for grid lines (exclude lines area)
        boolean a = x >= MARGIN && x <= (MAIN_WINDOW_WIDTH - MARGIN);
        boolean b = y >= MARGIN && y <= (MAIN_WINDOW_WIDTH - MARGIN);
        return a && b;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void drawGrid(Graphics panel) {
        panel.setColor(Color.DARK_GRAY);
        for (int i = 1; i < 3; i++) {
            panel.fillRect((MAIN_WINDOW_WIDTH - (MARGIN * 2)) / 3 * i, MARGIN, MARGIN, MAIN_WINDOW_WIDTH - MARGIN * 4);
            panel.fillRect(MARGIN, (MAIN_WINDOW_WIDTH - (MARGIN * 2)) / 3 * i, MAIN_WINDOW_WIDTH - MARGIN * 4, MARGIN);
        }
    }

    private void drawFigures(Graphics g) {
        for (int y = 0; y < board.getVerticalSize(); y++) {
            for (int x = 0; x < board.getHorisontalSize(); x++) {
                // TODO: refactor selection figure to draw logic
                int value = board.getboxValue(x, y);
                if (value == Role.CROSS_WIN.getValue()) {
                    drawFigure(g, crossWin, x, y);
                } else if (value == Role.NOUGHT_WIN.getValue()) {
                    drawFigure(g, circleWin, x, y);
                } else if (value == Role.CROSS.getValue()) {
                    drawFigure(g, cross, x, y);
                } else if (value == Role.NOUGHT.getValue()) {
                    drawFigure(g, circle, x, y);
                }
            }
        }
    }

    private void drawFigure(Graphics g, Image figure, int x, int y) {
        int imgSize = figure.getWidth(null);
        int start = MARGIN + BOX_SIZE / 2 - imgSize / 2;
        int offset = BOX_SIZE + MARGIN / 2;

        int crossLineStart = BOX_SIZE / 2 + MARGIN;
        int crossLineOffset = BOX_SIZE + MARGIN / 2;
        g.drawImage(figure, start + offset * x, start + offset * y, null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawFigures(g);
    }

    public void clearBoard() {
        clearValues();
        repaint();
    }

    private void clearValues() {
        status.setStatus(isCrosses ? "CROSSES TURN" : "NOUGHTS TURN");
        board.resetValues();
        resetActions();
    }

    private boolean isFinished() {

        boolean isFinished;
        isFinished = checkHorisontals();
        if (!isFinished) {
            isFinished = checkVerticals();
        }
        if (!isFinished) {
            isFinished = checkLeftDiagonal();
        }
        if (!isFinished) {
            isFinished = checkRightDiagonal();
        }
        if (!isFinished && actionsNumber == 9) {
            isFinished = true;
        }

        System.out.println(">>> IS FINISHED: " + isFinished);
        return isFinished;
    }

    private boolean checkRightDiagonal() {
        int sum = 0;
        int x = board.getVerticalSize() - 1;
        int y = 0;
        for (; x >= 0;) {
            sum += board.getboxValue(x, y);
            x--;
            y++;
        }

        if (threeInARow(sum)) {
            updateWinStats(sum);
            return true;
        }
        return false;
    }

    private boolean checkLeftDiagonal() {
        int sum = 0;
        for (int i = 0; i < board.getVerticalSize(); i++) {
            sum += board.getboxValue(i, i);
        }
        if (threeInARow(sum)) {
            updateWinStats(sum);
            return true;
        }
        return false;
    }

    private boolean checkVerticals() {
        for (int x = 0; x < board.getHorisontalSize(); x++) {
            int sum = 0;
            for (int y = 0; y < board.getVerticalSize(); y++) {
                sum += board.getboxValue(x, y);
            }
            if (threeInARow(sum)) {
                updateWinStats(sum);
                return true;
            }
        }
        return false;
    }

    private void updateWinStats(int sum) {
        if (sum > 0) {
            status.incrementWinValue(Role.CROSS);
        } else {
            status.incrementWinValue(Role.NOUGHT);
        }
    }

    private boolean checkHorisontals() {
        for (int y = 0; y < board.getVerticalSize(); y++) {
            int sum = 0;
            for (int x = 0; x < board.getHorisontalSize(); x++) {
                sum += board.getboxValue(x, y);
            }
            if (threeInARow(sum)) {
                updateWinStats(sum);
                //markWinLine()
                return true;
            }
        }
        return false;
    }

    private static boolean threeInARow(int sum) {
        return Math.abs(sum) == 3;
    }

    private void resetActions() {
        actionsNumber = 0;
    }

    private void loadImages() {
        try {
            cross = ImageIO.read(getClass().getClassLoader().getResource("icons/cross.png"));
            circle = ImageIO.read(getClass().getClassLoader().getResource("icons/circle.png"));
            crossWin = ImageIO.read(getClass().getClassLoader().getResource("icons/cross_WIN.png"));
            circleWin = ImageIO.read(getClass().getClassLoader().getResource("icons/circle_WIN.png"));
        } catch (IOException ex) {
            Logger.getLogger(GameBoardPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
