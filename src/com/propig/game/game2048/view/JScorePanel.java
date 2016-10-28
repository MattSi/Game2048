package com.propig.game.game2048.view;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JScorePanel extends JLabel implements ScoreListener {

	private int currentScore = 0;
	private static final String text = "Score: ";
	
	public JScorePanel(){
		super(text, SwingConstants.CENTER);
		this.currentScore = 0;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -904071664654050941L;

	@Override
	public void scoring(ScoreEventObject event) {
		// TODO Auto-generated method stub
		
		currentScore = event.getScore();
		setText("Score: " + String.valueOf(currentScore));
		
	}

}
