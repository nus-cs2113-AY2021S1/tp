package seedu.duke.command;

import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Ansi.colorize;

import seedu.duke.ui.Formatter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.command.HelpCommand.getColor;

class HelpCommandTest {

    private static final String HELP_STRING = "The recognized commands and their usages are listed below. "
            + "Parameters listed in brackets, [ ], represent optional inputs.";

    public static final String[] COMMANDS_USAGE = {
        HELP_STRING,
            colorize(AddNoteCommand.COMMAND_USAGE, getColor(false)),
            colorize(AddEventCommand.COMMAND_USAGE, getColor(true)),
            colorize(ArchiveNoteCommand.COMMAND_USAGE, getColor(false)),
            colorize(CreateTagCommand.COMMAND_USAGE, getColor(true)),
            colorize(DeleteNoteCommand.COMMAND_USAGE, getColor(false)),
            colorize(DeleteEventCommand.COMMAND_USAGE, getColor(true)),
            colorize(DeleteTagCommand.COMMAND_USAGE, getColor(false)),
            colorize(EditNoteCommand.COMMAND_USAGE, getColor(true)),
            colorize(EditEventCommand.COMMAND_USAGE, getColor(false)),
            colorize(ExitCommand.COMMAND_USAGE, getColor(true)),
            colorize(FindCommand.COMMAND_USAGE, getColor(false)),
            colorize(ListEventCommand.COMMAND_USAGE, getColor(true)),
            colorize(ListNoteCommand.COMMAND_USAGE, getColor(false)),
            colorize(ListTagCommand.COMMAND_USAGE, getColor(true)),
            colorize(PinCommand.COMMAND_USAGE, getColor(false)),
            colorize(RemindCommand.COMMAND_USAGE, getColor(true)),
            colorize(TagCommand.COMMAND_USAGE, getColor(false)),
            colorize(UnarchiveNoteCommand.COMMAND_USAGE, getColor(true)),
            colorize(ViewNoteCommand.COMMAND_USAGE, getColor(false))
    };

    @Test
    void executeTest() {
        String helpCommand = new HelpCommand().execute();

        assertEquals(Formatter.formatString(COMMANDS_USAGE, true), helpCommand);
    }
}
