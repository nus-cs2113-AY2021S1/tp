package seedu.smarthomebot.commands;

import seedu.smarthomebot.exceptions.DuplicateDataException;


public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new location in SmartHomeBot\n"
            + "Parameters: LOCATION\n"
            + "Example: " + COMMAND_WORD
            + " Bedroom 1";
    private static final String MESSAGE_LOCATION_EXIST = "Location already exist";
    private final String usersEnteredLocation;

    public CreateCommand(String location) {
        this.usersEnteredLocation = location;
    }

    @Override
    public CommandResult execute() {
        try {
            locationList.addLocation(usersEnteredLocation);
            return new CommandResult("Creating Location \"" + usersEnteredLocation + "\".....CREATED!");
        } catch (DuplicateDataException e) {
            return new CommandResult(MESSAGE_LOCATION_EXIST);
        }
    }
}
