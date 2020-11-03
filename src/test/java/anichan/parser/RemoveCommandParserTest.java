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
    private static final String EMPTY_DESCRIPTION1 = "";
    private static final String EMPTY_DESCRIPTION2 = "     ";
    private static final String NON_INTEGER_FIELD1 = "Trigun";
    private static final String NON_INTEGER_FIELD2 = "-a";
    private static final String TOO_MANY_FIELDS = "123 211";
    private static final String LARGE_INTEGER_ERROR = "1111111111111";
    private static final String VALID_FIELD_VALUE1 = "1";
    private static final String VALID_FIELD_VALUE2 = "   1";
    private static final String VALID_FIELD_VALUE3 = "1   ";

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
            testParse.parse(TOO_MANY_FIELDS);
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
    void parse_validValue_returnsInfoCommand() throws AniException {
        RemoveCommandParser testParse1 = new RemoveCommandParser();
        Command command1 = testParse1.parse(VALID_FIELD_VALUE1);
        assertTrue(command1 instanceof RemoveCommand);

        RemoveCommandParser testParse2 = new RemoveCommandParser();
        Command command2 = testParse2.parse(VALID_FIELD_VALUE2);
        assertTrue(command2 instanceof RemoveCommand);

        RemoveCommandParser testParse3 = new RemoveCommandParser();
        Command command3 = testParse3.parse(VALID_FIELD_VALUE3);
        assertTrue(command3 instanceof RemoveCommand);
    }
}
