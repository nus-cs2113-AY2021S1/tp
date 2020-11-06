package seedu.notus.util.parser;

//@@author R-Ramana

import seedu.notus.command.ArchiveNoteCommand;
import seedu.notus.command.Command;
import seedu.notus.command.ListNoteCommand;
import seedu.notus.command.UnarchiveNoteCommand;
import seedu.notus.data.exception.SystemException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParseListNoteCommandTest {

    private ParseListNoteCommand parser;
    private SystemException exception;
    private Command command;
    private String inputString;
    private String expectedMessage;
    private String actualMessage;

    @Test
    void parseCommand_validMessage_returnNewCommand() {
        inputString = "/tag cs2113 ";

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        inputString = " /sort up";

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        inputString = "/sort down";

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        inputString = "/tag cs2113/sort down";

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        inputString = "/tag cs2113/sort down /archive";

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        inputString = "/tag cs2113 /archive";

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        inputString = "/archive";

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        inputString = "";

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        inputString = " ";

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        inputString = " random input ";

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        parser = new ParseListNoteCommand(inputString);

        try {
            command = parser.parse();
            assertTrue(command instanceof ListNoteCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void parseCommand_missingTagName_throwException() {
        inputString = "/tag ";

        parser = new ParseListNoteCommand(inputString);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = SystemException.ExceptionType.EXCEPTION_MISSING_TAG.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = "/tag /sort up";

        parser = new ParseListNoteCommand(inputString);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = SystemException.ExceptionType.EXCEPTION_MISSING_TAG.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = "/tag /sort down /archive";

        parser = new ParseListNoteCommand(inputString);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = SystemException.ExceptionType.EXCEPTION_MISSING_TAG.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = "/tag /sort test /archive";

        parser = new ParseListNoteCommand(inputString);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = SystemException.ExceptionType.EXCEPTION_MISSING_TAG.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseCommand_missingSortOrder_throwException() {
        inputString = "/sort";

        parser = new ParseListNoteCommand(inputString);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = SystemException.ExceptionType.EXCEPTION_MISSING_SORT.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = "/sort /tag cs2113";

        parser = new ParseListNoteCommand(inputString);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = SystemException.ExceptionType.EXCEPTION_MISSING_SORT.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = "/sort /archive /tag";

        parser = new ParseListNoteCommand(inputString);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = SystemException.ExceptionType.EXCEPTION_MISSING_SORT.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseCommand_invalidSortOrder_throwException() {
        inputString = "/sort trySort";

        parser = new ParseListNoteCommand(inputString);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = SystemException.ExceptionType.EXCEPTION_INVALID_SORT_TYPE.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = "/sort trySort/tag cs2113";

        parser = new ParseListNoteCommand(inputString);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = SystemException.ExceptionType.EXCEPTION_INVALID_SORT_TYPE.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = "/sort test/archive /tag";

        parser = new ParseListNoteCommand(inputString);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = SystemException.ExceptionType.EXCEPTION_INVALID_SORT_TYPE.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseCommand_invalidPrefix_throwException() {
        inputString = "/t";

        parser = new ParseListNoteCommand(inputString);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = SystemException.ExceptionType.EXCEPTION_INVALID_PREFIX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}