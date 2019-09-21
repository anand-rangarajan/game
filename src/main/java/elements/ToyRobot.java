package elements;

import elements.enums.Command;
import elements.enums.Direction;
import elements.exception.RobotGameException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Optional;

/**
 * Class to represent ToyRobot implents methods from more general Robot interface.
 * This implements different specific ToyRobot operations.
 */
@Getter @Setter @NoArgsConstructor
public class ToyRobot implements Robot {

    private Position position;

    // Implements parent class method to command the robot
    @Override
    public void commandRobot(final Command command, final Optional<Position> optionalParams) throws RobotGameException{
        switch(command){
            case LEFT:
                Direction leftDirection = getDirectionFromPosition(this.position);
                position.setDirection(leftDirection.rotateAntiClockwise(leftDirection));
                break;
            case RIGHT:
                Direction rightDirection = getDirectionFromPosition(this.position);
                position.setDirection(rightDirection.rotateClockwise(rightDirection));
                break;
            case PLACE:
            case MOVE:
                this.position = optionalParams.orElseThrow(()-> new RobotGameException("Invalid parameters for the given command..."));
                break;
            case REPORT:
                report();
                break;
        }
    }

    //Report current position
    private void report(){
        String report = "Invalid position in the grid...";
        if( position != null) {
            report = String.format("Current Position : %d, %d, %s", position.getX(), position.getY(), position.getDirection());
        }
        System.out.println(report);
    }

    // Gets the direction enum from position object
    private Direction getDirectionFromPosition(final Position position) throws RobotGameException{
        if( position == null ){
            throw new RobotGameException("Current position is not valid/null...");
        }
        return position.getDirection();
    }
}
