package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.User;
import seedu.duke.human.Workspace;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageManager;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoveCommand extends Command {
    private static final String REMOVE_OPTION = "-d";

    private String option;
    private String animeIndex = "";
    private static final Logger LOGGER = Logger.getLogger(AddToWatchlistCommand.class.getName());
    
    public RemoveCommand(String description) {
        LOGGER.setLevel(Level.WARNING);
        String[] descriptionSplit = description.split(" ", 2);

        option = descriptionSplit[0];
        if (descriptionSplit.length == 2) {
            animeIndex = descriptionSplit[1];
        }
    }

    /**
     * Remove an anime from current watchlist.
     */
    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) throws AniException {
        Workspace activeWorkspace = user.getActiveWorkspace();

        if (!option.equals(REMOVE_OPTION)) {
            LOGGER.log(Level.WARNING, "Option type given is wrong");
            throw new AniException("Remove command only accepts the option: \"-d\".");
        }
        assert option.equals("-d") == true : "option type should have been \"-d\".";
        removeFromWatchlist(storageManager, activeWorkspace);

        return "Anime successfully removed from watchlist!";
    }
    
    private void removeFromWatchlist(StorageManager storageManager, Workspace activeWorkspace) throws AniException {
        if (animeIndex == null || animeIndex.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Anime ID is empty, exception thrown");
            throw new AniException("Anime ID cannot be empty.");
        }
        
        Watchlist activeWatchlist = activeWorkspace.getActiveWatchlist();
        activeWatchlist.removeAnimeFromList(animeIndex);
        ArrayList<Watchlist> watchlistList = activeWorkspace.getWatchlistList();

        storageManager.saveWatchlistList(activeWorkspace.getName(), watchlistList);
        LOGGER.log(Level.INFO, "Successfully removed anime from active watchlist");
    }
}