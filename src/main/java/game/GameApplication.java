package game;

import elements.GridTable;
import elements.ToyRobot;
import elements.exception.GameException;
import util.CommandLineProcessor;

import java.util.Scanner;

/**
 * Application entry point :
 *   - accepts commandline input
 *   - validates cli input
 *   - invokes game's play method
 */
public class GameApplication {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Game game = new ToyRobotGame(new ToyRobot(), new GridTable(4,4));

        System.out.println("Valid Commands : \'PLACE X,Y,EAST|WEST|NORTH|SOUTH\', MOVE, LEFT, RIGHT, REPORT");

        String input;
        while ((input = scanner.nextLine()) != null) {
            if(! CommandLineProcessor.isValidCommand(input)){
                System.out.println("Invalid command...");
                continue;
            }
            try {
                game.play(CommandLineProcessor.processCommand(input), CommandLineProcessor.processParams(input));
            }catch (GameException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
