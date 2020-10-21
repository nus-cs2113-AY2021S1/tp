package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.framework.Appliance;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
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
    public static final String MESSAGE_USAGE = "List: \n\t\t a. " + COMMAND_WORD
            + " appliance \n\t\t b. " + COMMAND_WORD + " location \n\t\t c. "
            + COMMAND_WORD + "appliance l/[LOCATION_NAME]";
    private static final String APPLIANCE_TYPE = "appliance";
    private static final String LOCATION_TYPE = "location";
    private final String parameter;
    private final String filteredLocation;

    public ListCommand(String arguments,String filteredLocation) {
        this.parameter = arguments;
        this.filteredLocation = filteredLocation;
    }

    @Override
    public CommandResult execute() {
        int index = 1;
        switch (parameter) {
        case LOCATION_TYPE:
            if (locationList.getLocations().size() == 0) {
                return new CommandResult(LINE + MESSAGE_LIST_NO_LOCATIONS);
            }
            String outputLocationsList = LINE + MESSAGE_LIST_LOCATIONS;
            for (String location : locationList.getLocations()) {
                outputLocationsList = outputLocationsList.concat(System.lineSeparator() + index + ": " + location);
                index++;
            }
            return new CommandResult(outputLocationsList);
        case APPLIANCE_TYPE:
            if (filteredLocation.equals("")) {
                if (applianceList.getAllAppliance().size() == 0) {
                    return new CommandResult(LINE + MESSAGE_LIST_NO_APPLIANCES);
                }
                String header = (LINE + MESSAGE_LIST_APPLIANCES);
                String outputApplianceList = displayOutput(header, applianceList.getAllAppliance());
                return new CommandResult(outputApplianceList);
            } else {
                ArrayList<Appliance> filterApplianceList =
                        (ArrayList<Appliance>) applianceList.getAllAppliance().stream()
                        .filter((s) -> s.getLocation().equals(filteredLocation))
                        .collect(toList());

                if (filterApplianceList.isEmpty()) {
                    return new CommandResult("Location: \"" + filteredLocation + "\" does not exist.");
                }
                String header = (LINE + "Here are the appliances in \"" + filteredLocation + "\"");
                String outputFilteredList = displayOutput(header, filterApplianceList);
                return new CommandResult(outputFilteredList);
            }
        default:
            return new CommandResult("To be implemented for V0.2");
        }
    }

    private String displayOutput(String header, ArrayList<Appliance> displayList) {
        int index = 1;
        String outputList = header;
        String format = "%-2d. %-" + Appliance.getMaxNameLength() + "s"
                + MESSAGE_DISPLAY_LOCATION + "%-" + Appliance.getMaxLocationLength() + "s"
                + MESSAGE_DISPLAY_STATUS + "%-5s"
                + MESSAGE_DISPLAY_WATT + "%-4sW"
                + MESSAGE_DISPLAY_TYPE + "%s";
        for (Appliance a : displayList) {
            outputList  = outputList.concat(System.lineSeparator() + String.format(format, index,
                    a.getName(), a.getLocation(), a.getStatus(), a.getWattage(), a.getType()));
            index++;
        }

        return outputList;
    }
}
