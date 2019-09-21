package util;

import elements.Position;
import elements.enums.Command;
import elements.enums.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * CLI processing utility to process the input received from command line.
 */
public class CommandLineProcessor {

    // Check if the give command is valid and has approprate parameters
    public static boolean isValidCommand(final String input) {
        String[] command = input.split("[ ,]+");
        try{
            Command commandEnum = Command.valueOf(command[0]);
            if(commandEnum.equals(Command.PLACE)){
                if( command.length != 4 ) return false;
                Integer.valueOf(command[1]);
                Integer.valueOf(command[2]);
                Direction.valueOf(command[3]);
            }
            else{
                if(command.length > 1) return false;
            }
        }catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }

    // Process the string command to corresponding enum
    public static Command processCommand(final String command) {
        return Command.valueOf(command.split(" ")[0]);
    }

    // Process the parameters from the command
    public static Optional<Position> processParams(final String params) {
        Optional<Position> returnValue = Optional.empty();
        if( processCommand(params).equals(Command.PLACE)){
            List<String> paramsList = Arrays.stream(params.split("[ ,]+")).skip(1).collect(Collectors.toList());
            int x = Integer.parseInt(paramsList.get(0));
            int y = Integer.parseInt(paramsList.get(1));
            Direction direction = Direction.valueOf(paramsList.get(2));
            Position position = new Position(x, y, direction);
            returnValue = Optional.of(position);
        }
        return returnValue;
    }
}
