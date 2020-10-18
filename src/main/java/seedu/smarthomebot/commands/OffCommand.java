package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.framework.Appliance;

import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_NOT_EXIST;
import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_PREVIOUSLY_OFF;

public class OffCommand extends Command {

    public static final String COMMAND_WORD = "off";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Turns off specified appliance by its indicated NAME \n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD + " Fan 1";
    private final String name;

    public OffCommand(String name) {
        this.name = name;
    }

    private int getApplianceToOffIndex() {
        for (Appliance appliance : applianceList.getAllAppliance()) {
            if (appliance.getName().equals((this.name))) {
                return applianceList.getAllAppliance().indexOf(appliance);
            }
        }
        return -1;
    }

    @Override
    public CommandResult execute() {
        int toOffApplianceIndex = getApplianceToOffIndex();
        if (toOffApplianceIndex < 0) {
            return new CommandResult(MESSAGE_APPLIANCE_NOT_EXIST);
        } else {
            Appliance toOffAppliance = applianceList.getAppliance(toOffApplianceIndex);
            if (toOffAppliance.switchOff()) {
                assert toOffAppliance.getStatus().equals("OFF") : "Appliance should be already OFF";
                return new CommandResult(MESSAGE_APPLIANCE_PREVIOUSLY_OFF);
            } else {
                assert toOffAppliance.getStatus().equals("OFF")  : "Appliance should be already OFF";
                return new CommandResult("Switching: " + toOffAppliance + "......OFF");
            }
        }

    }
}
