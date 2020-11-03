package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.Appliance;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_PREVIOUSLY_OFF;
import static seedu.smarthomebot.commons.Messages.LINE;

/**
 * Represent the command to turn off appliance.
 */
public class OffCommand extends Command {

    public static final String COMMAND_WORD = "off";
    public static final String MESSAGE_USAGE = "Switch OFF Appliance: \n\t\t a. " + COMMAND_WORD
            + " [APPLIANCE_NAME] \n\t\t b. " + COMMAND_WORD + " [LOCATION_NAME]";
    private static final String APPLIANCE_TYPE = "appliance";
    private static final String LOCATION_TYPE = "location";
    private final String key;

    public OffCommand(String key) {
        this.key = key;
    }

    private int getApplianceToOffIndex() {
        for (Appliance appliance : applianceList.getAllAppliance()) {
            if (appliance.getName().equals((this.key))) {
                return applianceList.getAllAppliance().indexOf(appliance);
            }
        }
        return -1;
    }

    @Override
    public CommandResult execute() {
        String type = APPLIANCE_TYPE;
        ArrayList<Appliance> filterApplianceList =
                (ArrayList<Appliance>) applianceList.getAllAppliance().stream()
                        .filter((s) -> s.getLocation().equals(this.key))
                        .collect(toList());
        if (!filterApplianceList.isEmpty()) {
            type = LOCATION_TYPE;
        }
        switch (type) {
        case (APPLIANCE_TYPE):
            return offByAppliance();
        case (LOCATION_TYPE):
            return offByLocation();
        default:
            return new CommandResult("Invalid Format");
        }
    }

    private CommandResult offByAppliance() {
        int toOffApplianceIndex = getApplianceToOffIndex();
        if (toOffApplianceIndex < 0) {
            assert toOffApplianceIndex < 0 : "Index should be negative.";
            return new CommandResult(MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST);
        } else {
            assert toOffApplianceIndex > 0 : "Index should be positive.";
            Appliance toOffAppliance = applianceList.getAppliance(toOffApplianceIndex);
            String outputResult = offAppliance(toOffAppliance, "", false);
            return new CommandResult(outputResult);
        }
    }

    private CommandResult offByLocation() {
        String outputResults = offByApplianceLoop();
        outputResults = outputResults.concat("All appliance in \"" + this.key + "\" are turned off ");
        return new CommandResult(outputResults);
    }

    private String offByApplianceLoop() {
        String outputResults = LINE;
        for (Appliance toOffAppliance : applianceList.getAllAppliance()) {
            if (toOffAppliance.getLocation().equals(this.key)) {
                outputResults = offAppliance(toOffAppliance, outputResults, true);
            }
        }
        return outputResults;
    }

    private String offAppliance(Appliance toOffAppliance, String outputResults, boolean isList) {
        boolean offResult = toOffAppliance.switchOff();
        assert toOffAppliance.getStatus().equals("OFF") : "Appliance should be already OFF";

        if (!isList) {
            if (offResult) {
                outputResults = LINE + "Switching: " + toOffAppliance + "......OFF";
            } else {
                outputResults = LINE + MESSAGE_APPLIANCE_PREVIOUSLY_OFF;
            }
        }
        return outputResults;
    }
}
