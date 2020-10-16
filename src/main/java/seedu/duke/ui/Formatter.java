package seedu.duke.ui;

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

    // Character code adapted from http://patorjk.com/software/taag/#p=display&f=Ghost&t=NotUS
    // Slight modifications made to make it easier on the eyes
    private static final String NOTUS_LOGO = Formatter.LS
            + Formatter.LS
            + "     .-') _               .-') _                 .-')    "
            + Formatter.LS
            + "    ( OO ) )             (  OO) )               ( OO ).  "
            + Formatter.LS
            + ",--./ ,--,'  .-'),-----. /     '._ ,--. ,--.   (_)---\\_) "
            + Formatter.LS
            + "|   \\ |  |\\ ( OO'  .-.  '|'--...__)|  | |  |   /    _ |  "
            + Formatter.LS
            + "|    \\|  | )/   |  | |  |'--.  .--'|  | | .-') \\  :` `.  "
            + Formatter.LS
            + "|  .     |/ \\_) |  | |  |   |  |   |  | |( OO ) '..`''.) "
            + Formatter.LS
            + "|  |\\    |    \\ |  | |  |   |  |   |  | | `-' /.-._)   \\ "
            + Formatter.LS
            + "|  | \\   |     `'  '-'  '   |  |  ('  '-'(_.-' \\       / "
            + Formatter.LS
            + "`--'  `--'       `-----'    `--'    `-----'     `-----'  "
            + Formatter.LS;

    public static String getNotusLogo() {
        return NOTUS_LOGO;
    }

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

    private static String createsHeaderSplit() {
        String formattedString;
        formattedString = ROW_SPLIT.repeat(MAX_CONTENT_LENGTH);
        return formattedString;
    }
}
