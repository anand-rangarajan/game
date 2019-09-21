package elements.enums;

public enum Direction {
    NORTH(0), EAST(1), SOUTH(2), WEST(3);

    private int direction;

    Direction(final int direction) {
        this.direction = direction;
    }

    public Direction rotateClockwise(final Direction currentDirection) {
        return currentDirection.ordinal() + 1 >= values().length ?
                Direction.NORTH : values()[currentDirection.ordinal() + 1];
    }

    public Direction rotateAntiClockwise(final Direction currentDirection) {
        return currentDirection.ordinal() - 1 < 0 ?
                Direction.WEST : values()[currentDirection.ordinal() - 1];
    }
}
