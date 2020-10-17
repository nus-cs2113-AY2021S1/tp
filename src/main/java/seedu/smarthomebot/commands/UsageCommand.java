package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.framework.Appliance;

import static seedu.smarthomebot.common.Messages.LINE;
import static seedu.smarthomebot.common.Messages.MESSAGE_DISPLAY_LOCATION;
import static seedu.smarthomebot.common.Messages.MESSAGE_DISPLAY_STATUS;
import static seedu.smarthomebot.common.Messages.MESSAGE_DISPLAY_USAGE;
import static seedu.smarthomebot.common.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.smarthomebot.common.Messages.MESSAGE_POWER_USAGE;
import static seedu.smarthomebot.common.Messages.MESSAGE_TOTAL_POWER_USAGE;

/**
 * Usage command of the application to show power usage.
 */

public class UsageCommand extends Command {

    public static final String COMMAND_WORD = "usage";

    @Override
    public CommandResult execute() {
        int totalUsage = 0;
        int index = 1;

        if (applianceList.getAllAppliance().size() == 0) {
            return new CommandResult(LINE + MESSAGE_LIST_NO_APPLIANCES);
        } else {
            String formattedResult = (LINE + MESSAGE_POWER_USAGE);
            String format = "%-2d. %-" + Appliance.getMaxNameLength() + "s"
                    + MESSAGE_DISPLAY_LOCATION + "%-" + Appliance.getMaxLocationLength() + "s"
                    + MESSAGE_DISPLAY_STATUS + "%-3s"
                    + MESSAGE_DISPLAY_USAGE + "%d kWh";
            for (Appliance a : applianceList.getAllAppliance()) {
                formattedResult = formattedResult.concat(System.lineSeparator() + String.format(format, index,
                        a.getName(), a.getLocation(), a.getStatus(), a.measureConsumption()));
                totalUsage += a.measureConsumption();
                index++;
            }
            formattedResult = formattedResult.concat(MESSAGE_TOTAL_POWER_USAGE + String.format("%d kWh", totalUsage));
            return new CommandResult(formattedResult);
        }
    }
}
