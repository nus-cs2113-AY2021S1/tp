package anichan.commands;

import anichan.exception.AniException;
import anichan.human.User;
import anichan.anime.AnimeData;
import anichan.logger.AniLogger;
import anichan.storage.StorageManager;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author michaeldinata
/**
 * Represents the command to show the full command list of the program.
 */
public class HelpCommand extends Command {
    private static final String COMMAND_EMPTY = "";
    private static final String COMMAND_ESTIMATE = "estimate";
    private static final String COMMAND_BROWSE = "browse";
    private static final String COMMAND_SEARCH = "search";
    private static final String COMMAND_INFO = "info";
    private static final String COMMAND_WORKSPACE = "workspace";
    private static final String COMMAND_WATCHLIST = "watchlist";
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_REMOVE = "remove";
    private static final String COMMAND_VIEW = "view";
    private static final String COMMAND_BOOKMARK = "bookmark";
    
    private static final Logger LOGGER = AniLogger.getAniLogger(HelpCommand.class.getName());
    private String commandOption;

    /**
     * Creates a new instance of HelpCommand with the specified command option.
     *
     * @param commandOption the specified command option to show help
     */
    public HelpCommand(String commandOption) {
        this.commandOption = commandOption;
    }

    /**
     * Returns a string representation of the specified command list of the program.
     *
     * @param animeData used to retrieve anime information
     * @param storageManager used to save or read AniChan data
     * @param user used to modify user data
     * @return result after executing the command
     * @throws AniException when an error occurred while executing the command
     */
    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) {
        return buildHelpOutput();
    }

    /**
     * Builds and returns a string representation of the specified command list of the program.
     * 
     * @return a string representation of the specified command list of the program.
     */
    private String buildHelpOutput() {
        StringBuilder result = new StringBuilder();
        LOGGER.log(Level.INFO, "Start of build help output");

        switch (commandOption) {
            case COMMAND_EMPTY:
                result = buildFullResult();
                break;
            case COMMAND_ESTIMATE:
                result = buildEstimateResult();
                break;
            case COMMAND_BROWSE:
                result = buildBrowseResult();
                break;
            case COMMAND_SEARCH:
                result = buildSearchResult();
                break;
            case COMMAND_INFO:
                result = buildInfoResult();
                break;
            case COMMAND_WORKSPACE:
                result = buildWorkspaceResult();
                break;
            case COMMAND_WATCHLIST:
                result = buildWatchlistResult();
                break;
            case COMMAND_ADD:
                result = buildAddResult();
                break;
            case COMMAND_REMOVE:
                result = buildRemoveResult();
                break;
            case COMMAND_VIEW:
                result = buildViewResult();
                break;
            case COMMAND_BOOKMARK:
                result = buildBookmarkResult();
                break;
        }
        LOGGER.log(Level.INFO, "End of build help output");
        
        return result.toString();
    }

    /**
     * Builds and returns help for all commands.
     * 
     * @return help for all commands
     */
    private StringBuilder buildFullResult() {
        StringBuilder result = new StringBuilder();
        result.append(buildEstimateResult());
        result.append(appendLineSeparators());
        result.append(buildBrowseResult());
        result.append(appendLineSeparators());
        result.append(buildSearchResult());
        result.append(appendLineSeparators());
        result.append(buildInfoResult());
        result.append(appendLineSeparators());
        result.append(buildWorkspaceResult());
        result.append(appendLineSeparators());
        result.append(buildWatchlistResult());
        result.append(appendLineSeparators());
        result.append(buildAddResult());
        result.append(appendLineSeparators());
        result.append(buildRemoveResult());
        result.append(appendLineSeparators());
        result.append(buildViewResult());
        result.append(appendLineSeparators());
        result.append(buildBookmarkResult());
        return result;
    }

    /**
     * Builds and returns help for 'estimate' command.
     * 
     * @return help for 'estimate' command
     */
    private StringBuilder buildEstimateResult() {
        StringBuilder result = new StringBuilder();
        result.append("Estimate time needed to translate a script:");
        result.append(System.lineSeparator());
        result.append("estimate <SCRIPT_FILE_NAME> [-wph WORDS_PER_HOUR]");
        return result;
    }

    /**
     * Builds and returns help for 'browse' command.
     * 
     * @return help for 'browse' command
     */
    private StringBuilder buildBrowseResult() {
        StringBuilder result = new StringBuilder();
        result.append("Browse through the anime list:");
        result.append(System.lineSeparator());
        result.append("browse [-s SORT_CATEGORY] [-o SORT_ORDER] [-p PAGE_NUMBER]");
        result.append(System.lineSeparator());
        result.append("    - SORT_CATEGORY: name, rating");
        result.append(System.lineSeparator());
        result.append("    - SORT_ORDER: asc, dsc");
        result.append(System.lineSeparator());
        result.append("    - PAGE_NUMBER: 1, 2, 3, ...");
        return result;
    }

    /**
     * Builds and returns help for 'search' command.
     * 
     * @return help for 'search' command
     */
    private StringBuilder buildSearchResult() {
        StringBuilder result = new StringBuilder();
        result.append("Search for anime:");
        result.append(System.lineSeparator());
        result.append("    - By title: search -n <SEARCH_TERM>");
        result.append(System.lineSeparator());
        result.append("    - By genre: search -g <SEARCH_TERM>");
        return result;
    }

    /**
     * Builds and returns help for 'info' command.
     * 
     * @return help for 'info' command
     */
    private StringBuilder buildInfoResult() {
        StringBuilder result = new StringBuilder();
        result.append("View information of an anime:");
        result.append(System.lineSeparator());
        result.append("info <ANIME_ID>");
        return result;
    }

    /**
     * Builds and returns help for 'workspace' command.
     * 
     * @return help for 'workspace' command
     */
    private StringBuilder buildWorkspaceResult() {
        StringBuilder result = new StringBuilder();
        result.append("Workspace management:");
        result.append(System.lineSeparator());
        result.append("    - Create new workspace:  workspace -n <NAME>");
        result.append(System.lineSeparator());
        result.append("    - Switch workspace:      workspace -s <NAME>");
        result.append(System.lineSeparator());
        result.append("    - List workspace:        workspace -l");
        result.append(System.lineSeparator());
        result.append("    - Delete workspace:      workspace -d <NAME>");
        return result;
    }

    /**
     * Builds and returns help for 'watchlist' command.
     * 
     * @return help for 'watchlist' command
     */
    private StringBuilder buildWatchlistResult() {
        StringBuilder result = new StringBuilder();
        result.append("Watchlist management:");
        result.append(System.lineSeparator());
        result.append("    - Create a new watchlist:    watchlist -n <WATCHLIST_NAME>");
        result.append(System.lineSeparator());
        result.append("    - List all watchlist:        watchlist -l");
        result.append(System.lineSeparator());
        result.append("    - Select a watchlist to use: watchlist -s <WATCHLIST_ID>");
        result.append(System.lineSeparator());
        result.append("    - Delete a watchlist:        watchlist -d <WATCHLIST_ID>");
        return result;
    }

    /**
     * Builds and returns help for 'add' command.
     * 
     * @return help for 'add' command
     */
    private StringBuilder buildAddResult() {
        StringBuilder result = new StringBuilder();
        result.append("Add an anime to active watchlist:");
        result.append(System.lineSeparator());
        result.append("add <ANIME_ID>");
        return result;
    }

    /**
     * Builds and returns help for 'remove' command.
     * 
     * @return help for 'remove' command
     */
    private StringBuilder buildRemoveResult() {
        StringBuilder result = new StringBuilder();
        result.append("Remove anime from active watchlist:");
        result.append(System.lineSeparator());
        result.append("remove <ANIME_ID_IN_WATCHLIST>");
        return result;
    }

    /**
     * Builds and returns help for 'view' command.
     * 
     * @return help for 'view' command
     */
    private StringBuilder buildViewResult() {
        StringBuilder result = new StringBuilder();
        result.append("View all anime in watchlist:");
        result.append(System.lineSeparator());
        result.append("view [-v WATCHLIST_ID]");
        return result;
    }

    /**
     * Builds and returns help for 'bookmark' command.
     * 
     * @return help for 'bookmark' command
     */
    private StringBuilder buildBookmarkResult() {
        StringBuilder result = new StringBuilder();
        result.append("Bookmark management:");
        result.append(System.lineSeparator());
        result.append("    - Bookmark an anime:     bookmark -a <ANIME_ID>");
        result.append(System.lineSeparator());
        result.append("    - Delete bookmark:       bookmark -d <BOOKMARK_ID>");
        result.append(System.lineSeparator());
        result.append("    - List bookmark entries: bookmark -l");
        result.append(System.lineSeparator());
        result.append("    - Edit a bookmark:       bookmark <BOOKMARK_ID> -e <EPISODE>");
        result.append(System.lineSeparator());
        result.append("    - Add note to bookmark:  bookmark <BOOKMARK_ID> -n <NOTE>");
        result.append(System.lineSeparator());
        result.append("    - View info of bookmark: bookmark <BOOKMARK_ID>");
        return result;
    }

    /**
     * Builds and returns line separators to distinguish different commands.
     *
     * @return line separators to distinguish different commands.
     */
    private StringBuilder appendLineSeparators() {
        StringBuilder result = new StringBuilder();
        result.append(System.lineSeparator());
        result.append(System.lineSeparator());
        return result;
    }
}
