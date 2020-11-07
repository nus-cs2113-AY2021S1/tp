package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.commons.exceptions.ApplianceNotFoundException;
import seedu.smarthomebot.data.appliance.Appliance;

import java.util.ArrayList;
import java.util.logging.Level;

import static java.util.stream.Collectors.toList;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_PREVIOUSLY_OFF;

//@@author Ang-Cheng-Jun

/**
 * Represent the Command to turn off Appliance(s).
 */
public class OffCommand extends Command {

    public static final String COMMAND_WORD = "off";
    public static final String MESSAGE_USAGE = "Switch OFF Appliance: \n\t\t a. " + COMMAND_WORD
            + " [APPLIANCE_NAME] \n\t\t b. " + COMMAND_WORD + " [LOCATION_NAME]";
    private static final String APPLIANCE_TYPE = "appliance";
    private static final String LOCATION_TYPE = "location";
    private final String argument;

    /**
     * Constructor for OffCommand.
     *
     * @param argument Appliance or Location 's name to be off.
     */
    public OffCommand(String argument) {
        assert !argument.isEmpty() : "OffCommand must not accept empty argument";
        this.argument = argument;
    }

    /**
     * Executing the OffCommand.
     */
    @Override
    public CommandResult execute() {
        try {
            String type = APPLIANCE_TYPE;
            ArrayList<Appliance> filterApplianceList =
                    (ArrayList<Appliance>) applianceList.getAllAppliance().stream()
                            .filter((s) -> s.getLocation().equals(argument))
                            .collect(toList());
            if (!filterApplianceList.isEmpty()) {
                type = LOCATION_TYPE;
            }

            switch (type) {
            case (APPLIANCE_TYPE):
                return offByApplianceName();
            case (LOCATION_TYPE):
                return offByLocation(filterApplianceList);
            default:
                return new CommandResult("Invalid Format");
            }
        } catch (ApplianceNotFoundException e) {
            if (locationList.isLocationCreated(argument)) {
                commandLogger.log(Level.WARNING, "Unable to Off: There are no Appliances in \"" + argument + "\".");
                return new CommandResult("There are no Appliances in \"" + argument + "\".");
            } else {
                commandLogger.log(Level.WARNING, "Unable to Off: " + MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST);
                return new CommandResult(MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST);
            }
        }
    }

    /**
     * Method to off Appliance by the name.
     *
     * @throws ApplianceNotFoundException when keyed Appliance is not found in ApplianceList.
     */
    private CommandResult offByApplianceName() throws ApplianceNotFoundException {
        int toOffApplianceIndex = applianceList.getApplianceIndex(argument);
        Appliance toOffAppliance = applianceList.getAppliance(toOffApplianceIndex);
        String outputResult = offAppliance(toOffAppliance, true);
        assert !outputResult.isEmpty() : "outputResult must contains String";
        commandLogger.log(Level.INFO, "Appliance Off with output message: " + outputResult);
        return new CommandResult(outputResult);
    }

    /**
     * Method to off Appliance by the Location.
     */
    private CommandResult offByLocation(ArrayList<Appliance> toOffAppliance) {
        offApplianceByLoop(toOffAppliance);
        String outputResult = "All Appliances in \"" + argument + "\" are turned off ";
        assert !outputResult.isEmpty() : "outputResult must contains String";
        commandLogger.log(Level.INFO, "Location Off with output message: " + outputResult);
        return new CommandResult(outputResult);
    }

    /**
     * Method to iterate through the list and filter out the location to turn off.
     */
    private void offApplianceByLoop(ArrayList<Appliance> toOffAppliance) {
        for (Appliance appliance : toOffAppliance) {
            offAppliance(appliance, false);
        }
    }

    /**
     * Method to switch off Appliance.
     *
     * @param toOffAppliance Appliance to switch off in Appliance.
     * @param isList         flag to return its corresponding output message.
     * @return the corresponding output Message in String if isList is true.
     */
    private String offAppliance(Appliance toOffAppliance, boolean isList) {
        boolean offResult = toOffAppliance.switchOff();
        String outputResult = "";
        assert toOffAppliance.getStatus().equals("OFF") : "Appliance should be already OFF";

        if (isList) {
            if (offResult) {
                outputResult = "Switching: " + toOffAppliance + "......OFF";
            } else {
                outputResult = toOffAppliance.getName() + MESSAGE_APPLIANCE_PREVIOUSLY_OFF;
            }
        }
        assert !outputResult.isEmpty() : "outputResult must contains String";
        return outputResult;
    }
}
