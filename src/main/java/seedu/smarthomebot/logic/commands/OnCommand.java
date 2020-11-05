package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.commons.exceptions.ApplianceNotFoundException;
import seedu.smarthomebot.data.appliance.type.AirConditioner;
import seedu.smarthomebot.data.appliance.type.Fan;
import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.logic.commands.exceptions.NoApplianceInLocationException;
import seedu.smarthomebot.logic.commands.exceptions.NoParameterForLocationException;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST;
import static seedu.smarthomebot.commons.Messages.LINE;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_PREVIOUSLY_ON;
import static seedu.smarthomebot.commons.Messages.MESSAGE_NO_PARAMETER_IN_ON_BY_LOCATION;
import static seedu.smarthomebot.commons.Messages.MESSAGE_INVALID_TEMPERATURE_AC;
import static seedu.smarthomebot.commons.Messages.MESSAGE_INVALID_FAN_SPEED;

//@@author Ang_Cheng_Jun
/**
 * Represent the command to turn on the appliance(s).
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

    public OnCommand(String argument, String parameter) {
        this.argument = argument;
        this.parameter = parameter;
    }

    @Override
    public CommandResult execute() {
        try {
            String onByType = APPLIANCE_TYPE;
            ArrayList<Appliance> filterApplianceList =
                    (ArrayList<Appliance>) applianceList.getAllAppliance().stream()
                            .filter((s) -> s.getLocation().equals(argument))
                            .collect(toList());

            if (!filterApplianceList.isEmpty()) {
                onByType = LOCATION_TYPE;
            }
            switch (onByType) {
            case (APPLIANCE_TYPE):
                return onByAppliance();
            case (LOCATION_TYPE):
                return onByLocation();
            default:
                return new CommandResult("Invalid Format");
            }
        } catch (ApplianceNotFoundException e) {
            return new CommandResult(MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST);
        } catch (NoParameterForLocationException e) {
            return new CommandResult(MESSAGE_NO_PARAMETER_IN_ON_BY_LOCATION);
        } catch (NoApplianceInLocationException e) {
            return new CommandResult("There is no appliance in \"" + argument + "\".");
        }
    }


    private CommandResult onByAppliance() throws ApplianceNotFoundException, NoApplianceInLocationException {
        int toOnApplianceIndex = getApplianceToOnIndex();
        Appliance toOnAppliance = applianceList.getAppliance(toOnApplianceIndex);
        String outputResult = onAppliance(toOnAppliance, "", false);
        return new CommandResult(outputResult);
    }

    private int getApplianceToOnIndex() throws ApplianceNotFoundException, NoApplianceInLocationException {
        for (Appliance appliance : applianceList.getAllAppliance()) {
            if (appliance.getName().equals((argument))) {
                return applianceList.getAllAppliance().indexOf(appliance);
            }
        }
        if (locationList.isLocationCreated(argument)) {
            throw new NoApplianceInLocationException();
        }
        throw new ApplianceNotFoundException();
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

    private CommandResult onByLocation() throws NoParameterForLocationException {
        if (!parameter.isEmpty()) {
            throw new NoParameterForLocationException();
        } else {
            String outputResult = LINE;
            outputResult = onApplianceByLoop(outputResult);
            return new CommandResult(outputResult);
        }
    }

    private String onApplianceByLoop(String outputResult) {
        for (Appliance toOnAppliance : applianceList.getAllAppliance()) {
            if (toOnAppliance.getLocation().equals(argument)) {
                outputResult = onAppliance(toOnAppliance, outputResult, true);
            }
        }
        outputResult = "All appliance in \"" + argument + "\" are turned on ";
        return outputResult;
    }

    private String onAppliance(Appliance toOnAppliance, String outputResult, boolean isList) {
        boolean onResult = toOnAppliance.switchOn();
        assert toOnAppliance.getStatus().equals("ON") : "Appliance should be already ON";
        String setParameterStatement = setParameter(parameter, toOnAppliance);
        if (!isList) {
            if (onResult) {
                outputResult = setParameterStatement + "Switching " + toOnAppliance.toString() + ".....ON";

            } else {
                outputResult = setParameterStatement
                        + MESSAGE_APPLIANCE_PREVIOUSLY_ON + toOnAppliance.toString();
            }
        }
        return outputResult;
    }

}
