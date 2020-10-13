package seedu.duke.command;

import seedu.duke.anime.Anime;
import seedu.duke.anime.AnimeData;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.exception.AniException;
import seedu.duke.human.UserManagement;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;

public class BookmarkAnimeCommand extends Command {

    public BookmarkAnimeCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(AnimeData animeData, ArrayList<Watchlist> activeWatchlistList, Watchlist activeWatchlist,
                          UserManagement userManagement) {
        String result = "";
        Bookmark bookmark = userManagement.getActiveUser().bookmark;
        if (description.contains(" ")) {
            String[] descriptionSplit = description.split(" ", 2);
            // Code to be added
            String commandOption = descriptionSplit[0];
            String commandArgument = descriptionSplit[1];
            if (commandOption.equals("-a")) {
                if (isInt(commandArgument)) {
                    int animeDataListIndex = Integer.parseInt(commandArgument);
                    Anime anime = animeData.getAnimeByID(animeDataListIndex);
                    result = "Saving " + anime.getAnimeID() + ". " + anime.getAnimeName() + " " + " to bookmark.";
                    //System.out.println("Saving " + anime.getAnimeID() + ".
                    // + anime.getAnimeName() + " " + " to bookmark.");
                    bookmark.addAnimeBookmark(anime.getAnimeName());
                } else {
                    ArrayList<Anime> findList = animeData.findName(commandArgument);
                    for (Anime anime : findList) {
                        result += "\t" + anime.getAnimeID() + ". " + anime.getAnimeName() + System.lineSeparator();
                        //System.out.println("\t" + anime.getAnimeID() + ". " + anime.getAnimeName());
                    }
                }
            } else if (commandOption.equals("-d")) {
                int bookmarkIndex = Integer.parseInt(commandArgument);
                String animeName = bookmark.getAnimeBookmarkByIndex(bookmarkIndex - 1);
                result = "Removing " + animeName + "! :(";
                //System.out.println("Removing " + animeName + "! :(");
                bookmark.removeAnimeBookmark(bookmarkIndex - 1);
            } else {
                int bookmarkIndex = Integer.parseInt(commandOption);
                String[] commandArgumentSplit = commandArgument.split(" ", 2);
                String commandOption2 = commandArgumentSplit[0];
                String commandArgument2 = commandArgumentSplit[1];
                if (commandOption2.equals("-e")) {
                    int episode = Integer.parseInt(commandArgument2);
                    bookmark.editAnimeBookmarkEpisode(bookmarkIndex - 1, episode);
                    String animeName = bookmark.getAnimeBookmarkByIndex(bookmarkIndex - 1);
                    result = "Editing " + animeName + " to have " + episode + " episode";
                    //System.out.println("Editing " + animeName + " to have " + episode + " episode");
                }
            }
        } else {
            if (description.equals("-l")) {
                String bookmarks = bookmark.animeListInString();
                result = bookmarks;
                //System.out.println(bookmarks);
            }
        }
        return result;
    }

    private void parameterParser(String[] paramGiven) throws AniException {
        for (String param : paramGiven) {
            String[] paramParts = param.split(" ");
            switch (paramParts[0].trim()) {
            case "": //skip the first empty param
                break;
            case "s":
                paramLengthCheck(paramParts);

                break;
            case "f":
                paramLengthCheck(paramParts);

                break;
            case "o":
                paramLengthCheck(paramParts);
                break;
            case "p":
                paramLengthCheck(paramParts);
                break;
            default:
                String invalidParameter = "-" + param + " is an invalid parameter!";
                throw new AniException(invalidParameter);
            }
        }
    }

    private void paramLengthCheck(String[] paramParts) throws AniException {
        // Parameter Additional Field Check
        if (paramParts.length < 2) {
            String invalidParameter = "Parameter : " + paramParts[0] + " requires an additional field";
            throw new AniException(invalidParameter);
        } else if (paramParts.length > 2) {
            String invalidParameter = "Parameter : " + paramParts[0] + " has too much fields";
            throw new AniException(invalidParameter);
        }
    }


    /**
     * Checks if String is a parsable int.
     *
     * @param checkStr string to check
     * @return true if parsable int
     */
    public boolean isInt(String checkStr) {
        return checkStr.matches("-?\\d+(\\.\\d+)?");
    }

}
