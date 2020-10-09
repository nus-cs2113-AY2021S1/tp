package seedu.duke.commands;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD;

    public static boolean isExit(Command command) {
        // instanceof returns false if it is null
        return command instanceof ExitCommand;
    }

    public void execute() {

    }
}
