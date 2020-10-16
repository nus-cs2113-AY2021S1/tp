package seedu.duke.command;

import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Ansi.colorize;

import seedu.duke.ui.Formatter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {

    private static final String HELP_STRING = Formatter.LS
            + "The recognized commands and their usages are listed below. "
            + "Parameters listed in brackets, [ ] represent optional inputs.";

    private static final String EXPECTED = HELP_STRING + Formatter.LS.repeat(2)
            + colorize(AddNoteCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()) + Formatter.LS
            + colorize(AddEventCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()) + Formatter.LS
            + colorize(CreateTagCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()) + Formatter.LS
            + colorize(DeleteNoteCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()) + Formatter.LS
            + colorize(DeleteEventCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()) + Formatter.LS
            + colorize(DeleteTagCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()) + Formatter.LS
            + colorize(EditNoteCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()) + Formatter.LS
            + colorize(EditEventCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()) + Formatter.LS
            + colorize(ExitCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()) + Formatter.LS
            + colorize(FindCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()) + Formatter.LS
            + colorize(ListEventCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()) + Formatter.LS
            + colorize(ListNoteCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()) + Formatter.LS
            + colorize(ListTagCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()) + Formatter.LS
            + colorize(PinCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()) + Formatter.LS
            + colorize(RemindCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()) + Formatter.LS
            + colorize(TagCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()) + Formatter.LS
            + colorize(ViewNoteCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT());

    @Test
    void execute_noInput_noErrorThrown() {
        String helpCommand = new HelpCommand().execute();

        assertEquals(EXPECTED, helpCommand);
    }
}
