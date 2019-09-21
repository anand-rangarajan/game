package elements;

import elements.enums.Command;
import elements.enums.Direction;
import elements.exception.RobotGameException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class ToyRobotTest {

    @Test(expected = RobotGameException.class)
    public void testCommandRobotInvalidPlaceCommand() throws RobotGameException{
        Robot robot = new ToyRobot();
        robot.commandRobot(Command.PLACE, Optional.empty());
    }

    @Test
    public void testCommandRobotValidPlaceCommand() throws RobotGameException{
        ToyRobot robot = createRobot();
        robot.commandRobot(Command.PLACE, Optional.of(new Position(1,1, Direction.EAST)));
        verifyRobotPosition(robot, 1,1,Direction.EAST);
    }

    @Test
    public void testCommandRobotValidPlaceCommandUnbounded() throws RobotGameException{
        ToyRobot robot = createRobot();
        robot.commandRobot(Command.PLACE, Optional.of(new Position(10000,10000,Direction.EAST)));
        verifyRobotPosition(robot, 10000,10000,Direction.EAST);
    }

    @Test(expected = RobotGameException.class)
    public void testCommandRobotInvalidLeft() throws RobotGameException{
        ToyRobot robot = createRobot();
        robot.commandRobot(Command.LEFT, Optional.empty());
    }

    @Test
    public void testCommandRobotValidLeft() throws RobotGameException{
        ToyRobot robot = createRobot();
        robot.commandRobot(Command.PLACE, Optional.of(new Position(1,1,Direction.EAST)));
        robot.commandRobot(Command.LEFT, Optional.empty());
        verifyRobotPosition(robot, 1,1,Direction.NORTH);
    }

    @Test(expected = RobotGameException.class)
    public void testCommandRobotInvalidRight() throws RobotGameException{
        ToyRobot robot = createRobot();
        robot.commandRobot(Command.RIGHT, Optional.empty());
    }

    @Test
    public void testCommandRobotValidRight() throws RobotGameException{
        ToyRobot robot = createRobot();
        robot.commandRobot(Command.PLACE, Optional.of(new Position(1,1,Direction.EAST)));
        robot.commandRobot(Command.RIGHT, Optional.empty());
        verifyRobotPosition(robot, 1,1,Direction.SOUTH);
    }

    @Test(expected = RobotGameException.class)
    public void testCommandRobotInValidMove() throws RobotGameException{
        ToyRobot robot = createRobot();
        robot.commandRobot(Command.MOVE, Optional.empty());
    }

    @Test
    public void testCommandRobotValidMove() throws RobotGameException{
        ToyRobot robot = createRobot();
        robot.commandRobot(Command.MOVE, Optional.of(new Position(1,1,Direction.EAST)));
        verifyRobotPosition(robot, 1,1,Direction.EAST);
    }

    private ToyRobot createRobot(){
        ToyRobot robot = new ToyRobot();
        Assert.assertNull(robot.getPosition());
        return robot;
    }

    private void verifyRobotPosition(final ToyRobot robot, final int x, final int y, final Direction direction){
        Position currentPosition = robot.getPosition();
        Assert.assertEquals(x, currentPosition.getX());
        Assert.assertEquals(y, currentPosition.getY());
        Assert.assertEquals(direction, currentPosition.getDirection());
    }
}
