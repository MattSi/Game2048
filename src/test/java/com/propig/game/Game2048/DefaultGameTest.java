package com.propig.game.Game2048;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.propig.game.Game2048.logic.DefaultGame;
import com.propig.game.Game2048.logic.IGame;


public class DefaultGameTest {
	
	IGame game;
	
	@Before
	public void setUp(){
		game = new DefaultGame();
	}
	
	
	@Test
	public void testBoardStatus(){
		long status = 0x2312231223122312L;
		
		game.setBoardStatus(status);
		long result = game.getBoardStatus();
		
		Assert.assertTrue(status==result);
	}
}
