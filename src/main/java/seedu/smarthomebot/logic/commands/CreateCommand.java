package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.commons.exceptions.DuplicateDataException;

public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";
    public static final String MESSAGE_USAGE = "Create location: " + COMMAND_WORD
            + " [LOCATION_NAME]";
    private static final String MESSAGE_LOCATION_EXIST = "Location already exist";
    private final String userEnteredLocation;

    public CreateCommand(String location) {
        assert location.isEmpty() != true : "CreateCommand must not accept empty parameter";
        this.userEnteredLocation = location;
    }

    @Override
    public CommandResult execute() {
        try {
            locationList.addLocation(userEnteredLocation);
            return new CommandResult("Creating Location \"" + userEnteredLocation + "\".....CREATED!");
        } catch (DuplicateDataException e) {
            return new CommandResult(MESSAGE_LOCATION_EXIST);
        }
    }
}
