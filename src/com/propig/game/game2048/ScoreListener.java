package com.propig.game.game2048;

import java.util.EventListener;

interface ScoreListener extends EventListener {

	public void scoring(ScoreEventObject event);
}
