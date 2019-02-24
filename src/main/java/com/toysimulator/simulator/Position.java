package com.toysimulator.simulator;


import com.toysimulator.exception.ToyExplorerException;

public class Position {
    int x;
    int y;

    public String getDiffx() {
        return diffx;
    }

    public void setDiffx(String diffx) {
        this.diffx = diffx;
    }

    public String getDiffy() {
        return diffy;
    }

    public void setDiffy(String diffy) {
        this.diffy = diffy;
    }

    String diffx;
    String diffy;

    public Position(Position position) {
        this.x = position.getX();
        this.y = position.getY();


    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }



    public void change(int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }

    public Position getNextPosition() throws ToyExplorerException {

        // new position to validate
        Position newPosition = new Position(this);
        switch (this.diffx) {

            case "X1":
                newPosition.change(1, 0);
                break;
            case "X2":
                newPosition.change(-1, 0);
                break;
            default:
                break;

        }

        switch(this.diffy){
            case "Y2":
                newPosition.change(0, -1);
                break;
            case "Y1":
                newPosition.change(0, 1);
                break;
            default:
                break;
        }


        return newPosition;
    }
}
