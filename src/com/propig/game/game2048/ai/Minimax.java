package com.propig.game.game2048.ai;

import com.propig.game.game2048.logic.GameLogic;
import com.propig.game.game2048.logic.MoveDirection;

import java.util.*;

/**
 * Created by msi on 2017/3/19.
 */
public class Minimax {

    public enum Player {
        COMPUTER,
        HUMAN
    }

    private static int heuristicScore(int actualScore, int numOfEmptyCells, int clusteringScore) {
        int score = (int) (actualScore + Math.log(actualScore) * numOfEmptyCells - clusteringScore);

        return Math.max(score, Math.min(actualScore, 1));
    }


    private static Map<String, Object> alphabeta(GameLogic gameLogic, int depth, int alpha, int beta, Player player) throws CloneNotSupportedException {

        Map<String, Object> result = new HashMap<>();

        MoveDirection direction = null;
        int bestScore = 0;

        if (gameLogic.isGameOver()) {
            if (gameLogic.gotSuccess()) {
                bestScore = Integer.MAX_VALUE;
            } else {
                bestScore = Math.min(gameLogic.getScore(), 1);
            }
        } else if (depth == 0) {
            bestScore = heuristicScore(gameLogic.getScore(),
                    gameLogic.getNumberOfEmptyCells(),
                    calClusteringScore(gameLogic.getBoard()));
        } else {
            if (player == Player.HUMAN) {
                bestScore = Integer.MIN_VALUE;
                for (MoveDirection moveDirection : MoveDirection.values()) {
                    GameLogic logicNew = (GameLogic) gameLogic.clone();
                    int currentScore;
                    if( !logicNew.move(moveDirection)){
                        continue;
                    }
                    currentScore = logicNew.getScore();
                    Map<String, Object> currentResult = alphabeta(logicNew, depth-1, alpha,beta, Player.COMPUTER);
                }
            } else {

            }
        }

        result.put("Score", bestScore);
        result.put("Direction", direction);
        return result;
    }

    private static int calClusteringScore(int[][] board) {
        int clusteringScore = 0;
        int[] neighbors = {-1, 0 - 1};


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    continue;
                }

                int numOfNeighbors = 0;
                int sum = 0;
                for (int k : neighbors) {
                    int x = i + k;
                    if (x < 0 || x >= board.length) {
                        continue;
                    }

                    for (int l : neighbors) {
                        int y = j + l;
                        if (y < 0 || y >= board.length) {
                            continue;
                        }

                        if (board[x][y] > 0) {
                            ++numOfNeighbors;
                            sum += Math.abs(board[i][j] - board[x][y]);
                        }
                    }
                }

                clusteringScore += sum / numOfNeighbors;
            }
        }
        return clusteringScore;
    }
}
