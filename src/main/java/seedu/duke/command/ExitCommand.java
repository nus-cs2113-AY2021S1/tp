package seedu.duke.command;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    private static final String COMMAND_USAGE = COMMAND_WORD + ": Exits the program.";

    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

    @Override
    public String execute() {
        return null;
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
