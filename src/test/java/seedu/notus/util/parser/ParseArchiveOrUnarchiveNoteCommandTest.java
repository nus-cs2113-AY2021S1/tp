package seedu.notus.util.parser;

//@@author R-Ramana

import org.junit.jupiter.api.Test;
import seedu.notus.command.ArchiveNoteCommand;
import seedu.notus.command.Command;
import seedu.notus.command.UnarchiveNoteCommand;

import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParseArchiveOrUnarchiveNoteCommandTest {

    private SystemException exception;
    private String inputString;
    private ParseArchiveOrUnarchiveNoteCommand parser;
    private Command command;
    private String expectedMessage;
    private String actualMessage;

    @Test
    void parseCommand_validMessage_returnNewCommand() {
        inputString = "/i 1 ";

        parser = new ParseArchiveOrUnarchiveNoteCommand(inputString, true);

        try {
            command = parser.parse();
            assertTrue(command instanceof ArchiveNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        parser = new ParseArchiveOrUnarchiveNoteCommand(inputString, false);

        try {
            command = parser.parse();
            assertTrue(command instanceof UnarchiveNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        inputString = "/t Test Title";

        parser = new ParseArchiveOrUnarchiveNoteCommand(inputString, true);

        try {
            command = parser.parse();
            assertTrue(command instanceof ArchiveNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        parser = new ParseArchiveOrUnarchiveNoteCommand(inputString, false);

        try {
            command = parser.parse();
            assertTrue(command instanceof UnarchiveNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void parseCommand_missingContent_throwException() {
        parser = new ParseArchiveOrUnarchiveNoteCommand(null, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_MESSAGE_AFTER_COMMAND.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseArchiveOrUnarchiveNoteCommand(null, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_MESSAGE_AFTER_COMMAND.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseCommand_invalidInput_throwException() {
        inputString = "";

        parser = new ParseArchiveOrUnarchiveNoteCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_INPUT_FORMAT.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseArchiveOrUnarchiveNoteCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_INPUT_FORMAT.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = " ";

        parser = new ParseArchiveOrUnarchiveNoteCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_INPUT_FORMAT.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseArchiveOrUnarchiveNoteCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_INPUT_FORMAT.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseCommand_missingIndexValue_throwException() {
        inputString = "/i ";

        parser = new ParseArchiveOrUnarchiveNoteCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_INDEX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseArchiveOrUnarchiveNoteCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_INDEX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseCommand_invalidIndexValue_throwException() {
        inputString = "/i -50";

        parser = new ParseArchiveOrUnarchiveNoteCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_INDEX_VALUE.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseArchiveOrUnarchiveNoteCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_INDEX_VALUE.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = "/i 0";

        parser = new ParseArchiveOrUnarchiveNoteCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_INDEX_VALUE.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseArchiveOrUnarchiveNoteCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_INVALID_INDEX_VALUE.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseCommand_missingTitleDescription_throwException() {
        inputString = "/t";

        parser = new ParseArchiveOrUnarchiveNoteCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TITLE.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseArchiveOrUnarchiveNoteCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TITLE.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}