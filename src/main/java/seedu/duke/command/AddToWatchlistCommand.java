package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.exception.AniException;
import seedu.duke.human.UserManagement;
import seedu.duke.storage.Storage;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;

public class AddToWatchlistCommand extends Command {
    private final String ADD_OPTION = "-a";
    
    private String option;
    private String animeName = "";

    public AddToWatchlistCommand(String description) {
        String[] descriptionSplit = description.split(" ", 2);
        
        option = descriptionSplit[0];
        if(descriptionSplit.length == 2) {
            animeName = descriptionSplit[1];
        }
    }

    /**
     * Adds an anime to current watchlist.
     */
    @Override
    public String execute(AnimeData animeData, ArrayList<Watchlist> activeWatchlistList, Watchlist activeWatchlist,
                          UserManagement userManagement) throws AniException {
        if (!option.equals(ADD_OPTION)) {
            throw new AniException("Watchlist command only accepts the option: \"-a\".");
        }
        
        addToWatchlist(userManagement.getStorage(), activeWatchlistList, activeWatchlist);

        return "Anime added to watchlist";
    }
    
    public void addToWatchlist(Storage storage, ArrayList<Watchlist> activeWatchlistList, 
                               Watchlist activeWatchlist) throws AniException { 
        if(animeName == null || animeName.trim().isEmpty()) {
            throw new AniException("Anime name cannot be empty.");
        }
        
        activeWatchlist.addAnimeToList(animeName);
        storage.saveWatchlist(activeWatchlistList);
    }
}
