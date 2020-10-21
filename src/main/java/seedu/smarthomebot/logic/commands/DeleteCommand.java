package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.model.framework.Appliance;
import seedu.smarthomebot.commons.exceptions.ApplianceNotFoundException;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete the existing appliance by its indicated NAME that has been added to SmartHomeBot\n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD
            + " Fan 1";
    private final String userEnteredName;

    public DeleteCommand(String name) {
        this.userEnteredName = name;
    }

    @Override
    public CommandResult execute() {
        try {
            Appliance toDeleteAppliance = applianceList.removeAppliance(this.userEnteredName);
            return new CommandResult("Deleting " + toDeleteAppliance + ".......DELETED.");
        } catch (ApplianceNotFoundException e) {
            return new CommandResult(this.userEnteredName + " does not exist.");
        }
    }

}
