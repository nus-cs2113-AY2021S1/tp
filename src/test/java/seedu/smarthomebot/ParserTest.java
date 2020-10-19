package seedu.smarthomebot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.smarthomebot.commands.AddCommand;
import seedu.smarthomebot.commands.Command;
import seedu.smarthomebot.commands.CreateCommand;
import seedu.smarthomebot.commands.DeleteCommand;
import seedu.smarthomebot.commands.ExitCommand;
import seedu.smarthomebot.commands.HelpCommand;
import seedu.smarthomebot.commands.InvalidCommand;
import seedu.smarthomebot.commands.ListCommand;
import seedu.smarthomebot.commands.OffCommand;
import seedu.smarthomebot.commands.OnCommand;
import seedu.smarthomebot.commands.RemoveCommand;
import seedu.smarthomebot.commands.UsageCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    void parse_helpCommand_parsedCorrectly() {
        final String input = "help";
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(HelpCommand.class));
    }

    @Test
    void parse_createCommand_parserCorrectly() {
        final String input = "create Bedroom1";
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(CreateCommand.class));
    }

    @Test
    void parse_addCommand_parserCorrectly() {
        final String input = "add Lightbulb l/Kitchen w/ 100 t/Light";
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(AddCommand.class));
    }

    @Test
    void parse_exitCommand_parserCorrectly() {
        final String input = "exit";
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(ExitCommand.class));
    }

    @Test
    void parse_invalidCommand_parserCorrectly() {
        final String input = "SmartHomeBot";
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(InvalidCommand.class));
    }

    @Test
    void parse_deleteCommand_parserCorrectly() {
        final String input = "delete Lightbulb";
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(DeleteCommand.class));
    }

    @Test
    void parse_removeCommand_parserCorrectly() {
        final String input = "remove Bedroom1";
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(RemoveCommand.class));
    }

    @Test
    void parse_usageCommand_parserCorrectly() {
        final String input = "usage";
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(UsageCommand.class));
    }

    @Test
    void parse_onCommand_parserCorrectly() {
        final String input = "on n/Lightbulb";
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(OnCommand.class));
    }

    @Test
    void parse_offCommand_parserCorrectly() {
        final String input = "off n/Lightbulb";
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(OffCommand.class));
    }

    @Test
    void parse_listCommand_parserCorrectly() {
        final String input = "list appliance";
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(ListCommand.class));
    }

}
