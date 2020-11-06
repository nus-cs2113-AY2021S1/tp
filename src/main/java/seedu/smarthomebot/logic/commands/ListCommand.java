package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.logic.commands.exceptions.EmptyApplianceListException;
import seedu.smarthomebot.logic.commands.exceptions.EmptyLocationListException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;
import seedu.smarthomebot.commons.exceptions.NoApplianceInLocationException;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_APPLIANCES;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_NO_LOCATIONS;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_LOCATIONS;

//@@author Ang_Cheng_Jun
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

    private final String argument;
    private final String filteredLocation;

    /**
     * Constructor for ListCommand.
     *
     * @param argument Appliance or Location 's name to be off.
     */
    public ListCommand(String argument, String filteredLocation) {
        assert argument.isEmpty() != true : "ListCommand must not accept empty argument";
        this.argument = argument;
        this.filteredLocation = filteredLocation;
    }

    /**
     * Executing the ListCommand.
     */
    @Override
    public CommandResult execute() {
        try {
            switch (argument) {
            case LOCATION_TYPE:
                return listLocation();
            case APPLIANCE_TYPE:
                return listAppliance();
            default:
                return new CommandResult("Invalid Format");
            }
        } catch (EmptyApplianceListException e) {
            return new CommandResult(MESSAGE_LIST_NO_APPLIANCES);
        } catch (LocationNotFoundException e) {
            return new CommandResult("Location: \"" + filteredLocation + "\" does not exist.");
        } catch (NoApplianceInLocationException e) {
            return new CommandResult("There is no Appliance in \"" + filteredLocation + "\".");
        } catch (EmptyLocationListException e) {
            return new CommandResult(MESSAGE_LIST_NO_LOCATIONS);
        }
    }

    /**
     * Method to list all the appliances or appliances in filteredLocation.
     *
     * @return the Appliance List in String.
     */
    private CommandResult listAppliance() throws LocationNotFoundException, EmptyApplianceListException,
            NoApplianceInLocationException {
        String outputResult;
        ArrayList<Appliance> outputApplianceList = applianceList.getAllAppliance();

        if (filteredLocation.equals("")) {
            if (outputApplianceList.size() == 0) {
                throw new EmptyApplianceListException();
            }
            String header = MESSAGE_LIST_APPLIANCES;
            outputResult = displayOutput(header, outputApplianceList);
        } else {
            // To filter out Appliances in the filteredLocation.
            ArrayList<Appliance> filterApplianceList =
                    (ArrayList<Appliance>) outputApplianceList.stream()
                            .filter((s) -> s.getLocation().equals(filteredLocation))
                            .collect(toList());

            if (filterApplianceList.isEmpty()) {
                if (locationList.isLocationCreated(filteredLocation)) {
                    throw new NoApplianceInLocationException();
                }
                throw new LocationNotFoundException();
            }
            String header = ("Here are the Appliances in \"" + filteredLocation + "\"");
            outputResult = displayOutput(header, filterApplianceList);
        }
        return new CommandResult(outputResult);
    }

    /**
     * Method to list all the locations.
     *
     * @return the Location List in String.
     */
    private CommandResult listLocation() throws EmptyLocationListException {

        int index = 1;
        ArrayList<String> outputLocationList = locationList.getAllLocations();

        if (outputLocationList.size() == 0) {
            throw new EmptyLocationListException();
        }
        String outputResult = MESSAGE_LIST_LOCATIONS;
        for (String location : outputLocationList) {
            outputResult = outputResult.concat(System.lineSeparator() + index + ": " + location);
            index++;
        }
        return new CommandResult(outputResult);
    }

    /**
     * Method to format the displayList.
     *
     * @param displayList Appliance List to display.
     * @return the formatted Appliance List in String.
     */
    private String displayOutput(String header, ArrayList<Appliance> displayList) {
        autoFormattingStringIndex();
        int index = 1;
        String outputResult = header;
        String format = "%-2d. %-" + maxNameLength + "s"
                + DISPLAY_LOCATION + "%-" + maxLocationLength + "s"
                + DISPLAY_STATUS + "%-3s"
                + DISPLAY_WATT + "%-4sW"
                + DISPLAY_TYPE + "%-9s"
                + DISPLAY_PARAMETER + "%s";

        for (Appliance a : displayList) {
            outputResult = outputResult.concat(System.lineSeparator() + String.format(format, index,
                    a.getName(), a.getLocation(), a.getStatus(), a.getWattage(), a.getType(), a.getParameter(true)));
            index++;
        }

        return outputResult;
    }

}
