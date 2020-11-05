package parser;

import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static common.Messages.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditCommandParserTest {
    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_throwsInvalidInputException() {
        // no index and no field specified
        assertThrows(InvalidInputException.class, () -> parser.parse("", ADMIN));
        assertThrows(InvalidInputException.class, () -> parser.parse("", MODULE));
        assertThrows(InvalidInputException.class, () -> parser.parse("", CHAPTER));

        // no index specified
        assertThrows(InvalidInputException.class, () -> parser.parse("CS2113", ADMIN));
        assertThrows(InvalidInputException.class, () -> parser.parse("Chapter1", MODULE));
        assertThrows(InvalidInputException.class, () -> parser.parse("q:1+1 | a:", CHAPTER));

        // no field specified
        assertThrows(InvalidInputException.class, () -> parser.parse("1", ADMIN));
        assertThrows(InvalidInputException.class, () -> parser.parse("1", MODULE));
        assertThrows(InvalidInputException.class, () -> parser.parse("1", CHAPTER));
    }

    @Test
    public void parse_incorrectLevel_throwsIncorrectAccessLevelException() {
        assertThrows(IncorrectAccessLevelException.class, () -> parser.parse("1 CS2113", ""));
    }

    @Test
    public void parse_invalidModuleName_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> parser.parse("1 ../Non-alphanumeric", ADMIN));
    }

    @Test
    public void parse_invalidChapterName_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> parser.parse("1 ../Non-alphanumeric", MODULE));
    }

    @Test
    public void parse_invalidQuestionAnswer_throwsInvalidInputException() {
        final String[] inputs = {
                // no content for question and answer
                "1 q: | a:",
                // no question and answer separator prefix
                "1 q:1*1 a:1",
                // no question prefix
                "1 1+1 | a:",
                // no answer prefix
                "1 q:1+1 | 2",
        };
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> parser.parse(input, CHAPTER));
        }
    }

}
