package seedu.duke.commands;

import seedu.duke.exceptions.EmptyParameterException;
import seedu.duke.data.framework.Appliance;

import static seedu.duke.common.Messages.LINE;
import static seedu.duke.common.Messages.MESSAGE_APPLIANCE_PREVIOUSLY_ON;

public class OnCommand extends Command {

    public static final String COMMAND_WORD = "on";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Turns on specified appliance by its indicated NAME \n"
            + "Parameters: NAME \n"
            + "Example: " + COMMAND_WORD + " Aircon 1 ";
    private final String name;

    public OnCommand(String name) throws EmptyParameterException {
        if (name.isEmpty()) {
            throw new EmptyParameterException();
        }
        this.name = name;
    }

    @Override
    public void execute() {
        for (Appliance i : appliances.getAllAppliance()) {
            String location = i.getLocation();
            if (i.getName().equals((this.name))) {
                if (i.switchOn()) {
                    String result = String.format("Switching on %s in %s ......ON!\n", name, location);
                    ui.showToUser(LINE + result);
                } else {
                    ui.showToUser(LINE + MESSAGE_APPLIANCE_PREVIOUSLY_ON);
                }
                return;
            }
        }
        ui.showToUser(LINE + name + " does not exist.");
    }

}
