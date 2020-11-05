package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.Appliance;

//@@author Ang_Cheng_Jun
/**
 * Represent the command to terminate the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_USAGE = "Exiting the application: " + COMMAND_WORD;
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT =
            "Exiting SmartHomeBot as requested, all Appliances are switched off.";

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    public CommandResult execute() {
        for (Appliance a : applianceList.getAllAppliance()) {
            a.switchOff();
        }
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }
}
