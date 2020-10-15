package seedu.smarthomebot.commands;

import seedu.smarthomebot.exceptions.EmptyParameterException;

import static seedu.smarthomebot.common.Messages.MESSAGE_LOCATION_NOT_EXIST;

public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Remove the indicated LOCATION and all the appliances in that LOCATION.\n"
            + "Parameters: LOCATION \n"
            + "Example: " + COMMAND_WORD
            + " Bedroom 1";
    private final String usersEnteredLocation;

    public RemoveCommand(String location) throws EmptyParameterException {
        if (location.isEmpty()) {
            throw new EmptyParameterException();
        }
        this.usersEnteredLocation = location;
    }

    @Override
    public CommandResult execute() {
        Boolean isLocationExist = locationList.isLocationCreated(this.usersEnteredLocation);
        if (isLocationExist) {
            for (int x = applianceList.getAllAppliance().size() - 1; x >= 0; x--) {
                if (applianceList.getAppliance(x).getLocation().equals(this.usersEnteredLocation)) {
                    applianceList.removeAppliance((applianceList.getAppliance(x).getName()));
                }
            }
            locationList.removeLocation(this.usersEnteredLocation);
            return null;
        } else {
            // throw new IndexOutOfBoundsException();
            return new CommandResult(MESSAGE_LOCATION_NOT_EXIST + " Nothing will be deleted.");
        }
    }
}
