package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.commons.exceptions.ApplianceNotFoundException;
import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.commons.exceptions.NoApplianceInLocationException;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_PREVIOUSLY_OFF;

//@@Ang_Cheng_Jun

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
     * Constructor for Off Command.
     *
     * @param argument Appliance or Location 's name to be off.
     */
    public OffCommand(String argument) {
        assert argument.isEmpty() != true : "InvalidCommand must not accept empty arguments";
        this.argument = argument;
    }

    /**
     * Executing the Off Command.
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
            return new CommandResult(MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST);
        } catch (NoApplianceInLocationException e) {
            return new CommandResult("There is no appliance in \"" + argument + "\".");
        }
    }

    /**
     * Method to off Appliance by the name.
     */
    private CommandResult offByApplianceName() throws ApplianceNotFoundException, NoApplianceInLocationException {
        int toOffApplianceIndex = applianceList.getApplianceIndex(argument, locationList);
        Appliance toOffAppliance = applianceList.getAppliance(toOffApplianceIndex);
        String outputResult = offAppliance(toOffAppliance, true);
        return new CommandResult(outputResult);
    }

    /**
     * Method to off Appliance by the Location.
     */
    private CommandResult offByLocation(ArrayList<Appliance> toOffAppliance) {
        offApplianceByLoop(toOffAppliance);
        String outputResult = "All Appliances in \"" + argument + "\" are turned off ";
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
     * @param isList        flag to return its corresponding output message.
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
        return outputResult;
    }
}
