package parser;

import commands.ExitCommand;
import exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandParserTest {
    private ExitCommandParser parser = new ExitCommandParser();

    @Test
    public void parse_withArgs_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> parser.parse("args"));
    }

    @Test
    public void parse_noArgs_returnsExitCommand() throws Exception {
        assertTrue(parser.parse("") instanceof ExitCommand);
    }

}
