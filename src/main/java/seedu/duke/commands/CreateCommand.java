package seedu.duke.commands;

import seedu.duke.EmptyParameterException;

public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new location in SmartHomeBot\n"
            + "Parameters: l/LOCATION\n"
            + "Example: " + COMMAND_WORD
            + " l/Bedroom 1";
    private final String usersEnteredLocation;

    public CreateCommand(String location) throws EmptyParameterException {
        if (location.isEmpty()) {
            throw new EmptyParameterException();
        }
        this.usersEnteredLocation = location;
    }

    @Override
    public void execute() {
        if (!homeLocationsList.isLocationCreated(usersEnteredLocation)) {
            homeLocationsList.addLocation(usersEnteredLocation);
        } else {
            System.out.println("Location already exist");
        }
    }

}
