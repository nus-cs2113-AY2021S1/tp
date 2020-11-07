package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.type.AirConditioner;
import seedu.smarthomebot.data.appliance.type.Fan;
import seedu.smarthomebot.data.appliance.type.Lights;
import seedu.smarthomebot.data.appliance.type.SmartPlug;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

import java.util.logging.Level;

import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_EXIST;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_TYPE_NOT_EXIST;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LOCATION_NOT_EXIST;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_LOCATION_CONFLICT;

//@@author zongxian-ctrl

/**
 * Represent the command for adding an appliance to the ApplianceList.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = "Add Appliance: " + COMMAND_WORD
            + " [APPLIANCE_NAME] l/[LOCATION_NAME] w/[WATTAGE] t/[TYPE_OF_APPLIANCE]";

    private final String name;
    private final String location;
    private final String wattage;
    private final String type;

    public AddCommand(String name, String location, String wattage, String type) {
        assert !name.isEmpty() : "AddCommand must not accept empty name";
        assert !location.isEmpty() : "AddCommand must not accept empty location";
        assert !wattage.isEmpty() : "AddCommand must not accept empty wattage";
        assert !type.isEmpty() : "AddCommand must not accept empty type";
        this.name = name;
        this.location = location;
        this.wattage = wattage;
        this.type = type.toLowerCase();
    }

    /**
     * Executing the AddCommand.
     */
    @Override
    public CommandResult execute() {
        try {
            switch (this.type) {
            case Fan.TYPE_WORD:
                Fan fan = new Fan(name, location, wattage, locationList);
                applianceList.addAppliance(fan);
                commandLogger.log(Level.INFO, "ADDING" + fan.toString());
                return new CommandResult("ADDING " + fan.toString() + "......ADDED!");
            case AirConditioner.TYPE_WORD:
                AirConditioner ac = new AirConditioner(name, location, wattage, locationList);
                applianceList.addAppliance(ac);
                commandLogger.log(Level.INFO, "ADDING" + ac.toString());
                return new CommandResult("ADDING " + ac.toString() + "......ADDED!");
            case Lights.TYPE_WORD:
                Lights light = new Lights(name, location, wattage, locationList);
                applianceList.addAppliance(light);
                commandLogger.log(Level.INFO, "ADDING" + light.toString());
                return new CommandResult("ADDING " + light.toString() + "......ADDED!");
            case SmartPlug.TYPE_WORD:
                SmartPlug smartPlug = new SmartPlug(name, location, wattage, locationList);
                applianceList.addAppliance(smartPlug);
                commandLogger.log(Level.INFO, "ADDING" + smartPlug.toString());
                return new CommandResult("ADDING " + smartPlug.toString() + "......ADDED!");
            default:
                commandLogger.log(Level.WARNING, MESSAGE_APPLIANCE_TYPE_NOT_EXIST);
                return new CommandResult(MESSAGE_APPLIANCE_TYPE_NOT_EXIST);
            }
        } catch (DuplicateDataException e) {
            commandLogger.log(Level.WARNING, MESSAGE_APPLIANCE_EXIST);
            return new CommandResult(MESSAGE_APPLIANCE_EXIST);
        } catch (LocationNotFoundException e) {
            commandLogger.log(Level.WARNING, MESSAGE_LOCATION_NOT_EXIST);
            return new CommandResult(MESSAGE_LOCATION_NOT_EXIST);
        } catch (InvalidApplianceNameException e) {
            commandLogger.log(Level.WARNING, MESSAGE_APPLIANCE_LOCATION_CONFLICT);
            return new CommandResult(MESSAGE_APPLIANCE_LOCATION_CONFLICT);
        }
    }
}
