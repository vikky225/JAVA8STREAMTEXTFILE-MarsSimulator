package com.toysimulator.simulator;


import com.toysimulator.exception.ToyExplorerException;

public class ToyExplorer {

    private Position position;

    public String getRoutes() {
        return Routes;
    }

    public void setRoutes(String routes) {
        Routes = routes;
    }

    private String Routes;

    public ToyExplorer() {
    }

    public ToyExplorer(Position position) {
        this.position = position;
    }

    public boolean setPosition(Position position) {
        if (position == null)
            return false;

        this.position = position;
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
