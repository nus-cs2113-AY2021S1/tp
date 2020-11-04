package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.type.AirConditioner;
import seedu.smarthomebot.data.appliance.type.Fan;
import seedu.smarthomebot.data.appliance.Appliance;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST;
import static seedu.smarthomebot.commons.Messages.LINE;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_PREVIOUSLY_ON;
import static seedu.smarthomebot.commons.Messages.MESSAGE_NO_PARAMETER_IN_ON_BY_LOCATION;
import static seedu.smarthomebot.commons.Messages.MESSAGE_INVALID_TEMPERATURE_AC;
import static seedu.smarthomebot.commons.Messages.MESSAGE_INVALID_FAN_SPEED;

/**
 * Represent the command to turn on the appliance.
 */
public class OnCommand extends Command {

    public static final String COMMAND_WORD = "on";
    public static final String MESSAGE_USAGE = "Switch ON Appliance: \n\t\t a. " + COMMAND_WORD
            + " [APPLIANCE_NAME] \n\t\t b. " + COMMAND_WORD + " [APPLIANCE_NAME] p/[PARAMETER] \n\t\t c. "
            + COMMAND_WORD + " [LOCATION_NAME]";
    private static final String APPLIANCE_TYPE = "appliance";
    private static final String LOCATION_TYPE = "location";
    private final String key;
    private final String parameter;

    public OnCommand(String key, String parameter) {
        this.key = key;
        this.parameter = parameter;
    }

    @Override
    public CommandResult execute() {
        String onByType = APPLIANCE_TYPE;
        // To check if any of the appliances contains the name of the location
        ArrayList<Appliance> filterApplianceList =
                (ArrayList<Appliance>) applianceList.getAllAppliance().stream()
                        .filter((s) -> s.getLocation().equals(this.key))
                        .collect(toList());

        // if list is empty
        if (!filterApplianceList.isEmpty()) {
            onByType = LOCATION_TYPE;
        }
        switch (onByType) {
        case (APPLIANCE_TYPE):
            return onByAppliance();
        case (LOCATION_TYPE):
            return onByLocation();
        default:
            return new CommandResult("");
        }
    }

    private int getApplianceToOnIndex() {
        for (Appliance appliance : applianceList.getAllAppliance()) {
            if (appliance.getName().equals((this.key))) {
                return applianceList.getAllAppliance().indexOf(appliance);
            }
        }
        return -1;
    }

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

    private CommandResult onByLocation() {
        if (!parameter.isEmpty()) {
            return new CommandResult(MESSAGE_NO_PARAMETER_IN_ON_BY_LOCATION);
        } else {
            String outputResults = LINE;
            outputResults = onApplianceByLoop(outputResults);
            return new CommandResult(outputResults);
        }
    }

    private CommandResult onByAppliance() {
        int toOnApplianceIndex = getApplianceToOnIndex();
        if (toOnApplianceIndex < 0) {
            return new CommandResult(MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST);
        } else {
            Appliance toOnAppliance = applianceList.getAppliance(toOnApplianceIndex);
            String outputResult = onAppliance(toOnAppliance, "", false);
            return new CommandResult(outputResult);
        }
    }

    private String onApplianceByLoop(String outputResults) {
        for (Appliance toOnAppliance : applianceList.getAllAppliance()) {
            if (toOnAppliance.getLocation().equals(this.key)) {
                outputResults = onAppliance(toOnAppliance, outputResults, true);
            }
        }
        outputResults = "All appliance in \"" + this.key + "\" are turned on ";
        return outputResults;
    }

    private String onAppliance(Appliance toOnAppliance, String outputResults, boolean isList) {
        boolean onResult = toOnAppliance.switchOn();
        assert toOnAppliance.getStatus().equals("ON") : "Appliance should be already ON";
        String setParameterStatement = setParameter(parameter, toOnAppliance);
        if (!isList) {
            if (onResult) {
                outputResults = setParameterStatement + "Switching " + toOnAppliance.toString() + ".....ON";

            } else {
                outputResults = setParameterStatement
                        + MESSAGE_APPLIANCE_PREVIOUSLY_ON + toOnAppliance.toString();
            }
        }

        return outputResults;
    }

}
