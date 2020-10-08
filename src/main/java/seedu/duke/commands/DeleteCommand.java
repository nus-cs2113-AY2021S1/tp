package seedu.duke.commands;

import seedu.duke.EmptyParameterException;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete the existing appliance by its indicated NAME that has been added to SmartHomeBot\n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD
            + " Fan 1";
    private final String userEnteredName;

    public DeleteCommand(String name) throws EmptyParameterException {
        if (name.isEmpty()) {
            throw new EmptyParameterException();
        }
        this.userEnteredName = name;
    }

    @Override
    public void execute() {
        for (int i = 0; i < appliances.getAllAppliance().size(); i++) {
            if (appliances.getAppliance(i).getName().equals(userEnteredName)) {
                appliances.removeAppliance(i);
                return;
            }
        }
        ui.showToUser(userEnteredName + " does not exist.");
    }
}
