package seedu.modtracker;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    ModuleList modList = new ModuleList();
    Parser parser = new Parser();

    @Test
    public void parse_invalidInput_expectPrintInvalid() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "exit addmod ";
        parser.parse(input, modList, null, null, true);
        String expected = "OOPS!!! I'm sorry, but I don't know what that means :-(" + System.lineSeparator();

        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_blankInput_expectPrintInvalid() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "";
        parser.parse(input, modList, null, null, true);
        String expected = "OOPS!!! I'm sorry, but I don't know what that means :-(" + System.lineSeparator();

        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_exitInput_expectPrintExit() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "exit";
        String name = "Lee";
        parser.parse(input, modList, name, null, true);
        String expected = "All changes saved." + System.lineSeparator()
                + "Bye " + name + ". Hope to see you again soon!" + System.lineSeparator();

        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_exitInput_expectExitStatusTrue() {
        String input = "exit";
        parser.parse(input, modList, null, null, true);
        assertTrue(parser.isExit());
    }

    @Test
    public void parse_anyInput_expectExitStatusFalse() {
        String input = "addexp ";
        parser.parse(input, modList, null, null, true);
        assertFalse(parser.isExit());
    }
}
