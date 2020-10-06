package seedu.duke.commands;

import seedu.duke.data.framework.Appliance;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Display all the appliances that have been added to SmartHomeBot \n"
            + "Example: " + COMMAND_WORD;

    @Override
    public void execute() {
        int index = 1;
        for (Appliance a : appliances.getAllAppliance()) {
            System.out.print(index + ": " + a + "|" + a.getLocation() + "\n");
            index++;
        }
        System.out.println(homeLocationsList);
    }

}
