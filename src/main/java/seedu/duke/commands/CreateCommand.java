package seedu.duke.commands;

public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";
    private final String usersEnteredLocation;

    public CreateCommand(String location) {
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
