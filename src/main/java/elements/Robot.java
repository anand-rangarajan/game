package elements;

import elements.enums.Command;
import elements.exception.RobotGameException;

import java.util.Optional;

public interface Robot {
    void commandRobot(Command command, Optional<Position> optionalParams) throws RobotGameException;
}
