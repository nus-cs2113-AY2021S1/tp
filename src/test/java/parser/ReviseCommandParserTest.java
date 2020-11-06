package parser;

import commands.ReviseCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static common.Messages.*;
import static common.Messages.CHAPTER;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReviseCommandParserTest {
    private ReviseCommandParser parser = new ReviseCommandParser();

    @Test
    public void parse_missingParts_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> parser.parse("", MODULE));
    }

    @Test
    public void parse_incorrectLevel_throwsIncorrectAccessLevelException() {
        assertThrows(IncorrectAccessLevelException.class, () -> parser.parse("1", ""));
        assertThrows(IncorrectAccessLevelException.class, () -> parser.parse("1", ADMIN));
        assertThrows(IncorrectAccessLevelException.class, () -> parser.parse("1", CHAPTER));
    }

    @Test
    public void parse_NonIntegerArgs_exception() {
        String input = "revise two";
        assertThrows(InvalidInputException.class, () -> parser.parse(input, MODULE));
    }


    @Test
    public void parse_emptyArgs_throwsInvalidInputException() {
        final String[] inputs = {
                "revise ",
        };
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> parser.parse(input, MODULE));
        }
    }

    @Test
    public void parse_validInput_returnsReviseModuleCommand() throws Exception {
        assertTrue(parser.parse("1", MODULE) instanceof ReviseCommand);
    }
}
