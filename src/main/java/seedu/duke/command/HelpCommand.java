package seedu.duke.command;

import seedu.duke.data.notebook.Tag.TagColor;
import seedu.duke.ui.InterfaceManager;

/**
 * Lists all the commands and usage.
 */
public class HelpCommand extends Command {

    private static final String COLOR_CYAN_STRING = TagColor.COLOR_CYAN.getColor();
    private static final String COLOR_WHITE_STRING = TagColor.COLOR_WHITE.getColor();
    private static final String COLOR_RESET_STRING = TagColor.COLOR_RESET.getColor();

    public static final String COMMAND_WORD = "help";

    public static final String HELP_STRING = InterfaceManager.LS
            + "The recognized commands and their usages are listed below. "
            + "Parameters listed in brackets, [ ] represent optional inputs." + InterfaceManager.LS;

    @Override
    public String execute() {
        return HELP_STRING + InterfaceManager.LS + COLOR_WHITE_STRING
                + AddNoteCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN_STRING
                + AddEventCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE_STRING
                + CreateTagCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN_STRING
                + DeleteNoteCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE_STRING
                + DeleteEventCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN_STRING
                + DeleteTagCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE_STRING
                + EditNoteCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN_STRING
                + EditEventCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE_STRING
                + ExitCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN_STRING
                + FindCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE_STRING
                + ListEventCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN_STRING
                + ListNoteCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE_STRING
                + ListTagCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN_STRING
                + PinCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE_STRING
                + RemindCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN_STRING
                + TagCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE_STRING
                + ViewNoteCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_RESET_STRING;
    }
}
