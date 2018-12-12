package com.propig.game.Game2048.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.propig.game.Game2048.util.Utils;

public class DefaultGame implements IGame {

    private int score;
    private long board;
    private int nextNumber;
    private List<Integer> wayback;
    private GameStatus gameStatus;
    private long currInputNumber;
    private Random random;
    private GameConstants gameConst;

    public DefaultGame() {
        resetGame();
    }

    @Override
    public void resetGame() {
        wayback = new ArrayList<Integer>();
        score = 0;
        gameStatus = GameStatus.Running;
        random = new Random();
        currInputNumber = 0;
    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public long getBoardStatus() {
        return board;
    }

    @Override
    public void setBoardStatus(long status) {
        board = status;
    }

    @Override
    public long getCurrentInputNumber() {
        return currInputNumber;
    }

    @Override
    public boolean goNextStep(MoveDirection direction) {
        // TODO Auto-generated method stub
        return false;
    }

    private int generateNextNumber(){
        int rnd = random.nextInt(10);
        if (rnd < 8){
            return 2;
        }
        return 4;
    }

}
