package anichan;

import anichan.anime.AnimeData;
import anichan.bookmark.Bookmark;
import anichan.commands.Command;
import anichan.exception.AniException;
import anichan.human.User;
import anichan.human.Workspace;
import anichan.logger.AniLogger;
import anichan.parser.Parser;
import anichan.ui.Ui;
import anichan.watchlist.Watchlist;
import anichan.storage.StorageManager;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author
public class Main {
    private static final String ANICHAN_STORAGE_DIRECTORY = "data" + File.separator;
    private static final Logger LOGGER = AniLogger.getAniLogger(Main.class.getName());

    private final Ui ui;
    private final Parser parser;
    private final StorageManager storageManager;

    private AnimeData animeData;
    private User user;

    public Main() {
        user = null;
        ui = new Ui();
        parser = new Parser();
        storageManager = new StorageManager(ANICHAN_STORAGE_DIRECTORY);

        LOGGER.log(Level.INFO, "AniChan started! Initializing...");

        // ========================== Initialize AniChan ==========================
        ui.printWelcomeMessage();
        ui.printHorizontalLine();
        try {
            user = storageManager.loadUser();
            ui.printMessage("User: Loaded successfully.");
            LOGGER.log(Level.INFO, "Loaded " + user.getName() + " from storage");
        } catch (AniException exception) {
            ui.printMessage("User: " + exception.getMessage());
            LOGGER.log(Level.WARNING, "Exception: " + exception.getMessage());
        }

        ArrayList<Workspace> workspaceList = new ArrayList<>();
        String[] workspaceNameList = storageManager.retrieveWorkspaceList();
        for (String workspaceName : workspaceNameList) {
            ui.printMessage("Workspace \"" + workspaceName + "\":");

            ArrayList<Watchlist> watchlistList = new ArrayList<>();
            try {
                String loadWatchlistResult = storageManager.loadWatchlistList(workspaceName, watchlistList);
                ui.printMessage("\tWatchlist: " + loadWatchlistResult);
                LOGGER.log(Level.INFO, "Loaded watchlist(s) " + loadWatchlistResult + " from storage");
            } catch (AniException exception) {
                ui.printMessage("\tWatchlist: " + exception.getMessage());
                LOGGER.log(Level.WARNING, "Exception: " + exception.getMessage());
            }

            Bookmark bookmark = new Bookmark();
            try {
                String loadBookmarkResult = storageManager.loadBookmark(workspaceName, bookmark);
                ui.printMessage("\tBookmark:  " + loadBookmarkResult);
                LOGGER.log(Level.INFO, "Loaded bookmark " + loadBookmarkResult + " from storage");
            } catch (AniException exception) {
                ui.printMessage("\tBookmark:  " + exception.getMessage());
                LOGGER.log(Level.WARNING, "Exception: " + exception.getMessage());
            }

            Workspace workspace = new Workspace(workspaceName, watchlistList, bookmark);
            workspaceList.add(workspace);
        }
        ui.printHorizontalLine();

        // ========================== New User Setup ==========================

        if (user == null) {
            LOGGER.log(Level.INFO, "Creating new user..");

            while (true) {
                try {
                    String[] userDialogueInput = ui.createUserDialogue();
                    user = new User(userDialogueInput[0], userDialogueInput[1]);
                    LOGGER.log(Level.INFO, "Created: " + user);
                    storageManager.saveUser(user);
                    break;
                } catch (AniException exception) {
                    ui.printErrorMessage("Invalid input detected!");
                    LOGGER.log(Level.WARNING, "Exception: " + exception.getMessage());
                }
            }
        }

        // ========================== Workspace Setup ==========================

        LOGGER.log(Level.INFO, "Workspace setup..");

        user.setWorkspaceList(workspaceList);
        if (user.getTotalWorkspaces() == 0) {
            try {
                Workspace newWorkspace = user.addWorkspace("Default");
                ArrayList<Watchlist> watchlistList = new ArrayList<>();
                watchlistList.add(new Watchlist("Default"));
                newWorkspace.setWatchlistList(watchlistList);
                user.setActiveWorkspace(newWorkspace);
                LOGGER.log(Level.INFO, "Workspace created: " + newWorkspace);

                storageManager.saveWorkspace(newWorkspace);
                LOGGER.log(Level.INFO, "Workspace saved to storage: " + newWorkspace.getName());
            } catch (AniException exception) {
                ui.printErrorMessage(exception.getMessage());
                LOGGER.log(Level.WARNING, "Exception: " + exception.getMessage());
            }
        }

        // ========================== Watchlist Setup ==========================

        Workspace activeWorkspace = user.getActiveWorkspace();
        ArrayList<Watchlist> watchlistList = activeWorkspace.getWatchlistList();
        if (watchlistList.size() == 0) {
            watchlistList.add(new Watchlist("Default"));
        }
        activeWorkspace.setActiveWatchlist(watchlistList.get(0));

        // ========================== AnimeDate Setup ==========================
        try {
            animeData = new AnimeData();
        } catch (AniException exception) {
            ui.printMessage("\tAnimeData: " + exception.getMessage());
            LOGGER.log(Level.WARNING, "Exception: " + exception.getMessage());
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
        new Main().run();
    }
}
