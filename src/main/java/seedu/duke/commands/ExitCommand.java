package seedu.duke.commands;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD;

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }

    public void execute() {

    }
}
