package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.commons.exceptions.ApplianceNotFoundException;

import static seedu.smarthomebot.commons.Messages.LINE;

//@@author zongxian-ctrl

/**
 * Represent the command for deleting an appliance in the ApplianceList.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "Delete Appliance: " + COMMAND_WORD
            + " [APPLIANCE_NAME]";
    private final String userEnteredName;

    /**
     * Constructor for DeleteCommand.
     *
     * @param name Appliance name to be deleted.
     */
    public DeleteCommand(String name) {
        assert name.isEmpty() != true : "DeleteCommand must not accept empty name";
        this.userEnteredName = name;
    }

    /**
     * Executing the DeleteCommand.
     */
    @Override
    public CommandResult execute() {
        try {
            Appliance toDeleteAppliance = applianceList.deleteAppliance(userEnteredName);
            autoFormattingStringIndex();
            return new CommandResult("Deleting " + toDeleteAppliance + ".......DELETED.");
        } catch (ApplianceNotFoundException e) {
            return new CommandResult(userEnteredName + " does not exist.");
        }
    }

}
