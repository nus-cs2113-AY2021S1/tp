package seedu.duke.command;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Exits the program.";

    private static final String COMMAND_SUCCESSFUL_MESSAGE = "Bye!";

    @Override
    public String execute() {
        return COMMAND_SUCCESSFUL_MESSAGE;
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
