package game;

import elements.GridTable;
import elements.Position;
import elements.ToyRobot;
import elements.exception.GameException;
import elements.enums.Command;
import java.util.Optional;

/**
 * ToyRobotGame implementing Game interface methods. Encapsuates Table, Robot and applies game's rules one every move.
 */
public class ToyRobotGame implements Game {

    private GridTable gridTable;
    private ToyRobot robot;
    private boolean isPlaced = false;

    public ToyRobotGame(final ToyRobot robot, final GridTable gridTable){
        this.robot = robot;
        this.gridTable = gridTable;
    }

    //Implementing Game interface's method
    @Override
    public void play(final Command command, final Optional<Position> optionalParams) throws GameException {
        if( isValidMove(command, optionalParams.orElse(null)) ){
            Optional<Position> params = optionalParams;
            if( command.equals(Command.MOVE)){
                params = Optional.of(calculateNextPositionInGrid(robot.getPosition()));
            }
            robot.commandRobot(command, params);
            System.out.println("Done.");
        }
        else{
            throw new GameException("Not a valid move...");
        }
    }

    // Checks if the move is valid
    @Override
    public boolean isValidMove(final Command command, final Position position){
        return checkRules(command, position);
    }

    // Apply all the rules before making the move
    private boolean checkRules(final Command command, final Position position) {
        //rule 1(robot is placed)
        if(! checkIsRobotPlaced(command, position)){
            return false;
        }
        //rule 2(not to fall off grid)
        if( command.equals(Command.MOVE) && !checkValidMoveCommand()){
            return false;
        }
        return true;
    }

    // Rule 1 : Check the rule if robot is placed in the table
    private boolean checkIsRobotPlaced(final Command command, final Position position) {
        if( ! isPlaced && position == null ){
            return false;
        }
        if( command != null && command.equals(Command.PLACE) && gridTable.isValidPosition(position)){
            isPlaced = true;
        }
        return isPlaced;
    }

    // Rule 2 : Check if the move command doesn't make the robot fall off the table
    private boolean checkValidMoveCommand() {
        return gridTable.isValidPosition(calculateNextPositionInGrid(robot.getPosition()));
    }

    // Calculate next position in the grid table from current position
    public Position calculateNextPositionInGrid(final Position position){
        Position nextPosition = new Position(position.getX(), position.getY(), position.getDirection());
        switch (position.getDirection()) {
            case NORTH:
                nextPosition.setY(position.getY() + 1);
                break;
            case EAST:
                nextPosition.setX(position.getX() + 1);
                break;
            case SOUTH:
                nextPosition.setY(position.getY() - 1);
                break;
            case WEST:
                nextPosition.setX(position.getX() - 1);
                break;
        }
        return nextPosition;
    }
}
