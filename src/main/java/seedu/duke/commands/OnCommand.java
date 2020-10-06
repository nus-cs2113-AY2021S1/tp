package seedu.duke.commands;

import seedu.duke.data.framework.Appliance;

public class OnCommand extends Command {

    public static final String COMMAND_WORD = "on";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Turns on the specified appliance, user might have to input  additional "
            + "parameter in accordance to the type of appliance\n"
            + "Parameters: n/NAME t/TYPE_OF_APPLIANCE [p/ANY_PARAMETER_REQUIRED]\n"
            + "Example: " + COMMAND_WORD
            + " n/Aircon 1 t/Air Conditioner p/25";
    private final String name;

    public OnCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        for (Appliance i : appliances.getAllAppliance()) {
            if (i.getName().equals((this.name))) {
                i.switchOn();
                System.out.println(this.name + ": ON");
            }
        }
    }

}