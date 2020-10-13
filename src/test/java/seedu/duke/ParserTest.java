package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testAddFoodCommand() {
        assertEquals(new AddFoodCommand("Food apple"), Parser.parse("Food apple"));
        assertEquals(new InvalidCommand("Food"), Parser.parse("Food"));
    }

    @Test
    public void testAddExerciseCommand() {
        assertEquals(new AddExerciseCommand("Exercise Push ups"), Parser.parse("Exercise Push ups"));
        assertEquals(new InvalidCommand("Exercise"), Parser.parse("Exercise"));
    }

    @Test
    public void testViewCommand() {
        assertEquals(new ViewCommand("View"), Parser.parse("View"));
    }

    @Test
    public void testInvalidCommand() {
        assertEquals(new InvalidCommand("haha"), Parser.parse("haha"));
    }

    @Test
    public void testDeleteCommand() {
        assertEquals(new DeleteCommand("delete 1"), Parser.parse("delete 1"));
        assertEquals(new InvalidCommand("delete"), Parser.parse("delete"));
    }

    @Test
    public void testExitCommand() {
        assertEquals(new ExitCommand("bye"), Parser.parse("bye"));
    }

    @Test
    public void testHelpCommand() {
        assertEquals(new HelpCommand("help"), Parser.parse("help"));
    }
}
