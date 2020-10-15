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

    private void feedbackAddToUser() {
        if (this.toPrint) {
            ui.showToUser("Adding " + type + ": " + name + " (" + power + "W) in " + location + ".....ADDED!");
        }
    }

    @Override
    public void execute() {
        if (homeLocationsList.isLocationCreated(location)) {
            try {
                switch (type.toLowerCase()) {
                case Fan.TYPE_WORD:
                    Fan fan = new Fan(name, location, power);
                    appliances.addAppliance(fan);
                    feedbackAddToUser();
                    break;
                case AirConditioner.TYPE_WORD:
                    AirConditioner ac = new AirConditioner(name, location, power);
                    appliances.addAppliance(ac);
                    feedbackAddToUser();
                    break;
                case Lights.TYPE_WORD:
                    Lights light = new Lights(name, location, power);
                    appliances.addAppliance(light);
                    feedbackAddToUser();
                    break;
                case WaterHeater.TYPE_WORD:
                    WaterHeater waterheater = new WaterHeater(name, location, power);
                    appliances.addAppliance(waterheater);
                    feedbackAddToUser();
                    break;
                default:
                    if (this.toPrint) {
                        ui.showToUser(MESSAGE_APPLIANCE_TYPE_NOT_EXIST);
                    }
                }
            } catch (InvalidAdditionOfAppliance e) {
                if (this.toPrint) {
                    ui.showToUser(MESSAGE_APPLIANCE_EXIST);
                }
            }

        } else {
            if (this.toPrint) {
                ui.showToUser(MESSAGE_LOCATION_NOT_EXIST);
            }
        }

    }


}
