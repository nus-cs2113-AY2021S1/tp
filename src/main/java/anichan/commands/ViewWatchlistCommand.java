package anichan.commands;

import anichan.anime.Anime;
import anichan.anime.AnimeData;
import anichan.exception.AniException;
import anichan.human.User;
import anichan.human.Workspace;
import anichan.logger.AniLogger;
import anichan.storage.StorageManager;
import anichan.watchlist.Watchlist;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command to view all anime in a certain Watchlist.
 */
public class ViewWatchlistCommand extends Command {
    protected static final String OUT_OF_BOUND_INDEX_ERROR = "Watchlist ID is invalid!";
    protected static final String EMPTY_WATCHLIST_ERROR = "There are no watchlists in your workspace!";
    
    private Integer watchlistIndex;
    private static final Logger LOGGER = AniLogger.getAniLogger(ViewWatchlistCommand.class.getName());

    /**
     * Creates a new instance of ViewWatchlistCommand.
     */
    public ViewWatchlistCommand() {
        // LOGGER.setLevel(Level.WARNING);
    }

    /**
     * Returns a string representation of all the anime in the
     * specified Watchlist.
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
        ArrayList<Watchlist> watchlistList = activeWorkspace.getWatchlistList();
        int watchlistSize = watchlistList.size();

        if (watchlistSize == 0) {
            LOGGER.log(Level.WARNING, EMPTY_WATCHLIST_ERROR);
            throw new AniException(EMPTY_WATCHLIST_ERROR);
        } else if (watchlistIndex < 0) {
            LOGGER.log(Level.WARNING, OUT_OF_BOUND_INDEX_ERROR);
            throw new AniException(OUT_OF_BOUND_INDEX_ERROR);
        } else if (watchlistIndex >= watchlistSize) {
            LOGGER.log(Level.WARNING, OUT_OF_BOUND_INDEX_ERROR);
            throw new AniException(OUT_OF_BOUND_INDEX_ERROR);
        }
        
        String result = buildAnimeInWatchlist(animeData, watchlistList);
        
        return result;
    }

    /**
     * Builds and returns a string representation of all the anime
     * in the specific Watchlist.
     * 
     * @param animeData used to retrieve anime information
     * @param watchlistList list of all the Watchlists
     * @return a string representation of all the anime in the specific Watchlist
     */
    private String buildAnimeInWatchlist(AnimeData animeData, ArrayList<Watchlist> watchlistList) {
        Watchlist selectedWatchlist = watchlistList.get(watchlistIndex);
        ArrayList<Integer> animeInWatchlist = selectedWatchlist.getAnimeList();
        String selectedWatchlistName = selectedWatchlist.getName();
        
        StringBuilder sbWatchlistAnime = new StringBuilder();
        sbWatchlistAnime.append("Here are the anime in ").append(selectedWatchlistName).append(" watchlist:");
        for (int i = 0; i < animeInWatchlist.size(); i++) {
            Integer animeIndex = animeInWatchlist.get(i);
            Anime anime = animeData.getAnime(animeIndex);
            String animeName = anime.getAnimeName();

            sbWatchlistAnime.append(System.lineSeparator());
            sbWatchlistAnime.append("\t").append(i + 1).append(". ");
            sbWatchlistAnime.append(animeName);
        }
        
        return sbWatchlistAnime.toString();
    }

    public void setWatchlistIndex(Integer watchlistIndex) {
        this.watchlistIndex = watchlistIndex - 1;
    }
}
