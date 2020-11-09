package parser;

import commands.BackAdminCommand;
import commands.BackModuleCommand;
import commands.GoChapterCommand;
import commands.GoModuleCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.security.KeyStore;

import static common.Messages.ADMIN;
import static common.Messages.CHAPTER;
import static common.Messages.MODULE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoCommandParserTest {
    private GoCommandParser parser = new GoCommandParser();

    @Test
    public void parse_withValidInput_returnsGoModuleCommand()
            throws InvalidInputException, IncorrectAccessLevelException {
        assertTrue(parser.parse("1", ADMIN) instanceof GoModuleCommand);
    }

    @Test
    public void parse_withValidInput_returnsGoChapterCommand()
            throws InvalidInputException, IncorrectAccessLevelException {
        assertTrue(parser.parse("1", MODULE) instanceof GoChapterCommand);
    }

    @Test
    public void parse_withInvalidInput_throwsInvalidInputException() {
        // empty arguments
        assertThrows(InvalidInputException.class, () -> parser.parse("", ADMIN));
        assertThrows(InvalidInputException.class, () -> parser.parse("", MODULE));

        // not index
        assertThrows(InvalidInputException.class, () -> parser.parse("w", ADMIN));
        assertThrows(InvalidInputException.class, () -> parser.parse("y", MODULE));
    }

    @Test
    public void parse_accessFromChapterLevel_throwsIncorrectAccessLevelException() {
        assertThrows(IncorrectAccessLevelException.class, () -> parser.parse("1", CHAPTER));
    }

}
