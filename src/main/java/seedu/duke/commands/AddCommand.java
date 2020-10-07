package seedu.duke.commands;

import seedu.duke.data.type.AirConditioner;
import seedu.duke.data.type.Fan;
import seedu.duke.data.type.Lights;
import seedu.duke.data.type.WaterHeater;

public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new appliance to the particular location to the SmartHomeBot. \n" + "Parameters: add l/LOCATION n/NAME w/WATTS t/TYPE_OF_APPLIANCE \n" + "Example: " + COMMAND_WORD + " l/Bedroom 1 n/Fan 1 w/50 t/Fan";

    private final String name;
    private final String location;
    private final String power;
    private final String type;

    public AddCommand(String name, String location, String power, String type) {
        this.name = name;
        this.location = location;
        this.power = power;
        this.type = type;
    }

    @Override
    public void execute() {
        if (homeLocationsList.isLocationCreated(location)) {
            switch (type.toLowerCase()) {
            case "fan":
                Fan fan = new Fan(name, location, power);
                appliances.addAppliance(fan);
                break;
            case "airconditioner":
                AirConditioner ac = new AirConditioner(name, location, power);
                appliances.addAppliance(ac);
                break;
            case "light":
                Lights light = new Lights(name, location, power);
                appliances.addAppliance(light);
                break;
            case "waterheater":
                WaterHeater waterheater = new WaterHeater(name, location, power);
                appliances.addAppliance(waterheater);
                break;
            default:
                System.out.println("Error Type is used!");
            }
        } else {
            System.out.println("Location does not exist");
        }
    }

}
