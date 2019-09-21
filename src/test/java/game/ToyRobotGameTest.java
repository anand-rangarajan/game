package game;

import elements.GridTable;
import elements.Position;
import elements.ToyRobot;
import elements.enums.Command;
import elements.enums.Direction;
import elements.exception.GameException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ToyRobotGameTest {

    @Mock
    private ToyRobot robot;
    @Mock
    private GridTable table;
    @Mock
    private Position position;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private ToyRobotGame game;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        when(position.getX()).thenReturn(1);
        when(position.getY()).thenReturn(1);
        when(position.getDirection()).thenReturn(Direction.EAST);

        game = new ToyRobotGame(robot, table);
    }

    @Test
    public void testIsValidMoveInvalidMoves() {
        Assert.assertFalse(game.isValidMove(null, null));
        Assert.assertFalse(game.isValidMove(null, position));
        Arrays.stream(Command.values()).forEach(command -> {
            Assert.assertFalse(game.isValidMove(command, null));
        });

        when(table.isValidPosition(any(Position.class))).thenReturn(false);
        Assert.assertFalse(game.isValidMove(Command.PLACE, position));
    }

    @Test
    public void testIsValidMoveValidMoves() {
        when(table.isValidPosition(any(Position.class))).thenReturn(true);
        Assert.assertTrue(game.isValidMove(Command.PLACE, position));
    }

    @Test
    public void testCalculateNextPosition() {
        Position oldPosition = new Position(1, 1, Direction.EAST);
        Position newPosition = game.calculateNextPositionInGrid(oldPosition);
        Assert.assertEquals(2, newPosition.getX());
        Assert.assertEquals(1, newPosition.getY());
        Assert.assertEquals(Direction.EAST, newPosition.getDirection());
    }

    @Test
    public void testPlayMoveBeforePlace() throws GameException {
        expectedException.expect(GameException.class);
        expectedException.expectMessage("Not a valid move...");
        game.play(Command.MOVE, Optional.of(new Position(1, 1, Direction.EAST)));
    }

    @Test
    public void testPlayRightBeforePlace() throws GameException {
        expectedException.expect(GameException.class);
        expectedException.expectMessage("Not a valid move...");
        game.play(Command.RIGHT, Optional.of(new Position(1, 1, Direction.EAST)));
    }

    @Test
    public void testPlayLeftBeforePlace() throws GameException {
        expectedException.expect(GameException.class);
        expectedException.expectMessage("Not a valid move...");
        game.play(Command.LEFT, Optional.of(new Position(1, 1, Direction.EAST)));
    }

    @Test
    public void testPlayReportBeforePlace() throws GameException {
        expectedException.expect(GameException.class);
        expectedException.expectMessage("Not a valid move...");
        game.play(Command.REPORT, Optional.of(new Position(1, 1, Direction.EAST)));
    }

    @Test
    public void testPlayValidPlace() throws GameException {
        when(table.isValidPosition(any(Position.class))).thenReturn(true);
        game.play(Command.PLACE, Optional.of(new Position(1, 1, Direction.EAST)));
    }
}
