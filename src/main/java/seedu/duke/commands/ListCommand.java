package seedu.duke.commands;

import seedu.duke.data.framework.Appliance;

import static seedu.duke.common.Messages.LINE;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
                                             + ": Display all the appliances that have been added to SmartHomeBot \n"
                                             + "Example: " + COMMAND_WORD;
    private static final String APPLIANCE_TYPE = "appliance";
    private static final String LOCATION_TYPE = "location";
    private final String parameter;

    public ListCommand(String arguments) {
        parameter = arguments;
    }

    @Override
    public void execute() {
        int index = 1;
        if (parameter.equals(LOCATION_TYPE)) {
            if (homeLocationsList.getLocations().size() == 0) {
                ui.showToUser(LINE + "There is currently no locations in the list");
                return;
            }
            System.out.println(LINE + "Here are the location you have entered.");
            for (String location : homeLocationsList.getLocations()) {
                ui.showToUser(index + ": " + location);
                index++;
            }
        } else if (parameter.equals(APPLIANCE_TYPE)) {
            if (appliances.getAllAppliance().size() == 0) {
                ui.showToUser(LINE + "There is currently no appliances in the list");
                return;
            }
            System.out.println(LINE + "Here are the appliances in your list.");
            for (Appliance a : appliances.getAllAppliance()) {
                ui.showToUser(index + ": " + a.getName()
                        + " | Location: " + a.getLocation()
                        + " | Status: " + a.getStatus()
                        + " | Watt: " + a.getPower()
                        + " | Type: " + a.getType());
                index++;
            }
        }
    }

}
