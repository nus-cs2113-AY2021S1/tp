package anichan.parser;

import anichan.commands.Command;
import anichan.commands.ViewWatchlistCommand;
import org.junit.jupiter.api.Test;
import anichan.exception.AniException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author michaeldinata
class ViewWatchlistParserTest {
    protected static final String EMPTY_DESCRIPTION1 = "";
    protected static final String EMPTY_DESCRIPTION2 = "     ";
    protected static final String NON_INTEGER_FIELD1 = "Default";
    protected static final String NON_INTEGER_FIELD2 = "-v";
    protected static final String TOO_MANY_PARAMETERS = "1 2";
    protected static final String LARGE_INTEGER_ERROR = "1111111111111";
    protected static final String VALID_PARAMETER_VALUE = "1";

    @Test
    void parse_emptyDescription_throwsAniException() throws AniException {
        ViewWatchlistParser testParse1 = new ViewWatchlistParser();
        Command command1 = testParse1.parse(EMPTY_DESCRIPTION1);
        assertTrue(command1 instanceof ViewWatchlistCommand);

        ViewWatchlistParser testParse2 = new ViewWatchlistParser();
        Command command2 = testParse2.parse(EMPTY_DESCRIPTION2);
        assertTrue(command2 instanceof ViewWatchlistCommand);
    }

    @Test
    void parse_nonIntegerField_throwsAniException() {
        ViewWatchlistParser testParse1 = new ViewWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse1.parse(NON_INTEGER_FIELD1);
        });

        ViewWatchlistParser testParse2 = new ViewWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse2.parse(NON_INTEGER_FIELD2);
        });
    }

    @Test
    void parse_tooManyParameters_throwsAniException() {
        ViewWatchlistParser testParse = new ViewWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(TOO_MANY_PARAMETERS);
        });
    }

    @Test
    void parse_largeIntegerError_throwsAniException() {
        ViewWatchlistParser testParse = new ViewWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(LARGE_INTEGER_ERROR);
        });
    }

    @Test
    void parse_validParameterAndValue_returnsInfoCommand() throws AniException {
        ViewWatchlistParser testParse = new ViewWatchlistParser();
        Command command = testParse.parse(VALID_PARAMETER_VALUE);
        assertTrue(command instanceof ViewWatchlistCommand);
    }
}
