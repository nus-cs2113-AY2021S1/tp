package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.logic.commands.exceptions.EmptyApplianceListException;

import java.util.ArrayList;
import java.util.logging.Level;

import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.smarthomebot.commons.Messages.MESSAGE_POWER_USAGE;
import static seedu.smarthomebot.commons.Messages.MESSAGE_TOTAL_POWER_USAGE;

//@@author fanceso

/**
 * Represent the command to display power usage of the application to the user.
 */
public class UsageCommand extends Command {

    public static final String COMMAND_WORD = "usage";
    public static final String MESSAGE_USAGE = "Usage of Appliance: " + COMMAND_WORD;

    private static final String DISPLAY_LOCATION = " | Location: ";
    private static final String DISPLAY_STATUS = " | Status: ";
    private static final String DISPLAY_USAGE = " | Usage: ";

    /**
     * Executing the UsageCommand.
     */
    @Override
    public CommandResult execute() {
        String outputResult;
        try {
            ArrayList<Appliance> outputApplianceList = applianceList.getAllAppliance();
            assert !outputApplianceList.equals(null) : "outputApplianceList must not be null";
            if (outputApplianceList.size() == 0) {
                commandLogger.log(Level.WARNING, MESSAGE_LIST_NO_APPLIANCES);
                throw new EmptyApplianceListException();
            } else {
                outputResult = displayOutput(outputApplianceList);
                assert !outputApplianceList.isEmpty() : "outputApplianceList must not be empty";
                return new CommandResult(outputResult);
            }
        } catch (EmptyApplianceListException e) {
            return new CommandResult(MESSAGE_LIST_NO_APPLIANCES);
        }
    }

    /**
     * Method to display output result of usage command.
     *
     * @param outputApplianceList is the list that to switch off in Appliance.
     * @return the Appliance List in String with auto formatted result.
     */
    private String displayOutput(ArrayList<Appliance> outputApplianceList) {
        int index = 1;
        double totalUsage = 0;
        String outputResult = MESSAGE_POWER_USAGE;
        autoFormattingStringIndex();
        String formatInString = "%-2d. %-" + maxNameLength + "s"
                + DISPLAY_LOCATION + "%-" + maxLocationLength + "s"
                + DISPLAY_STATUS + "%-3s"
                + DISPLAY_USAGE + "%.2f kWh";
        for (Appliance a : outputApplianceList) {
            outputResult = outputResult.concat(System.lineSeparator() + String.format(formatInString,
                    index, a.getName(), a.getLocation(), a.getStatus(), a.getPowerInDouble()));
            totalUsage += a.getPowerInDouble();
            index++;
        }
        outputResult = outputResult.concat(MESSAGE_TOTAL_POWER_USAGE + String.format("%.2f kWh", totalUsage));
        assert !outputResult.isEmpty() : "outputResult must contains String";
        commandLogger.log(Level.INFO, "Power usage computed with output message.\n" + outputResult);
        return outputResult;
    }

}
