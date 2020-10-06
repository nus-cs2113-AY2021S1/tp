package seedu.duke.commands;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
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
