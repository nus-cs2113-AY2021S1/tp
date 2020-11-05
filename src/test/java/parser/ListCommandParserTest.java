package parser;

import commands.ListCardsCommand;
import commands.ListChaptersCommand;
import commands.ListModulesCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static common.Messages.ADMIN;
import static common.Messages.CHAPTER;
import static common.Messages.MODULE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_withArgs_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> parser.parse("args", ADMIN));
        assertThrows(InvalidInputException.class, () -> parser.parse("args", MODULE));
        assertThrows(InvalidInputException.class, () -> parser.parse("args", CHAPTER));
    }

    @Test
    public void parse_incorrectLevel_throwsIncorrectAccessLevelException() {
        assertThrows(IncorrectAccessLevelException.class, () -> parser.parse("", ""));
    }

    @Test
    public void parse_noArgs_returnsListModulesCommand() throws Exception {
        assertTrue(parser.parse("", ADMIN) instanceof ListModulesCommand);
    }

    @Test
    public void parse_noArgs_returnsListChaptersCommand() throws Exception {
        assertTrue(parser.parse("", MODULE) instanceof ListChaptersCommand);
    }

    @Test
    public void parse_noArgs_returnsListCardsCommand() throws Exception {
        assertTrue(parser.parse("", CHAPTER) instanceof ListCardsCommand);
    }

}
