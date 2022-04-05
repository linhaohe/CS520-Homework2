package model;


public class RowGameModel {
    public static final String GAME_END_NOWINNER = "Game ends in a draw";
    private static final int rowWidth = 3;
    private static final int columnWidth = 3;

    private final RowBlockModel[][] blocksData = new RowBlockModel[rowWidth][columnWidth];

    /**
     * The current player taking their turn
     */
    private String player = "1";
    private int movesLeft = 9;

    private String finalResult = null;


    /**
     * initializing RowGameModel
     */
    public RowGameModel() {
        super();

        for (int row = 0; row < rowWidth; row++) {
            for (int col = 0; col < columnWidth; col++) {
                blocksData[row][col] = new RowBlockModel(this);
            } // end for col
        } // end for row
    }

    /**
     * Returns the non-null String value of the finalResult
     *
     * @return The String value of finalResult
     */
    public String getFinalResult() {
        return this.finalResult;
    }

    /**
     * set the FinalResult of RowGameModel
     *
     * @param finalResult an String
     */
    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
    }

    /**
     * Returns the non-null String value of the Current Player
     *
     * @return The non-null String value of Current Player
     */
    public String getCurrentPlayer() {
        return player;
    }

    /**
     * set the currentPlayer of RowGameModel
     *
     * @param player an non-Null String
     * @throws IllegalArgumentException When the given value is null
     */
    public void setCurrentPlayer(String player) {
        if (player == null) {
            throw new IllegalArgumentException("The value must be non-null.");
        }
        this.player = player;
    }

    /**
     * set the move left of RowGameModel
     *
     * @param move an integer greater or equals to 0
     * @throws IllegalArgumentException When the given move is below 0
     */
    public void setMovesLeft(int move) {
        if (move < 0) {
            throw new IllegalArgumentException("Move is below 0");
        }
        this.movesLeft = move;
    }

    /**
     * decrease the number of moveLeft by 1
     */
    public void decreaseMoveLeft() {
        this.movesLeft = this.movesLeft - 1;
    }

    /**
     * Returns the number of move left in row game model
     *
     * @return a int that is greater or equals to 0
     */
    public int getMovesLeft() {
        return this.movesLeft;
    }

    /**
     * Returns MxM block data of row game model
     *
     * @return The non-null 2D array of RowBlockModel
     */
    public RowBlockModel[][] getBlocksData() {
        return blocksData;
    }

    /**
     * set the block content at the given row and column
     *
     * @param row    The row that contains the block
     * @param column The column that contains the block
     * @param target The string that is being assign to the block
     * @throws IllegalArgumentException When the given value is null
     * @throws IllegalArgumentException When the given row or column is out of bound
     */
    public void setBlocksDataContent(int row, int column, String target) {
        if (target == null) {
            throw new IllegalArgumentException("The value must be non-null.");
        }
        if (row >= rowWidth || row < 0) {
            throw new IllegalArgumentException("Row index out of bound");
        }
        if (column >= columnWidth || column < 0) {
            throw new IllegalArgumentException("Column index out of bound");
        }

        this.blocksData[row][column].setContents(target);
    }

    /**
     * set the block legal move at the given row and column
     *
     * @param row      The row that contains the block
     * @param column   The column that contains the block
     * @param isLeagal The Boolean that is being assign to the block
     * @throws IllegalArgumentException When the given row or column is out of bound
     */
    public void setBlocksDataLegalMove(int row, int column, boolean isLeagal) {
        if (row >= rowWidth || row < 0) {
            throw new IllegalArgumentException("Row index out of bound");
        }
        if (column >= columnWidth || column < 0) {
            throw new IllegalArgumentException("Column index out of bound");
        }
        this.blocksData[row][column].setIsLegalMove(isLeagal);
    }

    /**
     * Reset the block at the given row and column
     *
     * @param row    The row that contains the block
     * @param column The column that contains the block
     * @throws IllegalArgumentException When the given row or column is out of bound
     */
    public void resetBlockData(int row, int column) {
        if (row >= rowWidth || row < 0) {
            throw new IllegalArgumentException("Row index out of bound");
        }
        if (column >= columnWidth || column < 0) {
            throw new IllegalArgumentException("Column index out of bound");
        }
        this.blocksData[row][column].reset();
    }

    /**
     * get the block content at the given row and column
     *
     * @param row    The row that contains the block
     * @param column The column that contains the block
     * @return a non-null string of the target block
     * @throws IllegalArgumentException When the given row or column is out of bound
     */
    public String getBlockDataContents(int row, int column) {
        if (row >= rowWidth || row < 0) {
            throw new IllegalArgumentException("Row index out of bound");
        }
        if (column >= columnWidth || column < 0) {
            throw new IllegalArgumentException("Column index out of bound");
        }
        return this.blocksData[row][column].getContents();
    }
}
