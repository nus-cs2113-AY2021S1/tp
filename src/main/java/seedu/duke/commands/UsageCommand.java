package seedu.duke.commands;

import seedu.duke.data.framework.Appliance;

import static seedu.duke.common.Messages.LINE;
import static seedu.duke.common.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.duke.common.Messages.MESSAGE_POWER_USAGE;
import static seedu.duke.ui.TextUi.showToUser;

/**
 * Usage command of the application to show power usage.
 */

public class UsageCommand extends Command {

    public static final String COMMAND_WORD = "usage";

    @Override
    public void execute() {
        double totalUsage = 0;
        int index = 1;

        if (appliances.getAllAppliance().size() == 0) {
            showToUser(LINE + MESSAGE_LIST_NO_APPLIANCES);
        } else {
            showToUser(LINE + MESSAGE_POWER_USAGE);
            for (Appliance a : appliances.getAllAppliance()) {
                double appliancePower = a.measureConsumption();
                totalUsage += appliancePower;
                ui.showWithUsageFormat(index, a.getName(), a.getLocation(), a.getStatus(), a.measureConsumption());
                index++;
            }
            String formattedUsage = String.format("%.2f kWh", totalUsage);
            showToUser("\nTotal power consumption: " + formattedUsage);
        }
    }
}
