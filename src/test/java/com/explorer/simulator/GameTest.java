package com.explorer.simulator;


import com.toysimulator.exception.BlockExplorerException;
import com.toysimulator.exception.ToyExplorerException;
import com.toysimulator.simulator.BlockExplorer;
import com.toysimulator.simulator.Position;
import com.toysimulator.simulator.SquareBoard;
import com.toysimulator.simulator.ToyExplorer;
import com.toysimulator.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GameTest {

    final int BOARD_ROWS = 5;
    final int BOARD_COLUMNS = 5;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testPlacing() throws ToyExplorerException {

        SquareBoard board = new SquareBoard(BOARD_COLUMNS, BOARD_ROWS);
        ToyExplorer toyExplorer = new ToyExplorer();
        BlockExplorer blockExplorer = new BlockExplorer();
        Game game = new Game(board, toyExplorer,blockExplorer);

        Assert.assertTrue(game.placeToyExplorer(new Position(0, 1)));
        Assert.assertTrue(game.placeToyExplorer(new Position(2, 2)));
        Assert.assertFalse(game.placeToyExplorer(new Position(6, 6)));
        Assert.assertFalse(game.placeToyExplorer(new Position(-1, 5)));
    }

    @Test
    public void testPlacingExceptions() throws ToyExplorerException {

        SquareBoard board = new SquareBoard(BOARD_COLUMNS, BOARD_ROWS);
        ToyExplorer toyExplorer = new ToyExplorer();
        BlockExplorer blockExplorer = new BlockExplorer();
        Game game = new Game(board, toyExplorer,blockExplorer);

        thrown.expect(ToyExplorerException.class);
        game.placeToyExplorer(null);
        thrown.expect(ToyExplorerException.class);
        game.placeToyExplorer(new Position(0, 1));
    }

    @Test
    public void testEval() throws ToyExplorerException , BlockExplorerException {

        SquareBoard board = new SquareBoard(BOARD_COLUMNS, BOARD_ROWS);
        ToyExplorer toyExplorer = new ToyExplorer();
        BlockExplorer blockExplorer = new BlockExplorer();
        Game game = new Game(board, toyExplorer,blockExplorer);

        game.eval("PLACE 0,0");
        Assert.assertEquals("E:(0,0) B:", game.eval("REPORT"));




        // invalid commands
        thrown.expect(ToyExplorerException.class);
        Assert.assertEquals("Invalid command", game.eval("PLACE12"));
        thrown.expect(ToyExplorerException.class);
        Assert.assertEquals("Invalid command", game.eval("LSSDD"));
        thrown.expect(ToyExplorerException.class);
        Assert.assertEquals("Invalid command", game.eval("DDDDD"));
    }
}
