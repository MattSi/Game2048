package com.propig.game.Game2048.logic;

import java.util.ArrayList;
import java.util.List;

import com.propig.game.Game2048.util.Utils;

public class DefaultGame implements IGame {
	
	private int[][] board = null;
	private int score;
	private int nextNumber;
	private List<Integer> wayback;
	private GameStatus gameStatus;
	
	public DefaultGame() {
		resetGame();
	}

	@Override
	public void resetGame() {
		board = new int[ROW][COL];
		for(int i=0; i<ROW; i++){
			for(int j=0; j<COL; j++){
				board[i][j] = 0;
			}
		}
		wayback = new ArrayList<Integer>();
		score = 0;
		gameStatus = GameStatus.Running;
		
	}

	@Override
	public GameStatus getGameStatus() {
		return gameStatus;
	}

	@Override
	public long getBoardStatus() {
		long status = 0L;
		long preStatus = 0L;
		double indx = 0D;
		for(int i=0; i<ROW; i++){
			for(int j=0; j<COL; j++){
				preStatus = status;
				indx = Utils.log2(board[i][j]);
				status |=(long)indx;
				preStatus = status;
				status <<= 4;
			}
		}
		
		return preStatus;
	}

	@Override
	public void setBoardStatus(long status) {
		long mask =  0xF000000000000000L;
		long mask2 = 0x000000000000000FL;
		long indx=0;
		long tmp=0;
		
		for(int i=0; i<ROW; i++){
			for(int j=0; j<COL; j++){
				indx = status & mask;
				tmp = indx >>> 60;
				tmp = tmp & mask2;
				board[i][j] = (int)Math.pow(2, tmp);
				status <<= 4;
				
			}
		}
	}
	
	@Override
	public long getCurrentInputNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean goNextStep(MoveDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}



}
