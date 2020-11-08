package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.logic.commands.exceptions.EmptyApplianceListException;

import java.util.ArrayList;
import java.util.logging.Level;

import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.smarthomebot.commons.Messages.MESSAGE_USAGE_RESET;

//@@author fanceso

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
                commandLogger.log(Level.WARNING, MESSAGE_LIST_NO_APPLIANCES);
                throw new EmptyApplianceListException();
            } else {
                assert !resetApplianceList.isEmpty() : "resetApplianceList must not be empty";
                for (Appliance a : resetApplianceList) {
                    a.resetPowerUsage();
                    assert a.getPowerInString().equals("0.00") : "Power usage needs to be reset to 0.00";
                }
                commandLogger.log(Level.INFO, MESSAGE_USAGE_RESET);
                return new CommandResult(MESSAGE_USAGE_RESET);
            }
        } catch (EmptyApplianceListException e) {
            return new CommandResult(MESSAGE_LIST_NO_APPLIANCES);
        }
    }

}