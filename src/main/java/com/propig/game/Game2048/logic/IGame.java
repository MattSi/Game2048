package com.propig.game.Game2048.logic;

public interface IGame {
	
	public final int GOAL = 2048;
	public final int ROW = 4;
	public final int COL = 4;

    /**
     * Reset or initialize game.
     */
    public void resetGame();

    /**
     * Get game status
     * @return
     */
    public GameStatus getGameStatus();

    /**
     * Return Current Board status
     * @return 1111 1111 1111 1111 1111 1111 1111 1111
     */
    public long getBoardStatus();
    
    /**
     * 
     * @param status
     */
    public void setBoardStatus(long status);

    /**
     * Return Input Number 
     * @return 0000 0000 0000 0100 0000 0000 0000 0000 0000
     */
    public long getCurrentInputNumber();
    
    /**
     * Go next step with direction
     * @param direction
     * @return True means next step moved, False means can not move with this direction
     */
    public boolean goNextStep(MoveDirection direction);
    
}
