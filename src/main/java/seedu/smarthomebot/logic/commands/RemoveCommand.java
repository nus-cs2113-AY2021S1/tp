package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.commons.exceptions.ApplianceNotFoundException;
import seedu.smarthomebot.commons.exceptions.InvalidLocationException;

import java.util.logging.Level;

import static seedu.smarthomebot.commons.Messages.LINE;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LOCATION_NOT_EXIST;

//@@author zongxian-ctrl

/**
 * Represent the command for removing a location in the LocationList.
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";
    public static final String MESSAGE_USAGE = "Remove location: " + COMMAND_WORD
            + " [LOCATION_NAME]";
    private final String userEnteredLocation;

    /**
     * Constructor for OnCommand.
     *
     * @param location name of Location to be removed from LocationList.
     */
    public RemoveCommand(String location) {
        assert location.isEmpty() != true : "RemoveCommand must not accept empty location";
        this.userEnteredLocation = location;
    }

    /**
     * Executing the RemoveCommand.
     */
    @Override
    public CommandResult execute() {
        try {
            locationList.removeLocation(userEnteredLocation);
            applianceList.deleteByLocation(userEnteredLocation);
            commandLogger.log(Level.INFO, "Removed location " + userEnteredLocation);
            return new CommandResult("Removing LOCATION \"" + userEnteredLocation + "\"......REMOVED!");
        } catch (InvalidLocationException e) {
            commandLogger.log(Level.WARNING, MESSAGE_LOCATION_NOT_EXIST);
            return new CommandResult(MESSAGE_LOCATION_NOT_EXIST + " Nothing will be deleted.");
        } catch (ApplianceNotFoundException e) {
            commandLogger.log(Level.WARNING, MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST);
            return new CommandResult(MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST + " Nothing will be deleted.");
        }
    }
}
