package game;

import elements.Position;
import elements.enums.Command;
import elements.exception.GameException;
import java.util.Optional;

public interface Game {
    void play(Command command, Optional<Position> optionalParams) throws GameException;
    boolean isValidMove(Command command, Position position);
}
