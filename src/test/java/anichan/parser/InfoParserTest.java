package anichan.parser;

import anichan.commands.Command;
import anichan.commands.InfoCommand;
import anichan.exception.AniException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author michaeldinata
class InfoParserTest {
    protected static final String EMPTY_DESCRIPTION1 = "";
    protected static final String EMPTY_DESCRIPTION2 = "     ";
    protected static final String EMPTY_FIELD1 = "-a";
    protected static final String EMPTY_FIELD2 = "-a     ";
    protected static final String INVALID_PARAMETER1 = "-n 1";
    protected static final String INVALID_PARAMETER2 = "- -";
    protected static final String INVALID_PARAMETER3 = "- a";
    protected static final String NON_INTEGER_FIELD1 = "-a Sword Art Online";
    protected static final String NON_INTEGER_FIELD2 = "-a -";
    protected static final String TOO_MANY_PARAMETERS = "-a 1 -a 2";
    protected static final String LARGE_INTEGER_ERROR = "-a 1111111111111";
    protected static final String VALID_PARAMETER_VALUE = "-a 1";

    @Test
    void parse_emptyDescription_throwsAniException() {
        InfoParser testParse1 = new InfoParser();
        assertThrows(AniException.class, () -> {
            testParse1.parse(EMPTY_DESCRIPTION1);
        });
        
        InfoParser testParse2 = new InfoParser();
        assertThrows(AniException.class, () -> {
           testParse2.parse(EMPTY_DESCRIPTION2); 
        });
    }

    @Test
    void parse_emptyField_throwsAniException() {
        InfoParser testParse1 = new InfoParser();
        assertThrows(AniException.class, () -> {
            testParse1.parse(EMPTY_FIELD1);
        });

        InfoParser testParse2 = new InfoParser();
        assertThrows(AniException.class, () -> {
            testParse2.parse(EMPTY_FIELD2);
        });
    }

    @Test
    void parse_invalidOption_throwsAniException() {
        InfoParser testParse1 = new InfoParser();
        assertThrows(AniException.class, () -> {
            testParse1.parse(INVALID_PARAMETER1);
        });

        InfoParser testParse2 = new InfoParser();
        assertThrows(AniException.class, () -> {
            testParse2.parse(INVALID_PARAMETER2);
        });

        InfoParser testParse3 = new InfoParser();
        assertThrows(AniException.class, () -> {
            testParse3.parse(INVALID_PARAMETER3);
        });
    }

    @Test
    void parse_nonIntegerField_throwsAniException() {
        InfoParser testParse1 = new InfoParser();
        assertThrows(AniException.class, () -> {
            testParse1.parse(NON_INTEGER_FIELD1);
        });

        InfoParser testParse2 = new InfoParser();
        assertThrows(AniException.class, () -> {
            testParse2.parse(NON_INTEGER_FIELD2);
        });
    }

    @Test
    void parse_tooManyParameters_throwsAniException() {
        InfoParser testParse = new InfoParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(TOO_MANY_PARAMETERS);
        });
    }

    @Test
    void parse_largeIntegerError_throwsAniException() {
        InfoParser testParse = new InfoParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(LARGE_INTEGER_ERROR);
        });
    }

    @Test
    void parse_validParameterAndValue_returnsInfoCommand() throws AniException {
        InfoParser testParse = new InfoParser();
        Command command = testParse.parse(VALID_PARAMETER_VALUE);
        assertTrue(command instanceof InfoCommand);
    }
}
