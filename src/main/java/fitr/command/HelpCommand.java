package fitr.command;

import fitr.Recommender;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

public class HelpCommand extends Command {
    public HelpCommand(String userInput) {
        this.command = userInput;
    }

    @Override
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        Ui.printHelpMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
