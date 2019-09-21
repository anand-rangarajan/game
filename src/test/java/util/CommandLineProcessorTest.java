package util;

import elements.enums.Command;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.Optional;

public class CommandLineProcessorTest {
    @Test
    public void testProcessCommandValidCommands(){
        Arrays.stream(Command.values()).forEach(x -> {
            Assert.assertEquals(x, CommandLineProcessor.processCommand(x.toString()));
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProcessCommandInvalidCommands(){
        CommandLineProcessor.processCommand("RANDOM");
    }

    @Test
    public void testIsValidCommand(){
        //negative cases
        Arrays.stream(Command.values()).filter(command -> !command.equals(Command.PLACE)).forEach(x -> {
            Assert.assertFalse(CommandLineProcessor.isValidCommand(x + "0,0,NORTH"));
        });
        Assert.assertFalse(CommandLineProcessor.isValidCommand("PLACE"));
        Assert.assertFalse(CommandLineProcessor.isValidCommand("RANDOM"));

        //positive cases
        Assert.assertTrue(CommandLineProcessor.isValidCommand("PLACE 0,0,NORTH"));
        Arrays.stream(Command.values()).filter(command -> !command.equals(Command.PLACE)).forEach(x -> {
            Assert.assertTrue(CommandLineProcessor.isValidCommand(x.toString()));
        });
    }

    @Test
    public void testProcessParams(){
        Arrays.stream(Command.values()).filter(command -> !command.equals(Command.PLACE)).forEach(x -> {
            Assert.assertEquals(Optional.empty(), CommandLineProcessor.processParams(x.toString()));
        });

        Arrays.stream(Command.values()).filter(command -> !command.equals(Command.PLACE)).forEach(x -> {
            Assert.assertEquals(Optional.empty(), CommandLineProcessor.processParams(x.toString() + " test"));
        });
    }
}
