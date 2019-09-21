package elements;

import elements.enums.Direction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class to represent the current place in the table along with direction.
 */
@Getter @Setter @NoArgsConstructor
public class Position {
    int x;
    int y;
    Direction direction;

    public Position(final int x, final int y, final Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}