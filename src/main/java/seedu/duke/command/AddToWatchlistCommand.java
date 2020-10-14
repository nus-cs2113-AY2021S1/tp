package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.User;
import seedu.duke.human.UserManagement;
import seedu.duke.storage.Storage;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;

public class AddToWatchlistCommand extends Command {

    public AddToWatchlistCommand(String description) {
        this.description = description;
    }

    /**
     * Adds an anime to current watchlist.
     */
    @Override
    public String execute(AnimeData animeData, UserManagement userManagement) throws AniException {
        Storage storage = userManagement.getStorage();
        User activeUser = userManagement.getActiveUser();
        Watchlist activeWatchlist = activeUser.getActiveWatchlist();
        ArrayList<Watchlist> activeWatchlistList = activeUser.getWatchlistList();

        int activeWatchlistIndex = activeWatchlistList.indexOf(activeWatchlist);
        String[] descriptionSplit = description.split(" ", 2);
        try {
            String commandOption = descriptionSplit[0];
            String animeName = descriptionSplit[1];
            if (commandOption.equals("-a") && animeName != null && !animeName.trim().isEmpty()) {
                activeWatchlist.addAnimeToList(animeName);
                activeWatchlistList.set(activeWatchlistIndex, activeWatchlist);
            } else {
                throw new AniException("addToWatchlist");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AniException("addToWatchlist");
        }

        storage.saveWatchlist(activeWatchlistList);
        return "Anime added to watchlist";
    }
}
