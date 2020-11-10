package seedu.notus.util.parser;

//@@author R-Ramana

import org.junit.jupiter.api.Test;
import seedu.notus.command.Command;
import seedu.notus.command.CreateTagCommand;
import seedu.notus.command.DeleteTagCommand;
import seedu.notus.command.FindCommand;
import seedu.notus.data.exception.SystemException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParseFindCommandTest {

    private ParseFindCommand parser;
    private SystemException exception;
    private String inputString;
    private String expectedMessage;
    private String actualMessage;

    @Test
    void parseCommand_validMessage_returnNewCommand() {
        inputString = "test note title ";

        parser = new ParseFindCommand(inputString);
        Command command;
        try {
            command = parser.parse();
            assertTrue(command instanceof FindCommand);
        } catch (SystemException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void parseCommand_missingContent_throwException() {
        parser = new ParseFindCommand(inputString);
        exception = assertThrows(SystemException.class, () -> parser.parse());
        expectedMessage = SystemException.ExceptionType.EXCEPTION_MISSING_KEYWORD.toString();
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}