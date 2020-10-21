package seedu.duke.command;

import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Ansi.colorize;

import seedu.duke.ui.Formatter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {

    private static final String HELP_STRING = "The recognized commands and their usages are listed below. "
            + "Parameters listed in brackets, [ ] represent optional inputs.";

    public static final String[] COMMANDS_USAGE = {
        HELP_STRING,
        colorize(AddNoteCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()),
        colorize(AddEventCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()),
        colorize(CreateTagCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()),
        colorize(DeleteNoteCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()),
        colorize(DeleteEventCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()),
        colorize(DeleteTagCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()),
        colorize(EditNoteCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()),
        colorize(EditEventCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()),
        colorize(ExitCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()),
        colorize(FindCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()),
        colorize(ListEventCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()),
        colorize(ListNoteCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()),
        colorize(ListTagCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()),
        colorize(PinCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()),
        colorize(RemindCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()),
        colorize(TagCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()),
        colorize(ViewNoteCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT())
    };

    @Test
    void executeTest() {
        String helpCommand = new HelpCommand().execute();

        assertEquals(Formatter.formatString(COMMANDS_USAGE, true), helpCommand);
    }
}
