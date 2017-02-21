package com.propig.game.game2048.ai;

import com.propig.game.game2048.logic.GameLogic;
import com.propig.game.game2048.logic.MoveDirection;
import com.propig.game.game2048.view.Board;

/**
 * Created by msi on 2017/2/20.
 */
public abstract class AutoMoving {
    protected int steps;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    protected boolean isRunning;

    public int getSteps() {
        return steps;
    }

    protected GameLogic logic;
    protected Board board;

    public AutoMoving(Board board, GameLogic logic) {
        this.steps = 0;
        this.logic = logic;
        this.board = board;
        this.isRunning = false;
        if (this.logic == null || this.board == null) {
            throw new RuntimeException("Init Error, please re-start this application.");
        }
    }

    public abstract void play();

    public abstract boolean canMove(MoveDirection direction);

    public abstract float rating(MoveDirection direction);

}
