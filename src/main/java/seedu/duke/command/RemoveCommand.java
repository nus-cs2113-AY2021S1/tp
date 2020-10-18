package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.User;
import seedu.duke.human.Workspace;
import seedu.duke.storage.StorageManager;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoveCommand extends Command {
    protected static final String OUT_OF_BOUND_INDEX_ERROR = "Invalid Watchlist Index!";
    protected static final String EMPTY_WATCHLIST_ERROR = "Watchlist is empty!";

    private Integer watchlistListIndex;
    private static final Logger LOGGER = Logger.getLogger(AddToWatchlistCommand.class.getName());
    
    public RemoveCommand() {
        LOGGER.setLevel(Level.WARNING);
    }

    /**
     * Remove an anime from current watchlist.
     */
    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) throws AniException {
        Workspace activeWorkspace = user.getActiveWorkspace();

        removeFromWatchlist(storageManager, activeWorkspace);

        return "Anime successfully removed from watchlist!";
    }
    
    private void removeFromWatchlist(StorageManager storageManager, Workspace activeWorkspace) throws AniException {
        Watchlist activeWatchlist = activeWorkspace.getActiveWatchlist();
        ArrayList<Watchlist> watchlistList = activeWorkspace.getWatchlistList();
        
        if (activeWatchlist.getWatchlistSize() == 0) {
            throw new AniException(EMPTY_WATCHLIST_ERROR);
        } else if (activeWatchlist.getWatchlistSize() <= watchlistListIndex - 1) {
            throw new AniException(OUT_OF_BOUND_INDEX_ERROR);
        } else if (watchlistListIndex < 0) {
            throw new AniException(OUT_OF_BOUND_INDEX_ERROR);
        } 
        
        assert this.watchlistListIndex >= 0 : "Watchlist index has to be valid";
        activeWatchlist.removeAnimeFromList(watchlistListIndex);

        storageManager.saveWatchlistList(activeWorkspace.getName(), watchlistList);
        LOGGER.log(Level.INFO, "Successfully removed anime from active watchlist");
    }
    
    public void setWatchlistListIndex(Integer watchlistListIndex) {
        this.watchlistListIndex = watchlistListIndex - 1;
    }
}