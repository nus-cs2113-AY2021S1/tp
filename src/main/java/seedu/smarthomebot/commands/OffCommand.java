package seedu.smarthomebot.commands;

import seedu.smarthomebot.exceptions.EmptyParameterException;
import seedu.smarthomebot.data.framework.Appliance;

import static seedu.smarthomebot.common.Messages.LINE;
import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_PREVIOUSLY_OFF;

public class OffCommand extends Command {

    public static final String COMMAND_WORD = "off";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Turns off specified appliance by its indicated NAME \n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD + " Fan 1";
    private final String name;

    public OffCommand(String name) throws EmptyParameterException {
        if (name.isEmpty()) {
            throw new EmptyParameterException();
        }
        this.name = name;
    }

    @Override
    public CommandResult execute() {
        for (Appliance appliance : applianceList.getAllAppliance()) {
            if (appliance.getName().equals((this.name))) {
                if (appliance.switchOff()) {
                    assert !appliance.switchOff() : "Appliance should be already OFF";
                    String location = appliance.getLocation();
                    String result = String.format("Switching off %s in %s ......OFF!", name, location);
                    return new CommandResult(LINE + result);
                } else {
                    return new CommandResult(LINE + MESSAGE_APPLIANCE_PREVIOUSLY_OFF);
                }
            }
        }
        return new CommandResult(name + " does not exist.");
    }
}
