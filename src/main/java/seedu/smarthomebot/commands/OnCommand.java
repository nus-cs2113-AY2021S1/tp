package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.AirConditioner;
import seedu.smarthomebot.data.WaterHeater;
import seedu.smarthomebot.data.Fan;
import seedu.smarthomebot.data.framework.Appliance;
import seedu.smarthomebot.exceptions.InvalidWattageValueException;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static seedu.smarthomebot.common.Messages.LINE;
import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_NOT_EXIST;
import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_PREVIOUSLY_ON;
import static seedu.smarthomebot.common.Messages.MESSAGE_INVALID_FAN_SPEED;
import static seedu.smarthomebot.common.Messages.MESSAGE_INVALID_TEMPERATURE_AC;
import static seedu.smarthomebot.common.Messages.MESSAGE_INVALID_TEMPERATURE_WH;

public class OnCommand extends Command {

    public static final String COMMAND_WORD = "on";
    public static final String MESSAGE_USAGE = "Switch ON Appliance: \n\t\t a. " + COMMAND_WORD
            + " [APPLIANCE_NAME] \n\t\t b. " + COMMAND_WORD + " [APPLIANCE_NAME] p/[PARAMETER] \n\t\t c. "
            + COMMAND_WORD + " [LOCATION_NAME]";

    private final String name;
    private final String parameter;
    private static final String APPLIANCE_TYPE = "appliance";
    private static final String LOCATION_TYPE = "location";

    public OnCommand(String name, String parameter) {
        this.name = name;
        this.parameter = parameter;
    }

    private static int convertParameterToInt(String parameter) throws InvalidWattageValueException {
        try {
            return Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            throw new InvalidWattageValueException();
        }

    }

    private int getApplianceToOnIndex() {
        for (Appliance appliance : applianceList.getAllAppliance()) {
            if (appliance.getName().equals((this.name))) {
                return applianceList.getAllAppliance().indexOf(appliance);
            }
        }
        return -1;
    }

    private String setParameter(String parameter, Appliance appliance) {
        String toPrint = " ";
        switch (appliance.getType().toLowerCase()) {
        case AirConditioner.TYPE_WORD:
            AirConditioner ac = (AirConditioner) appliance;
            if (isParameterValid(parameter, 15, 30)) {
                ac.setTemperature(parameter);
                toPrint = ac.toString();
            } else {
                toPrint = MESSAGE_INVALID_TEMPERATURE_AC;
            }
            break;
        case Fan.TYPE_WORD:
            Fan fan = (Fan) appliance;
            if (isParameterValid(parameter, 0, 4)) {
                fan.setSpeed(parameter);
                toPrint = fan.toString();
            } else {
                toPrint = MESSAGE_INVALID_FAN_SPEED;
            }
            break;
        case WaterHeater.TYPE_WORD:
            WaterHeater waterHeater = (WaterHeater) appliance;
            if (isParameterValid(parameter, 20, 50)) {
                waterHeater.setTemperature(parameter);
                toPrint = waterHeater.toString();
            } else {
                toPrint = MESSAGE_INVALID_TEMPERATURE_WH;
            }
            break;
        default:
            break;
        }
        return toPrint;
    }

    private boolean isParameterValid(String parameter, int lowerBound, int upperBound) {
        try {
            int acTemperature = convertParameterToInt(parameter);
            if ((acTemperature < upperBound) && (acTemperature > lowerBound)) {
                return true;
            }
        } catch (InvalidWattageValueException e) {
            return false;
        }
        return false;
    }

    @Override
    public CommandResult execute() {
        String type = APPLIANCE_TYPE;
        ArrayList<Appliance> filterApplianceList =
                (ArrayList<Appliance>) applianceList.getAllAppliance().stream()
                        .filter((s) -> s.getLocation().equals(name))
                        .collect(toList());
        if (!filterApplianceList.isEmpty()) {
            type = LOCATION_TYPE;
        }
        switch (type) {
        case(APPLIANCE_TYPE) :
            int toOnApplianceIndex = getApplianceToOnIndex();
            if (toOnApplianceIndex < 0) {
                return new CommandResult(MESSAGE_APPLIANCE_NOT_EXIST);
            } else {
                Appliance toOnAppliance = applianceList.getAppliance(toOnApplianceIndex);
                String outputResult = displayOutput(toOnAppliance, "",0);
                return new CommandResult(outputResult);
            }
        case(LOCATION_TYPE) :
            if (locationList.isLocationCreated(this.name)) {
                String outputResults = LINE;
                for (Appliance toOnAppliance: applianceList.getAllAppliance()) {
                    if (toOnAppliance.getLocation().equals(this.name)) {
                        outputResults = displayOutput(toOnAppliance, outputResults, 1);
                    }
                }
                outputResults = outputResults.concat("All appliance in \"" + this.name + "\" are turned on ");
                return new CommandResult(outputResults);
            } else {
                return new CommandResult("No appliance in this location");
            }
        default :
            return new CommandResult("To be implemented for V0.2");
        }
    }

    private String displayOutput(Appliance toOnAppliance, String outputResults, int isList) {
        if (toOnAppliance.switchOn()) {
            assert toOnAppliance.getStatus().equals("ON") : "Appliance should be already ON";
            String setParameterStatement = setParameter(parameter, toOnAppliance);
            if (isList == 1) {
                outputResults = outputResults.concat(MESSAGE_APPLIANCE_PREVIOUSLY_ON
                                                    + setParameterStatement + "\n" + LINE);
            } else {
                outputResults = LINE + MESSAGE_APPLIANCE_PREVIOUSLY_ON + setParameterStatement;
            }
        } else {
            assert toOnAppliance.getStatus().equals("ON") : "Appliance should be already ON";
            setParameter(parameter, toOnAppliance);
            if (isList == 1) {
                outputResults = outputResults.concat("Switching: " + toOnAppliance + "......ON \n" + LINE);
            } else {
                outputResults = LINE + "Switching: " + toOnAppliance + "......ON";
            }
        }
        return outputResults;
    }

}
