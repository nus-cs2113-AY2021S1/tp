package seedu.duke.command;

import seedu.duke.anime.Anime;
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
    protected static final String DUPLICATE_ANIME_ERROR = "Anime is already in this watchlist!";
    protected static final String OUT_OF_BOUND_INDEX_ERROR = "Anime ID is invalid!";

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
        
        Anime anime = animeData.getAnimeByID(animeIndex);
        String animeName = anime.getAnimeName();

        return animeName + " added to watchlist!";
    }
    
    public void addToWatchlist(StorageManager storageManager, Workspace activeWorkspace) throws AniException {
        Watchlist activeWatchlist = activeWorkspace.getActiveWatchlist();
        ArrayList<Watchlist> watchlistList = activeWorkspace.getWatchlistList();
        ArrayList<Integer> activeWatchlistList = activeWatchlist.getAnimeList();
        
        if (activeWatchlistList.contains(animeIndex)) {
            throw new AniException(DUPLICATE_ANIME_ERROR);
        } else if (animeIndex < 0) {
            throw new AniException(OUT_OF_BOUND_INDEX_ERROR);
        }
        
        activeWatchlist.addAnimeToList(animeIndex);

        storageManager.saveWatchlistList(activeWorkspace.getName(), watchlistList);
        LOGGER.log(Level.INFO, "Successfully added and stored anime into active watchlist");
    }
       
    public void setAnimeIndex(Integer animeIndex) {
        this.animeIndex = animeIndex - 1;
    }
}
