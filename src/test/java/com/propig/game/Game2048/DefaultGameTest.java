package com.propig.game.Game2048;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.propig.game.Game2048.logic.DefaultGame;
import com.propig.game.Game2048.logic.IGame;

import java.util.Random;


public class DefaultGameTest {
	
	IGame game;
	
	@Before
	public void setUp(){
		game = new DefaultGame();
	}
	
	
	@Test
	public void testBoardStatus(){
		Random random = new Random();

		long status = 0L;
		long result = 0L;

		for(int i=0; i<1000000; i++){
			status = random.nextLong();
			game.setBoardStatus(status);
			result = game.getBoardStatus();
			Assert.assertTrue(status==result);
		}

		

	}
}
