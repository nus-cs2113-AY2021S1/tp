package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.Appliance;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static seedu.smarthomebot.commons.Messages.LINE;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_APPLIANCES;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_NO_LOCATIONS;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_LOCATIONS;

/**
 * Represent the command to list LocationList or ApplianceList to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = "List: \n\t\t a. " + COMMAND_WORD
            + " appliance \n\t\t b. " + COMMAND_WORD + " location \n\t\t c. "
            + COMMAND_WORD + " appliance l/[LOCATION_NAME]";
    private static final String APPLIANCE_TYPE = "appliance";
    private static final String LOCATION_TYPE = "location";

    private static final String DISPLAY_LOCATION = " | Location: ";
    private static final String DISPLAY_STATUS = " | Status: ";
    private static final String DISPLAY_WATT = " | Watt: ";
    private static final String DISPLAY_TYPE = " | Type: ";
    private static final String DISPLAY_PARAMETER = " | Parameter: ";

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
            return listLocation(index);
        case APPLIANCE_TYPE:
            return listAppliance();
        default:
            return new CommandResult("Error");
        }
    }

    private CommandResult listAppliance() {
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
                if (locationList.isLocationCreated(filteredLocation)) {
                    return new CommandResult("There is no appliance in \"" + filteredLocation + "\".");
                }
                return new CommandResult("Location: \"" + filteredLocation + "\" does not exist.");
            }
            String header = (LINE + "Here are the appliances in \"" + filteredLocation + "\"");
            String outputFilteredList = displayOutput(header, filterApplianceList);
            return new CommandResult(outputFilteredList);
        }
    }

    private CommandResult listLocation(int index) {
        if (locationList.getAllLocations().size() == 0) {
            return new CommandResult(LINE + MESSAGE_LIST_NO_LOCATIONS);
        }
        String outputLocationsList = LINE + MESSAGE_LIST_LOCATIONS;
        for (String location : locationList.getAllLocations()) {
            outputLocationsList = outputLocationsList.concat(System.lineSeparator() + index + ": " + location);
            index++;
        }
        return new CommandResult(outputLocationsList);
    }

    private String displayOutput(String header, ArrayList<Appliance> displayList) {
        autoFormattingStringIndex();
        int index = 1;
        String outputList = header;
        String format = "%-2d. %-" + maxNameLength + "s"
                + DISPLAY_LOCATION + "%-" + maxLocationLength + "s"
                + DISPLAY_STATUS + "%-3s"
                + DISPLAY_WATT + "%-4sW"
                + DISPLAY_TYPE + "%-9s"
                + DISPLAY_PARAMETER + "%s";

        for (Appliance a : displayList) {
            outputList = outputList.concat(System.lineSeparator() + String.format(format, index,
                    a.getName(), a.getLocation(), a.getStatus(), a.getWattage(), a.getType(), a.getParameter(true)));
            index++;
        }

        return outputList;
    }

}
