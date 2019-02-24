package com.toysimulator.simulator;

import com.toysimulator.exception.ToyExplorerException;

import java.util.ArrayList;
import java.util.List;

public class BlockExplorer {

    public List getPositions() {
        return Positions;
    }

    public void setPositions(List positions) {
        Positions = positions;
    }

    private List<Position> Positions = new ArrayList<>();
    private Position position;
    public BlockExplorer() {
    }

    public BlockExplorer(Position position) {
        this.position = position;
    }

    public boolean setPosition(Position position) {
        if (position == null)
            return false;

        this.position = position;
        this.Positions.add(this.position);
        return true;
    }

    public boolean move() throws ToyExplorerException {
        return move(position.getNextPosition());
    }

    /**
     * Moves the robot one unit forward in the direction it is currently facing
     *
     * @return true if moved successfully
     */
    public boolean move(Position newPosition) {
        if (newPosition == null)
            return false;

        // change position
        this.position = newPosition;
        return true;
    }

    public Position getPosition() {
        return this.position;
    }


}
