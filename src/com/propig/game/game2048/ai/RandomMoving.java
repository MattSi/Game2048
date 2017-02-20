package com.propig.game.game2048.ai;

import com.propig.game.game2048.logic.GameLogic;
import com.propig.game.game2048.logic.MoveDirection;
import com.propig.game.game2048.view.Board;

/**
 * Created by msi on 2017/2/20.
 */
public class RandomMoving extends AutoMoving{

    public RandomMoving(Board board, GameLogic logic){
        super(board, logic);
    }

    @Override
    public void doMove(MoveDirection direction) {

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
