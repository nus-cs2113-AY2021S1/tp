package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.commons.exceptions.ApplianceNotFoundException;
import seedu.smarthomebot.data.appliance.type.AirConditioner;
import seedu.smarthomebot.data.appliance.type.Fan;
import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.commons.exceptions.NoApplianceInLocationException;
import seedu.smarthomebot.logic.commands.exceptions.ParameterFoundException;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_PREVIOUSLY_ON;
import static seedu.smarthomebot.commons.Messages.MESSAGE_NO_PARAMETER_IN_ON_BY_LOCATION;
import static seedu.smarthomebot.commons.Messages.MESSAGE_INVALID_TEMPERATURE_AC;
import static seedu.smarthomebot.commons.Messages.MESSAGE_INVALID_FAN_SPEED;

//@@author leonlowzd
/**
 * Represent the Command to turn on Appliance(s).
 */
public class OnCommand extends Command {

    public static final String COMMAND_WORD = "on";
    public static final String MESSAGE_USAGE = "Switch ON Appliance: \n\t\t a. " + COMMAND_WORD
            + " [APPLIANCE_NAME] \n\t\t b. " + COMMAND_WORD + " [APPLIANCE_NAME] p/[PARAMETER] \n\t\t c. "
            + COMMAND_WORD + " [LOCATION_NAME]";

    private static final String APPLIANCE_TYPE = "appliance";
    private static final String LOCATION_TYPE = "location";
    private final String argument;
    private final String parameter;

    /**
     * Constructor for OnCommand.
     *
     * @param argument  Appliance or Location 's name to be on.
     * @param parameter To set Appliance's parameter: only valid for fan and aircon.
     */
    public OnCommand(String argument, String parameter) {
        this.argument = argument;
        this.parameter = parameter;
    }

    /**
     * Executing the OnCommand.
     */
    @Override
    public CommandResult execute() {
        try {
            String onByType = APPLIANCE_TYPE;
            // To filter out Appliances with location corresponding to the argument.
            ArrayList<Appliance> filterApplianceList =
                    (ArrayList<Appliance>) applianceList.getAllAppliance().stream()
                            .filter((s) -> s.getLocation().equals(argument))
                            .collect(toList());

            // If the list is not empty; it means that user wants to onByLocation.
            if (!filterApplianceList.isEmpty()) {
                onByType = LOCATION_TYPE;
            }

            switch (onByType) {
            case (APPLIANCE_TYPE):
                return onByApplianceName();
            case (LOCATION_TYPE):
                return onByLocation(filterApplianceList);
            default:
                return new CommandResult("Invalid Format");
            }
        } catch (ApplianceNotFoundException e) {
            if (locationList.isLocationCreated(argument)) {
                return new CommandResult("There are no Appliances in \"" + argument + "\".");
            } else {
                return new CommandResult(MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST);
            }
        } catch (ParameterFoundException e) {
            return new CommandResult(MESSAGE_NO_PARAMETER_IN_ON_BY_LOCATION);
        }
    }

    /**
     * Method to on Appliance by the name.
     */
    private CommandResult onByApplianceName() throws ApplianceNotFoundException {
        int toOnApplianceIndex = applianceList.getApplianceIndex(argument);
        Appliance toOnAppliance = applianceList.getAppliance(toOnApplianceIndex);
        String outputResult = onAppliance(toOnAppliance, true);
        return new CommandResult(outputResult);
    }

    /**
     * Method to set Appliance's parameter.
     *
     * @return error message if there is an error setting the parameter.
     */
    private String setParameter(String parameter, Appliance appliance) {
        String setParameterStatement = "";
        switch (appliance.getType().toLowerCase()) {
        case AirConditioner.TYPE_WORD:
            AirConditioner ac = (AirConditioner) appliance;
            if (!ac.setTemperature(parameter)) {
                setParameterStatement = MESSAGE_INVALID_TEMPERATURE_AC;
            }
            break;
        case Fan.TYPE_WORD:
            Fan fan = (Fan) appliance;
            if (!fan.setSpeed(parameter)) {
                setParameterStatement = MESSAGE_INVALID_FAN_SPEED;
            }
            break;
        default:
            return "";
        }
        return setParameterStatement;
    }

    /**
     * Method to On Appliance by the Location.
     */
    private CommandResult onByLocation(ArrayList<Appliance> toOnAppliance) throws ParameterFoundException {
        if (!parameter.isEmpty()) {
            throw new ParameterFoundException();
        } else {
            onApplianceByLoop(toOnAppliance);
            String outputResult = "All Appliances in \"" + argument + "\" are turned on ";
            return new CommandResult(outputResult);
        }
    }

    /**
     * Method to iterate through the toOnAppliance List and turn on the Appliance.
     */
    private void onApplianceByLoop(ArrayList<Appliance> toOnAppliance) {
        for (Appliance appliance : toOnAppliance) {
            onAppliance(appliance, false);
        }
    }

    /**
     * Method to switch on Appliance.
     *
     * @param toOnAppliance Appliance to switch on in Appliance.
     * @param isList        flag to return its corresponding output message.
     * @return the corresponding output Message in String if isList is true.
     */
    private String onAppliance(Appliance toOnAppliance, boolean isList) {
        String outputResult = "";
        boolean onResult = toOnAppliance.switchOn();
        assert toOnAppliance.getStatus().equals("ON") : "Appliance should be already ON";
        String setParameterStatement = setParameter(parameter, toOnAppliance);
        if (isList) {
            if (onResult) {
                outputResult = setParameterStatement + "Switching " + toOnAppliance.toString() + ".....ON";

            } else {
                outputResult = setParameterStatement
                        + toOnAppliance.getName() + MESSAGE_APPLIANCE_PREVIOUSLY_ON + toOnAppliance.toString();
            }
        }
        return outputResult;
    }

}
