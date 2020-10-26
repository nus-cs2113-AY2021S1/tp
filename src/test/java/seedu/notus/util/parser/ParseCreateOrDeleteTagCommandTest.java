package seedu.notus.util.parser;

//@@author Chongjx

import org.junit.jupiter.api.Test;
import seedu.notus.command.Command;
import seedu.notus.command.CreateTagCommand;
import seedu.notus.command.DeleteTagCommand;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParseCreateOrDeleteTagCommandTest {

    private ParseCreateTagOrDeleteCommand parser;
    private SystemException exception;
    private String inputString;
    private String expectedMessage;
    private String actualMessage;

    @Test
    void parseCommand_validMessage_returnNewCommand() {
        inputString = "/tag tag1 ";

        parser = new ParseCreateTagOrDeleteCommand(inputString, true);
        Command command;
        try {
            command = parser.parse();
            assertTrue(command instanceof CreateTagCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        parser = new ParseCreateTagOrDeleteCommand(inputString, false);
        try {
            command = parser.parse();
            assertTrue(command instanceof DeleteTagCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        inputString = "/tag tag1 /tag tag2";

        parser = new ParseCreateTagOrDeleteCommand(inputString, true);
        try {
            command = parser.parse();
            assertTrue(command instanceof CreateTagCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }

        parser = new ParseCreateTagOrDeleteCommand(inputString, false);
        try {
            command = parser.parse();
            assertTrue(command instanceof DeleteTagCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void parseCommand_missingContent_throwException() {
        parser = new ParseCreateTagOrDeleteCommand(null, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        String expectedMessage = ExceptionType.EXCEPTION_MISSING_MESSAGE_AFTER_COMMAND.toString();
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseCreateTagOrDeleteCommand(null, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_MESSAGE_AFTER_COMMAND.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseCommand_missingTagPrefix_throwException() {
        inputString = "";

        parser = new ParseCreateTagOrDeleteCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TAG_PREFIX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseCreateTagOrDeleteCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TAG_PREFIX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = " ";

        parser = new ParseCreateTagOrDeleteCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TAG_PREFIX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseCreateTagOrDeleteCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TAG_PREFIX.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseCommand_missingTagDescription_throwException() {
        inputString = "/tag ";

        parser = new ParseCreateTagOrDeleteCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TAG.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseCreateTagOrDeleteCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TAG.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inputString = "/tag tag1/tag";

        parser = new ParseCreateTagOrDeleteCommand(inputString, true);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TAG.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        parser = new ParseCreateTagOrDeleteCommand(inputString, false);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = ExceptionType.EXCEPTION_MISSING_TAG.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}