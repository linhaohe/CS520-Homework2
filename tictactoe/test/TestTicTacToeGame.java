import model.RowGameModel;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

import model.RowBlockModel;
import controller.RowGameController;
import view.RowGameGUI;

import javax.swing.*;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class TestTicTacToeGame {
    private RowGameController game;
    private RowGameModel currentGameModel;
    @Before
    public void setUp() {
        game = new RowGameController();
        currentGameModel = new RowGameModel();
    }

    @After
    public void tearDown() {
	    game = null;
        currentGameModel = null;
    }

    @org.junit.Test
    public void testNewGameModel() {
        assertEquals ("1", game.getgameModel().getCurrentPlayer());
        assertEquals (9, game.getgameModel().getMovesLeft());
        assertNull(game.getgameModel().getFinalResult());
        assertNotNull(game.getGameView());

    }

    @org.junit.Test
    public void testController() {
        //test controller intilization
        assertNotNull ( game.getgameModel());
        assertNotNull ( game.getGameView());
    }

    @org.junit.Test
    public void testModel() {
        assertEquals ("1", currentGameModel.getCurrentPlayer());
        assertEquals (9, currentGameModel.getMovesLeft());
        assertEquals ("", currentGameModel.getBlocksData()[0][0].getContents());
        assertFalse(currentGameModel.getBlocksData()[0][0].getIsLegalMove());

    }

    @org.junit.Test
    public void testView() {
        RowGameGUI view = new RowGameGUI(game);
        assertNotNull(view.getGui());
        assertNotNull(view.getBlocks());

    }

    @org.junit.Test
    public void testMove() {
        //Test Legal Move
        JButton block = game.getGameView().getBlocks()[0][0];
        RowGameModel mode = game.getgameModel();
        game.move(block);
        assertEquals("2",mode.getCurrentPlayer());
        assertEquals("X",mode.getBlockDataContents(0,0));
        assertEquals("X",block.getText());
        assertEquals(8,mode.getMovesLeft());

    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void IleaglMove() {
        //Test IleaglMove of a block that dose not exist
        JButton block = new JButton();
        game.move(block);

    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void IleaglMoveClicked() {
        //Test a block that is arleady been clicked
        game.move(game.getGameView().getBlocks()[0][0]);
        game.move(game.getGameView().getBlocks()[0][0]);

    }

    @org.junit.Test
    public void testPlayer1Win() {
        //Test Legal Move
        JButton block = game.getGameView().getBlocks()[0][0];
        RowGameModel mode = game.getgameModel();
        game.move(block);
        game.move(game.getGameView().getBlocks()[1][0]);
        game.move(game.getGameView().getBlocks()[0][1]);
        game.move(game.getGameView().getBlocks()[2][0]);
        game.move(game.getGameView().getBlocks()[0][2]);

        assertEquals("Player 1 wins!",mode.getFinalResult());
        assertEquals(4,mode.getMovesLeft());

    }

    @org.junit.Test
    public void testTide() {
        //Test Legal Move
        RowGameModel mode = game.getgameModel();

        game.move(game.getGameView().getBlocks()[0][0]);
        game.move(game.getGameView().getBlocks()[0][1]);
        game.move(game.getGameView().getBlocks()[1][1]);
        game.move(game.getGameView().getBlocks()[2][2]);
        game.move(game.getGameView().getBlocks()[1][2]);
        game.move(game.getGameView().getBlocks()[1][0]);
        game.move(game.getGameView().getBlocks()[2][0]);
        game.move(game.getGameView().getBlocks()[0][2]);
        game.move(game.getGameView().getBlocks()[2][1]);

        assertEquals("Game ends in a draw",mode.getFinalResult());
        assertEquals(0,mode.getMovesLeft());

    }

    @org.junit.Test
    public void testRest(){
        game.move(game.getGameView().getBlocks()[0][0]);
        game.move(game.getGameView().getBlocks()[0][1]);
        game.move(game.getGameView().getBlocks()[1][1]);
        game.move(game.getGameView().getBlocks()[2][2]);
        game.move(game.getGameView().getBlocks()[1][2]);
        game.move(game.getGameView().getBlocks()[1][0]);
        game.move(game.getGameView().getBlocks()[2][0]);
        game.move(game.getGameView().getBlocks()[0][2]);
        game.move(game.getGameView().getBlocks()[2][1]);
        game.resetGame();
        RowGameModel mode = game.getgameModel();
        assertEquals ("1", game.getgameModel().getCurrentPlayer());
        assertEquals (9, game.getgameModel().getMovesLeft());
        assertNull(game.getgameModel().getFinalResult());
        assertNotNull(game.getGameView());

    }


    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testNewBlockViolatesPrecondition() {
	RowBlockModel block = new RowBlockModel(null);
    }
}
