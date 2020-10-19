package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.AirConditioner;
import seedu.smarthomebot.data.Fan;
import seedu.smarthomebot.data.framework.Appliance;
import seedu.smarthomebot.exceptions.InvalidValueException;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static seedu.smarthomebot.common.Messages.LINE;
import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_NOT_EXIST;
import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_PREVIOUSLY_ON;
import static seedu.smarthomebot.common.Messages.MESSAGE_INVALID_FAN_SPEED;
import static seedu.smarthomebot.common.Messages.MESSAGE_INVALID_TEMPERATURE_AC;

public class OnCommand extends Command {

    public static final String COMMAND_WORD = "on";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Turns on specified appliance by its indicated NAME \n"
            + "Parameters: NAME \n"
            + "Example: " + COMMAND_WORD + " Aircon 1 ";
    private final String name;
    private final String parameter;
    private static final String APPLIANCE_TYPE = "appliance";
    private static final String LOCATION_TYPE = "location";

    public OnCommand(String name, String parameter) {
        this.name = name;
        this.parameter = parameter;
    }

    private static int convertParameterToInt(String parameter) throws InvalidValueException {
        try {
            return Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            throw new InvalidValueException();
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
        } catch (InvalidValueException e) {
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
                if (toOnAppliance.switchOn()) {
                    assert toOnAppliance.getStatus().equals("ON") : "Appliance should be already ON";
                    String setParameterStatement = setParameter(parameter, toOnAppliance);
                    return new CommandResult(MESSAGE_APPLIANCE_PREVIOUSLY_ON + setParameterStatement);
                } else {
                    assert toOnAppliance.getStatus().equals("ON") : "Appliance should be already ON";
                    setParameter(parameter, toOnAppliance);
                    return new CommandResult("Switching: " + toOnAppliance + "......ON ");
                }
            }
        case(LOCATION_TYPE) :
            if (locationList.isLocationCreated(this.name)) {
                String str = "";
                for (Appliance toOnAppliance: applianceList.getAllAppliance()) {
                    if (toOnAppliance.getLocation().equals(this.name)) {
                        if (toOnAppliance.switchOn()) {
                            assert toOnAppliance.getStatus().equals("ON") : "Appliance should be already ON";
                            String setParameterStatement = setParameter(parameter, toOnAppliance);
                            str = str + MESSAGE_APPLIANCE_PREVIOUSLY_ON + setParameterStatement + "\n" + LINE;
                        } else {
                            assert toOnAppliance.getStatus().equals("ON") : "Appliance should be already ON";
                            setParameter(parameter, toOnAppliance);
                            str = str + "Switching: " + toOnAppliance + "......ON \n" + LINE;
                        }
                    }
                }
                str = str + "All appliance in \"" + this.name + "\" are turned on ";
                return new CommandResult(str);
            } else {
                return new CommandResult("No appliance in this location");
            }
        default :
            return new CommandResult("To be implemented for V0.2");
        }
    }

}
