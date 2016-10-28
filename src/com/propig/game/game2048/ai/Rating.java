package com.propig.game.game2048.ai;

public class Rating {

	private static final double monotonicWeight = 1.0f; 
	private static final double smoothWeitht = 0.1f;
	private static final double spaceNumberWeight = 2.7f;
	private static final double isoSpaceNumberWeight = 1.0f;
	
	private static double monotonic(int[][] board){
		return 0.0 * monotonicWeight;
	}
	
	private static double smooth(int[][] board){
		return 0.0 * smoothWeitht;
	}
	
	private static double spaceNumber(int [][] board){
		return 0.0 * spaceNumberWeight;
	}
	
	private static double isoSpaceNumber(int [][] board){
		return 0.0 * isoSpaceNumberWeight;
	}
	
	public static double rating(int [][] board){
		return monotonic(board) + 
				smooth(board) +
				spaceNumber(board) +
				isoSpaceNumber(board);
	}
	
}
