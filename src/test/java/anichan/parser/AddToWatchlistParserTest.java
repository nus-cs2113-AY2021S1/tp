package anichan.parser;

import org.junit.jupiter.api.Test;

import anichan.exception.AniException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AddToWatchlistParserTest {
    protected static final String EMPTY_DESCRIPTION = "";
    protected static final String EMPTY_FIELD = "-a";
    protected static final String INVALID_PARAMETER = "-n 1";
    protected static final String NON_INTEGER_FIELD = "-a Gundam";
    protected static final String TOO_MANY_PARAMETERS = "-a 1 -a 2";

    @Test
    void parse_emptyDescription_throwsAniException() {
        AddToWatchlistParser testParse = new AddToWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(EMPTY_DESCRIPTION);
        });
    }

    @Test
    void parse_emptyField_throwsAniException() {
        AddToWatchlistParser testParse = new AddToWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(EMPTY_FIELD);
        });
    }

    @Test
    void parse_invalidOption_throwsAniException() {
        AddToWatchlistParser testParse = new AddToWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(INVALID_PARAMETER);
        });
    }

    @Test
    void parse_nonIntegerField_throwsAniException() {
        AddToWatchlistParser testParse = new AddToWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(NON_INTEGER_FIELD);
        });
    }
    
    @Test
    void parse_tooManyParameters_throwsAniException() {
        AddToWatchlistParser testParse = new AddToWatchlistParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(TOO_MANY_PARAMETERS);
        });
    }
}
