package com.propig.game.game2048.view;

import java.util.EventListener;

interface ScoreListener extends EventListener {

	public void scoring(ScoreEventObject event);
}
