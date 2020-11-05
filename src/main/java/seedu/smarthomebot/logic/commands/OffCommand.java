package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.commons.exceptions.ApplianceNotFoundException;
import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;
import seedu.smarthomebot.logic.commands.exceptions.NoApplianceInLocationException;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_PREVIOUSLY_OFF;

//@@Ang_Cheng_Jun
/**
 * Represent the command to turn off appliance.
 */
public class OffCommand extends Command {

    public static final String COMMAND_WORD = "off";
    public static final String MESSAGE_USAGE = "Switch OFF Appliance: \n\t\t a. " + COMMAND_WORD
            + " [APPLIANCE_NAME] \n\t\t b. " + COMMAND_WORD + " [LOCATION_NAME]";
    private static final String APPLIANCE_TYPE = "appliance";
    private static final String LOCATION_TYPE = "location";
    private final String argument;

    public OffCommand(String argument) {
        assert argument.isEmpty() != true : "InvalidCommand must not accept empty arguments";
        this.argument = argument;
    }

    @Override
    public CommandResult execute() {
        try {
            String type = APPLIANCE_TYPE;
            ArrayList<Appliance> filterApplianceList =
                    (ArrayList<Appliance>) applianceList.getAllAppliance().stream()
                            .filter((s) -> s.getLocation().equals(this.argument))
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
        } catch (ApplianceNotFoundException e) {
            return new CommandResult(MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST);
        } catch (NoApplianceInLocationException e) {
            return new CommandResult("There is no appliance in \"" + argument + "\".");
        }
    }

    private CommandResult offByAppliance() throws ApplianceNotFoundException, NoApplianceInLocationException {
        int toOffApplianceIndex = getApplianceToOffIndex();
        Appliance toOffAppliance = applianceList.getAppliance(toOffApplianceIndex);
        String outputResult = offAppliance(toOffAppliance, false);
        return new CommandResult(outputResult);
    }

    private int getApplianceToOffIndex() throws ApplianceNotFoundException, NoApplianceInLocationException {
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

    private CommandResult offByLocation() {
        offByApplianceLoop();
        String outputResult = "All Appliances in \"" + this.argument + "\" are turned off ";
        return new CommandResult(outputResult);
    }

    private void offByApplianceLoop() {
        for (Appliance toOffAppliance : applianceList.getAllAppliance()) {
            if (toOffAppliance.getLocation().equals(this.argument)) {
                offAppliance(toOffAppliance, true);
            }
        }
    }

    private String offAppliance(Appliance toOffAppliance, boolean isList) {
        boolean offResult = toOffAppliance.switchOff();
        String outputResult = "";
        assert toOffAppliance.getStatus().equals("OFF") : "Appliance should be already OFF";

        if (!isList) {
            if (offResult) {
                outputResult = "Switching: " + toOffAppliance + "......OFF";
            } else {
                outputResult = toOffAppliance.getName() + MESSAGE_APPLIANCE_PREVIOUSLY_OFF;
            }
        }
        return outputResult;
    }
}
