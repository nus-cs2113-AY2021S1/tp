package seedu.duke.util;

import seedu.duke.data.notebook.Notebook;
import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Tag;
import seedu.duke.data.timetable.Timetable;
import seedu.duke.data.timetable.Event;

import java.util.ArrayList;

public class Formatter {

    /** A platform independent line separator. */
    public static final String LS = System.lineSeparator();
    private static final String ROW_SPLIT = "-";
    private static final String COLUMN_SPLIT = "|";
    private static final String COLUMN_START = "|| ";
    private static final String COLUMN_END = " ||";

    // Temporary value for the maximum number of characters
    private static final int MAX_CONTENT_LENGTH = 50;

    public static String formatNotebook(String header, Notebook notebook) {
        String formattedString = createsRowSplit() + LS;
        return formattedString;
    }

    public static String formatNote(String header, Note note) {
        String formattedString = "";
        return formattedString;
    }

    public static String formatTags(String header, ArrayList<Tag> tags) {
        String formattedString = "";
        return formattedString;
    }

    public static String formatTimetable(String header, Timetable timetable) {
        String formattedString = "";
        return formattedString;
    }

    public static String formatEvent(String header, Event event) {
        String formattedString = "";
        return formattedString;
    }

    public static String formatString(String message) {
        // may have to handle the message length if it exceeds MAX_LENGTH_CONTENT
        return encloseTopAndBottom(encloseRow(message));
    }

    public static String formatString(String[] input, boolean hasHeader) {
        String formattedString = "";
        return formattedString;
    }

    private static String createsRowSplit() {
        return ROW_SPLIT.repeat(MAX_CONTENT_LENGTH);
    }

    private static String encloseTopAndBottom(String message) {
        return createsRowSplit() + LS + message + createsRowSplit() + LS;
    }

    private static String encloseRow(String message) {
        return COLUMN_START + message + COLUMN_END;
    }
}
