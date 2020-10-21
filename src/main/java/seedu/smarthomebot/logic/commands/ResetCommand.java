package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.model.framework.Appliance;

import static seedu.smarthomebot.commons.Messages.LINE;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.smarthomebot.commons.Messages.MESSAGE_USAGE_RESET;

public class ResetCommand extends Command {

    public static final String COMMAND_WORD = "p_reset";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Reset all the appliance power usage and total power consumption to zero\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        if (applianceList.getAllAppliance().size() == 0) {
            return new CommandResult(LINE + MESSAGE_LIST_NO_APPLIANCES);
        } else {
            for (Appliance a : applianceList.getAllAppliance()) {
                a.resetPower();
            }
            return new CommandResult(LINE + MESSAGE_USAGE_RESET);
        }
    }

}