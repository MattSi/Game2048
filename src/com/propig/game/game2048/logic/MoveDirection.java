package com.propig.game.game2048.logic;

public enum MoveDirection {
    UP(0, "Up"),

    DOWN(1, "Down"),

    LEFT(2, "Left"),

    RIGHT(3, "Right");

    private final int code;
    private final String description;

    private MoveDirection(final int code, final String desc) {
        this.code = code;
        this.description = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}


