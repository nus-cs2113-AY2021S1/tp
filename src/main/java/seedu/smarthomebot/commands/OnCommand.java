package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.AirConditioner;
import seedu.smarthomebot.data.Fan;
import seedu.smarthomebot.data.framework.Appliance;
import seedu.smarthomebot.exceptions.InvalidValue;

import static seedu.smarthomebot.common.Messages.*;

public class OnCommand extends Command {

    public static final String COMMAND_WORD = "on";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Turns on specified appliance by its indicated NAME \n"
            + "Parameters: NAME \n"
            + "Example: " + COMMAND_WORD + " Aircon 1 ";
    private final String name;
    private final String parameter;

    public OnCommand(String name, String parameter) {
        this.name = name;
        this.parameter = parameter;

    }

    private static int convertParameterToInt(String parameter) throws InvalidValue {
        try {
            return Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            throw new InvalidValue();
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

    private void setParameter(String parameter, Appliance appliance) {
        switch (appliance.getType().toLowerCase()) {
        case AirConditioner.TYPE_WORD:
            AirConditioner ac = (AirConditioner) appliance;
            if (isParameterValid(parameter, 15, 30)) {
                ac.setTemperature(parameter);
            } else {

            }
            break;
        case Fan.TYPE_WORD:
            Fan fan = (Fan) appliance;
            if (isParameterValid(parameter, 0, 4)) {
                fan.setSpeed(parameter);
            } else {

            }
            break;
        default:
            break;
        }
    }

    private boolean isParameterValid(String parameter, int lowerBound, int upperBound) {
        try {
            int acTemperature = convertParameterToInt(parameter);
            if ((acTemperature < upperBound) && (acTemperature > lowerBound)) return true;
        } catch (InvalidValue e) {
            return false;
        }
        return false;
    }

    private boolean isFanSpeedValid(String parameter) {
        return true;
    }

    @Override
    public CommandResult execute() {
        int toOnApplianceIndex = getApplianceToOnIndex();
        if (toOnApplianceIndex < 0) {
            return new CommandResult(MESSAGE_APPLIANCE_NOT_EXIST);
        } else {
            Appliance toOnAppliance = applianceList.getAppliance(toOnApplianceIndex);
            if (toOnAppliance.switchOn()) {
                assert !toOnAppliance.switchOn() : "Appliance should be already OFF";
                return new CommandResult(MESSAGE_APPLIANCE_PREVIOUSLY_ON);
            } else {
                setParameter(parameter, toOnAppliance);
                return new CommandResult("Switching: " + toOnAppliance + "......ON");
            }
        }
    }

}
