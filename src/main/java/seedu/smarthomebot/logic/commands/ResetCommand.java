package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.logic.commands.exceptions.EmptyApplianceListException;

import java.util.ArrayList;

import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.smarthomebot.commons.Messages.MESSAGE_USAGE_RESET;

/**
 * Represent the command to reset the power monitored.
 */
public class ResetCommand extends Command {

    public static final String COMMAND_WORD = "p_reset";
    public static final String MESSAGE_USAGE = "Resetting previous recorded usage: " + COMMAND_WORD;

    /**
     * Executing the ResetCommand.
     */
    @Override
    public CommandResult execute() {
        try {
            ArrayList<Appliance> resetApplianceList = applianceList.getAllAppliance();
            if (resetApplianceList.size() == 0) {
                throw new EmptyApplianceListException();
            } else {
                for (Appliance a : resetApplianceList) {
                    a.resetPowerUsage();
                }
                return new CommandResult(MESSAGE_USAGE_RESET);
            }
        } catch (EmptyApplianceListException e) {
            return new CommandResult(MESSAGE_LIST_NO_APPLIANCES);
        }
    }

}
