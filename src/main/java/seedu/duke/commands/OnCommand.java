package seedu.duke.commands;

import seedu.duke.EmptyParameterException;
import seedu.duke.data.framework.Appliance;

import static seedu.duke.common.Messages.LINE;

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
                System.out.printf(LINE + "Switching on %s in %s ......ON!\n", name, location);
                i.switchOn();
                return;
            }
        }
        System.out.println(LINE + name + " does not exist.");
    }

}
