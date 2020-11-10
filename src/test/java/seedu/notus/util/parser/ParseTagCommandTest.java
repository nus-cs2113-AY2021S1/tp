package seedu.notus.util.parser;

//@@author Chongjx

import org.junit.jupiter.api.Test;
import seedu.notus.command.Command;
import seedu.notus.command.TagEventCommand;
import seedu.notus.command.TagNoteCommand;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParseTagCommandTest {

    private ParseTagCommand parser;
    private SystemException exception;
    private String inputString;
    private String expectedMessage;
    private String actualMessage;

    @Test
    void parseCommand_validMessage_returnNewCommand() {
        inputString = "/i 1 /tag tag1 ";

        parser = new ParseTagCommand(inputString, true);
        Command command;
        try {
            command = parser.parse();
            assertTrue(command instanceof TagNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        parser = new ParseTagCommand(inputString, false);
        try {
            command = parser.parse();
            assertTrue(command instanceof TagEventCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        inputString = "/tag tag1/i 1/tag tag2";

        parser = new ParseTagCommand(inputString, true);
        try {
            command = parser.parse();
            assertTrue(command instanceof TagNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        parser = new ParseTagCommand(inputString, false);
        try {
            command = parser.parse();
            assertTrue(command instanceof TagEventCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void parseCommand_missingContent_throwException() {
        parser = new ParseTagCommand(null, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        String expectedMessage = ExceptionType.EXCEPTION_MISSING_MESSAGE_AFTER_COMMAND.toString();
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseTagCommand(null, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_MESSAGE_AFTER_COMMAND.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseCommand_missingTagPrefix_throwException() {
        inputString = "";

        parser = new ParseTagCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TAG_PREFIX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseTagCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TAG_PREFIX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = " ";

        parser = new ParseTagCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TAG_PREFIX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseTagCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TAG_PREFIX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseCommand_missingTagDescription_throwException() {
        inputString = "/tag ";

        parser = new ParseTagCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TAG.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseTagCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TAG.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = "/tag tag1/tag";

        parser = new ParseTagCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TAG.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseTagCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TAG.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseCommand_missingIndexPrefix_throwException() {
        inputString = "/tag tag1";

        parser = new ParseTagCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_INDEX_PREFIX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseTagCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_INDEX_PREFIX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseCommand_missingIndex_throwException() {
        inputString = "/tag tag1 /i ";

        parser = new ParseTagCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_INDEX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseTagCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_INDEX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = "/i /tag tag1";

        parser = new ParseTagCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_INDEX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseTagCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_INDEX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseCommand_invalidIndex_throwException() {
        inputString = "/i 0";

        parser = new ParseTagCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_INDEX_VALUE.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseTagCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_INDEX_VALUE.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = "/i -5";

        parser = new ParseTagCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_INDEX_VALUE.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseTagCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_INDEX_VALUE.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = "/i notNumber";

        parser = new ParseTagCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_INDEX_FORMAT.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseTagCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_INDEX_FORMAT.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseCommand_invalidPrefix_throwException() {
        inputString = "/t title";

        parser = new ParseTagCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_PREFIX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseTagCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_PREFIX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = "/i 1 /tag tag1 /t title";

        parser = new ParseTagCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_PREFIX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseTagCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_PREFIX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}