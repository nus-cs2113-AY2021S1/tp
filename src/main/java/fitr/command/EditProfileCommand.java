package fitr.command;

import fitr.exercise.Recommender;
import fitr.common.Messages;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;

public class EditProfileCommand extends Command {
    private final String argument;

    public EditProfileCommand(String command, String argument) {
        this.command = command;
        this.argument = argument;
    }

    @Override
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        if (command.equalsIgnoreCase(Messages.EDIT_NAME)) {
            editName(user, argument);
        } else if (command.equalsIgnoreCase(Messages.EDIT_HEIGHT)) {
            editHeight(user, argument);
        } else if (command.equalsIgnoreCase(Messages.EDIT_WEIGHT)) {
            editWeight(user, argument);
        } else if (command.equalsIgnoreCase(Messages.EDIT_AGE)) {
            editAge(user, argument);
        } else if (command.equalsIgnoreCase(Messages.EDIT_GENDER)) {
            editGender(user, argument);
        } else if (command.equalsIgnoreCase(Messages.EDIT_FITNESS)) {
            editFitness(user, argument);
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

    private void editName(User user, String argument) {
        user.setName(argument, true);
        Ui.printCustomMessage(Messages.NAME_ECHO_HEADER + user.getName());
    }

    private void editHeight(User user, String argument) {
        user.setupHeight(argument, true);
        Ui.printCustomMessage(Messages.HEIGHT_ECHO_HEADER + String.format("%.2f", user.getHeight()));
    }

    private void editWeight(User user, String argument) {
        user.setupWeight(argument, true);
        Ui.printCustomMessage(Messages.WEIGHT_ECHO_HEADER + String.format("%.2f", user.getWeight()));
    }

    private void editAge(User user, String argument) {
        user.setupAge(argument, true);
        Ui.printCustomMessage(Messages.AGE_ECHO_HEADER + user.getAge());
    }

    private void editGender(User user, String argument) {
        user.setupGender(argument, true);
        Ui.printCustomMessage(Messages.GENDER_ECHO_HEADER + user.getGender());
    }

    private void editFitness(User user, String argument) {
        user.setupFitnessLevel(argument, true);
        Ui.printCustomMessage(Messages.FITNESS_ECHO_HEADER + user.getUserFitnessLevelString());
    }
}
