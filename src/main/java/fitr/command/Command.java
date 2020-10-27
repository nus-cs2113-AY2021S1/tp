package fitr.command;

import fitr.Recommender;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
import fitr.user.User;

public abstract class Command {
    protected String command;

    public abstract void execute(ListManager listManager, StorageManager storageManager,
                                 User user, Recommender recommender);

    public abstract boolean isExit();
}
