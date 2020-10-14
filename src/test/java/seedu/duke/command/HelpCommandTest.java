package seedu.duke.command;

import org.junit.jupiter.api.Test;

import seedu.duke.ui.InterfaceManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {

    private static final String COLOR_WHITE = "\u001B[30m";
    private static final String COLOR_CYAN = "\u001B[36m";
    private static final String COLOR_RESET = "\u001B[0m";

    private static final String HELP_STRING = InterfaceManager.LS
            + "The recognized commands and their usages are listed below. "
            + "Parameters listed in brackets, [ ] represent optional inputs." + InterfaceManager.LS;

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
