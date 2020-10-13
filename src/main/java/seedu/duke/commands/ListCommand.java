package seedu.duke.commands;

import seedu.duke.data.framework.Appliance;

import static seedu.duke.common.Messages.LINE;
import static seedu.duke.common.Messages.MESSAGE_LIST_APPLIANCES;
import static seedu.duke.common.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.duke.common.Messages.MESSAGE_LIST_NO_LOCATIONS;
import static seedu.duke.common.Messages.MESSAGE_LIST_LOCATIONS;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display all the appliances that have been added to SmartHomeBot \n" + "Example: " + COMMAND_WORD;
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
                ui.showToUser(LINE + MESSAGE_LIST_NO_LOCATIONS);
                return;
            }
            ui.showToUser(LINE + MESSAGE_LIST_LOCATIONS);
            for (String location : homeLocationsList.getLocations()) {
                ui.showToUser(index + ": " + location);
                index++;
            }
        } else if (parameter.equals(APPLIANCE_TYPE)) {
            if (appliances.getAllAppliance().size() == 0) {
                ui.showToUser(LINE + MESSAGE_LIST_NO_APPLIANCES);
                return;
            }
            ui.showToUser(LINE + MESSAGE_LIST_APPLIANCES);
            for (Appliance a : appliances.getAllAppliance()) {
                ui.showWithListFormat(index, a.getName(), a.getLocation(), a.getStatus(), a.getPower(), a.getType());
                index++;
            }
        }
    }

}
