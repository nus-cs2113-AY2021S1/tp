package seedu.duke;

import seedu.duke.anime.AnimeData;
import seedu.duke.anime.AnimeStorage;
import seedu.duke.command.Command;
import seedu.duke.exception.AniException;
import seedu.duke.human.Workspace;
import seedu.duke.human.User;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.watchlist.Watchlist;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    private static final String USER_PROFILE_FILE_NAME = "userprofile.txt";
    private static final String WATCHLIST_FILE_NAME = "watchlist.txt";
    private static final String ANIME_DATA_SOURCE_FOLDER = "/AniListData";

    private final Ui ui;
    private final Parser parser;

    private AnimeStorage animeStorage;
    private AnimeData animeData;
    private User user;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
        Storage storage = new Storage(USER_PROFILE_FILE_NAME, WATCHLIST_FILE_NAME);

        // Load user and watchlist list.
        ui.printWelcomeMessage();
        ui.printHorizontalLine();
        // user.setActiveWorkspace(storage.loadUser(ui));
        user = storage.loadUser(ui);
        final ArrayList<Watchlist> watchlistList = storage.loadWatchlist(ui);
        ui.printHorizontalLine();

        // Setup user and workspace

        if (user == null) {
            while (true) {
                try {
                    String[] userDialogueInput = ui.createUserDialogue();
                    user = new User(userDialogueInput[0], userDialogueInput[1]);
                    user.setActiveWorkspace(user.addWorkspace(userDialogueInput[2]));
                    break;
                } catch (AniException e) {
                    ui.printErrorMessage("Invalid input detected!");
                }
            }
        }

        // Get activeWorkspace
        Workspace activeWorkspace = user.getActiveWorkspace();
        assert user.getActiveWorkspace() != null : "Workspace should have been created";


        activeWorkspace.setWatchlistList(watchlistList);
        if (watchlistList.isEmpty()) {
            Watchlist activeWatchlist = new Watchlist("Default");
            watchlistList.add(activeWatchlist);
            activeWorkspace.setActiveWatchlist(activeWatchlist);
            activeWorkspace.setWatchlistList(watchlistList);

            try {
                storage.saveWatchlist(watchlistList);
            } catch (AniException exception) {
                ui.printErrorMessage(exception.getMessage());
            }
        } else {
            activeWorkspace.setActiveWatchlist(watchlistList.get(0));
            assert activeWorkspace.getActiveWatchlist() != null : "Active watchlist should not be null.";
        }

        try {
            animeStorage = new AnimeStorage(ANIME_DATA_SOURCE_FOLDER);
            animeData = new AnimeData(animeStorage.readAnimeDatabase());
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    public void run() {
        boolean shouldExit = false;
        while (!shouldExit) {
            try {
                String userInput = ui.readUserInput(user);
                Command command = parser.getCommand(userInput);
                String commandOutput = command.execute(animeData, user);

                ui.printMessage(commandOutput);
                shouldExit = command.getShouldExit();
            } catch (AniException exception) {
                ui.printErrorMessage(exception.getMessage());
            }
        }

        String goodbyeName = user.getHonorificName();
        ui.printGoodbyeMessage(goodbyeName);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
