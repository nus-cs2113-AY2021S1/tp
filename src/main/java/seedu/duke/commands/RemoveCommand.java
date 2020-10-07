package seedu.duke.commands;

public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Remove the location and all the appliances in that location.\n"
            + "Parameters: l/LOCATION\n"
            + "Example: " + COMMAND_WORD
            + " l/Bedroom 1";
    private final String usersEnteredLocation;

    public RemoveCommand(String location) {
        this.usersEnteredLocation = location;
    }

    @Override
    public void execute() {
        for (int x = appliances.getAllAppliance().size() - 1; x >= 0; x--) {
            if (appliances.getAppliance(x).getLocation().equals(usersEnteredLocation)) {
                appliances.removeAppliance(x);
            }
        }
        homeLocationsList.removeLocation(usersEnteredLocation);
    }

}
