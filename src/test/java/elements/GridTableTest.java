package elements;

import elements.enums.Direction;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class GridTableTest {
    @Test
    public void testIsValidPosition(){
        //positive cases
        int rows = 4;
        int cols = 4;
        GridTable table = new GridTable(rows, cols);
        Arrays.stream(Direction.values()).forEach(direction -> {
            IntStream.range(rows, 0).forEach( row -> {
                IntStream.range(0, cols).forEach( col -> {
                    Assert.assertTrue(table.isValidPosition(new Position(row, col, direction)));
                });
            });
        });

        //invalid cases
        Assert.assertFalse(table.isValidPosition(new Position(-1,1, Direction.EAST)));
        Assert.assertFalse(table.isValidPosition(new Position(1,5, Direction.EAST)));
    }
}
