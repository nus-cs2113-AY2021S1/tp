package seedu.duke.command;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    @Override
    public String execute() {
        return null;
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
