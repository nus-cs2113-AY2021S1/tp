package parser;

import commands.RemoveCardCommand;
import commands.RemoveChapterCommand;
import commands.RemoveModuleCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static common.Messages.ADMIN;
import static common.Messages.CHAPTER;
import static common.Messages.MODULE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemoveCommandParserTest {
    private RemoveCommandParser parser = new RemoveCommandParser();

    @Test
    public void parse_missingParts_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> parser.parse("", ADMIN));
        assertThrows(InvalidInputException.class, () -> parser.parse("", MODULE));
        assertThrows(InvalidInputException.class, () -> parser.parse("", CHAPTER));
    }

    @Test
    public void parse_incorrectLevel_throwsIncorrectAccessLevelException() {
        assertThrows(IncorrectAccessLevelException.class, () -> parser.parse("1", ""));
    }

    @Test
    public void parse_NonIntegerArgs_exception() {
        String input = "remove two";
        assertThrows(InvalidInputException.class, () -> parser.parse(input, ADMIN));
        assertThrows(InvalidInputException.class, () -> parser.parse(input, MODULE));
        assertThrows(InvalidInputException.class, () -> parser.parse(input, CHAPTER));
    }


    @Test
    public void parse_emptyArgs_throwsInvalidInputException() {
        final String[] inputs = {
            "remove ",
        };
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> parser.parse(input, ADMIN));
            assertThrows(InvalidInputException.class, () -> parser.parse(input, MODULE));
            assertThrows(InvalidInputException.class, () -> parser.parse(input, CHAPTER));
        }
    }

    @Test
    public void parse_validInputAdmin_returnsRemoveModuleCommand() throws Exception {
        assertTrue(parser.parse("1", ADMIN) instanceof RemoveModuleCommand);
    }

    @Test
    public void parse_validInputModule_returnsRemoveChapterCommand() throws Exception {
        assertTrue(parser.parse("1", MODULE) instanceof RemoveChapterCommand);
    }

    @Test
    public void parse_validInputChapter_returnsRemoveCardCommand() throws Exception {
        assertTrue(parser.parse("1", CHAPTER) instanceof RemoveCardCommand);
    }

}
