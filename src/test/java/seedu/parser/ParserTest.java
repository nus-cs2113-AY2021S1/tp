package seedu.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.command.AddCommand;
import seedu.command.ByeCommand;
import seedu.command.ClearCommand;
import seedu.command.Command;
import seedu.command.CompareCommand;
import seedu.command.DeleteCommand;
import seedu.command.EditCommand;
import seedu.command.FindCommand;
import seedu.command.HelpCommand;
import seedu.command.ListCommand;
import seedu.command.LogInCommand;
import seedu.command.RemoveUserCommand;
import seedu.exception.WhereGotTimeException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void parse_unknownCommand_throwDukeException() {
        final String input = "hello, testing";
        assertThrows(WhereGotTimeException.class, () -> Parser.parse(input));
    }

    @Test
    public void parse_addCommand_parsedCorrectly() throws WhereGotTimeException {
        final String input = "add /CS2113 Lecture /fri /1600-1800 /LT15";
        parseAndAssertCommandType(input, AddCommand.class);
    }

    @Test
    public void parse_byeCommand_parsedCorrectly() throws WhereGotTimeException {
        final String input = "bye";
        parseAndAssertCommandType(input, ByeCommand.class);
    }

    @Test
    public void parse_clearCommand_parsedCorrectly() throws WhereGotTimeException {
        final String input = "clear /sun";
        parseAndAssertCommandType(input, ClearCommand.class);
    }

    @Test
    public void parse_compareCommand_parsedCorrectly() throws WhereGotTimeException {
        final String input = "compare /Alex /mon";
        parseAndAssertCommandType(input, CompareCommand.class);
    }

    @Test
    public void parse_deleteCommand_parsedCorrectly() throws WhereGotTimeException {
        final String input = "delete /tue /2";
        parseAndAssertCommandType(input, DeleteCommand.class);
    }

    @Test
    public void parse_editCommand_parsedCorrectly() throws WhereGotTimeException {
        final String input = "edit /mon /2 /1000-1200";
        parseAndAssertCommandType(input, EditCommand.class);
    }

    @Test
    public void parse_findCommand_parsedCorrectly() throws WhereGotTimeException {
        final String input = "find /CS2113";
        parseAndAssertCommandType(input, FindCommand.class);
    }

    @Test
    public void parse_helpCommand_parsedCorrectly() throws WhereGotTimeException {
        final String input = "help";
        parseAndAssertCommandType(input, HelpCommand.class);
    }

    @Test
    public void parse_listCommand_parsedCorrectly() throws WhereGotTimeException {
        final String input = "list /all";
        parseAndAssertCommandType(input, ListCommand.class);
    }

    @Test
    public void parse_loginCommand_parsedCorrectly() throws WhereGotTimeException {
        final String input = "login /John /132456";
        parseAndAssertCommandType(input, LogInCommand.class);
    }

    @Test
    public void parse_removeCommand_parsedCorrectly() throws WhereGotTimeException {
        final String input = "remove /John /132456";
        parseAndAssertCommandType(input, RemoveUserCommand.class);
    }
    
    @Test
    public void parse_loginCommand_testExpectedException_noDescription() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("login");
        });
    }

    @Test
    public void parse_loginCommand_testExpectedException_noDescriptionSlash() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("login man /123123");
        });
    }

    @Test
    public void parse_loginCommand_testExpectedException_emptyUsername() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("login /     /123123");
        });
    }

    @Test
    public void parse_loginCommand_testExpectedException_emptyPassword() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("login /man /");
        });
    }

    @Test
    public void parse_loginCommand_testExpectedException_wrongLengthPassword() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("login /man /123");
        });
    }

    @Test
    public void parse_loginCommand_testExpectedException_invalidUsername() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("login /ke$ha /123123");
        });
    }

    @Test
    public void parse_loginCommand_testExpectedException_not6NumbersPassword() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("login /man /123abc");
        });
    }

    @Test
    public void parse_clearCommand_noDescription() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("clear");
        });
    }

    @Test
    public void parse_clearCommand_noSlash() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("clear tue");
        });
    }

    @Test
    public void parse_clearCommand_extraDescription() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("clear d/mon");
        });
    }

    @Test
    public void parse_clearCommand_missingDay() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("clear /");
        });
    }

    @Test
    public void parse_listCommand_noDescription() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("list");
        });
    }

    @Test
    public void parse_listCommand_noSlash() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("list mon");
        });
    }

    @Test
    public void parse_listCommand_extraDescription() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("list all /mon");
        });
    }

    @Test
    public void parse_listCommand_missingDay() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("list /");
        });
    }

    @Test
    public void parse_deleteCommand_noDescription() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("delete");
        });
    }

    @Test
    public void parse_deleteCommand_noSlash() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("delete mon");
        });
    }

    @Test
    public void parse_deleteCommand_extraDescription() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("delete xy /mon /1");
        });
    }

    @Test
    public void parse_deleteCommand_missingDay() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("delete / /1");
        });
    }

    @Test
    public void parse_deleteCommand_missingIndex() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("delete /mon /");
        });
    }

    @Test
    public void parse_deleteCommand_missingSlash() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("delete /mon");
        });
    }

    @Test
    public void parse_findCommand_noDescription() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("find");
        });
    }

    @Test
    public void parse_findCommand_noSlash() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("find Lec");
        });
    }

    @Test
    public void parse_findCommand_extraDescription() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("find lec /CS2113");
        });
    }

    @Test
    public void parse_findCommand_missingKeyword() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("find /");
        });
    }

    @Test
    public void parse_removeCommand_testExpectedException_noDescription() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("remove");
        });
    }

    @Test
    public void parse_removeCommand_testExpectedException_noDescriptionSlash() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("remove man /123123");
        });
    }

    @Test
    public void parse_removeCommand_testExpectedException_emptyUsername() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("remove /     /123123");
        });
    }

    @Test
    public void parse_removeCommand_testExpectedException_emptyPassword() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("remove /man /");
        });
    }

    @Test
    public void parse_removeCommand_testExpectedException_wrongLengthPassword() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("remove /man /123");
        });
    }

    @Test
    public void parse_removeCommand_testExpectedException_invalidUsername() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("remove /ke$ha /123123");
        });
    }

    @Test
    public void parse_removeCommand_testExpectedException_not6NumbersPassword() {
        Assertions.assertThrows(WhereGotTimeException.class, () -> {
            Parser.parse("remove /man /123abc");
        });
    }
    
    /**
     * Parses input and asserts the class/type of the returned command object.
     *
     * @param input to be parsed
     * @param expectedCommandClass expected class of returned command
     */
    private <T extends Command> void parseAndAssertCommandType(String input, Class<T> expectedCommandClass)
            throws WhereGotTimeException {
        final Command result = Parser.parse(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
    }
}