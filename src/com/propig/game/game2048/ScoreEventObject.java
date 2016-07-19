package com.propig.game.game2048;

import java.util.EventObject;

class ScoreEventObject extends EventObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4070276983700285305L;
	private int score;
	
	public ScoreEventObject(Object source, int score) {
		super(source);
		// TODO Auto-generated constructor stub
		
		this.setScore(score);
	}
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	
}
