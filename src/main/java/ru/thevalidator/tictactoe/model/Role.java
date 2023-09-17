/*
 * Copyright (C) 2023 thevalidator
 */

package ru.thevalidator.tictactoe.model;

/**
 * @author thevalidator
 */
public enum Role {
    
    CROSS(1),
    CROSS_WIN(2),
    NOUGHT(-1),
    NOUGHT_WIN(-2);
    
    private final int value;

    private Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
