package seedu.duke.storage;

import seedu.duke.exception.AniException;
import seedu.duke.human.User;
import seedu.duke.ui.Ui;
import seedu.duke.watchlist.Watchlist;

import java.text.ParseException;
import java.util.ArrayList;

public class Decoder {
    private static final String FILE_LINE_DELIMITER = " \\| ";

    public User decodeUserString(Ui ui, String fileString) {
        User user = null;
        try {
            String[] fileStringParts = fileString.split(FILE_LINE_DELIMITER);
            String name = fileStringParts[0].trim();
            String birthDate = fileStringParts[1].trim();
            String gender = fileStringParts[2].trim();
            user = new User(name, birthDate, gender);
            ui.printMessage("AniChan successfully loaded the user.");
        } catch (AniException | ParseException | IndexOutOfBoundsException exception) {
            ui.printErrorMessage("AniChan could not load the saved user.");
        }

        return user;
    }

    public ArrayList<Watchlist> decodeWatchlistString(Ui ui, String fileString) {
        ArrayList<Watchlist> watchlists = new ArrayList<>();
        String[] fileStringLines = fileString.split(System.lineSeparator());

        boolean hasCorruptedWatchlist = false;
        for (String line : fileStringLines) {
            Watchlist savedWatchList = createWatchlistObject(line);
            if (savedWatchList == null) {
                hasCorruptedWatchlist = true;
                continue;
            }

            watchlists.add(savedWatchList);
        }

        if (hasCorruptedWatchlist) {
            ui.printErrorMessage("AniChan could not load some of the saved watchlist(s).");
        } else {
            ui.printMessage("AniChan successfully loaded all of the saved watchlist(s).");
        }
        return watchlists;
    }

    private Watchlist createWatchlistObject(String line) {
        String[] lineSplit = line.split(FILE_LINE_DELIMITER, 2);
        if (!isValidWatchlistString(lineSplit)) {
            return null;
        }

        String watchlistName = lineSplit[0];
        String animeListString = lineSplit[1].substring(1, lineSplit[1].length() - 1);

        ArrayList<String> animeList = new ArrayList<>();
        String[] animes = animeListString.split(", ");
        for (String anime : animes) {
            if (!anime.isBlank()) {
                animeList.add(anime);
            }
        }

        return new Watchlist(watchlistName, animeList);
    }

    private boolean isValidWatchlistString(String[] lineSplit) {
        boolean isValidSplitLength = (lineSplit.length == 2);
        if (!isValidSplitLength) {
            return false;
        }

        boolean isValidWatchlistString = (lineSplit[1].startsWith("[")) && (lineSplit[1].endsWith("]"));
        if (!isValidWatchlistString) {
            return false;
        }

        return true;
    }
}
