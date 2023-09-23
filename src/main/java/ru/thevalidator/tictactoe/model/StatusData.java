/*
 * Copyright (C) 2023 thevalidator
 */

package ru.thevalidator.tictactoe.model;

import javax.swing.JLabel;

/**
 * @author thevalidator
 */
public class StatusData {
    
    private final JLabel crosses;
    private final JLabel noughts;
    private final JLabel status;

    public StatusData(JLabel crosses, JLabel noughts, JLabel status) {
        this.crosses = crosses;
        this.noughts = noughts;
        this.status = status;
    }
    
    public void setStatus(String statusText) {
        status.setText(statusText);
    }
    
    public void incrementWinValue(Role role) {
        if (role.equals(Role.CROSS)) {
            incrementValue(crosses);
        } else if (role.equals(Role.NOUGHT)) {
            incrementValue(noughts);
        }
    }

    private void incrementValue(JLabel label) {
        int actual = Integer.parseInt(label.getText());
        label.setText(String.valueOf(actual + 1));
    }

}
