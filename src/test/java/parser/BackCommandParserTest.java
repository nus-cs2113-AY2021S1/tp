package parser;

import commands.BackAdminCommand;
import commands.BackModuleCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static common.Messages.ADMIN;
import static common.Messages.MODULE;
import static common.Messages.CHAPTER;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BackCommandParserTest {
    private BackCommandParser parser = new BackCommandParser();

    @Test
    public void parse_withArgs_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> parser.parse("args", MODULE));
    }

    @Test
    public void parse_noArgs_returnsBackAdminCommand() throws Exception {
        assertTrue(parser.parse("", MODULE) instanceof BackAdminCommand);
    }

    @Test
    public void parse_noArgs_returnsBackModuleCommand() throws Exception {
        assertTrue(parser.parse("", CHAPTER) instanceof BackModuleCommand);
    }

    @Test
    public void parse_wrongAccess_returnFail() throws Exception {
        assertThrows(IncorrectAccessLevelException.class, () -> parser.parse("", ADMIN));
    }
}
