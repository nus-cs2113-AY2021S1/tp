package fitr.command;

import fitr.exercise.Recommender;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

public class InvalidCommand extends Command {

    public InvalidCommand(String userInput) {
        this.command = userInput;
    }

    @Override
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        Ui.printFormatError(command);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
