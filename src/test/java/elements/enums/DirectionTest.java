package elements.enums;

import org.junit.Assert;
import org.junit.Test;

public class DirectionTest {

    @Test
    public void testRotateClockwise(){
        Direction direction = Direction.NORTH;
        Assert.assertEquals(Direction.EAST, direction.rotateClockwise(Direction.NORTH));
        Assert.assertEquals(Direction.SOUTH, direction.rotateClockwise(Direction.EAST));
        Assert.assertEquals(Direction.WEST, direction.rotateClockwise(Direction.SOUTH));
        Assert.assertEquals(Direction.NORTH, direction.rotateClockwise(Direction.WEST));
    }

    @Test
    public void testRotateAntiClockwise(){
        Direction direction = Direction.NORTH;
        Assert.assertEquals(Direction.WEST, direction.rotateAntiClockwise(Direction.NORTH));
        Assert.assertEquals(Direction.SOUTH, direction.rotateAntiClockwise(Direction.WEST));
        Assert.assertEquals(Direction.EAST, direction.rotateAntiClockwise(Direction.SOUTH));
        Assert.assertEquals(Direction.NORTH, direction.rotateAntiClockwise(Direction.EAST));
    }
}
