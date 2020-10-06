package seedu.duke.commands;

import seedu.duke.data.type.AirConditioner;
import seedu.duke.data.type.Fan;
import seedu.duke.data.type.Lights;
import seedu.duke.data.type.WaterHeater;

public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    private final String name;
    private final String location;
    private final String type;

    public AddCommand(String name, String location, String type) {
        this.name = name;
        this.location = location;
        this.type = type;
    }

    @Override
    public void execute() {
        if (homeLocationsList.isLocationCreated(location)) {
            switch (type.toLowerCase()) {
            case "fan":
                Fan fan = new Fan(name, location);
                appliances.addAppliance(fan);
                break;
            case "airconditioner":
                AirConditioner ac = new AirConditioner(name, location);
                appliances.addAppliance(ac);
                break;
            case "light":
                Lights light = new Lights(name, location);
                appliances.addAppliance(light);
                break;
            case "waterheater":
                WaterHeater waterheater = new WaterHeater(name, location);
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
