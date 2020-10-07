package seedu.duke.command;

/**
 * Lists all the commands and usage.
 */
public class HelpCommand extends Command {

    public static final String COLOR_CYAN_STRING = "\u001B[36m";
    public static final String COLOR_WHITE_STRING = "\u001B[30m";
    public static final String COLOR_RESET_STRING = "\u001B[0m";

    public static final String COMMAND_WORD = "help";

    private static final String HELP_STRING = "\nThe recognized commands and their usages are listed below. "
            + "Parameters listed in brackets, [ ] represent optional inputs. \n";

    @Override
    public String execute() {
        String helpList = HELP_STRING + "\n" + COLOR_WHITE_STRING + AddCommand.getCommandUsageNote() + "\n"
                + COLOR_CYAN_STRING + AddCommand.getCommandUsageEvent() + COLOR_WHITE_STRING + "\n"
                + CreateTagCommand.getCommandUsage() + "\n" + COLOR_CYAN_STRING
                + DeleteCommand.getCommandUsageNote() + COLOR_WHITE_STRING + "\n"
                + DeleteCommand.getCommandUsageEvent() + "\n" + COLOR_CYAN_STRING
                + DeleteTagCommand.getCommandUsage() + "\n" + COLOR_WHITE_STRING
                + EditCommand.getCommandUsageNote() + "\n" + COLOR_CYAN_STRING
                + EditCommand.getCommandUsageEvent() + "\n" + COLOR_WHITE_STRING
                + ExitCommand.getCommandUsage() + "\n" + COLOR_CYAN_STRING
                + FindCommand.getCommandUsage() + "\n" + COLOR_WHITE_STRING
                + ListEventCommand.getCommandUsage() + "\n" + COLOR_CYAN_STRING
                + ListNoteCommand.getCommandUsage() + "\n" + COLOR_WHITE_STRING
                + ListTagCommand.getCommandUsage() + "\n" + COLOR_CYAN_STRING
                + PinCommand.getCommandUsage() + "\n" + COLOR_WHITE_STRING
                + RemindCommand.getCommandUsage() + "\n" + COLOR_CYAN_STRING
                + TagCommand.getCommandUsage() + "\n" + COLOR_WHITE_STRING
                + ViewNoteCommand.getCommandUsage() + "\n" + COLOR_RESET_STRING;

        return helpList;
    }
}
