package com.explorer.simulator;


import com.toysimulator.exception.ToyExplorerException;
import com.toysimulator.simulator.Position;
import com.toysimulator.simulator.ToyExplorer;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ToyExplorerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testMovement() throws ToyExplorerException {

        ToyExplorer robot = new ToyExplorer(new Position(0, 0));
        Assert.assertEquals(0, robot.getPosition().getX());
        Assert.assertEquals(0, robot.getPosition().getY());
        robot.setPosition(new Position(1, 2));
        Assert.assertEquals(1, robot.getPosition().getX());
        Assert.assertEquals(2, robot.getPosition().getY());

    }

}