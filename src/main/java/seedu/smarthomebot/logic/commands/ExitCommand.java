package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.Appliance;

import java.util.logging.Level;

import static seedu.smarthomebot.commons.Messages.MESSAGE_EXIT_ACKNOWLEDGEMENT;


//@@author Ang_Cheng_Jun

/**
 * Represent the command to terminate the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_USAGE = "Exiting the application: " + COMMAND_WORD;

    /**
     * Method to check if ExitCommand class is created.
     *
     * @param command check for ExitCommand class.
     * @return true if ExitCommand class is created
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    /**
     * Executing the ExitCommand.
     */
    public CommandResult execute() {
        for (Appliance a : applianceList.getAllAppliance()) {
            a.switchOff();
        }
        commandLogger.log(Level.WARNING, MESSAGE_EXIT_ACKNOWLEDGEMENT);
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }
}
