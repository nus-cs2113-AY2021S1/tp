package seedu.notus.command;

//@@author R-Ramana

import seedu.notus.ui.Formatter;

import org.junit.jupiter.api.Test;

import static seedu.notus.command.HelpCommand.getColor;
import static com.diogonunes.jcolor.Ansi.colorize;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {

    private static final String HELP_STRING = "The recognized commands and their usages are listed below. "
            + "Parameters listed in brackets, [ ], represent optional inputs.";

    public static final String[] COMMANDS_USAGE = {HELP_STRING,
            colorize(AddNoteCommand.COMMAND_USAGE, getColor(false)),
            colorize(AddEventCommand.COMMAND_USAGE, getColor(true)),
            colorize(CreateTagCommand.COMMAND_USAGE, getColor(false)),
            colorize(DeleteNoteCommand.COMMAND_USAGE, getColor(true)),
            colorize(DeleteEventCommand.COMMAND_USAGE, getColor(false)),
            colorize(DeleteTagCommand.COMMAND_USAGE, getColor(true)),
            colorize(EditNoteCommand.COMMAND_USAGE, getColor(false)),
            colorize(EditEventCommand.COMMAND_USAGE, getColor(true)),
            colorize(ArchiveNoteCommand.COMMAND_USAGE, getColor(false)),
            colorize(UnarchiveNoteCommand.COMMAND_USAGE, getColor(true)),
            colorize(ListEventCommand.COMMAND_USAGE, getColor(false)),
            colorize(ListNoteCommand.COMMAND_USAGE, getColor(true)),
            colorize(ListTagCommand.COMMAND_USAGE, getColor(false)),
            colorize(FindCommand.COMMAND_USAGE, getColor(true)),
            colorize(PinCommand.COMMAND_USAGE, getColor(false)),
            colorize(RemindCommand.COMMAND_USAGE, getColor(true)),
            colorize(TagNoteCommand.COMMAND_USAGE, getColor(false)),
            colorize(TagEventCommand.COMMAND_USAGE, getColor(true)),
            colorize(ViewNoteCommand.COMMAND_USAGE, getColor(false)),
            colorize(ExitCommand.COMMAND_USAGE, getColor(true))
    };

    @Test
    void executeTest() {
        String helpCommand = new HelpCommand().execute();

        assertEquals(Formatter.formatString(COMMANDS_USAGE, true), helpCommand);
    }
}
