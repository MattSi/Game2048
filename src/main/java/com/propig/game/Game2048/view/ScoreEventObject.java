package com.propig.game.Game2048.view;

import java.util.EventObject;

class ScoreEventObject extends EventObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4070276983700285305L;
	private int score;
	
	ScoreEventObject(Object source, int score) {
		super(source);
		// TODO Auto-generated constructor stub
		
		this.setScore(score);
	}
	/**
	 * @return the score
	 */
	int getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	void setScore(int score) {
		this.score = score;
	}

	
}
