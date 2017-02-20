package com.propig.game.game2048.ai;

import com.propig.game.game2048.logic.GameLogic;
import com.propig.game.game2048.logic.MoveDirection;
import com.propig.game.game2048.view.Board;

/**
 * Created by msi on 2017/2/20.
 */
public abstract class AutoMoving {
    private int steps;

    public int getSteps() {
        return steps;
    }

    private GameLogic logic;
    private Board board;

    public AutoMoving(Board board, GameLogic logic) {
        this.steps = 0;
        this.logic = logic;
        this.board = board;
        if (this.logic == null || this.board == null) {
            throw new RuntimeException("Init Error, please re-start this application.");
        }
    }

    public abstract void doMove(MoveDirection direction);

    public abstract boolean canMove(MoveDirection direction);

    public abstract float rating(MoveDirection direction);

}
