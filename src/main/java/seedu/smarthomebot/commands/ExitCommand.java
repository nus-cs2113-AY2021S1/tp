package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.framework.Appliance;
import seedu.smarthomebot.data.framework.Power;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_EXIT_ACKNOWEDGEMENT = "Exiting SmartHomeBot as requested ...";

    public static boolean isExit(Command command) {
        // instanceof returns false if it is null
        return command instanceof ExitCommand;
    }

    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWEDGEMENT);
    }
}
