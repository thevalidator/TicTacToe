/*
 * Copyright (C) 2023 thevalidator
 */

package ru.thevalidator.tictactoe.model;

/**
 * @author thevalidator
 */
public enum Role {
    
    CROSS(1),
    NOUGHT(-1);
    
    private final int value;

    private Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
