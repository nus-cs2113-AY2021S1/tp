package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.human.UserManagement;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;

public class ExitCommand extends Command {
    protected static final String NULL_STRING = "";

    @Override
    public String execute(AnimeData animeData, ArrayList<Watchlist> activeWatchlistList, Watchlist activeWatchlist,
                          UserManagement userManagement) {
        setShouldExit();
        return NULL_STRING;
    }
}
