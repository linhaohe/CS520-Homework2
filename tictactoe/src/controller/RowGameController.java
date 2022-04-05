package controller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import model.RowGameModel;
import view.RowGameGUI;

public class RowGameController {
    private final RowGameModel gameModel;
    private final RowGameGUI gameView;
    private static final int rowWidth = 3;
    private static final int columnWidth = 3;


    /**
     * Creates a new game initializing the GUI.
     */
    public RowGameController() {
        gameModel = new RowGameModel();
        gameView = new RowGameGUI(this);

        for (int row = 0; row < rowWidth; row++) {
            for (int column = 0; column < columnWidth; column++) {
                gameModel.setBlocksDataContent(row, column, "");
                gameModel.setBlocksDataLegalMove(row, column, true);
                gameView.updateBlock(gameModel, row, column);
            }
        }
    }

    /**
     * Moves the current player into the given block.
     *
     * @param block The block to be moved to by the current player
     */
    public void move(JButton block) {
        gameModel.decreaseMoveLeft();
        if (gameModel.getMovesLeft() % 2 == 1) {
            gameView.setPlayerturnText("'X': Player 1");
        } else {
            gameView.setPlayerturnText("'O': Player 2");
        }
        String currentPlayerSign = gameModel.getCurrentPlayer().equals("1") ? "X" : "O";
        JButton[][] blocks = gameView.getBlocks();
        Boolean isFind = false;
        for (int row = 0; row < blocks.length; row++) {
            for (int column = 0; column < blocks[0].length; column++) {
                if (block == blocks[row][column]) {
                    if(!("".equals(gameModel.getBlockDataContents(row,column)))){
                        throw new IllegalArgumentException("The block already been clicked");
                    }
                    gameModel.setBlocksDataContent(row, column, currentPlayerSign);
                    gameView.updateBlock(gameModel, row, column);
                    isFind = true;
                    if (gameModel.getMovesLeft() < 7) {
                        if (checkSolved(row, column)) {
                            gameModel.setFinalResult("Player " + gameModel.getCurrentPlayer() + " wins!");
                            endGame();
                        } else if (gameModel.getMovesLeft() == 0) {
                            gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
                        }
                        if (gameModel.getFinalResult() != null) {
                            gameView.setPlayerturnText(gameModel.getFinalResult());
                        }
                    }
                    gameModel.setCurrentPlayer(gameModel.getCurrentPlayer().equals("1") ? "2" : "1");
                    break;
                }
            }
        }
        if(!isFind){
            throw new IllegalArgumentException("Block not found");
        }

    }

    /**
     * End the game by seting enabled of each button to false
     * which disallow the user to interact with the button
     */
    private void endGame() {
        JButton[][] blocks = gameView.getBlocks();
        for (int row = 0; row < rowWidth; row++) {
            for (int column = 0; column < columnWidth; column++) {
                blocks[row][column].setEnabled(false);
            }
        }
    }

    /**
     * Resets the game to be able to start playing again.
     */
    public void resetGame() {
        for (int row = 0; row < rowWidth; row++) {
            for (int column = 0; column < columnWidth; column++) {
                gameModel.resetBlockData(row, column);
                gameModel.setBlocksDataLegalMove(row, column, true);
                gameView.updateBlock(gameModel, row, column);
            }
        }
        gameModel.setCurrentPlayer("1");
        gameModel.setMovesLeft(9);
        gameModel.setFinalResult(null);
        gameView.setPlayerturnText("Player 1 to play 'X'");
    }

    /**
     * check after placing a block at the given row and column
     * if the newly placed block will result the player wining the game
     *
     * @param row    The row that contains the block
     * @param column The column that contains the block
     * @return boolean  True the player placed the block win false otherwise.
     */
    private boolean checkSolved(int row, int column) {
        if (checkRow(row) || checkColumn(column)) {
            return true;
        }
        if (((row == 0 && column == 0) || (row == 1 && column == 1) || (row == 2 && column == 2)) && checkDiagonalTopLeft()) {
            return true;
        }
        return ((row == 0 && column == 2) || (row == 1 && column == 1) || (row == 2 && column == 0)) && checkDiagonalTopRight();
    }

    /**
     * check after placing a block at the given row
     * if the newly placed block will result the player wining the game
     *
     * @param row The row that contains the block
     * @return boolean  True the player placed the block win false otherwise.
     */
    private boolean checkRow(int row) {
        String firstElement = gameModel.getBlockDataContents(row, 0);
        String secondElement = gameModel.getBlockDataContents(row, 1);
        String thirdElement = gameModel.getBlockDataContents(row, 2);

        return firstElement.equals(secondElement) && secondElement.equals(thirdElement);
    }

    /**
     * check after placing a block at the given column
     * if the newly placed block will result the player wining the game
     *
     * @param column The row that contains the block
     * @return boolean  True the player placed the block win false otherwise.
     */
    private boolean checkColumn(int column) {
        String firstElement = gameModel.getBlockDataContents(0, column);
        String secondElement = gameModel.getBlockDataContents(1, column);
        String thirdElement = gameModel.getBlockDataContents(2, column);

        return firstElement.equals(secondElement) && secondElement.equals(thirdElement);
    }


    /**
     * check after placing a block at the diagonal location
     * if the newly placed block will result the player wining the game
     *
     * @return boolean  True the player placed the block win false otherwise.
     */
    private boolean checkDiagonalTopLeft() {
        String firstElement = gameModel.getBlockDataContents(0, 0);
        String secondElement = gameModel.getBlockDataContents(1, 1);
        String thirdElement = gameModel.getBlockDataContents(2, 2);

        return firstElement.equals(secondElement) && secondElement.equals(thirdElement);
    }

    /**
     * check after placing a block at the diagonal location
     * if the newly placed block will result the player wining the game
     *
     * @return boolean  True the player placed the block win false otherwise.
     */
    private boolean checkDiagonalTopRight() {
        String firstElement = gameModel.getBlockDataContents(0, 2);
        String secondElement = gameModel.getBlockDataContents(1, 1);
        String thirdElement = gameModel.getBlockDataContents(2, 0);

        return firstElement.equals(secondElement) && secondElement.equals(thirdElement);
    }

    /**
     * get gameModel from RowGameController.java
     *
     * @return RowGameModel
     */
    public RowGameModel getgameModel() {
        return gameModel;
    }

    /**
     * get gameView from RowGameController.java
     *
     * @return RowGameGUI
     */
    public RowGameGUI getGameView() {
        return gameView;
    }
}
