package com.propig.game.game2048.view;

import java.util.EventListener;

interface ScoreListener extends EventListener {

	void scoring(ScoreEventObject event);
}
