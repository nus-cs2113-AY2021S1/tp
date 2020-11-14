package anichan.commands;

import anichan.human.User;
import anichan.anime.AnimeData;
import anichan.storage.StorageManager;

//@@author michaeldinata
/**
 * Represents the command to exit the program.
 */
public class ExitCommand extends Command {
    protected static final String NULL_STRING = "";

    /**
     * Exits the program.
     *
     * @param animeData used to retrieve anime information
     * @param storageManager used to save or read AniChan data
     * @param user used to modify user data
     * @return result after executing the command
     */
    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) {
        setShouldExit();
        return NULL_STRING;
    }
}
