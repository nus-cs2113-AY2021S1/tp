package logic.parser;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ui.CommonUi.EMPTY_STRING;

//@@author tienkhoa16
class CommonParserTest {

    @Test
    void testParseCommand_inputTwoWords_returnNonEmptyStringInSecondElement() {
        CommonParser parser = new CommonParser();
        String rawUserInput = "add /n";
        String[] expectedSplit = {"add", "/n"};

        assertEquals(Arrays.toString(expectedSplit),
                Arrays.toString(parser.parseCommand(rawUserInput)));
    }

    @Test
    void testParseCommand_inputThreeWords_returnNonEmptyStringInSecondElement() {
        CommonParser parser = new CommonParser();
        String rawUserInput = "add /n test";
        String[] expectedSplit = {"add", "/n test"};

        assertEquals(Arrays.toString(expectedSplit),
                Arrays.toString(parser.parseCommand(rawUserInput)));
    }

    @Test
    void testParseCommand_inputOneWord_returnEmptyStringInSecondElement() {
        CommonParser parser = new CommonParser();
        String rawUserInput = "add";
        String[] expectedSplit = {"add", EMPTY_STRING};

        assertEquals(Arrays.toString(expectedSplit),
                Arrays.toString(parser.parseCommand(rawUserInput)));
    }

    @Test
    void testParseCommand_inputEmptyString_throwsAssertionError() {
        CommonParser parser = new CommonParser();

        assertThrows(AssertionError.class, () -> {
            parser.parseCommand(EMPTY_STRING);
        });
    }

    @Test
    void testParseCommand_inputNull_throwsAssertionError() {
        CommonParser parser = new CommonParser();
        assertThrows(AssertionError.class, () -> {
            parser.parseCommand(null);
        });
    }
}
