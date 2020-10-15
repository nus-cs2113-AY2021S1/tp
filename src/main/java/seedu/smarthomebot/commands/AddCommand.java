package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.AirConditioner;
import seedu.smarthomebot.data.Fan;
import seedu.smarthomebot.data.Lights;
import seedu.smarthomebot.data.WaterHeater;
import seedu.smarthomebot.exceptions.InvalidAdditionOfAppliance;

import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_EXIST;
import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_TYPE_NOT_EXIST;
import static seedu.smarthomebot.common.Messages.MESSAGE_LOCATION_NOT_EXIST;


public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a new appliance to the particular location to the SmartHomeBot. \n"
            + "Parameters: add NAME l/LOCATION w/WATTS t/TYPE_OF_APPLIANCE \n" + "Example: "
            + COMMAND_WORD + " Fan1 l/Bedroom 1 w/50 t/Fan";

    private final String name;
    private final String location;
    private final String power;
    private final String type;
    private final boolean toPrint;

    public AddCommand(String name, String location, String power, String type, Boolean toPrint) {
        this.name = name;
        this.location = location;
        this.power = power;
        this.type = type;
        this.toPrint = toPrint;
    }

    private String feedbackAddToUser() {
        if (this.toPrint) {
            return ("Adding " + type + ": " + name + " (" + power + "W) in " + location + ".....ADDED!");
        } else {
            return null;
        }
    }

    @Override
    public CommandResult execute() {
        if (locationList.isLocationCreated(location)) {
            try {
                switch (type.toLowerCase()) {
                case Fan.TYPE_WORD:
                    Fan fan = new Fan(name, location, power);
                    applianceList.addAppliance(fan);
                    return new CommandResult(feedbackAddToUser());
                case AirConditioner.TYPE_WORD:
                    AirConditioner ac = new AirConditioner(name, location, power);
                    applianceList.addAppliance(ac);
                    return new CommandResult(feedbackAddToUser());
                case Lights.TYPE_WORD:
                    Lights light = new Lights(name, location, power);
                    applianceList.addAppliance(light);
                    return new CommandResult(feedbackAddToUser());
                case WaterHeater.TYPE_WORD:
                    WaterHeater waterheater = new WaterHeater(name, location, power);
                    applianceList.addAppliance(waterheater);
                    return new CommandResult(feedbackAddToUser());
                default:
                    if (this.toPrint) {
                        return new CommandResult(MESSAGE_APPLIANCE_TYPE_NOT_EXIST);
                    }
                }
            } catch (InvalidAdditionOfAppliance e) {
                if (this.toPrint) {
                    return new CommandResult(MESSAGE_APPLIANCE_EXIST);
                }
            }

        } else {
            if (this.toPrint) {
                return new CommandResult(MESSAGE_LOCATION_NOT_EXIST);
            }
        }
        return null;
    }

}
