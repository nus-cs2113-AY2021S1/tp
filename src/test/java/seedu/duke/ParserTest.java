package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    public void parseUserInput_emptyInput_showInvalidCommand() {
        Parser parser = new Parser();

        String input = "";

        assertThrows(DukeException.class, () -> {
            parser.parseUserInput(input);
        });
    }

    @Test
    public void parseUserInput_oneWord_returnsOneString() throws DukeException {
        Parser parser = new Parser();

        String input = "test";
        String[] stringParts = parser.parseUserInput(input);

        assertEquals(stringParts.length, 1);
    }

    @Test
    public void parseUserInput_moreThanTwoWords_returnsTwoStrings() throws DukeException {
        Parser parser = new Parser();

        String input = "test more than two";
        String[] stringParts = parser.parseUserInput(input);

        assertEquals(stringParts.length, 2);
    }
}