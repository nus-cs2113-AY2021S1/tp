package seedu.smarthomebot.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.smarthomebot.logic.commands.AddCommand;
import seedu.smarthomebot.logic.commands.Command;
import seedu.smarthomebot.logic.commands.CreateCommand;
import seedu.smarthomebot.logic.commands.DeleteCommand;
import seedu.smarthomebot.logic.commands.ExitCommand;
import seedu.smarthomebot.logic.commands.HelpCommand;
import seedu.smarthomebot.logic.commands.InvalidCommand;
import seedu.smarthomebot.logic.commands.ListCommand;
import seedu.smarthomebot.logic.commands.OffCommand;
import seedu.smarthomebot.logic.commands.OnCommand;
import seedu.smarthomebot.logic.commands.RemoveCommand;
import seedu.smarthomebot.logic.commands.UsageCommand;
import seedu.smarthomebot.logic.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smarthomebot.commons.Messages.MESSAGE_EMPTY_PARAMETER;
import static seedu.smarthomebot.commons.Messages.MESSAGE_INVALID_ADD_COMMAND;
import static seedu.smarthomebot.commons.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smarthomebot.commons.Messages.MESSAGE_INVALID_LIST_COMMAND;

class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }


    @Test
    public void parse_emptyInput_returnsInvalid() {
        final String[] emptyInputs = {"", " ", "\n  \n"};
        final String resultMessage = (MESSAGE_INVALID_COMMAND_FORMAT);
        parseAndAssertIncorrectWithMessage(resultMessage, emptyInputs);
    }

    @Test
    public void parse_helpCommand_parsedCorrectly() {
        final String input = "help";
        parseAndAssertCommand(input, HelpCommand.class);
    }

    @Test
    public void parse_createCommand_parserCorrectly() {
        final String input = "create Bedroom1";
        parseAndAssertCommand(input, CreateCommand.class);
    }

    @Test
    public void parse_addCommand_parserCorrectly() {
        final String input = "add Lightbulb l/Kitchen w/ 100 t/Light";
        parseAndAssertCommand(input, AddCommand.class);
    }

    @Test
    public void parse_exitCommand_parserCorrectly() {
        final String input = "exit";
        parseAndAssertCommand(input, ExitCommand.class);
    }

    @Test
    public void parse_invalidCommand_parserCorrectly() {
        final String input = "SmartHomeBot";
        parseAndAssertCommand(input, InvalidCommand.class);
    }

    @Test
    public void parse_deleteCommand_parserCorrectly() {
        final String input = "delete Lightbulb";
        parseAndAssertCommand(input, DeleteCommand.class);
    }

    @Test
    public void parse_removeCommand_parserCorrectly() {
        final String input = "remove Bedroom1";
        parseAndAssertCommand(input, RemoveCommand.class);
    }

    @Test
    public void parse_usageCommand_parserCorrectly() {
        final String input = "usage";
        parseAndAssertCommand(input, UsageCommand.class);
    }

    @Test
    public void parse_onCommand_parserCorrectly() {
        final String input = "on Lightbulb";
        parseAndAssertCommand(input, OnCommand.class);
    }

    @Test
    public void parse_offCommand_parserCorrectly() {
        final String input = "off Lightbulb";
        parseAndAssertCommand(input, OffCommand.class);
    }

    @Test
    public void parse_listCommand_parserCorrectly() {
        final String input = "list appliance";
        parseAndAssertCommand(input, ListCommand.class);
    }

    @Test
    public void parse_CommandsWithNoParameter_errorMessage() {
        final String[] inputs = {
            "create", "create ", "delete", "delete ",
            "on", "on ", "off", "off ", "remove", "remove "
        };
        final String resultMessage = MESSAGE_EMPTY_PARAMETER;
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_listCommandNoParameter_errorMessage() {
        final String[] inputs = {"list", "list ", "list appliance b/"};
        final String resultMessage = MESSAGE_INVALID_LIST_COMMAND;
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_addCommandInvalidParameter_errorMessage() {
        final String[] inputs = {
            "add Light t/100 l/BR1 w/fan", "add  ", "add appliance b/",
            "add Light l/BR1 w/fan", "add Light", "add Light 1234"
        };
        final String resultMessage = MESSAGE_INVALID_ADD_COMMAND;
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_addCommandEmptyParameter_errorMessage() {
        final String[] inputs = {
            "add Light l/ w/100 t/fan", "add Light l/BR1 w/ t/fan",
            "add Light l/BR1 w/100 t/"
        };
        final String resultMessage = MESSAGE_EMPTY_PARAMETER;
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }


    /**
     * Asserts that parsing the given inputs will return InvalidCommand with the given feedback.
     */
    private void parseAndAssertIncorrectWithMessage(String feedback, String... inputs) {
        for (String input : inputs) {
            final InvalidCommand result = parseAndAssertCommand(input, InvalidCommand.class);
            assertEquals(result.feedbackToUser, feedback);
        }
    }

    /**
     * Parses input and assert it is the correct class command object.
     */
    private <T extends Command> T parseAndAssertCommand(String input, Class<T> expectedCommandClass) {
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        return (T) result;
    }
}
