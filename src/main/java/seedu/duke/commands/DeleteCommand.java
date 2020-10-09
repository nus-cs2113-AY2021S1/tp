package seedu.duke.commands;

import seedu.duke.exceptions.EmptyParameterException;

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
        Boolean isApplianceExist = appliances.isAppliance(this.userEnteredName);
        if (isApplianceExist) {
            appliances.removeAppliance(this.userEnteredName);
        } else {
            ui.showToUser(userEnteredName + " does not exist.");
        }


    }
}
