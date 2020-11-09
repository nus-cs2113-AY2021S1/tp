package parser;

import commands.BackAdminCommand;
import commands.BackModuleCommand;
import commands.ShowRateCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static common.Messages.ADMIN;
import static common.Messages.CHAPTER;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShowRateParserTest {
    private ShowRateCommandParser parser = new ShowRateCommandParser();

    @Test
    public void parse_withArgs_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> parser.parse("args", CHAPTER));
    }

    @Test
    public void parse_noArgs_returnsShowRateCommand() throws Exception {
        assertTrue(parser.parse("", CHAPTER) instanceof ShowRateCommand);
    }

    @Test
    public void parse_invalidAccess_returnFail() throws Exception {
        assertThrows(IncorrectAccessLevelException.class, () -> parser.parse("", ADMIN));
    }
}
