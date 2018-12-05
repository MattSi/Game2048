package com.propig.game.Game2048.view;

import java.util.EventListener;

interface ScoreListener extends EventListener {

	void scoring(ScoreEventObject event);
}
