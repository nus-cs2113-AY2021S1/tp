package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.commons.exceptions.ApplianceNotFoundException;

//@@author zongxian-ctrl
/**
 * Represent the command for deleting an appliance in the ApplianceList.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "Delete Appliance: " + COMMAND_WORD
            + " [APPLIANCE_NAME]";
    private final String userEnteredName;

    public DeleteCommand(String name) {
        assert name.isEmpty() != true : "DeleteCommand must not accept empty parameter";
        this.userEnteredName = name;
    }

    @Override
    public CommandResult execute() {
        try {
            Appliance toDeleteAppliance = applianceList.deleteAppliance(this.userEnteredName);
            return new CommandResult("Deleting " + toDeleteAppliance + ".......DELETED.");
        } catch (ApplianceNotFoundException e) {
            return new CommandResult(this.userEnteredName + " does not exist.");
        }
    }

}
