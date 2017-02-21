package com.propig.game.game2048;

import com.propig.game.game2048.ai.RandomMoving;
import com.propig.game.game2048.logic.GameLogic;
import com.propig.game.game2048.view.Board;
import com.propig.game.game2048.view.JAboutDialog;
import com.propig.game.game2048.view.JBestScorePanel;
import com.propig.game.game2048.view.JScorePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameSwing extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = -6320473553027546885L;

    private GameSwing() {
        logic = new GameLogic();
        board = new Board(logic);
        randomMoving = new RandomMoving(board, logic);
        timer = new Timer(300, this);
        setUI();
    }

    private Board board = null;
    private GameLogic logic = null;
    private Timer timer = null;
    private RandomMoving randomMoving = null;

    private void createLayout() {
        // Init information bar


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
        JMenu menuAI = new JMenu("AI");
        menuAI.setMnemonic(KeyEvent.VK_A);
        JMenu menuHelp = new JMenu("Help");
        menuHelp.setMnemonic(KeyEvent.VK_H);

        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.setMnemonic(KeyEvent.VK_N);
        newGameItem.setToolTipText("Start a new game.");
        newGameItem.addActionListener(e -> {
            // TODO Auto-generated method stub
            board.resetGame();
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_E);
        exitItem.setToolTipText("Exit application");
        exitItem.addActionListener(e -> System.exit(0));

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setMnemonic(KeyEvent.VK_E);
        aboutItem.setToolTipText("About this application");
        aboutItem.addActionListener(e -> {
            JAboutDialog aboutDialog = new JAboutDialog(this);
            aboutDialog.setVisible(true);
            aboutDialog.setLocationRelativeTo(null);

        });

        JMenuItem randomItem = new JMenuItem("Random");
        randomItem.setMnemonic(KeyEvent.VK_R);
        randomItem.setToolTipText("Random move");
        randomItem.addActionListener(e->{
            timer.start();
        });
        menuFile.add(newGameItem);
        menuFile.add(exitItem);
        menuAI.add(randomItem);
        menuHelp.add(aboutItem);
        menuBar.add(menuFile);
        menuBar.add(menuAI);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        randomMoving.play();
    }
}
