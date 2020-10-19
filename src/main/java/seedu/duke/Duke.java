package seedu.duke;

import seedu.duke.anime.AnimeData;
import seedu.duke.anime.AnimeStorage;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.command.Command;
import seedu.duke.exception.AniException;
import seedu.duke.human.Workspace;
import seedu.duke.human.User;
import seedu.duke.parser.Parser;
import seedu.duke.storage.StorageManager;
import seedu.duke.ui.Ui;
import seedu.duke.watchlist.Watchlist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    private static final String ANIME_DATA_SOURCE_FOLDER = "/AniListData";

    private final Ui ui;
    private final Parser parser;
    private final StorageManager storageManager;

    private AnimeStorage animeStorage;
    private AnimeData animeData;
    private User user;

    public Duke() {
        user = null;
        ui = new Ui();
        parser = new Parser();
        storageManager = new StorageManager("data" + File.separator);

        // ========================== Initialize AniChan ==========================
        ui.printWelcomeMessage();
        ui.printHorizontalLine();
        try {
            user = storageManager.loadUser();
            ui.printMessage("User: Loaded successfully.");
        } catch (AniException exception) {
            ui.printMessage("User: " + exception.getMessage());
        }

        ArrayList<Workspace> workspaceList = new ArrayList<>();
        String[] workspaceNameList = storageManager.retrieveWorkspaceList();
        for (String workspaceName : workspaceNameList) {
            ui.printMessage("Workspace \"" + workspaceName + "\":");

            ArrayList<Watchlist> watchlistList = new ArrayList<>();
            try {
                String loadWatchlistResult = storageManager.loadWatchlistList(workspaceName, watchlistList);
                ui.printMessage("\tWatchlist: " + loadWatchlistResult);
            } catch (AniException exception) {
                ui.printMessage("\tWatchlist: " + exception.getMessage());
            }

            Bookmark bookmark = new Bookmark();
            try {
                String loadBookmarkResult = storageManager.loadBookmark(workspaceName, bookmark);
                ui.printMessage("\tBookmark:  " + loadBookmarkResult);
            } catch (AniException exception) {
                ui.printMessage("\tBookmark:  " + exception.getMessage());
            }

            Workspace workspace = new Workspace(workspaceName, watchlistList, bookmark);
            workspaceList.add(workspace);
        }
        ui.printHorizontalLine();

        // ========================== New User Setup ==========================

        if (user == null) {
            while (true) {
                try {
                    String[] userDialogueInput = ui.createUserDialogue();
                    user = new User(userDialogueInput[0], userDialogueInput[1]);
                    storageManager.saveUser(user);
                    break;
                } catch (AniException e) {
                    ui.printErrorMessage("Invalid input detected!");
                }
            }
        }

        // ========================== Workspace Setup ==========================

        user.setWorkspaceList(workspaceList);
        if (user.getTotalWorkspaces() == 0) {
            Workspace newWorkspace = user.addWorkspace("Default");
            ArrayList<Watchlist> watchlistList = new ArrayList<>();
            watchlistList.add(new Watchlist("Default"));
            newWorkspace.setWatchlistList(watchlistList);
            user.setActiveWorkspace(newWorkspace);

            try {
                storageManager.saveWorkspace(newWorkspace);
            } catch (AniException exception) {
                ui.printErrorMessage(exception.getMessage());
            }
        }

        // ========================== Watchlist Setup ==========================

        Workspace activeWorkspace = user.getActiveWorkspace();
        ArrayList<Watchlist> watchlistList = activeWorkspace.getWatchlistList();
        if (watchlistList.size() == 0) {
            watchlistList.add(new Watchlist("Default"));
        }
        activeWorkspace.setActiveWatchlist(watchlistList.get(0));

        // ========================== Anime Data Setup ==========================

        try {
            animeStorage = new AnimeStorage(ANIME_DATA_SOURCE_FOLDER);
            animeData = new AnimeData(animeStorage.readAnimeDatabase());
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    public void run() {
        while (ui.hasNextLine(user)) {
            try {
                String userInput = ui.readUserInput();
                Command command = parser.getCommand(userInput);
                String commandOutput = command.execute(animeData, storageManager, user);
                ui.printMessage(commandOutput);

                if (command.getShouldExit()) {
                    ui.printGoodbyeMessage(user.getHonorificName());
                    break;
                }
            } catch (AniException exception) {
                ui.printErrorMessage(exception.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
