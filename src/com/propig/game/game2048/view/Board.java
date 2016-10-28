package com.propig.game.game2048.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.propig.game.game2048.logic.GameLogic;
import com.propig.game.game2048.logic.MoveDirection;

public class Board extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8304836464559068263L;
	private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
	private static final String MOVE_UP = "move up";
	private static final String MOVE_DOWN = "move down";
	private static final String MOVE_LEFT = "move left";
	private static final String MOVE_RIGHT = "move right";

	private final int BoardWidth = 4;
	private final int BoardHeight = 4;
	private Color background = null;
	private Color blockBackground = null;
	private GameLogic logic = null;
	private Collection<ScoreListener> listener = null;
	private ScoreEventObject eventObject = null;
	private Font font = null;

	public void addListener(ScoreListener item) {
		if (listener == null) {
			listener = new HashSet<ScoreListener>();
		}

		listener.add(item);
	}

	public void moveListener(ScoreListener item) {
		if (listener == null) {
			return;
		}
		listener.remove(item);
	}

	private void doOperation(int score) {
		if (listener == null) {
			return;
		}
//		ScoreEventObject eventObject = new ScoreEventObject(this, score);

		eventObject.setScore(score);
		notifyAllListener(eventObject);
	}

	private void notifyAllListener(ScoreEventObject eventObject) {
		Iterator<ScoreListener> iterator = listener.iterator();

		while (iterator.hasNext()) {
			ScoreListener listener = iterator.next();
			listener.scoring(eventObject);
		}
	}

	public Board(GameLogic logic) {
		super(true);
		background = new Color(187, 173, 160);
		blockBackground = new Color(205, 193, 180);

		bindSomeKeys();
		this.logic = logic;
		this.logic.resetGame();
		this.eventObject = new ScoreEventObject(this,  0);
		this.font = new Font("Serif", Font.BOLD, 40);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// TODO do your own paint
		doDrawing(g);
	}

	private void doDrawing(Graphics g) {
		Dimension size = getSize();
		int gap = 5; // pixes
		int intGap = 5; // pixes
		int backgroundLength = 0;
		int internalBackLength = 0;

		// prepare the square background
		if (size.getWidth() > size.getHeight()) {
			backgroundLength = (int) (size.getHeight() - gap * 2);
		} else {
			backgroundLength = (int) (size.getWidth() - gap * 2);
		}
		int x = (int) (size.getWidth() - backgroundLength) / 2;
		int y = (int) (size.getHeight() - backgroundLength) / 2;
		g.setColor(background);
		g.fillRect(x, y, backgroundLength, backgroundLength);

		// prepare blocks that contain numbers
		internalBackLength = (backgroundLength - (BoardHeight + 1) * intGap) / BoardHeight;
		g.setColor(blockBackground);
		for (int yy = 0; yy < logic.getBoardLength(); yy++) {
			for (int xx = 0; xx < BoardWidth; xx++) {
				int number = logic.getItemOfBoard(yy, xx);

				if (number == 0) {
					g.setColor(BlockColor.getBlockColor(number, true));

					g.fillRoundRect(x + (xx + 1) * intGap + xx * internalBackLength,
							y + (yy + 1) * intGap + yy * internalBackLength, internalBackLength, internalBackLength, 10,
							10);
					continue;
				}
				g.setColor(BlockColor.getBlockColor(getNumberToDisplay(number), true));

				g.fillRoundRect(x + (xx + 1) * intGap + xx * internalBackLength,
						y + (yy + 1) * intGap + yy * internalBackLength, internalBackLength, internalBackLength, 10,
						10);

				g.setColor(BlockColor.getBlockColor(getNumberToDisplay(number), false));
				g.setFont(font);
				drawString(g, String.valueOf(number), x + (xx + 1) * intGap + xx * internalBackLength,
						y + (yy + 1) * intGap + yy * internalBackLength, internalBackLength);
			}
		}
	}

	private int getNumberToDisplay(int num) {
		int index = (int) (Math.log((double) num) / Math.log((2.0d)));
		return index;
	}

	private void drawString(Graphics g, String str, int xPos, int yPos, int width) {
		FontMetrics fMetrics = g.getFontMetrics();
		Rectangle2D r = fMetrics.getStringBounds(str, g);
		int x = (width - (int) r.getWidth()) / 2;
		int y = (width - (int) r.getHeight()) / 2 + fMetrics.getAscent();
		g.drawString(str, x + xPos, y + yPos);
	}

	private void bindSomeKeys() {
		getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), MOVE_UP);
		getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), MOVE_DOWN);
		getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), MOVE_LEFT);
		getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), MOVE_RIGHT);
		getActionMap().put(MOVE_UP, new MoveUpAction());
		getActionMap().put(MOVE_DOWN, new MoveDownAction());
		getActionMap().put(MOVE_LEFT, new MoveLeftAction());
		getActionMap().put(MOVE_RIGHT, new MoveRightAction());
	}

	private class MoveRightAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = -4363931925844495698L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (logic.move(MoveDirection.RIGHT)) {
				moveAction();
			}

		}

	}

	private class MoveLeftAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2450292966312852300L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (logic.move(MoveDirection.LEFT)) {
				moveAction();
			}
		}
	}

	private class MoveDownAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = -3786944203907341929L;

		@Override
		public void actionPerformed(ActionEvent e) {

			if (logic.move(MoveDirection.DOWN)) {
				moveAction();
			}
		}
	}

	private class MoveUpAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 8839422076870034522L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (logic.move(MoveDirection.UP)) {
				moveAction();
			}
		}
	}

	private void moveAction() {
		boolean isGameOver = logic.isGameOver();
		boolean isSuccess = logic.gotSuccess();

		if (!isGameOver && !isSuccess) {
			doOperation(logic.getScore());
			logic.putNumber();

		} else {
			if (isGameOver) {
				JOptionPane.showMessageDialog(new JFrame(), "Failed, Please try again.", "Game 2048",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				// means you got the goal.
				JOptionPane.showMessageDialog(new JFrame(), "Congratulation! You got 2048!", "Game 2048",
						JOptionPane.INFORMATION_MESSAGE);
			}

			resetGame();
		}
		repaint();

		if (logic.isGameOver()) {
			JOptionPane.showMessageDialog(new JFrame(), "Failed, Please try again.", "Game 2048",
					JOptionPane.INFORMATION_MESSAGE);
			resetGame();
			repaint();
		}
	}

	public void resetGame() {
		logic.resetGame();
		doOperation(0);
		repaint();
	}
}
