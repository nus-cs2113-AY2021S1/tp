package anichan.commands;

import anichan.human.User;
import anichan.anime.AnimeData;
import anichan.logger.AniLogger;
import anichan.storage.StorageManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HelpCommand extends Command {
    private static final Logger LOGGER = AniLogger.getAniLogger(HelpCommand.class.getName());
    private static String output;
    
    public HelpCommand() {
        // LOGGER.setLevel(Level.WARNING);
        this.output = buildHelpOutput();
    }
    
    /**
     * Shows help function.
     */
    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) {
        return output;
    }
    
    private String buildHelpOutput() {
        StringBuilder result = new StringBuilder();
        LOGGER.log(Level.INFO, "Start of build help output");
        
        result.append("Estimate time needed to translate a script:");
        result.append(System.lineSeparator());
        result.append("estimate <SCRIPT_FILE_NAME> [-wph WORDS_PER_HOUR]");

        result.append(System.lineSeparator());
        result.append(System.lineSeparator());

        result.append("Browse through the anime list:");
        result.append(System.lineSeparator());
        result.append("browse [-s SORT_CATEGORY] [-o SORT_ORDER] [-p PAGE_NUMBER]");
        result.append(System.lineSeparator());
        result.append("    - SORT_CATEGORY: name, rating");
        result.append(System.lineSeparator());
        result.append("    - SORT_ORDER: asc, dsc");
        result.append(System.lineSeparator());
        result.append("    - PAGE_NUMBER: 1, 2, 3, ...");

        result.append(System.lineSeparator());
        result.append(System.lineSeparator());
        
        result.append("Search for anime:");
        result.append(System.lineSeparator());
        result.append("    - By title: search -n <SEARCH_TERM>");
        result.append(System.lineSeparator());
        result.append("    - By genre: search -g <SEARCH_TERM>");
        
        result.append(System.lineSeparator());
        result.append(System.lineSeparator());
        
        result.append("View information of an anime:");
        result.append(System.lineSeparator());
        result.append("info -a <ANIME_ID>");

        result.append(System.lineSeparator());
        result.append(System.lineSeparator());
        
        result.append("Workspace management:");
        result.append(System.lineSeparator());
        result.append("    - Create new workspace:  workspace -n <NAME>");
        result.append(System.lineSeparator());
        result.append("    - Switch workspace:      workspace -s <NAME>");
        result.append(System.lineSeparator());
        result.append("    - List workspace:        workspace -l");
        result.append(System.lineSeparator());
        result.append("    - Delete workspace:      workspace -d <NAME>");

        result.append(System.lineSeparator());
        result.append(System.lineSeparator());
        
        result.append("Watchlist management:");
        result.append(System.lineSeparator());
        result.append("    - Create a new watchlist:    watchlist -n <WATCHLIST_NAME>");
        result.append(System.lineSeparator());
        result.append("    - List all watchlist:        watchlist -l");
        result.append(System.lineSeparator());
        result.append("    - Select a watchlist to use: watchlist -s <WATCHLIST_ID>");
        result.append(System.lineSeparator());
        result.append("    - Delete a watchlist:        watchlist -d <WATCHLIST_ID>");
        
        result.append(System.lineSeparator());
        result.append(System.lineSeparator());
        
        result.append("Add an anime to active watchlist:");
        result.append(System.lineSeparator());
        result.append("add -a <ANIME_ID>");

        result.append(System.lineSeparator());
        result.append(System.lineSeparator());
        
        result.append("Remove anime from active watchlist:");
        result.append(System.lineSeparator());
        result.append("remove -d <ANIME_ID_IN_WATCHLIST>");

        result.append(System.lineSeparator());
        result.append(System.lineSeparator());
        
        result.append("View all anime in watchlist:");
        result.append(System.lineSeparator());
        result.append("view -v <WATCHLIST_ID>");
        
        result.append(System.lineSeparator());
        result.append(System.lineSeparator());
        
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

        LOGGER.log(Level.INFO, "End of build help output");
        
        return result.toString();
    }
}
