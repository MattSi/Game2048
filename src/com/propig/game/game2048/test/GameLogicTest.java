package com.propig.game.game2048.test;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.propig.game.game2048.logic.GameLogic;

import org.junit.Assert;


public class GameLogicTest {

	GameLogic logic ;
	@Before
	public void setUp() throws Exception {
		logic = new GameLogic();
	}

	@After
	public void tearDown() throws Exception {
	}

	private void setBoard(int[][] newBoard) throws Exception{
		Field fieldBoard = logic.getClass().getDeclaredField("board");
		fieldBoard.setAccessible(true);
		fieldBoard.set(logic, newBoard);
	}
	
	@Test
	public void test_is_game_over() throws Exception{
		int[][] board1 = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
		int[][] board2 = {{2,4,2,8},{4,8,16,2},{2,4,2,4},{4,2,8,4}};
		int[][] board3 = {{2,4,2,8},{4,8,64,4},{2,4,8,16},{4,2,32,2}};
		
		setBoard(board1);
		Assert.assertTrue(!logic.isGameOver());
		
		
		setBoard(board2);
		Assert.assertTrue(!logic.isGameOver());
		
		setBoard(board3);
		Assert.assertTrue(logic.isGameOver());
	}
	
	@Test
	public void test_get_next_random_cell() throws Exception{
		int[][] board2 = {{2,4,2,0},{4,8,16,2},{2,4,2,4},{4,2,8,4}};
		int[][] board3 = {{2,4,2,8},{0,8,64,4},{2,4,0,16},{4,2,0,0}};
		Set<Integer> cellSet = new HashSet<Integer>();
		
		setBoard(board2);
		
		Method testGetNextRandomCell = logic.getClass().getDeclaredMethod("getNextRandomCell");
		testGetNextRandomCell.setAccessible(true);
		Object result = testGetNextRandomCell.invoke(logic);
		int nextCell = Integer.parseInt(String.valueOf(result));

		Assert.assertTrue(nextCell == 3);
		
		setBoard(board3);
		cellSet.add(4);
		cellSet.add(10);
		cellSet.add(14);
		cellSet.add(15);
		result = testGetNextRandomCell.invoke(logic);
		nextCell = Integer.parseInt(String.valueOf(result));
		System.err.println(nextCell);
		Assert.assertTrue(cellSet.contains(nextCell));
	}
	
	
	@Test
	public void test_move_left() throws Exception{
		int[][] board2 = {{2,2, 4,4,},{0,0,0,0},{0,0,0,0},{8,0,8,0}};
		setBoard(board2);
		
		Method testMoveLeft = logic.getClass().getDeclaredMethod("moveLeft");
		testMoveLeft.setAccessible(true);
		Object result = testMoveLeft.invoke(logic);
		int score = Integer.parseInt(String.valueOf(result));

		Assert.assertTrue(score == 28);	
	}
	
	@Test
	public void test_move_right() throws Exception{
		int[][] board3 = {{2,4,2,8},{0,8,64,4},{2,4,0,16},{4,2,2,0}};
		setBoard(board3);
		
		Method testMoveRight = logic.getClass().getDeclaredMethod("moveRight");
		testMoveRight.setAccessible(true);
		Object result = testMoveRight.invoke(logic);
		int score = Integer.parseInt(String.valueOf(result));

		Assert.assertTrue(score == 4);	
	}
	
	@Test
	public void test_move_up() 
			throws Exception{
		int[][] board3 = {{2,4,2,8},{0,8,4,8},{2,4,0,16},{4,2,2,0}};
		setBoard(board3);
		
		
		
		Method testMoveUp = logic.getClass().getDeclaredMethod("moveUp");
		testMoveUp.setAccessible(true);
		Object result = testMoveUp.invoke(logic);
		int score = Integer.parseInt(String.valueOf(result));
		Assert.assertTrue(score == 20);
	}
	
	
	@Test
	public void test_move_down() throws Exception{
		int[][] board3 = {{2,4,2,8},{0,8,4,8},{2,4,0,16},{4,2,2,0}};
		setBoard(board3);

		Method testMoveDown = logic.getClass().getDeclaredMethod("moveDown");
		testMoveDown.setAccessible(true);
		Object result = testMoveDown.invoke(logic);
		int score = Integer.parseInt(String.valueOf(result));

		Assert.assertTrue(score == 20);
	}
}
