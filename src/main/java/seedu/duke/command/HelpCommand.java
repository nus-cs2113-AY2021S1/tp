package seedu.duke.command;

import seedu.duke.ui.InterfaceManager;

/**
 * Lists all the commands and usage.
 */
public class HelpCommand extends Command {

    public static final String COLOR_CYAN_STRING = "\u001B[36m";
    public static final String COLOR_WHITE_STRING = "\u001B[30m";
    public static final String COLOR_RESET_STRING = "\u001B[0m";

    public static final String COMMAND_WORD = "help";

    private static final String HELP_STRING = InterfaceManager.LS
            + "The recognized commands and their usages are listed below. "
            + "Parameters listed in brackets, [ ] represent optional inputs." + InterfaceManager.LS;

    @Override
    public String execute() {
        String helpList = HELP_STRING + InterfaceManager.LS + COLOR_WHITE_STRING
                + AddCommand.getCommandUsageNote() + InterfaceManager.LS + COLOR_CYAN_STRING
                + AddCommand.getCommandUsageEvent() + InterfaceManager.LS + COLOR_WHITE_STRING
                + CreateTagCommand.getCommandUsage() + InterfaceManager.LS + COLOR_CYAN_STRING
                + DeleteCommand.getCommandUsageNote() + InterfaceManager.LS + COLOR_WHITE_STRING
                + DeleteCommand.getCommandUsageEvent() + InterfaceManager.LS + COLOR_CYAN_STRING
                + DeleteTagCommand.getCommandUsage() + InterfaceManager.LS + COLOR_WHITE_STRING
                + EditCommand.getCommandUsageNote() + InterfaceManager.LS + COLOR_CYAN_STRING
                + EditCommand.getCommandUsageEvent() + InterfaceManager.LS + COLOR_WHITE_STRING
                + ExitCommand.getCommandUsage() + InterfaceManager.LS + COLOR_CYAN_STRING
                + FindCommand.getCommandUsage() + InterfaceManager.LS + COLOR_WHITE_STRING
                + ListEventCommand.getCommandUsage() + InterfaceManager.LS + COLOR_CYAN_STRING
                + ListNoteCommand.getCommandUsage() + InterfaceManager.LS + COLOR_WHITE_STRING
                + ListTagCommand.getCommandUsage() + InterfaceManager.LS + COLOR_CYAN_STRING
                + PinCommand.getCommandUsage() + InterfaceManager.LS + COLOR_WHITE_STRING
                + RemindCommand.getCommandUsage() + InterfaceManager.LS + COLOR_CYAN_STRING
                + TagCommand.getCommandUsage() + InterfaceManager.LS + COLOR_WHITE_STRING
                + ViewNoteCommand.getCommandUsage() + InterfaceManager.LS + COLOR_RESET_STRING;

        return helpList;
    }
}
