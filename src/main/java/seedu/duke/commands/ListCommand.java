package seedu.duke.commands;

import seedu.duke.common.Messages;
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
                ui.showToUser(LINE + Messages.MESSAGE_LIST_NO_LOCATIONS);
                return;
            }
            System.out.println(LINE + Messages.MESSAGE_LIST_LOCATIONS);
            for (String location : homeLocationsList.getLocations()) {
                ui.showToUser(index + ": " + location);
                index++;
            }
        } else if (parameter.equals(APPLIANCE_TYPE)) {
            if (appliances.getAllAppliance().size() == 0) {
                ui.showToUser(LINE + Messages.MESSAGE_LIST_NO_APPLIANCES);
                return;
            }
            System.out.println(LINE + Messages.MESSAGE_LIST_APPLIANCES);
            for (Appliance a : appliances.getAllAppliance()) {
                ui.showToUser(index + ": " + a.getName()
                        + Messages.MESSAGE_DISPLAY_LOCATION + a.getLocation()
                        + Messages.MESSAGE_DISPLAY_STATUS  + a.getStatus()
                        + Messages.MESSAGE_DISPLAY_WATT  + a.getPower()
                        + Messages.MESSAGE_DISPLAY_TYPE  + a.getType());
                index++;
            }
        }
    }

}
