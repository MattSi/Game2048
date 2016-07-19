package com.propig.game.game2048;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class GameSwing extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6320473553027546885L;

	public GameSwing() {
		setUI();
	}

	private JScorePanel currScore = null;
	private JBestScorePanel bestScore = null;
	private Board board = null;
	private GameLogic logic = null;

	private void createLayout() {
		// Init information bar
		logic = new GameLogic();
		board = new Board(logic);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(1, 2, 10, 10));

		JLabel logo = new JLabel("2048", SwingConstants.CENTER);
		logo.setFont(new Font("Serif", Font.BOLD, 50));
		logo.setBackground(new Color(237, 197, 1));
		logo.setForeground(Color.white);
		logo.setOpaque(true);

		JPanel infoRightPanel = new JPanel(new GridLayout(2, 1));
		currScore = new JScorePanel();
		currScore.setFont(new Font("Serif", Font.BOLD, 25));
		currScore.setBackground(Color.magenta);
		currScore.setForeground(Color.white);
		currScore.setOpaque(true);

		bestScore = new JBestScorePanel();
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
		newgameItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				board.resetGame();
			}
		});
		
		
		
		
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_E);
		exitItem.setToolTipText("Exit application");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				GameSwing gameSwing = new GameSwing();
				gameSwing.setVisible(true);
			}
		});
	}
}
