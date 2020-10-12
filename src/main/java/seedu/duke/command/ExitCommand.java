package seedu.duke.command;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    private static final String COMMAND_USAGE = COMMAND_WORD + ": Exits the program.";

    private static final String COMMAND_SUCCESSFUL_MESSAGE = "Bye!";

    /**
     * Gets how the command is expected to be used.
     *
     * @return String representation of how the command is to be used.
     */
    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

    @Override
    public String execute() {
        return COMMAND_SUCCESSFUL_MESSAGE;
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
