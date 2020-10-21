package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.framework.type.AirConditioner;
import seedu.smarthomebot.data.framework.type.WaterHeater;
import seedu.smarthomebot.data.framework.type.Fan;
import seedu.smarthomebot.data.framework.Appliance;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static seedu.smarthomebot.common.Messages.*;

public class OnCommand extends Command {

    public static final String COMMAND_WORD = "on";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Turns on specified appliance by its indicated NAME \n"
            + "Parameters: NAME \n"
            + "Example: " + COMMAND_WORD + " Aircon 1 ";
    private static final String APPLIANCE_TYPE = "appliance";
    private static final String LOCATION_TYPE = "location";
    private final String name;
    private final String parameter;

    public OnCommand(String name, String parameter) {
        this.name = name;
        this.parameter = parameter;
    }

    @Override
    public CommandResult execute() {
        String onByType = APPLIANCE_TYPE;
        // To determine if the keyword is a location, if so, append that selected appliance into a list
        ArrayList<Appliance> filterApplianceList =
                (ArrayList<Appliance>) applianceList.getAllAppliance().stream()
                        .filter((s) -> s.getLocation().equals(name))
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
            if (appliance.getName().equals((this.name))) {
                return applianceList.getAllAppliance().indexOf(appliance);
            }
        }
        return -1;
    }

    private String setParameter(String parameter, Appliance appliance) {
        switch (appliance.getType().toLowerCase()) {
        case AirConditioner.TYPE_WORD:
            AirConditioner ac = (AirConditioner) appliance;
            return ac.setTemperature(parameter);
        case Fan.TYPE_WORD:
            Fan fan = (Fan) appliance;
            return fan.setSpeed(parameter);
        case WaterHeater.TYPE_WORD:
            WaterHeater waterHeater = (WaterHeater) appliance;
            return waterHeater.setTemperature(parameter);
        default:
            return "";
        }
    }

    private CommandResult onByLocation() {
        if (locationList.isLocationCreated(this.name)) {
            String outputResults = LINE;
            outputResults = onApplianceByLoop(outputResults);
            return new CommandResult(outputResults);
        } else {
            return new CommandResult("No appliances found in this location");
        }
    }

    private CommandResult onByAppliance() {
        int toOnApplianceIndex = getApplianceToOnIndex();
        if (toOnApplianceIndex < 0) {
            return new CommandResult(MESSAGE_APPLIANCE_NOT_EXIST);
        } else {
            Appliance toOnAppliance = applianceList.getAppliance(toOnApplianceIndex);
            String outputResult = onAppliance(toOnAppliance, "", false);
            return new CommandResult(outputResult);
        }
    }

    private String onApplianceByLoop(String outputResults) {
        for (Appliance toOnAppliance : applianceList.getAllAppliance()) {
            if (toOnAppliance.getLocation().equals(this.name)) {
                outputResults = onAppliance(toOnAppliance, outputResults, true);
            }
        }
        outputResults = outputResults.concat("All appliance in \"" + this.name + "\" are turned on ");
        return outputResults;
    }


    private String onAppliance(Appliance toOnAppliance, String outputResults, boolean isList) {
        boolean onResult = toOnAppliance.switchOn();
        String setParameterStatement = setParameter(parameter, toOnAppliance);
        assert toOnAppliance.getStatus().equals("ON") : "Appliance should be already ON";

        if (!isList) {
            if (onResult) {
                outputResults = setParameterStatement.contains("Previous set temperature will be set.")
                        ? "Switching " + toOnAppliance.toString() + ".....ON" + setParameterStatement
                        : "Switching " + toOnAppliance.toString() + ".....ON";

            } else {
                outputResults = MESSAGE_APPLIANCE_PREVIOUSLY_ON + setParameterStatement;
            }

        }

        return outputResults;
    }

}
