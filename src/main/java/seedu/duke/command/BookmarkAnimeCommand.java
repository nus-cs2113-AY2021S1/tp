package seedu.duke.command;

import seedu.duke.anime.Anime;
import seedu.duke.anime.AnimeData;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.exception.AniException;
import seedu.duke.human.UserManagement;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;

public class BookmarkAnimeCommand extends Command {

    private String bookmarkName;
    private int bookmarkIndex;
    private int animeIndex;
    private int bookmarkEpisode;
    // e for edit, a for add, d for delete
    private String bookmarkAction;

    public BookmarkAnimeCommand(String description) throws AniException {
        this.description = description;
        String[] paramGiven = description.split("-");
        if(paramGiven.length > 2){
            String invalidDescription = "-" + description + " has too many parameter!";
            throw new AniException(invalidDescription);
        }
        parameterParser(paramGiven[1]);
        if (bookmarkAction.equals("e")) {
            boolean isValidBookmarkIndex = setBookmarkIndex(paramGiven[0].trim());
            if(!isValidBookmarkIndex){
                String invalidBookmarkIndex = "" + paramGiven[0] + " is an invalid parameter!"
                        + " Bookmark index for edit episode requires integer.";
                throw new AniException(invalidBookmarkIndex);
            }
        }
    }

    @Override
    public String execute(AnimeData animeData, ArrayList<Watchlist> activeWatchlistList, Watchlist activeWatchlist,
                          UserManagement userManagement) {
        String result = "";
        Bookmark bookmark = userManagement.getActiveUser().bookmark;

        switch (bookmarkAction) {
        case "e":
            bookmark.editAnimeBookmarkEpisode(bookmarkIndex - 1, bookmarkEpisode);
            Anime animeToEdit = bookmark.getAnimeBookmarkByIndex(animeData, bookmarkIndex - 1);
            result = "Editing " + animeToEdit.getAnimeName() + " to have " + bookmarkEpisode + " episode";
            //System.out.println("Editing " + animeName + " to have " + episode + " episode");
            break;
        case "a":
            Anime animeToAdd = animeData.getAnimeByID(animeIndex - 1);
            result = "Saving " + animeToAdd.getAnimeID() + ". " + animeToAdd.getAnimeName() + " " + " to bookmark.";
            bookmark.addAnimeBookmark(animeToAdd.getAnimeID());
            break;
        case "d":
            Anime animeToDelete = bookmark.getAnimeBookmarkByIndex(animeData, bookmarkIndex - 1);
            result = "Removing " + animeToDelete.getAnimeName() + "! :(";
            //System.out.println("Removing " + animeName + "! :(");
            bookmark.removeAnimeBookmark(bookmarkIndex - 1);
            break;
        case "l":
            result = "Listing all anime in bookmark: ";
            String bookmarks = bookmark.animeListInString(animeData);
            result += bookmarks;
            break;
        default:
        }
        return result;
    }

    private void parameterParser(String paramGiven) throws AniException {
        String[] paramParts = paramGiven.split(" ");
        switch (paramParts[0].trim()) {
        case "e":
            paramLengthCheck(paramParts);
            setBookmarkAction(paramParts[0]);
            Boolean isValidBookmarkEpisode = setBookmarkEpisode(paramParts[1].trim());
            if(!isValidBookmarkEpisode){
                String invalidParameter = "-" + paramGiven + " is an invalid parameter!"
                        + " Bookmark edit episode param requires integer.";
                throw new AniException(invalidParameter);
            }
            break;
        case "a":
            paramLengthCheck(paramParts);
            setBookmarkAction(paramParts[0]);
            Boolean isValidAnimeIndex = setAnimeIndex(paramParts[1].trim());
            if(!isValidAnimeIndex){
                String invalidParameter = "-" + paramGiven + " is an invalid parameter!"
                        + " Bookmark Add param requires integer.";
                throw new AniException(invalidParameter);
            }
            break;
        case "d":
            paramLengthCheck(paramParts);
            setBookmarkAction(paramParts[0]);
            Boolean isValidBookmarkIndex = setBookmarkIndex(paramParts[1].trim());
            if(!isValidBookmarkIndex){
                String invalidParameter = "-" + paramGiven + " is an invalid parameter!"
                        + " Bookmark delete param requires integer.";
                throw new AniException(invalidParameter);
            }
            break;
        case "l":
            setBookmarkAction(paramParts[0]);
            break;
        default:
            String invalidParameter = "-" + paramGiven + " is an invalid parameter!";
            throw new AniException(invalidParameter);
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

    private void setBookmarkAction(String actionString){
        this.bookmarkAction = actionString;
    }

    private boolean setBookmarkIndex(String bookmarkIndexString) {
        if (isInt(bookmarkIndexString)) {
            bookmarkIndex = Integer.parseInt(bookmarkIndexString);
            return true;
        } else {
            return false;
        }
    }

    private boolean setAnimeIndex(String animeIndexString) {
        if (isInt(animeIndexString)) {
            animeIndex = Integer.parseInt(animeIndexString);
            return true;
        } else {
            return false;
        }
    }

    private boolean setBookmarkEpisode(String bookmarkEpisodeString) {
        if (isInt(bookmarkEpisodeString)) {
            bookmarkEpisode = Integer.parseInt(bookmarkEpisodeString);
            return true;
        } else {
            return false;
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
