package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.Workspace;
import seedu.duke.human.User;
import seedu.duke.storage.StorageManager;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WatchlistCommand extends Command {
    private static final String CREATE_OPTION = "-n";
    private static final String LIST_OPTION = "-l";
    private static final String SELECT_OPTION = "-s";
    private static final String DELETE_OPTION = "-d";

    private final String option;
    private String optionInformation;
    private static final Logger LOGGER = Logger.getLogger(WatchlistCommand.class.getName());

    public WatchlistCommand(String description) {
        String[] descriptionSplit = description.split(" ", 2);
        option = descriptionSplit[0];
        optionInformation = "";
        if (descriptionSplit.length == 2) {
            optionInformation = descriptionSplit[1];
        }

        LOGGER.setLevel(Level.WARNING);
    }

    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) throws AniException {
        Workspace activeWorkspace = user.getActiveWorkspace();

        switch (option) {
        case CREATE_OPTION:
            return createWatchlist(storageManager, activeWorkspace);
        case LIST_OPTION:
            return listAllWatchlist(activeWorkspace);
        case SELECT_OPTION:
            return selectWatchlist(activeWorkspace);
        case DELETE_OPTION:
            return deleteWatchlist(storageManager, activeWorkspace);
        default:
            LOGGER.log(Level.WARNING, "Invalid option received: \"" + option + "\".");
            throw new AniException("Watchlist command only accepts the options: -n, -l, -s, and -d.");
        }
    }

    private String createWatchlist(StorageManager storageManager, Workspace activeWorkspace) throws AniException {
        if (optionInformation.isBlank()) {
            LOGGER.log(Level.WARNING, "Watchlist name is empty.");
            throw new AniException("Watchlist name cannot be empty!");
        }

        Watchlist createdWatchlist = new Watchlist(optionInformation);
        ArrayList<Watchlist> watchlistList = activeWorkspace.getWatchlistList();
        boolean isWatchlistNameUnique = !watchlistList.contains(createdWatchlist);
        if (!isWatchlistNameUnique) {
            LOGGER.log(Level.WARNING, "Watchlist name \"" + optionInformation + "\" is not unique.");
            throw new AniException("There is already a watchlist named \"" + optionInformation + "\".");
        }

        watchlistList.add(createdWatchlist);
        LOGGER.log(Level.INFO, "Watchlist \"" + createdWatchlist.getName() + "\" created successfully.");

        storageManager.saveWatchlistList(activeWorkspace.getName(), watchlistList);
        return "Watchlist \"" + optionInformation + "\" has been created successfully!";
    }

    private String listAllWatchlist(Workspace activeWorkspace) {
        ArrayList<Watchlist> watchlistList = activeWorkspace.getWatchlistList();
        if (watchlistList.size() == 0) {
            LOGGER.log(Level.INFO, "Attempts to list watchlist when there is none.");
            return "Uhh.. You have no watchlist to list..";
        }

        StringBuilder sbWatchlistList = new StringBuilder();
        sbWatchlistList.append("Currently, you have ");
        sbWatchlistList.append(watchlistList.size()).append(" watchlist(s):");
        for (int i = 0; i < watchlistList.size(); i++) {
            Watchlist watchlist = watchlistList.get(i);
            sbWatchlistList.append(System.lineSeparator());
            sbWatchlistList.append("\t").append(i + 1).append(". ");
            sbWatchlistList.append(watchlist.getName());
        }

        String watchlistListString = sbWatchlistList.toString();
        assert !(watchlistListString.isBlank()) : "The string listing watchlist(s) cannot be empty.";
        return watchlistListString;
    }

    private String selectWatchlist(Workspace activeWorkspace) throws AniException {
        ArrayList<Watchlist> watchlistList = activeWorkspace.getWatchlistList();
        int listSize = watchlistList.size();
        if (listSize == 0) {
            LOGGER.log(Level.INFO, "Attempts to select from an empty watchlist list.");
            return "Uhh.. You have no watchlist to select..";
        }

        int selectIndex = parseInteger(optionInformation);
        if (selectIndex < 0 || selectIndex >= listSize) {
            LOGGER.log(Level.WARNING, "Select index specified is out of range.");
            throw new AniException("\"" + (selectIndex + 1) + "\" is not a valid watchlist index.");
        }

        Watchlist selectedWatchlist = watchlistList.get(selectIndex);
        Watchlist activeWatchlist = activeWorkspace.getActiveWatchlist();
        if (selectedWatchlist.equals(activeWatchlist)) {
            LOGGER.log(Level.INFO, "Selected watchlist is currently the active watchlist.");
            throw new AniException("Uhh.. Current active watchlist is \"" + selectedWatchlist.getName() + "\"..");
        }

        activeWorkspace.setActiveWatchlist(selectedWatchlist);
        LOGGER.log(Level.INFO, "Watchlist at index \"" + selectIndex + "\" selected successfully.");
        return "\"" + selectedWatchlist.getName() + "\" is now your active watchlist.";
    }

    private String deleteWatchlist(StorageManager storageManager, Workspace activeWorkspace) throws AniException {
        ArrayList<Watchlist> watchlistList = activeWorkspace.getWatchlistList();
        int listSize = watchlistList.size();
        if (listSize == 0) {
            LOGGER.log(Level.INFO, "Attempts to delete from an empty watchlist list.");
            return "Uhh.. You have no watchlist to delete..";
        }

        int deleteIndex = parseInteger(optionInformation);
        if (deleteIndex < 0 || deleteIndex >= watchlistList.size()) {
            LOGGER.log(Level.WARNING, "Delete index specified is out of range.");
            throw new AniException("\"" + (deleteIndex + 1) + "\" is not a valid watchlist index.");
        }

        Watchlist deletedWatchlist = watchlistList.get(deleteIndex);
        Watchlist activeWatchlist = activeWorkspace.getActiveWatchlist();
        watchlistList.remove(deleteIndex);
        LOGGER.log(Level.INFO, "Watchlist at index \"" + deleteIndex + "\" deleted successfully.");
        String commandOutput = "Watchlist \"" + deletedWatchlist.getName() + "\" has been deleted successfully!";

        if (deletedWatchlist.equals(activeWatchlist)) {
            activeWorkspace.setActiveWatchlist(watchlistList.get(0));
            commandOutput += System.lineSeparator();
            commandOutput += "Changed active watchlist to: \"" + activeWorkspace.getActiveWatchlistName() + "\".";
        }

        storageManager.saveWatchlistList(activeWorkspace.getName(), watchlistList);
        return commandOutput;
    }

    private int parseInteger(String optionInformation) throws AniException {
        try {
            return Integer.parseInt(optionInformation) - 1;
        } catch (NumberFormatException exception) {
            LOGGER.log(Level.WARNING, "Failed to parse \"" + optionInformation + "\" into a integer.");
            throw new AniException("The watchlist index has to be a number.");
        }
    }
}
