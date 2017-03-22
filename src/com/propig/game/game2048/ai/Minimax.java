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

    public static MoveDirection findBestMove(GameLogic logic, int depth)
            throws CloneNotSupportedException {
        Map<String, Object> result = alphabeta(logic, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, Player.HUMAN);

        return (MoveDirection) result.get("Direction");
    }

    private static int heuristicScore(int actualScore, int numOfEmptyCells, int clusteringScore) {
        int score = (int) (actualScore + Math.log(actualScore) * numOfEmptyCells - clusteringScore);

        return Math.max(score, Math.min(actualScore, 1));
    }


    private static Map<String, Object> alphabeta(GameLogic gameLogic, int depth, int alpha, int beta, Player player)
            throws CloneNotSupportedException {

        Map<String, Object> result = new HashMap<>();

        MoveDirection bestDirection = null;
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
                    if (!logicNew.move(moveDirection)) {
                        continue;
                    }
                    Map<String, Object> currentResult = alphabeta(logicNew, depth - 1, alpha, beta, Player.COMPUTER);
                    int currentScore = ((Number) currentResult.get("Score")).intValue();
                    if (currentScore > alpha) {
                        alpha = currentScore;
                        bestDirection = moveDirection;
                    }
                    if (beta <= alpha) {
                        break;
                    }
                }
                bestScore = alpha;
            } else {
                List<Integer> moves = gameLogic.getEmptyCellIds();

                int[] possibleValues = {2, 4};
                abortloop:
                for (Integer cellId : moves) {
                    for (int value : possibleValues) {
                        GameLogic newLogic = (GameLogic) gameLogic.clone();
                        newLogic.putNumber(cellId, value);

                        Map<String, Object> currentResult = alphabeta(newLogic, depth - 1, alpha, beta, Player.HUMAN);
                        int currentScore = ((Number) currentResult.get("Score")).intValue();

                        if (currentScore < beta) {
                            beta = currentScore;
                        }
                        if (beta <= alpha) {
                            break abortloop;
                        }
                    }
                }

                bestScore = beta;
                if (moves.isEmpty()) {
                    bestScore = 0;
                }
            }
        }

        result.put("Score", bestScore);
        result.put("Direction", bestDirection);
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
                if (numOfNeighbors != 0)
                    clusteringScore += sum / numOfNeighbors;
            }
        }
        return clusteringScore;
    }
}
