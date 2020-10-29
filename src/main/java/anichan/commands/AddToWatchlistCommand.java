package anichan.commands;

import anichan.human.User;
import anichan.human.Workspace;
import anichan.logger.AniLogger;
import anichan.watchlist.Watchlist;
import anichan.anime.Anime;
import anichan.anime.AnimeData;
import anichan.exception.AniException;
import anichan.storage.StorageManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author michaeldinata
/**
 * Represents the command to add an anime to active Watchlist.
 */
public class AddToWatchlistCommand extends Command {
    protected static final String DUPLICATE_ANIME_ERROR = "Anime is already in this watchlist!";
    protected static final String OUT_OF_BOUND_INDEX_ERROR = "Anime ID is invalid!";

    private Integer animeIndex;
    private static final Logger LOGGER = AniLogger.getAniLogger(AddToWatchlistCommand.class.getName());

    /**
     * Creates a new instance of AddToWatchlistCommand.
     */
    public AddToWatchlistCommand() {
        // LOGGER.setLevel(Level.WARNING);
    }

    /**
     * Executes addition of anime into active Watchlist.
     * 
     * @param animeData used to retrieve anime information
     * @param storageManager used to save or read AniChan data
     * @param user used to modify user data
     * @return result after executing the command
     * @throws AniException when an error occurred while executing the command
     */
    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) throws AniException {
        Workspace activeWorkspace = user.getActiveWorkspace();
        addToWatchlist(animeData, storageManager, activeWorkspace);
        
        Anime anime = animeData.getAnime(animeIndex);
        String animeName = anime.getAnimeName();

        return animeName + " added to watchlist!";
    }

    /**
     * Adds selected anime to active Watchlist.
     *
     * @param animeData used to retrieve anime information
     * @param storageManager used to save watchlist data
     * @param activeWorkspace used to update watchlist list and save watchlist data to correct folder
     * @throws AniException when an error occurred while creating the watchlist
     */
    public void addToWatchlist(AnimeData animeData, StorageManager storageManager, 
                               Workspace activeWorkspace) throws AniException {
        Watchlist activeWatchlist = activeWorkspace.getActiveWatchlist();
        ArrayList<Integer> activeWatchlistList = activeWatchlist.getAnimeList();
        int indexSize = animeData.getSize();
        
        if (activeWatchlistList.contains(animeIndex)) {
            LOGGER.log(Level.WARNING, DUPLICATE_ANIME_ERROR);
            throw new AniException(DUPLICATE_ANIME_ERROR);
        } else if (animeIndex < 0) {
            LOGGER.log(Level.WARNING, OUT_OF_BOUND_INDEX_ERROR);
            throw new AniException(OUT_OF_BOUND_INDEX_ERROR);
        } else if (animeIndex >= indexSize) {
            LOGGER.log(Level.WARNING, OUT_OF_BOUND_INDEX_ERROR);
            throw new AniException(OUT_OF_BOUND_INDEX_ERROR);
        }

        assert this.animeIndex >= 0 : "Anime index has to be valid";
        activeWatchlist.addAnimeToList(animeIndex);

        ArrayList<Watchlist> watchlistList = activeWorkspace.getWatchlistList();
        storageManager.saveWatchlistList(activeWorkspace.getName(), watchlistList);
        LOGGER.log(Level.INFO, "Successfully added and stored anime into active watchlist");
    }

    /**
     * Sets the anime index to be added into the active Watchlist.
     * 
     * @param animeIndex the specified anime index to be added
     */
    public void setAnimeIndex(Integer animeIndex) {
        this.animeIndex = animeIndex - 1;
    }
}
