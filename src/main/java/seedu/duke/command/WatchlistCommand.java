package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.UserManagement;
import seedu.duke.storage.Storage;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;

public class WatchlistCommand extends Command {
    private static final String CREATE_OPTION = "-n";
    private static final String LIST_OPTION = "-l";

    private final String option;
    private String optionInformation;

    public WatchlistCommand(String description) {
        String[] descriptionSplit = description.split(" ", 2);
        option = descriptionSplit[0];
        optionInformation = "";
        if (descriptionSplit.length == 2) {
            optionInformation = descriptionSplit[1];
        }
    }

    @Override
    public String execute(AnimeData animeData, ArrayList<Watchlist> activeWatchlistList, Watchlist activeWatchlist,
                          UserManagement userManagement) throws AniException {
        if (option.equals(CREATE_OPTION)) {
            return createWatchlist(userManagement.getStorage(), activeWatchlistList);
        } else if (option.equals(LIST_OPTION)) {
            return listAllWatchlist(activeWatchlistList);
        } else {
            throw new AniException("Watchlist command only accepts the option: \"-n\" and \"-l\".");
        }
    }

    private String createWatchlist(Storage storage, ArrayList<Watchlist> activeWatchlistList) throws AniException {
        if (optionInformation.isBlank()) {
            throw new AniException("Watchlist name cannot be empty.");
        }

        Watchlist newWatchlist = new Watchlist(optionInformation);
        activeWatchlistList.add(newWatchlist);
        storage.saveWatchlist(activeWatchlistList);
        return "Watchlist created successfully!";
    }

    private String listAllWatchlist(ArrayList<Watchlist> watchlists) {
        if (watchlists.size() == 0) {
            return "You have no watchlist.";
        }

        StringBuilder sbWatchlistDisplay = new StringBuilder();
        sbWatchlistDisplay.append("Currently, you have ");
        sbWatchlistDisplay.append(watchlists.size()).append(" watchlists:");
        for (int i = 0; i < watchlists.size(); i++) {
            Watchlist watchlist = watchlists.get(i);
            sbWatchlistDisplay.append(System.lineSeparator());
            sbWatchlistDisplay.append("\t");
            sbWatchlistDisplay.append(i + 1).append(". ");
            sbWatchlistDisplay.append(watchlist.getName());
        }

        return sbWatchlistDisplay.toString();
    }
}
