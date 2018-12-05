package com.propig.game.Game2048.logic;

import java.util.*;

public class GameLogic implements Cloneable {

    private static int goal = 2048;
    private static int boardLength = 4;
    private int[][] board = null;
    private int[][] lastBoard = null;
    private int[] emptyCell = null;
    private int[] tmpArray = null;
    private int score;
    private boolean got2048;
    private Random random = null;
    private boolean isMoved = false;

    public boolean isMoved(){
        return isMoved;
    }
    public int getLastRandomPut() {
        return lastRandomPut;
    }

    private int lastRandomPut = -1;

    /**
     * Constructor, and init the game board.
     */
    public GameLogic() {
        board = new int[boardLength][boardLength];
        lastBoard = new int[boardLength][boardLength];
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                board[i][j] = 0;
                lastBoard[i][j] = 0;
            }
        }

        score = 0;
        got2048 = false;
        emptyCell = new int[boardLength * boardLength];
        tmpArray = new int[boardLength];
        random = new Random();
    }

    public int[][] getBoard() {
        return clone2dArray(board);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        GameLogic copy = (GameLogic) super.clone();
        copy.board = clone2dArray(board);
        copy.lastBoard = clone2dArray(lastBoard);
        copy.emptyCell = emptyCell.clone();
        copy.tmpArray = tmpArray.clone();
        return copy;
    }

    public boolean isEqual(int[][] newBoard) {
        boolean equal = true;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != newBoard[i][j]) {
                    equal = false; //The two boards are not same.
                    return equal;
                }
            }
        }
        return equal;
    }

    private int[][] clone2dArray(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }

    public int getBoardLength() {
        return boardLength;
    }

    public int getItemOfBoard(int x, int y) {
        return board[x][y];
    }

    public void resetGame() {
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                board[i][j] = 0;
                lastBoard[i][j] = 0;
            }
        }
        for (int i = 0; i < boardLength * boardLength; i++) {
            emptyCell[i] = 0;
        }
        score = 0;
        got2048 = false;
        isMoved = false;
        putNumber();
        putNumber();
    }

    public int getScore() {
        return score;
    }

    public boolean gotSuccess() {
        return got2048;
    }

    public List<Integer> getEmptyCellIds() {
        List<Integer> cellList = new ArrayList<>();

        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (board[i][j] == 0) {
                    cellList.add(i * boardLength + j);
                }
            }
        }
        return cellList;
    }

    public int getNumberOfEmptyCells() {
        int emptyLength = 0;

        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (board[i][j] == 0) {
                    ++emptyLength;
                }
            }
        }

        return emptyLength;
    }

    /**
     * Get next available cell, that can be used to put a random number.
     *
     * @return cell index, [0 - boardLength * boardLength -1]
     */
    private int getNextRandomCell() {
        int emptyLength = 0;
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (board[i][j] == 0) {
                    emptyCell[emptyLength++] = i * boardLength + j;
                }
            }
        }
        if (emptyLength == 0) {
            return -1;
        }
        int index = random.nextInt(emptyLength);
        return emptyCell[index];
    }

    /**
     * 8/10 chance to return 2,
     *
     * @return
     */
    private int getNextNumber() {
        int rndNumber = random.nextInt(10);
        if (rndNumber < 8)
            return 2;
        return 4;
    }

    public void putNumber() {
        int cellIndex = getNextRandomCell();
        if (cellIndex == -1)
            return;
        int number = getNextNumber();
        int row = cellIndex / boardLength;
        int col = cellIndex % boardLength;
        board[row][col] = number;
    }

    public void putNumber(int cellId, int value) {
        int row = cellId / boardLength;
        int col = cellId % boardLength;
        board[row][col] = value;
    }

    public boolean move(MoveDirection md) {
        int currentScore;
        isMoved = false;
        for (int i = 0; i < boardLength; i++) {
            System.arraycopy(board[i], 0, lastBoard[i], 0, boardLength);
        }
        switch (md) {
            case LEFT:
                // from up to down, from left to right, reduce the number on board.
                currentScore = moveLeft();
                break;
            case RIGHT:
                // from up and down, from right to left, reduce the number on board.
                currentScore = moveRight();
                break;
            case UP:
                // from down to up, from left to right, reduce the number on board.
                currentScore = moveUp();
                break;
            default:
                // DOWN,
                // from down to up, from left to right, reduce the number on board.
                currentScore = moveDown();
                break;
        }
        this.score += currentScore;

        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (lastBoard[i][j] != board[i][j]) {
                    isMoved = true;
                }
                if (board[i][j] == goal) {
                    got2048 = true;
                }
            }
        }
        return isMoved;
    }

    private void removeMiddleZero(MoveDirection direction, int row, int col) {
        int i, cIndex;
        for (i = 0; i < boardLength; i++) {
            tmpArray[i] = 0;
        }
        switch (direction) {
            case LEFT:
                cIndex = 0;
                for (i = 0; i < boardLength; i++) {
                    if (board[row][i] != 0) {
                        tmpArray[cIndex] = board[row][i];
                        cIndex++;
                    }
                }
                for (i = 0; i < boardLength; i++) {
                    board[row][i] = tmpArray[i];
                }
                break;
            case RIGHT:
                cIndex = boardLength - 1;
                for (i = boardLength - 1; i >= 0; i--) {
                    if (board[row][i] != 0) {
                        tmpArray[cIndex] = board[row][i];
                        cIndex--;
                    }
                }
                for (i = boardLength - 1; i >= 0; i--) {
                    board[row][i] = tmpArray[i];
                }
                break;
            case UP:
                cIndex = 0;
                for (i = 0; i < boardLength; i++) {
                    if (board[i][col] != 0) {
                        tmpArray[cIndex] = board[i][col];
                        cIndex++;
                    }
                }
                for (i = 0; i < boardLength; i++) {
                    board[i][col] = tmpArray[i];
                }
                break;
            case DOWN:
                cIndex = boardLength - 1;
                for (i = boardLength - 1; i >= 0; i--) {
                    if (board[i][col] != 0) {
                        tmpArray[cIndex] = board[i][col];
                        cIndex--;
                    }
                }
                for (i = boardLength - 1; i >= 0; i--) {
                    board[i][col] = tmpArray[i];
                }
                break;
        }
    }

    /**
     * @return means how much score in this move
     */
    private int moveLeft() {
        int cIndex;
        int currentScore = 0;
        for (int row = 0; row < boardLength; row++) {
            removeMiddleZero(MoveDirection.LEFT, row, -1);
            cIndex = 0;
            while (cIndex < boardLength - 1) {
                if (board[row][cIndex] == board[row][cIndex + 1]) {
                    board[row][cIndex] *= 2;
                    board[row][cIndex + 1] = 0;
                    currentScore += board[row][cIndex];
                    cIndex += 2;
                } else {
                    cIndex++;
                }
            }
            removeMiddleZero(MoveDirection.LEFT, row, -1);
        }

        return currentScore;
    }

    private int moveRight() {
        int cIndex;
        int currentScore = 0;
        for (int row = 0; row < boardLength; row++) {
            removeMiddleZero(MoveDirection.RIGHT, row, -1);
            cIndex = boardLength - 1;
            while (cIndex > 0) {
                if (board[row][cIndex] == board[row][cIndex - 1]) {
                    board[row][cIndex] *= 2;
                    board[row][cIndex - 1] = 0;
                    currentScore += board[row][cIndex];
                    cIndex -= 2;
                } else {
                    cIndex--;
                }
            }
            removeMiddleZero(MoveDirection.RIGHT, row, -1);
        }

        return currentScore;
    }

    private int moveUp() {
        int cIndex;
        int currentScore = 0;
        for (int col = 0; col < boardLength; col++) {
            removeMiddleZero(MoveDirection.UP, -1, col);
            cIndex = 0;
            while (cIndex < boardLength - 1) {
                if (board[cIndex][col] == board[cIndex + 1][col]) {
                    board[cIndex][col] *= 2;
                    board[cIndex + 1][col] = 0;
                    currentScore += board[cIndex][col];
                    cIndex += 2;
                } else {
                    cIndex++;
                }
            }
            removeMiddleZero(MoveDirection.UP, -1, col);
        }
        return currentScore;
    }

    private int moveDown() {
        int cIndex;
        int currentScore = 0;
        for (int col = 0; col < boardLength; col++) {
            removeMiddleZero(MoveDirection.UP, -1, col);
            cIndex = boardLength - 1;
            while (cIndex > 0) {
                if (board[cIndex][col] == board[cIndex - 1][col]) {
                    board[cIndex][col] *= 2;
                    board[cIndex - 1][col] = 0;
                    currentScore += board[cIndex][col];
                    cIndex -= 2;
                } else {
                    cIndex--;
                }
            }
            removeMiddleZero(MoveDirection.DOWN, -1, col);
        }
        return currentScore;
    }

    /**
     * We have to check whether the game is over.
     *
     * @return
     */
    public boolean isGameOver() {
        int i, j;
        for (i = 0; i < boardLength; i++) {
            for (j = 0; j < boardLength; j++) {
                if (board[i][j] == 0)
                    return false;
            }
        }

        for (i = 0; i < boardLength - 1; i++) {
            for (j = 0; j < boardLength - 1; j++) {
                if (board[i][j] == board[i][j + 1] || board[i][j] == board[i + 1][j])
                    return false;
            }
        }
        // Now we check the board condition
        for (j = 0; j < boardLength - 1; j++) {
            if (board[boardLength - 1][j] == board[boardLength - 1][j + 1])
                return false;
        }
        for (i = 0; i < boardLength - 1; i++) {
            if (board[i][boardLength - 1] == board[i + 1][boardLength - 1])
                return false;
        }
        if (board[boardLength - 2][boardLength - 1] == board[boardLength - 1][boardLength - 1]
                || board[boardLength - 1][boardLength - 2] == board[boardLength - 1][boardLength - 1]) {
            return false;
        }
        return true;
    }

}
