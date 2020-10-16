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
    private static final String ROW_SPLIT = "=";
    private static final String COLUMN_SPLIT = "|";
    private static final String COLUMN_START = "|| ";
    private static final String COLUMN_END = " ||";
    private static final String EMPTY_SPACE = " ";

    /** Maximum number of characters within a row. */
    private static final int MAX_ROW_LENGTH = 100;
    /** Maximum length of message to within a row, minus the start and end formatting. */
    private static final int MAX_MESSAGE_LENGTH = MAX_ROW_LENGTH - COLUMN_START.length() - COLUMN_END.length();


    // Character code adapted from http://patorjk.com/software/taag/#p=display&f=Ghost&t=NotUS
    // Slight modifications made to make it easier on the eyes
    private static final String NOTUS_LOGO = LS
            + LS
            + "     .-') _               .-') _                 .-')    "
            + LS
            + "    ( OO ) )             (  OO) )               ( OO ).  "
            + LS
            + ",--./ ,--,'  .-'),-----. /     '._ ,--. ,--.   (_)---\\_) "
            + LS
            + "|   \\ |  |\\ ( OO'  .-.  '|'--...__)|  | |  |   /    _ |  "
            + LS
            + "|    \\|  | )/   |  | |  |'--.  .--'|  | | .-') \\  :` `.  "
            + LS
            + "|  .     |/ \\_) |  | |  |   |  |   |  | |( OO ) '..`''.) "
            + LS
            + "|  |\\    |    \\ |  | |  |   |  |   |  | | `-' /.-._)   \\ "
            + LS
            + "|  | \\   |     `'  '-'  '   |  |  ('  '-'(_.-' \\       / "
            + LS
            + "`--'  `--'       `-----'    `--'    `-----'     `-----'  "
            + LS;

    public static String getNotusLogo() {
        return NOTUS_LOGO;
    }

    public static String formatNotebook(String header, Notebook notebook) {
        String formattedString = "";
        return formattedString;
    }

    public static String formatNotebook(Notebook notebook) {
        String formattedString = "";
        return formattedString;
    }

    public static String formatNote(String header, Note note) {
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

    /**
     * Formats a string to be printed out.
     *
     * @param message String to be formatted.
     * @param isColored Determines if the string is colored.
     * @return Formatted message.
     */
    public static String formatString(String message, boolean isColored) {
        return encloseTopAndBottom(encloseRow(message, isColored));
    }

    /**
     * Formats an arraylist of string to be printed out. Each element in the list will be printed in a newline.
     *
     * @param messages Arraylist of strings to be formatted.
     * @param hasHeader Determines if there is a header. Header MUST be the first element in the list.
     * @param isColored Determines if there is any word in the string that is colored.
     * @return Formatted message.
     */
    public static String formatString(ArrayList<String> messages, boolean hasHeader, boolean isColored) {
        String formattedString = "";
        if (hasHeader) {
            formattedString = generatesHeader(messages.get(0));

            for (int i = 1; i < messages.size(); ++i) {
                formattedString = formattedString.concat(encloseRow(messages.get(i),isColored));
            }
        } else {
            for (String s : messages) {
                formattedString = formattedString.concat(encloseRow(s, isColored));
            }
        }
        return encloseTopAndBottom(formattedString);
    }

    /**
     * Generates a header row with the format.
     *
     * @param header Header message
     * @return Formatted header.
     */
    private static String generatesHeader(String header) {
        return encloseRow(header, false) + generatesRowSplit();
    }

    /**
     * Generates a row of pre-defined characters as to segregate row contents.
     *
     * @return A row of defined characters.
     */
    private static String generatesRowSplit() {
        return ROW_SPLIT.repeat(MAX_ROW_LENGTH) + LS;
    }

    /**
     * Encloses the top and bottom of the formatted message.
     *
     * @param message Formatted message to be enclosed.
     * @return Enclosed message.
     */
    private static String encloseTopAndBottom(String message) {
        return generatesRowSplit() + message + generatesRowSplit();
    }

    /**
     * Encloses the sides of the message.
     *
     * @param message Message to be enclosed.
     * @param isColored Determines if there is any word in the string that is colored.
     * @return Enclosed message.
     */
    private static String encloseRow(String message, boolean isColored) {
        int numBlanks;

        // Calculates the number of blank cells according to the message.
        // For colored text, ignore the ansi codes.
        if (isColored) {
            numBlanks = MAX_MESSAGE_LENGTH - message.length() + Tag.NUM_ANSI_CHAR;
        } else {
            numBlanks = MAX_MESSAGE_LENGTH - message.length();
        }

        // Adds empty space to the message
        if (numBlanks >= 0) {
            return COLUMN_START + message + EMPTY_SPACE.repeat(numBlanks) + COLUMN_END + LS;
        } else {
            // Cut off the message and prints it on the next line.
            int startIndex = message.length() + numBlanks;
            String preservedMessage = message.substring(0, startIndex);
            String truncatedMessage = message.substring(startIndex);
            return encloseRow(preservedMessage, isColored) + encloseRow(truncatedMessage, isColored);
        }
    }
}
