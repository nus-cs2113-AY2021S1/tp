package fitr.command;

import fitr.parser.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ParserTest {
    @Test
    public void testAddFoodCommand() {
        assertSame(new AddFoodCommand("Food apple"), Parser.parse("Food apple"));
        assertSame(new InvalidCommand("Food"), Parser.parse("Food"));
    }

    @Test
    public void testAddExerciseCommand() {
        assertSame(new AddExerciseCommand("Exercise Push ups"), Parser.parse("Exercise Push ups"));
        assertSame(new InvalidCommand("Exercise"), Parser.parse("Exercise"));
    }

    @Test
    public void testViewCommand() {
        assertSame(new ViewCommand("View"), Parser.parse("View"));
    }

    @Test
    public void testInvalidCommand() {
        assertSame(new InvalidCommand("haha"), Parser.parse("haha"));
    }

    @Test
    public void testDeleteCommand() {
        assertSame(new DeleteCommand("delete 1"), Parser.parse("delete 1"));
        assertSame(new InvalidCommand("delete"), Parser.parse("delete"));
    }

    @Test
    public void testExitCommand() {
        assertSame(new ExitCommand("bye"), Parser.parse("bye"));
    }
}