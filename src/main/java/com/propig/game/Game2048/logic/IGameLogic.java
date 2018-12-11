package com.propig.game.Game2048.logic;

public interface IGameLogic {

    /**
     * Reset or initialize game.
     */
    public void resetGame();

    /**
     * Get move status
     * @return
     */
    public MoveStatus getMoveStatus();

    /**
     * Get game status
     * @return
     */
    public GameStatus getGameStatus();

    /**
     * Return Current Board status
     * @return 1111 1111 1111 1111 1111 1111 1111 1111
     */
    public int getBoardStatus();

    /**
     * Go next step with direction
     * @param direction
     * @return True means next step moved, False means can not move with this direction
     */
    public boolean goNextStep(MoveDirection direction);

}
