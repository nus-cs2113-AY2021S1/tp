package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.framework.Appliance;

import static seedu.smarthomebot.common.Messages.LINE;
import static seedu.smarthomebot.common.Messages.MESSAGE_DISPLAY_LOCATION;
import static seedu.smarthomebot.common.Messages.MESSAGE_DISPLAY_STATUS;
import static seedu.smarthomebot.common.Messages.MESSAGE_DISPLAY_TYPE;
import static seedu.smarthomebot.common.Messages.MESSAGE_DISPLAY_WATT;
import static seedu.smarthomebot.common.Messages.MESSAGE_LIST_APPLIANCES;
import static seedu.smarthomebot.common.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.smarthomebot.common.Messages.MESSAGE_LIST_NO_LOCATIONS;
import static seedu.smarthomebot.common.Messages.MESSAGE_LIST_LOCATIONS;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Display all the appliances that have been added to SmartHomeBot \n"
            + "Example: " + COMMAND_WORD;
    private static final String APPLIANCE_TYPE = "appliance";
    private static final String LOCATION_TYPE = "location";
    private final String parameter;

    public ListCommand(String arguments) {
        this.parameter = arguments;
    }

    @Override
    public CommandResult execute() {
        int index = 1;
        switch (parameter) {
        case LOCATION_TYPE:
            if (locationList.getLocations().size() == 0) {
                return new CommandResult(LINE + MESSAGE_LIST_NO_LOCATIONS);
            }
            String result = LINE + MESSAGE_LIST_LOCATIONS;
            for (String location : locationList.getLocations()) {
                result = result.concat(System.lineSeparator() + index + ": " + location);
                index++;
            }
            return new CommandResult(result);
        case APPLIANCE_TYPE:
            if (applianceList.getAllAppliance().size() == 0) {
                return new CommandResult(LINE + MESSAGE_LIST_NO_APPLIANCES);
            }
            String formattedResult = (LINE + MESSAGE_LIST_APPLIANCES);
            String format = "%-2d. %-" + Appliance.getMaxNameLength() + "s"
                    + MESSAGE_DISPLAY_LOCATION + "%-" + Appliance.getMaxLocationLength() + "s"
                    + MESSAGE_DISPLAY_STATUS + "%-5s"
                    + MESSAGE_DISPLAY_WATT + "%-4sW"
                    + MESSAGE_DISPLAY_TYPE + "%s";
            for (Appliance a : applianceList.getAllAppliance()) {
                formattedResult = formattedResult.concat(System.lineSeparator() + String.format(format, index,
                        a.getName(), a.getLocation(), a.getStatus(), a.getPower(), a.getType()));
                index++;
            }
            return new CommandResult(formattedResult);
        default:
            return new CommandResult("To be implemented for V0.2");
        }

    }

}
