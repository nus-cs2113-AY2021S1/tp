package seedu.SmartHomeBot.commands;

import seedu.SmartHomeBot.exceptions.EmptyParameterException;
import seedu.SmartHomeBot.data.framework.Appliance;

import static seedu.SmartHomeBot.common.Messages.LINE;
import static seedu.SmartHomeBot.common.Messages.MESSAGE_APPLIANCE_PREVIOUSLY_OFF;

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
    public void execute() {
        for (Appliance i : appliances.getAllAppliance()) {
            if (i.getName().equals((this.name))) {
                if (i.switchOff()) {
                    String location = i.getLocation();
                    String result = String.format("Switching off %s in %s ......OFF!\n", name, location);
                    ui.showToUser(LINE + result);
                } else {
                    ui.showToUser(LINE + MESSAGE_APPLIANCE_PREVIOUSLY_OFF);
                }
                return;
            }
        }
        ui.showToUser(name + " does not exist.");
    }

}
