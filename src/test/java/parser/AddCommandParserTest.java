package parser;

import commands.AddCardCommand;
import commands.AddChapterCommand;
import commands.AddModuleCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static common.Messages.ADMIN;
import static common.Messages.CHAPTER;
import static common.Messages.MODULE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_missingParts_throwsInvalidInputException() {
        // no field specified
        assertThrows(InvalidInputException.class, () -> parser.parse("", ADMIN));
        assertThrows(InvalidInputException.class, () -> parser.parse("", MODULE));
        assertThrows(InvalidInputException.class, () -> parser.parse("", CHAPTER));
    }

    @Test
    public void parse_incorrectLevel_throwsIncorrectAccessLevelException() {
        assertThrows(IncorrectAccessLevelException.class, () -> parser.parse("CS2113", ""));
    }

    @Test
    public void parse_invalidModuleName_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> parser.parse("../Non-alphanumeric", ADMIN));
    }

    @Test
    public void parse_invalidChapterName_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> parser.parse("../Non-alphanumeric", MODULE));
    }

    @Test
    public void parse_invalidQuestionAnswer_throwsInvalidInputException() {
        final String[] inputs = {
            // no content for question
            "q: | a:2",
            // no content for answer
            "q:1+1 | a:",
            // no question and answer separator prefix
            "q:1+1 a:2",
            // no question prefix
            "1+1 | a:2",
            // no answer prefix
            "q:1+1 | 2",
        };
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> parser.parse(input, CHAPTER));
        }
    }

    @Test
    public void parse_validInput_returnsAddModuleCommand() throws Exception {
        assertTrue(parser.parse("CS2113", ADMIN) instanceof AddModuleCommand);
    }

    @Test
    public void parse_validInput_returnsEditChapterCommand() throws Exception {
        assertTrue(parser.parse("Chapter 1", MODULE) instanceof AddChapterCommand);
    }

    @Test
    public void parse_validInput_returnsEditCardCommand() throws Exception {
        assertTrue(parser.parse("q:1+1 | a:2", CHAPTER) instanceof AddCardCommand);
    }

}
