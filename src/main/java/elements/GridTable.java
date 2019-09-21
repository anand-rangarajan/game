package elements;

/**
 * Class representation of Grid table where the ToyRobot roams.
 */
public class GridTable {
    int rows;
    int columns;

    public GridTable(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    // Check if the given position is a valid point in the table
    public boolean isValidPosition(final Position position) {
        return !(position.getX() < 0 || position.getY() > this.rows || position.getY() < 0 ||
                position.getX() > this.columns);
    }
}
