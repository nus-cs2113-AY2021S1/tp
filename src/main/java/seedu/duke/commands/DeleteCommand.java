package seedu.duke.commands;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete the existing appliance by its indicated INDEX that has been added to SmartHomeBot\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD
            + " 1";
    private final String userEnteredName;

    public DeleteCommand(String name) {
        this.userEnteredName = name;
    }

    @Override
    public void execute() {
        for (int i = 0; i < appliances.getAllAppliance().size(); i++) {
            if (appliances.getAppliance(i).getName().equals(userEnteredName)) {
                appliances.removeAppliance(i);
                break;
            }
        }
    }
}
