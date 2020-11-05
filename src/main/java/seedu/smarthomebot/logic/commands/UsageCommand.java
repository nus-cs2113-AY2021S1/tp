package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.commons.exceptions.ApplianceNotFoundException;
import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.logic.commands.exceptions.EmptyApplianceListException;

import static seedu.smarthomebot.commons.Messages.LINE;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.smarthomebot.commons.Messages.MESSAGE_POWER_USAGE;
import static seedu.smarthomebot.commons.Messages.MESSAGE_TOTAL_POWER_USAGE;

/**
 * Represent the command to display power usage of the application to the user.
 */
public class UsageCommand extends Command {

    public static final String COMMAND_WORD = "usage";
    public static final String MESSAGE_USAGE = "Usage of Appliance: " + COMMAND_WORD;
    public static final String DISPLAY_LOCATION = " | Location: ";
    public static final String DISPLAY_STATUS = " | Status: ";
    public static final String DISPLAY_USAGE = " | Usage: ";

    @Override
    public CommandResult execute() {
        double totalUsage = 0;
        int index = 1;
        try {
            if (applianceList.getAllAppliance().size() == 0) {
                throw new EmptyApplianceListException();
            } else {
                autoFormattingStringIndex();
                String formattedResult = (LINE + MESSAGE_POWER_USAGE);
                String format = "%-2d. %-" + maxNameLength + "s"
                        + DISPLAY_LOCATION + "%-" + maxLocationLength + "s"
                        + DISPLAY_STATUS + "%-3s"
                        + DISPLAY_USAGE + "%.2f kWh";
                for (Appliance a : applianceList.getAllAppliance()) {
                    formattedResult = formattedResult.concat(System.lineSeparator() + String.format(format, index,
                            a.getName(), a.getLocation(), a.getStatus(), a.getPowerInDouble()));
                    totalUsage += a.getPowerInDouble();
                    index++;
                }
                formattedResult = formattedResult.concat(MESSAGE_TOTAL_POWER_USAGE + String.format("%.2f kWh", totalUsage));
                return new CommandResult(formattedResult);
            }
        } catch (EmptyApplianceListException e) {
            return new CommandResult(LINE + MESSAGE_LIST_NO_APPLIANCES);
        }
    }

}
