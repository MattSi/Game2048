package com.propig.game.Game2048.view;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JBestScorePanel extends JLabel implements ScoreListener {

	private int bestScore = 0;
	private static final String text = "Best : ";
	
	public JBestScorePanel(){
		super(text, SwingConstants.CENTER);
		bestScore = 0;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8893350675564671304L;

	@Override
	public void scoring(ScoreEventObject event) {
		// TODO Auto-generated method stub
		int currentScore = event.getScore();
		if(bestScore <= currentScore){
			bestScore = currentScore;
			setText("Best : " + String.valueOf(bestScore));
		}
	}


}
