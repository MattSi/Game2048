package com.propig.game.Game2048.logic;

import com.propig.game.Game2048.util.Utils;

public class DefaultGameMatrixCalc {
    private int[][] board;
    private int[] emptyCell;
    private int[] tmpArray;
    GameConstants gameConst;


    public DefaultGameMatrixCalc() {
        gameConst = GameConstants.getInstance();
        board = new int[gameConst.getRow()][gameConst.getCol()];
        emptyCell = new int[gameConst.getRow() * gameConst.getCol()];
        tmpArray = new int[gameConst.getRow() * gameConst.getCol()];
    }

    /**
     * Change Board status to long
     *
     * @return
     */
    public long getBoardStatus() {
        long status = 0L;
        long preStatus = 0L;
        for (int i = 0; i < gameConst.getRow(); i++) {
            for (int j = 0; j < gameConst.getCol(); j++) {
                status |= (long) Utils.log2(board[i][j]);
                preStatus = status;
                status <<= 4;
            }
        }
        return preStatus;
    }

    public void setBoardStatus(long status) {
        long mask = 0xF000000000000000L;
        long mask2 = 0x000000000000000FL;
        long indx;

        for (int i = 0; i < gameConst.getRow(); i++) {
            for (int j = 0; j < gameConst.getCol(); j++) {
                indx = (status & mask) >>> 60;
                indx &= mask2;
                board[i][j] = (int) Math.pow(2, indx);
                status <<= 4;
            }
        }
    }

    public boolean move(long currentStatus, MoveDirection direction) {
        setBoardStatus(currentStatus);

        switch (direction) {
            case LEFT:
                break;
            case RIGHT:
                break;
            case UP:
                break;
            case DOWN:
                break;
        }
        return getBoardStatus() != currentStatus;
    }

}
