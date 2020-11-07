package fitr.command;

import fitr.exercise.Recommender;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

import java.util.logging.Logger;

public class ExitCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(ExitCommand.class.getName());

    public ExitCommand(String command) {
        assert command != null;
        this.command = command;
    }

    @Override
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        LOGGER.fine("Exiting the application.");
        Ui.printExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
