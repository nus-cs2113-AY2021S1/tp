package seedu.duke.commands;

import seedu.duke.exceptions.EmptyParameterException;
import seedu.duke.exceptions.InvalidAddtionOfLocation;

import static seedu.duke.common.Messages.MESSAGE_REPEATED_LOCATION;

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
        try{
            homeLocationsList.addLocation(usersEnteredLocation);
        } catch (InvalidAddtionOfLocation e){
            ui.showToUser(MESSAGE_REPEATED_LOCATION);
        }

    }

}
