package seedu.duke.commands;

import seedu.duke.EmptyParameterException;
import seedu.duke.data.framework.Appliance;

import static seedu.duke.common.Messages.LINE;

public class OffCommand extends Command {

    public static final String COMMAND_WORD = "off";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Turns off specified appliance by its indicated name \n"
            + "Parameters: n/NAME\n"
            + "Example: " + COMMAND_WORD
            + " n/Fan 1";
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
            String location = i.getLocation();
            if (i.getName().equals((this.name))) {
                System.out.printf(LINE + "Switching off %s in %s ......OFF!\n", name, location);
                i.switchOff();
                return;
            }
        }
        System.out.println(name + " does not exist.");
    }

}
