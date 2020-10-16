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
    private static final int MAX_CONTENT_LENGTH = 50;

    public static String formatNotebook(Notebook notebook) {
        String formattedString = "";
        return formattedString;
    }

    public static String formatNote(Note note) {
        String formattedString = "";
        return formattedString;
    }

    public static String formatTags(ArrayList<Tag> tags) {
        String formattedString = "";
        return formattedString;
    }

    public static String formatTimetable(Timetable timetable) {
        String formattedString = "";
        return formattedString;
    }

    public static String formatEvent(Event event) {
        String formattedString = "";
        return formattedString;
    }

    public static String formatString(String input) {
        String formattedString = "";
        return formattedString;
    }

    public static String formatString(String[] input, boolean hasHeader) {
        String formattedString = "";
        return formattedString;
    }

    private static String createsHeader() {
        String formattedString;
        formattedString = ROW_SPLIT.repeat(MAX_CONTENT_LENGTH);
        return formattedString;
    }
}
