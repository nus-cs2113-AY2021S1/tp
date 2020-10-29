package anichan.storage;

import anichan.bookmark.Bookmark;
import anichan.bookmark.Note;
import anichan.exception.AniException;

import java.io.File;
import java.util.ArrayList;

//@@author
public class BookmarkStorage extends Storage {
    private static final String BOOKMARK_FILE_NAME = "bookmark.txt";
    private static final String BOOKMARK_LINE_DELIMITER = "~";

    private final String storageDirectory;

    public BookmarkStorage(String storageDirectory) {
        this.storageDirectory = storageDirectory;
    }
    // ========================== Save and Load ==========================

    public void save(String workspaceName, Bookmark bookmark) throws AniException {
        String bookmarkDirectory = storageDirectory + workspaceName + File.separator;
        String bookmarkFilePath = bookmarkDirectory + BOOKMARK_FILE_NAME;
        String encodedWatchlistString = encode(bookmark);
        new File(bookmarkDirectory).mkdirs();
        writeFile(bookmarkFilePath, encodedWatchlistString);
    }

    public String load(String workspaceName, Bookmark bookmark) throws AniException {
        String bookmarkFilePath = storageDirectory + workspaceName + File.separator + BOOKMARK_FILE_NAME;
        String fileString = readFile(bookmarkFilePath);
        if (fileString.isBlank()) {
            return "Empty bookmark file.";
        }

        String[] fileLines = fileString.split(System.lineSeparator());
        return decode(fileLines, bookmark);
    }

    // ========================== Encode and Decode ==========================

    private String encode(Bookmark bookmark) {
        StringBuilder sbBookmark = new StringBuilder();
        ArrayList<Integer> animeBookmarkList = bookmark.getAnimeBookmarkList();
        ArrayList<Integer> animeEpisode = bookmark.getAnimeEpisode();
        ArrayList<Note> animeNote = bookmark.getAnimeNote();
        for (int i = 0; i < bookmark.getBookmarkSize(); i++) {
            sbBookmark.append(animeBookmarkList.get(i));
            sbBookmark.append(BOOKMARK_LINE_DELIMITER);
            sbBookmark.append(animeEpisode.get(i));
            sbBookmark.append(BOOKMARK_LINE_DELIMITER);
            Note note = animeNote.get(i);

            for (int j = 0; j < note.getSize(); j++) {
                sbBookmark.append(note.getNote(j));
                sbBookmark.append(BOOKMARK_LINE_DELIMITER);
            }
            if (note.getSize() != 0) {
                sbBookmark.setLength(sbBookmark.length() - 1); // Remove "~" for the last item in the string.
            }
            sbBookmark.append(System.lineSeparator());
        }
        String encodedBookmarkString = sbBookmark.toString();
        //assert (encodedBookmarkString.isBlank()) : "Encoded bookmark string should not be blank.";
        return encodedBookmarkString;
    }

    private String decode(String[] fileLines, Bookmark bookmark) {
        boolean hasCorruptedBookmark = false;
        for (String line : fileLines) {
            String[] lineSplit = line.split(BOOKMARK_LINE_DELIMITER, 3);
            if (!isValidBookmarkString(lineSplit)) {
                hasCorruptedBookmark = true;
                continue;
            }

            int bookmarkIndex = Integer.parseInt(lineSplit[0]);
            int bookmarkEpisode = Integer.parseInt(lineSplit[1]);
            Note note = new Note();
            String[] lineSplitNotes = lineSplit[2].split(BOOKMARK_LINE_DELIMITER);
            if (lineSplitNotes[0].trim().length() > 0) {
                for (String noteString : lineSplitNotes) {
                    note.addNote(noteString.trim());
                }
            }

            bookmark.addAnimeBookmarkEpisode(bookmarkIndex, bookmarkEpisode, note);
        }

        if (hasCorruptedBookmark) {
            return "Not all loaded successfully.";
        } else {
            return "Loaded successfully.";
        }
    }

    // ========================== Validation ==========================

    private boolean isValidBookmarkString(String[] lineSplit) {
        boolean isValidSplitLength = (lineSplit.length == 3);
        if (!isValidSplitLength) {
            return false;
        }

        boolean isFirstPartInteger = isPositiveOrNegativeInteger(lineSplit[0]);
        boolean isSecondPartInteger = isPositiveOrNegativeInteger(lineSplit[1]);
        return isFirstPartInteger && isSecondPartInteger;
    }
}
