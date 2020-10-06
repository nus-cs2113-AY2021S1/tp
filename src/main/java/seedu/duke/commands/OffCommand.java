package seedu.duke.commands;

import seedu.duke.data.framework.Appliance;

public class OffCommand extends Command {

    public static final String COMMAND_WORD = "off";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Turns off specified appliance by its indicated name \n"
            + "Parameters: n/NAME\n"
            + "Example: " + COMMAND_WORD
            + " n/Fan 1";
    private final String name;

    public OffCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        for (Appliance i : appliances.getAllAppliance()) {
            if (i.getName().equals((this.name))) {
                i.switchOff();
                System.out.println(this.name + ": OFF");
            }
        }
    }

}