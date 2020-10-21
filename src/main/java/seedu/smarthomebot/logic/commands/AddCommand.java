package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.model.framework.type.AirConditioner;
import seedu.smarthomebot.model.framework.type.Fan;
import seedu.smarthomebot.model.framework.type.Lights;
import seedu.smarthomebot.model.framework.type.SmartPlug;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_EXIST;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_TYPE_NOT_EXIST;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LOCATION_NOT_EXIST;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_LOCATION_CONFLICT;

public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a new appliance to the particular location to the SmartHomeBot. \n"
            + "Parameters: add [APPLIANCE_NAME] l/[LOCATION_NAME] w/[WATTAGE] t/[TYPE_OF_APPLIANCE] \n" + "Example: "
            + COMMAND_WORD + " Fan1 l/Bedroom 1 w/50 t/Fan";

    private final String name;
    private final String location;
    private final String wattage;
    private final String type;

    public AddCommand(String name, String location, String wattage, String type) {
        this.name = name;
        this.location = location;
        this.wattage = wattage;
        this.type = type;
    }

    @Override
    public CommandResult execute() {
        try {
            if (!locationList.isLocationCreated(this.location)) {
                throw new LocationNotFoundException();
            } else if (locationList.isLocationCreated(this.name)) {
                throw new InvalidApplianceNameException();
            }
            switch (type.toLowerCase()) {
            case Fan.TYPE_WORD:
                Fan fan = new Fan(name, location, wattage);
                applianceList.addAppliance(fan);
                return new CommandResult("ADDING " + fan.toString() + "......ADDED");
            case AirConditioner.TYPE_WORD:
                AirConditioner ac = new AirConditioner(name, location, wattage);
                applianceList.addAppliance(ac);
                return new CommandResult("ADDING " + ac.toString() + "......ADDED");
            case Lights.TYPE_WORD:
                Lights light = new Lights(name, location, wattage);
                applianceList.addAppliance(light);
                return new CommandResult("ADDING " + light.toString() + "......ADDED");
            case SmartPlug.TYPE_WORD:
                SmartPlug smartPlug = new SmartPlug(name, location, wattage);
                applianceList.addAppliance(smartPlug);
                return new CommandResult("ADDING " + smartPlug.toString() + "......ADDED");
            default:
                return new CommandResult(MESSAGE_APPLIANCE_TYPE_NOT_EXIST);
            }
        } catch (DuplicateDataException e) {
            return new CommandResult(MESSAGE_APPLIANCE_EXIST);

        } catch (LocationNotFoundException e) {
            return new CommandResult(MESSAGE_LOCATION_NOT_EXIST);
        } catch (InvalidApplianceNameException e) {
            return new CommandResult(MESSAGE_APPLIANCE_LOCATION_CONFLICT);
        }
    }
}
