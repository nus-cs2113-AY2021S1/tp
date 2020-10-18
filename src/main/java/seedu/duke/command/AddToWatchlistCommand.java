package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.Workspace;
import seedu.duke.human.User;
import seedu.duke.storage.StorageManager;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddToWatchlistCommand extends Command {

    private Integer animeIndex;
    private static final Logger LOGGER = Logger.getLogger(AddToWatchlistCommand.class.getName());

    public AddToWatchlistCommand() {
        LOGGER.setLevel(Level.WARNING);
    }

    /**
     * Adds an anime to current watchlist.
     */
    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) throws AniException {
        Workspace activeWorkspace = user.getActiveWorkspace();
        addToWatchlist(storageManager, activeWorkspace);

        return "Anime added to watchlist!";
    }
    
    public void addToWatchlist(StorageManager storageManager, Workspace activeWorkspace) throws AniException {
        Watchlist activeWatchlist = activeWorkspace.getActiveWatchlist();
        ArrayList<Watchlist> watchlistList = activeWorkspace.getWatchlistList();
        
        activeWatchlist.addAnimeToList(animeIndex);

        storageManager.saveWatchlistList(activeWorkspace.getName(), watchlistList);
        LOGGER.log(Level.INFO, "Successfully added and stored anime into active watchlist");
    }
       
    public void setAnimeIndex(Integer animeIndex) {
        this.animeIndex = animeIndex;
    }
}
