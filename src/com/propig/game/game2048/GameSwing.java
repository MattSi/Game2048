package com.propig.game.game2048;

import com.propig.game.game2048.logic.GameLogic;
import com.propig.game.game2048.view.Board;
import com.propig.game.game2048.view.JBestScorePanel;
import com.propig.game.game2048.view.JScorePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameSwing extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6320473553027546885L;

	private GameSwing() {
		setUI();
	}

	private Board board = null;

	private void createLayout() {
		// Init information bar
		GameLogic logic = new GameLogic();
		board = new Board(logic);

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(1, 2, 10, 10));

		JLabel logo = new JLabel("2048", SwingConstants.CENTER);
		logo.setFont(new Font("Serif", Font.BOLD, 50));
		logo.setBackground(new Color(237, 197, 1));
		logo.setForeground(Color.white);
		logo.setOpaque(true);

		JPanel infoRightPanel = new JPanel(new GridLayout(2, 1));
		JScorePanel currScore = new JScorePanel();
		currScore.setFont(new Font("Serif", Font.BOLD, 25));
		currScore.setBackground(Color.magenta);
		currScore.setForeground(Color.white);
		currScore.setOpaque(true);

		JBestScorePanel bestScore = new JBestScorePanel();
		bestScore.setFont(new Font("Serif", Font.BOLD, 25));
		bestScore.setBackground(Color.lightGray);
		bestScore.setForeground(Color.white);
		bestScore.setOpaque(true);

		board.addListener(currScore);
		board.addListener(bestScore);

		infoRightPanel.add(bestScore);
		infoRightPanel.add(currScore);

		infoPanel.add(logo);
		infoPanel.add(infoRightPanel);

		setLayout(new BorderLayout(5, 5));
		add(infoPanel, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);
		pack();
	}

	private void createMenuBar() {
		// Init menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		menuFile.setMnemonic(KeyEvent.VK_F);
		JMenu menuHelp = new JMenu("Help");

		JMenuItem newgameItem = new JMenuItem("New Game");
		newgameItem.setMnemonic(KeyEvent.VK_N);
		newgameItem.setToolTipText("Start a new game.");
		newgameItem.addActionListener(e -> {
            // TODO Auto-generated method stub
            board.resetGame();
        });

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_E);
		exitItem.setToolTipText("Exit application");
		exitItem.addActionListener(e -> System.exit(0));
		menuFile.add(newgameItem);
		menuFile.add(exitItem);
		menuBar.add(menuFile);
		menuBar.add(menuHelp);
		setJMenuBar(menuBar);
	}

	private void setUI() {
		createMenuBar();
		createLayout();

		setTitle("Game 2048");
		setSize(400, 500);
		setResizable(false);
		setFocusTraversalKeysEnabled(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
            // TODO Auto-generated method stub
            GameSwing gameSwing = new GameSwing();
            gameSwing.setVisible(true);
        });
	}
}
