package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.logic.commands.exceptions.EmptyApplianceListException;
import seedu.smarthomebot.logic.commands.exceptions.EmptyLocationListException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;
import seedu.smarthomebot.commons.exceptions.NoApplianceInLocationException;

import java.util.ArrayList;
import java.util.logging.Level;

import static java.util.stream.Collectors.toList;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_APPLIANCES;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_LOCATIONS;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_NO_LOCATIONS;

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
     * Constructor for List Command.
     *
     * @param argument         to determine ListLocation or ListAppliance.
     * @param filteredLocation input location filter for ListAppliance.
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
                commandLogger.log(Level.WARNING, "Invalid Format");
                return new CommandResult("Invalid Format");
            }
        } catch (EmptyApplianceListException e) {
            commandLogger.log(Level.WARNING, MESSAGE_LIST_NO_APPLIANCES);
            return new CommandResult(MESSAGE_LIST_NO_APPLIANCES);
        } catch (LocationNotFoundException e) {
            commandLogger.log(Level.WARNING, "Location: \"" + filteredLocation + "\" does not exist.");
            return new CommandResult("Location: \"" + filteredLocation + "\" does not exist.");
        } catch (NoApplianceInLocationException e) {
            commandLogger.log(Level.WARNING, "There is no Appliance in \"" + filteredLocation + "\".");
            return new CommandResult("There is no Appliance in \"" + filteredLocation + "\".");
        } catch (EmptyLocationListException e) {
            commandLogger.log(Level.WARNING, MESSAGE_LIST_NO_LOCATIONS);
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
            outputResult = listAllAppliances(outputApplianceList);
        } else {
            outputResult = listApplianceByLocation(outputApplianceList);
        }
        commandLogger.log(Level.INFO, outputResult);
        return new CommandResult(outputResult);
    }


    private String listAllAppliances(ArrayList<Appliance> outputApplianceList) throws EmptyApplianceListException {
        if (outputApplianceList.size() == 0) {
            throw new EmptyApplianceListException();
        }
        String outputResult = displayOutput(MESSAGE_LIST_APPLIANCES, outputApplianceList);
        commandLogger.log(Level.INFO, outputResult);
        return outputResult;
    }

    private String listApplianceByLocation(ArrayList<Appliance> outputApplianceList)
            throws LocationNotFoundException, NoApplianceInLocationException {
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
        String outputResult = displayOutput(header, filterApplianceList);
        commandLogger.log(Level.INFO, outputResult);
        return outputResult;
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
        commandLogger.log(Level.INFO, outputResult);
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
        commandLogger.log(Level.INFO, outputResult);
        return outputResult;
    }
}
