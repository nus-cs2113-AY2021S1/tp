package anichan.parser;

import anichan.commands.Command;
import anichan.commands.InfoCommand;
import anichan.exception.AniException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author michaeldinata
class InfoParserTest {
    private static final String EMPTY_DESCRIPTION1 = "";
    private static final String EMPTY_DESCRIPTION2 = "     ";
    private static final String NON_INTEGER_FIELD1 = "Sword Art Online";
    private static final String NON_INTEGER_FIELD2 = "-a";
    private static final String TOO_MANY_FIELDS = "123 211";
    private static final String LARGE_INTEGER_ERROR = "1111111111111";
    private static final String VALID_FIELD_VALUE1 = "1";
    private static final String VALID_FIELD_VALUE2 = "   1";
    private static final String VALID_FIELD_VALUE3 = "1   ";

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
            testParse.parse(TOO_MANY_FIELDS);
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
    void parse_validValue_returnsInfoCommand() throws AniException {
        InfoParser testParse1 = new InfoParser();
        Command command1 = testParse1.parse(VALID_FIELD_VALUE1);
        assertTrue(command1 instanceof InfoCommand);

        InfoParser testParse2 = new InfoParser();
        Command command2 = testParse2.parse(VALID_FIELD_VALUE2);
        assertTrue(command2 instanceof InfoCommand);

        InfoParser testParse3 = new InfoParser();
        Command command3 = testParse3.parse(VALID_FIELD_VALUE3);
        assertTrue(command3 instanceof InfoCommand);
    }
}
