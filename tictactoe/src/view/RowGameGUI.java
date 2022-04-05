package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import model.RowBlockModel;
import model.RowGameModel;
import controller.RowGameController;

public class RowGameGUI {
    private static final int rowWidth = 3;
    private static final int columnWidth = 3;
    private final JFrame gui = new JFrame("Tic Tac Toe");
    public RowGameModel gameModel = new RowGameModel();
    private final JButton[][] blocks = new JButton[rowWidth][columnWidth];
    private final JButton reset = new JButton("Reset");
    private final JTextArea playerturn = new JTextArea();

    /**
     * Creates a new game initializing the GUI.
     */
    public RowGameGUI(RowGameController controller) {
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(500, 350));
        gui.setResizable(true);

        JPanel gamePanel = new JPanel(new FlowLayout());
        JPanel game = new JPanel(new GridLayout(rowWidth, columnWidth));
        gamePanel.add(game, BorderLayout.CENTER);

        JPanel options = new JPanel(new FlowLayout());
        options.add(reset);
        JPanel messages = new JPanel(new FlowLayout());
        messages.setBackground(Color.white);

        gui.add(gamePanel, BorderLayout.NORTH);
        gui.add(options, BorderLayout.CENTER);
        gui.add(messages, BorderLayout.SOUTH);

        messages.add(playerturn);
        playerturn.setText("Player 1 to play 'X'");

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.resetGame();
            }
        });

        // Initialize a JButton for each cell of the 3x3 game board.
        for (int row = 0; row < rowWidth; row++) {
            for (int column = 0; column < columnWidth; column++) {
                blocks[row][column] = new JButton();
                blocks[row][column].setPreferredSize(new Dimension(75, 75));
                game.add(blocks[row][column]);
                blocks[row][column].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        controller.move((JButton) e.getSource());
                    }
                });
            }
        }
    }

    /**
     * Updates the block at the given row and column
     * after one of the player's moves.
     *
     * @param gameModel The RowGameModel containing the block
     * @param row       The row that contains the block
     * @param column    The column that contains the block
     */
    public void updateBlock(RowGameModel gameModel, int row, int column) {
        RowBlockModel[][] blockdata = gameModel.getBlocksData();
        blocks[row][column].setText(blockdata[row][column].getContents());
        blocks[row][column].setEnabled(blockdata[row][column].getIsLegalMove());
    }

    /**
     * get the gui object from RowGamGUI.java
     *
     * @return JFrame
     */
    public JFrame getGui() {
        return gui;
    }

    /**
     * get MxM JButton Array from RowGamGUI.java
     *
     * @return JButton[][]
     */
    public JButton[][] getBlocks() {
        return blocks;
    }

    /**
     * Updates the text display for current player
     *
     * @param target The name of the current player
     * @throws IllegalArgumentException When the given value is null
     */
    public void setPlayerturnText(String target) {
        if (target == null) {
            throw new IllegalArgumentException("The value must be non-null.");
        }
        playerturn.setText(target);
    }
}
