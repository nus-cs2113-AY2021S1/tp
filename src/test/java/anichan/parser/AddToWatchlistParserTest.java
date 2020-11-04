package anichan.parser;

import anichan.commands.AddToWatchlistCommand;
import anichan.commands.Command;
import org.junit.jupiter.api.Test;

import anichan.exception.AniException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author michaeldinata
class AddToWatchlistParserTest {
    private static final String EMPTY_DESCRIPTION1 = "";
    private static final String EMPTY_DESCRIPTION2 = "     ";
    private static final String NON_INTEGER_FIELD1 = "Gundam";
    private static final String NON_INTEGER_FIELD2 = "-a";
    private static final String TOO_MANY_FIELDS = "123 211";
    private static final String LARGE_INTEGER_ERROR = "1111111111111";
    private static final String VALID_FIELD_VALUE1 = "1";
    private static final String VALID_FIELD_VALUE2 = "   1";
    private static final String VALID_FIELD_VALUE3 = "1   ";

    @Test
    void parse_emptyDescription_throwsAniException() {
        AddToWatchlistParser testParse1 = new AddToWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse1.parse(EMPTY_DESCRIPTION1);
        });
        
        AddToWatchlistParser testParse2 = new AddToWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse2.parse(EMPTY_DESCRIPTION2);
        });
    }

    @Test
    void parse_nonIntegerField_throwsAniException() {
        AddToWatchlistParser testParse1 = new AddToWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse1.parse(NON_INTEGER_FIELD1);
        });

        AddToWatchlistParser testParse2 = new AddToWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse2.parse(NON_INTEGER_FIELD2);
        });
    }
    
    @Test
    void parse_tooManyParameters_throwsAniException() {
        AddToWatchlistParser testParse = new AddToWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(TOO_MANY_FIELDS);
        });
    }
    
    @Test
    void parse_largeIntegerError_throwsAniException() {
        AddToWatchlistParser testParse = new AddToWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(LARGE_INTEGER_ERROR);
        });
    }
    
    @Test
    void parse_validParameterAndValue_returnsAddToWatchlistCommand() throws AniException {
        AddToWatchlistParser testParse1 = new AddToWatchlistParser();
        Command command1 = testParse1.parse(VALID_FIELD_VALUE1);
        assertTrue(command1 instanceof AddToWatchlistCommand);

        AddToWatchlistParser testParse2 = new AddToWatchlistParser();
        Command command2 = testParse2.parse(VALID_FIELD_VALUE2);
        assertTrue(command2 instanceof AddToWatchlistCommand);

        AddToWatchlistParser testParse3 = new AddToWatchlistParser();
        Command command3 = testParse3.parse(VALID_FIELD_VALUE3);
        assertTrue(command3 instanceof AddToWatchlistCommand);
    }
}
