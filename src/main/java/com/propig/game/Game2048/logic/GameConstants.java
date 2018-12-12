package com.propig.game.Game2048.logic;

public class GameConstants {
    private int goal = 2048;
    private int row = 4;
    private int col = 4;

    private static GameConstants instance = new GameConstants();

    private GameConstants(){}

    public static GameConstants getInstance(){
        return instance;
    }

    public int getGoal(){
        return goal;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }
}
