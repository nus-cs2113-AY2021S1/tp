package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.human.User;
import seedu.duke.storage.StorageManager;

public class ExitCommand extends Command {
    protected static final String NULL_STRING = "";

    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) {
        setShouldExit();
        return NULL_STRING;
    }
}
