package seedu.duke.command;

import org.junit.jupiter.api.Test;

import seedu.duke.ui.InterfaceManager;
import seedu.duke.data.notebook.Tag.TagColor;


import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {

    private static final String COLOR_WHITE = TagColor.COLOR_WHITE.getColor();
    private static final String COLOR_CYAN = TagColor.COLOR_CYAN.getColor();
    private static final String COLOR_RESET = TagColor.COLOR_RESET.getColor();

    private static final String HELP_STRING = InterfaceManager.LS
            + "The recognized commands and their usages are listed below. "
            + "Parameters listed in brackets, [ ] represent optional inputs.";

    private static final String EXPECTED = HELP_STRING + InterfaceManager.LS + COLOR_WHITE
            + AddNoteCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN
            + AddEventCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE
            + CreateTagCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN
            + DeleteNoteCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE
            + DeleteEventCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN
            + DeleteTagCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE
            + EditNoteCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN
            + EditEventCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE
            + ExitCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN
            + FindCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE
            + ListEventCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN
            + ListNoteCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE
            + ListTagCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN
            + PinCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE
            + RemindCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN
            + TagCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE
            + ViewNoteCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_RESET;

    @Test
    void execute_noInput_noErrorThrown() {
        String helpCommand = new HelpCommand().execute();

        assertEquals(EXPECTED, helpCommand);
    }
}
