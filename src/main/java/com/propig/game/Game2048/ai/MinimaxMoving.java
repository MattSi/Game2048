package com.propig.game.Game2048.ai;

import com.propig.game.Game2048.logic.GameLogic;
import com.propig.game.Game2048.logic.MoveDirection;
import com.propig.game.Game2048.view.Board;

import java.util.Random;

/**
 * Created by sim on 3/22/2017.
 */
public class MinimaxMoving extends AutoMoving {
    public MinimaxMoving(Board board, GameLogic logic) {
        super(board, logic);
    }

    @Override
    public void play() throws CloneNotSupportedException {
        if (!isRunning)
            return;
        MoveDirection moveDirection = Minimax.findBestMove(logic, 8);

        if(logic.move(moveDirection)){
            board.moveAction();
        }

        if (logic.isGameOver() || logic.gotSuccess()) {
            isRunning = false;
        }
    }

    @Override
    public boolean canMove(MoveDirection direction) {
        return false;
    }

    @Override
    public float rating(MoveDirection direction) {
        return 0;
    }
}
