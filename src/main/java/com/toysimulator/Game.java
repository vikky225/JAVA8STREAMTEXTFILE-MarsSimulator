package com.toysimulator;


import com.toysimulator.enums.Command;
import com.toysimulator.exception.BlockExplorerException;
import com.toysimulator.exception.ToyExplorerException;
import com.toysimulator.simulator.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    Board squareBoard;
    ToyExplorer toyExplorer;
    BlockExplorer blockExplorer;

    public Game(Board squareBoard, ToyExplorer toyExplorer, BlockExplorer blockExplorer) {
        this.squareBoard = squareBoard;
        this.toyExplorer = toyExplorer;
        this.blockExplorer= blockExplorer;
    }

    /**
     * Places the toy on the squareBoard  in position X,Y
     *
     * @param position Toy position
     * @return true if placed successfully
     * @throws com.toysimulator.exception.ToyExplorerException
     */
    public boolean placeToyExplorer(Position position) throws ToyExplorerException {

        if (squareBoard == null)
            throw new ToyExplorerException("Invalid squareBoard object");

        if (position == null)
            throw new ToyExplorerException("Invalid position object");


        // validate the position
        if (!squareBoard.isValidPosition(position))
            return false;

        if(blockExplorer.getPositions().size() > 0){
          blockExplorer.getPositions().clear();

        }
        // if position is valid then assign values to fields
        toyExplorer.setPosition(position);
        return true;
    }

    /**
     * Places the block on the squareBoard  in position X,Y
     *
     * @param position block position
     * @return true if placed successfully
     * @throws com.toysimulator.exception.BlockExplorerException
     */
    public boolean placeBlockExplorer(Position position) throws BlockExplorerException {

        if (squareBoard == null)
            throw new BlockExplorerException("Invalid squareBoard object");

        if (position == null)
            throw new BlockExplorerException("Invalid position object");



        // validate the position
        if (!squareBoard.isValidPosition(position))
            return false;

          blockExplorer.setPosition(position);
        // if position is valid then assign values to fields

        return true;
    }


    /**
     * Places the toy on the squareBoard and figure out source to target position X,Y with shortest path
     *
     * @param  toy target position
     * @return true if placed successfully
     * @throws com.toysimulator.exception.ToyExplorerException
     */
    public boolean shortRoute(Position targetposition) throws ToyExplorerException {

        StringBuilder sb = new StringBuilder();
        if (squareBoard == null)
            throw new ToyExplorerException("Invalid squareBoard object");

        if (targetposition == null)
            throw new ToyExplorerException("Invalid position object");


        // validate the position
        if (!squareBoard.isValidPosition(targetposition))
            return false;

        if(blockExplorer.getPositions().size()>0){


            if(targetposition.getX()>toyExplorer.getPosition().getX()){
              int  diffX = targetposition.getX()-toyExplorer.getPosition().getX();//X1 case

              for(int i=0;i<diffX;i++) {

                  sb.append("("+(toyExplorer.getPosition().getX()+i)+","+toyExplorer.getPosition().getY()+")");
              }
            }else if(targetposition.getX()<toyExplorer.getPosition().getX()) {
              int  diffX = toyExplorer.getPosition().getX()-targetposition.getX(); //-X1 case

              for(int i=0;i<diffX;i++) {

                  sb.append("("+(toyExplorer.getPosition().getX()-i)+","+toyExplorer.getPosition().getY()+")");
                }
            }else {
                // only y we have to move
                toyExplorer.getPosition().setDiffx("DEFAULT");
            }

            if(targetposition.getY()>toyExplorer.getPosition().getY()){
                int diffY=targetposition.getY()-toyExplorer.getPosition().getY(); //+y1 case
                    for(int i=0;i<diffY;i++) {
                   sb.append("("+toyExplorer.getPosition().getX()+","+(toyExplorer.getPosition().getY()+i)+")");
                }
            }else {
                int diffY=toyExplorer.getPosition().getY()-targetposition.getY(); //-y1 case
                   for(int i=0;i<diffY;i++) {

                    sb.append("("+toyExplorer.getPosition().getX()+","+ (toyExplorer.getPosition().getY()-i)+")");
                }
            }


        }else{
            toyExplorer.getPosition().setDiffy("DEFAULT");
           // only X we have to move
        }
        // if position is valid then assign values to fields
        toyExplorer.setPosition(targetposition);
        toyExplorer.setRoutes(String.valueOf(sb));


        return true;
    }



    /**
     * Evals and executes a string command.
     *
     * @param inputString command string
     * @return string value of the executed command
     * @throws com.toysimulator.exception.ToyExplorerException
     *
     */
    public String eval(String inputString) throws ToyExplorerException, BlockExplorerException {
        String[] args = inputString.split(" ");

        // validate command
        Command command;
        try {
            command = Command.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            throw new ToyExplorerException("Invalid command");
        }
        if (command == Command.PLACE && args.length < 2) {
            throw new ToyExplorerException("Invalid command");
        }

        if (command == Command.BLOCK && args.length < 2) {
            throw new ToyExplorerException("Invalid command");
        }
        if (command == Command.EXPLORER && args.length < 2) {
            throw new ToyExplorerException("Invalid command");
        }


        // validate PLACE params
        String[] params;
        int x = 0;
        int y = 0;

        if (command == Command.PLACE ||command == Command.BLOCK ||command ==Command.EXPLORER) {
            params = args[1].split(",");
            try {
                x = Integer.parseInt(params[0]);
                y = Integer.parseInt(params[1]);
                } catch (Exception e) {
                throw new ToyExplorerException("Invalid command");
            }
        }

        String output;

        switch (command) {
            case PLACE:
                output = String.valueOf(placeToyExplorer(new Position(x, y)));
                break;
            case EXPLORER:
              String  output1 = String.valueOf(shortRoute(new Position(x, y)));
                output =  "PATH:"+toyExplorer.getRoutes();
                break;
            case BLOCK:
                output = String.valueOf(placeBlockExplorer(new Position(x, y)));
                break;
            case REPORT:
                output = report();
                break;
            default:
                throw new ToyExplorerException("Invalid command");
        }

        return output;
    }

    /**
     * Returns the X,Y and Direction of the robot
     */
    public String report() {

        if (toyExplorer.getPosition() == null)
            return null;

        if (blockExplorer.getPosition() == null)
            return "E:(" + toyExplorer.getPosition().getX() + "," + toyExplorer.getPosition().getY() + ")" + " B:";

        if (blockExplorer.getPositions().size() > 0) {
           // return "E:(" + toyExplorer.getPosition().getX() + "," + toyExplorer.getPosition().getY() + ")" + "B:";

            StringBuilder sb =new StringBuilder();
            for (Object p : blockExplorer.getPositions()) {

                 String.valueOf(sb.append("("+((Position) p).getX() + "," + ((Position) p).getY()+")"));
            }
               return "E:(" + toyExplorer.getPosition().getX() + "," + toyExplorer.getPosition().getY() + ")"+" B:"+String.valueOf(sb);
        }

        if(toyExplorer.getRoutes()!=null){
            return toyExplorer.getRoutes();
        }
        return "E:(" + toyExplorer.getPosition().getX() + "," + toyExplorer.getPosition().getY() + ")" + " B:";
    }
}
