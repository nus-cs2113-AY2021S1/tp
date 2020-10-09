package seedu.duke.commands;

import seedu.duke.exceptions.EmptyParameterException;

import static seedu.duke.common.Messages.MESSAGE_LOCATION_NOT_EXIST;

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
    public void execute() {
        Boolean isLocationExist = homeLocationsList.isLocationCreated(this.usersEnteredLocation);
        if (isLocationExist) {
            for (int x = appliances.getAllAppliance().size() - 1; x >= 0; x--) {
                if (appliances.getAppliance(x).getLocation().equals(this.usersEnteredLocation)) {
                    appliances.removeAppliance((appliances.getAppliance(x).getName()));
                }
            }
            homeLocationsList.removeLocation(this.usersEnteredLocation);
        } else {
            ui.showToUser(MESSAGE_LOCATION_NOT_EXIST + " Nothing will be deleted.");
        }

    }

}
