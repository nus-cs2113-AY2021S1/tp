package seedu.duke.command;

import org.junit.jupiter.api.Test;

import seedu.duke.data.notebook.Tag.TagColor;
import seedu.duke.ui.Formatter;


import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {

    private static final String COLOR_WHITE = TagColor.COLOR_WHITE.getColor();
    private static final String COLOR_CYAN = TagColor.COLOR_CYAN.getColor();
    private static final String COLOR_RESET = TagColor.COLOR_RESET.getColor();

    private static final String HELP_STRING = Formatter.LS
            + "The recognized commands and their usages are listed below. "
            + "Parameters listed in brackets, [ ] represent optional inputs.";

    private static final String EXPECTED = HELP_STRING + Formatter.LS + COLOR_WHITE
            + AddNoteCommand.COMMAND_USAGE + Formatter.LS + COLOR_CYAN
            + AddEventCommand.COMMAND_USAGE + Formatter.LS + COLOR_WHITE
            + CreateTagCommand.COMMAND_USAGE + Formatter.LS + COLOR_CYAN
            + DeleteNoteCommand.COMMAND_USAGE + Formatter.LS + COLOR_WHITE
            + DeleteEventCommand.COMMAND_USAGE + Formatter.LS + COLOR_CYAN
            + DeleteTagCommand.COMMAND_USAGE + Formatter.LS + COLOR_WHITE
            + EditNoteCommand.COMMAND_USAGE + Formatter.LS + COLOR_CYAN
            + EditEventCommand.COMMAND_USAGE + Formatter.LS + COLOR_WHITE
            + ExitCommand.COMMAND_USAGE + Formatter.LS + COLOR_CYAN
            + FindCommand.COMMAND_USAGE + Formatter.LS + COLOR_WHITE
            + ListEventCommand.COMMAND_USAGE + Formatter.LS + COLOR_CYAN
            + ListNoteCommand.COMMAND_USAGE + Formatter.LS + COLOR_WHITE
            + ListTagCommand.COMMAND_USAGE + Formatter.LS + COLOR_CYAN
            + PinCommand.COMMAND_USAGE + Formatter.LS + COLOR_WHITE
            + RemindCommand.COMMAND_USAGE + Formatter.LS + COLOR_CYAN
            + TagCommand.COMMAND_USAGE + Formatter.LS + COLOR_WHITE
            + ViewNoteCommand.COMMAND_USAGE + COLOR_RESET;

    @Test
    void execute_noInput_noErrorThrown() {
        String helpCommand = new HelpCommand().execute();

        assertEquals(EXPECTED, helpCommand);
    }
}
