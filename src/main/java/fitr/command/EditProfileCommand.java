package fitr.command;

import fitr.Recommender;
import fitr.common.Messages;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;

public class EditProfileCommand extends Command {

    public EditProfileCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        if (command.equalsIgnoreCase(Messages.EDIT_NAME)) {
            editName(user);
        } else if (command.equalsIgnoreCase(Messages.EDIT_HEIGHT)) {
            editHeight(user);
        } else if (command.equalsIgnoreCase(Messages.EDIT_WEIGHT)) {
            editWeight(user);
        } else if (command.equalsIgnoreCase(Messages.EDIT_AGE)) {
            editAge(user);
        } else if (command.equalsIgnoreCase(Messages.EDIT_GENDER)) {
            editGender(user);
        } else if (command.equalsIgnoreCase(Messages.EDIT_FITNESS)) {
            editFitness(user);
        } else {
            Ui.printInvalidCommandError();
        }

        try {
            storageManager.writeUserProfile(user);
        } catch (IOException e) {
            Ui.printCustomMessage(Messages.MISSING_FILE);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private void editName(User user) {
        Ui.printCustomMessage(Messages.EDIT_NAME_HEADER);
        user.setName();
        Ui.printCustomMessage(Messages.NAME_ECHO_HEADER + user.getName());
    }

    private void editHeight(User user) {
        Ui.printCustomMessage(Messages.EDIT_HEIGHT_HEADER);
        user.setupHeight();
        Ui.printCustomMessage(Messages.HEIGHT_ECHO_HEADER + user.getHeight());
    }

    private void editWeight(User user) {
        Ui.printCustomMessage(Messages.EDIT_WEIGHT_HEADER);
        user.setupWeight();
        Ui.printCustomMessage(Messages.WEIGHT_ECHO_HEADER + user.getWeight());
    }

    private void editAge(User user) {
        Ui.printCustomMessage(Messages.EDIT_AGE_HEADER);
        user.setupAge();
        Ui.printCustomMessage(Messages.AGE_ECHO_HEADER + user.getAge());
    }

    private void editGender(User user) {
        Ui.printCustomMessage(Messages.EDIT_GENDER_HEADER);
        user.setupGender();
        Ui.printCustomMessage(Messages.GENDER_ECHO_HEADER + user.getGender());
    }

    private void editFitness(User user) {
        Ui.printCustomMessage(Messages.INPUT_FITNESS_LEVEL);
        user.setupFitnessLevel();
        Ui.printCustomMessage(Messages.FITNESS_ECHO_HEADER + user.getUserFitnessLevelString());
    }
}
