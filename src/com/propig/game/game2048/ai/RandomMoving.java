package com.propig.game.game2048.ai;

import com.propig.game.game2048.logic.GameLogic;
import com.propig.game.game2048.logic.MoveDirection;
import com.propig.game.game2048.view.Board;

import java.util.Random;

/**
 * Created by msi on 2017/2/20.
 */
public class RandomMoving extends AutoMoving {

    public RandomMoving(Board board, GameLogic logic) {
        super(board, logic);
    }

    @Override
    public void play() {
        if (!isRunning)
            return;
        Random rnd = new Random();
        /*
         * 1. generate a random number between 0, 3
         * 2. a random number maps to a direction
         * 3. check whether this direction is available
         */
        MoveDirection direction;
        while (true) {

            switch (rnd.nextInt(4)) {
                case 0:
                    direction = MoveDirection.DOWN;
                    break;
                case 1:
                    direction = MoveDirection.LEFT;
                    break;
                case 2:
                    direction = MoveDirection.RIGHT;
                    break;
                default:
                    direction = MoveDirection.UP;
                    break;
            }
            if (logic.move(direction)) {
                board.moveAction();
                break;
            }
        }

        if (logic.isGameOver() || logic.gotSuccess() ) {
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
