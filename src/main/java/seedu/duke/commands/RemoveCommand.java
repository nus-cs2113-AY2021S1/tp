package seedu.duke.commands;

public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";
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
