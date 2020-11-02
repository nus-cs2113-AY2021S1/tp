package anichan.parser;

import anichan.commands.Command;
import anichan.commands.RemoveCommand;
import anichan.commands.ViewWatchlistCommand;
import org.junit.jupiter.api.Test;
import anichan.exception.AniException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author michaeldinata
class RemoveCommandParserTest {
    protected static final String EMPTY_DESCRIPTION1 = "";
    protected static final String EMPTY_DESCRIPTION2 = "     ";
    protected static final String EMPTY_FIELD1 = "-d";
    protected static final String EMPTY_FIELD2 = "-d     ";
    protected static final String INVALID_PARAMETER1 = "-n 1";
    protected static final String INVALID_PARAMETER2 = "- -";
    protected static final String INVALID_PARAMETER3 = "- d";
    protected static final String NON_INTEGER_FIELD1 = "-d Trigun";
    protected static final String NON_INTEGER_FIELD2 = "-d -";
    protected static final String TOO_MANY_PARAMETERS = "-d 1 -d 2";
    protected static final String LARGE_INTEGER_ERROR = "-d 1111111111111";
    protected static final String VALID_PARAMETER_VALUE = "-d 1";

    @Test
    void parse_emptyDescription_throwsAniException() {
        RemoveCommandParser testParse1 = new RemoveCommandParser();
        assertThrows(AniException.class, () -> {
            testParse1.parse(EMPTY_DESCRIPTION1);
        });

        RemoveCommandParser testParse2 = new RemoveCommandParser();
        assertThrows(AniException.class, () -> {
            testParse2.parse(EMPTY_DESCRIPTION2);
        });
    }

    @Test
    void parse_emptyField_throwsAniException() {
        RemoveCommandParser testParse1 = new RemoveCommandParser();
        assertThrows(AniException.class, () -> {
            testParse1.parse(EMPTY_FIELD1);
        });

        RemoveCommandParser testParse2 = new RemoveCommandParser();
        assertThrows(AniException.class, () -> {
            testParse2.parse(EMPTY_FIELD2);
        });
    }

    @Test
    void parse_invalidOption_throwsAniException() {
        RemoveCommandParser testParse1 = new RemoveCommandParser();
        assertThrows(AniException.class, () -> {
            testParse1.parse(INVALID_PARAMETER1);
        });

        RemoveCommandParser testParse2 = new RemoveCommandParser();
        assertThrows(AniException.class, () -> {
            testParse2.parse(INVALID_PARAMETER2);
        });

        RemoveCommandParser testParse3 = new RemoveCommandParser();
        assertThrows(AniException.class, () -> {
            testParse3.parse(INVALID_PARAMETER3);
        });
    }

    @Test
    void parse_nonIntegerField_throwsAniException() {
        RemoveCommandParser testParse1 = new RemoveCommandParser();
        assertThrows(AniException.class, () -> {
            testParse1.parse(NON_INTEGER_FIELD1);
        });

        RemoveCommandParser testParse2 = new RemoveCommandParser();
        assertThrows(AniException.class, () -> {
            testParse2.parse(NON_INTEGER_FIELD2);
        });
    }

    @Test
    void parse_tooManyParameters_throwsAniException() {
        RemoveCommandParser testParse = new RemoveCommandParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(TOO_MANY_PARAMETERS);
        });
    }

    @Test
    void parse_largeIntegerError_throwsAniException() {
        RemoveCommandParser testParse = new RemoveCommandParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(LARGE_INTEGER_ERROR);
        });
    }

    @Test
    void parse_validParameterAndValue_returnsInfoCommand() throws AniException {
        RemoveCommandParser testParse = new RemoveCommandParser();
        Command command = testParse.parse(VALID_PARAMETER_VALUE);
        assertTrue(command instanceof RemoveCommand);
    }
}
