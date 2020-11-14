package anichan.parser;

import anichan.commands.Command;
import anichan.commands.ViewWatchlistCommand;
import org.junit.jupiter.api.Test;
import anichan.exception.AniException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author michaeldinata
class ViewWatchlistParserTest {
    private static final String EMPTY_DESCRIPTION1 = "";
    private static final String EMPTY_DESCRIPTION2 = "     ";
    private static final String EMPTY_FIELD1 = "-v";
    private static final String EMPTY_FIELD2 = "-v   ";
    private static final String INVALID_PARAMETER1 = "-";
    private static final String INVALID_PARAMETER2 = "-n 1";
    private static final String INVALID_PARAMETER3 = "- -";
    private static final String NON_INTEGER_FIELD1 = "-v Default";
    private static final String NON_INTEGER_FIELD2 = "-v -";
    private static final String TOO_MANY_PARAMETERS = "-v 1 -v 2";
    private static final String LARGE_INTEGER_ERROR = "-v 1111111111111";
    private static final String VALID_PARAMETER_VALUE = "-v 1";

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
    void parse_emptyField_throwsAniException() throws AniException {
        ViewWatchlistParser testParse1 = new ViewWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse1.parse(EMPTY_FIELD1);
        });

        ViewWatchlistParser testParse2 = new ViewWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse2.parse(EMPTY_FIELD2);
        });
    }

    @Test
    void parse_invalidParameter_throwsAniException() throws AniException {
        ViewWatchlistParser testParse1 = new ViewWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse1.parse(INVALID_PARAMETER1);
        });

        ViewWatchlistParser testParse2 = new ViewWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse2.parse(INVALID_PARAMETER2);
        });

        ViewWatchlistParser testParse3 = new ViewWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse3.parse(INVALID_PARAMETER3);
        });
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
