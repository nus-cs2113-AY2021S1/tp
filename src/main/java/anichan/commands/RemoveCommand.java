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

public class RemoveCommand extends Command {
    protected static final String OUT_OF_BOUND_INDEX_ERROR = "Invalid Watchlist Index!";
    protected static final String EMPTY_WATCHLIST_ERROR = "Watchlist is empty!";

    private Integer animeIndexInWatchlist;
    private Integer animeIndex;
    private static final Logger LOGGER = AniLogger.getAniLogger(RemoveCommand.class.getName());
    
    public RemoveCommand() {
        // LOGGER.setLevel(Level.WARNING);
    }

    /**
     * Remove an anime from current watchlist.
     */
    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) throws AniException {
        Workspace activeWorkspace = user.getActiveWorkspace();
        
        removeFromWatchlist(storageManager, activeWorkspace);
        Anime anime = animeData.getAnime(animeIndex);
        String animeName = anime.getAnimeName();

        return animeName + " successfully removed from watchlist!";
    }
    
    private void removeFromWatchlist(StorageManager storageManager, Workspace activeWorkspace) throws AniException {
        Watchlist activeWatchlist = activeWorkspace.getActiveWatchlist();
        
        if (activeWatchlist.getWatchlistSize() == 0) {
            LOGGER.log(Level.WARNING, EMPTY_WATCHLIST_ERROR);
            throw new AniException(EMPTY_WATCHLIST_ERROR);
        } else if (activeWatchlist.getWatchlistSize() <= animeIndexInWatchlist - 1) {
            LOGGER.log(Level.WARNING, OUT_OF_BOUND_INDEX_ERROR);
            throw new AniException(OUT_OF_BOUND_INDEX_ERROR);
        } else if (animeIndexInWatchlist < 0) {
            LOGGER.log(Level.WARNING, OUT_OF_BOUND_INDEX_ERROR);
            throw new AniException(OUT_OF_BOUND_INDEX_ERROR);
        } 
        
        assert this.animeIndexInWatchlist >= 0 : "anime index has to be valid";
        animeIndex = activeWatchlist.getWatchlistListAnimeIndex(animeIndexInWatchlist);
        activeWatchlist.removeAnimeFromList(animeIndexInWatchlist);

        ArrayList<Watchlist> watchlistList = activeWorkspace.getWatchlistList();
        storageManager.saveWatchlistList(activeWorkspace.getName(), watchlistList);
        LOGGER.log(Level.INFO, "Successfully removed anime from active watchlist");
    }
    
    public void setWatchlistListIndex(Integer animeIndexInWatchlist) {
        this.animeIndexInWatchlist = animeIndexInWatchlist - 1;
    }
}
