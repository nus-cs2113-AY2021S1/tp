package seedu.duke.storage;

import seedu.duke.human.Gender;
import seedu.duke.human.User;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;

public class Encoder {
    private static final String FILE_LINE_DELIMITER = " | ";

    public String encodeUser(User user) throws NullPointerException {
        String name = user.getName().trim();
        String birthDate = user.getDobString().trim();
        Gender gender = user.getGender();

        return name + FILE_LINE_DELIMITER + birthDate + FILE_LINE_DELIMITER + gender;
    }

    public String encodeWatchlist(ArrayList<Watchlist> watchlists) throws NullPointerException {
        StringBuilder sbWatchlist = new StringBuilder();
        for (Watchlist watchlist : watchlists) {
            sbWatchlist.append(watchlist.getName());
            sbWatchlist.append(FILE_LINE_DELIMITER);
            sbWatchlist.append(watchlist.getAnimeList().toString());
            sbWatchlist.append(System.lineSeparator());
        }

        return sbWatchlist.toString();
    }
}
