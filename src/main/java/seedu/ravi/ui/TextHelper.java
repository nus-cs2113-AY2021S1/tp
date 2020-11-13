package seedu.ravi.ui;

import static seedu.ravi.util.Message.MESSAGE_LOADING_TEMPLATE;

public class TextHelper {
    public static final String DIV_LINE =
            "════════════════════════════════════════════════════════════════════════════════";

    public static final String MESSAGE_TIMETABLE_HEADER =
            " ┌───────────┬────┬────────────────────┐\n"
                    + " │   Time    │ ID │       Lesson       │\n"
                    + " ├───────────┼────┼────────────────────┤\n";
    public static final String MESSAGE_TIMETABLE_MIDDLE = "\n ├───────────┼────┼────────────────────┤\n";
    public static final String MESSAGE_TIMETABLE_FOOTER = "\n └───────────┴────┴────────────────────┘\n";

    public static final int MAX_WIDTH = DIV_LINE.length();

    //Offset required to convert between 1-indexing and 0-indexing
    public static final int DISPLAY_INDEX_OFFSET = 1;

    //%1$ catches the furthest left arg, %2$ catches the 2nd arg
    private static final String MESSAGE_INDEX_LIST_FORMAT = "\n%1$d. %2$s";

    /**
     *  Formats a string with its index in the list.
     *
     * @param listIndex task/module index
     * @param listItem task/module name or description
     * @return String containing the index list in the intended format
     */
    public static String getIndexListFormat(int listIndex, String listItem) {
        return String.format(MESSAGE_INDEX_LIST_FORMAT, listIndex, listItem);
    }

    /**
     * Returns a string of the loading status based on the status code.
     * Status parameter is a 4 digit number. First 3 digits are Hundreds: Timetable, Tens: Tasks, Ones: Modules.
     * 0 - Files do not exist, skipping
     * 1 - Files exist, loading success
     * 2 - Files exist, error parsing JSON
     * Then for the Thousands: NUSMods.
     * 0 - Loaded from data directory
     * 1 - Loaded from NUSMods API (subsequently to be saved to data directory)
     * 2 - No internet connection, loaded from backup in jar file
     *
     * @param status
     *  The status code parameter
     * @return
     *  The loading status string
     */
    public static String parseStatusCode(int status) {
        String[] items = {"Modules", "Tasks", "Timetable"};
        int latestCode;
        String eachItem;
        String statusMsg;
        String loadingOutcomes = "";
        for (int i = 0; i < 3; i++) {
            eachItem = items[i];
            latestCode = status % 10; // find ones digit of status
            status /= 10; // remove ones digit
            switch (latestCode) {
            case 0:
                statusMsg = "@|bold,blue,BG_BLACK Skipped (file not found!)|@";
                break;
            case 1:
                statusMsg = "@|bold,green,BG_BLACK Success!|@";
                break;
            case 2:
                statusMsg = "@|bold,red,BG_BLACK Failed (corrupted file auto-renamed)|@";
                break;
            default:
                statusMsg = "You shouldn't be here";
                break;
            }
            loadingOutcomes += centerString(String.format(MESSAGE_LOADING_TEMPLATE, eachItem, statusMsg)) + "\n";
        }

        // Now find the NUSMods status, the remaining digit in the status code
        String nusModsStatus;
        switch (status) {
        case 0:
            nusModsStatus = "@|bold,green,BG_BLACK Loaded from data directory!|@";
            break;
        case 1:
            nusModsStatus = "@|bold,blue,BG_BLACK Downloaded latest version!|@";
            break;
        case 2:
            nusModsStatus = "@|bold,red,BG_BLACK No internet - using packaged backup|@";
            break;
        default:
            nusModsStatus = "You shouldn't be here";
            break;
        }
        loadingOutcomes += centerString(String.format(MESSAGE_LOADING_TEMPLATE, "NUSMods", nusModsStatus)) + "\n";
        return loadingOutcomes;
    }

    /**
     * Simple function that returns a string centered in 'width' number of characters.
     * Empty characters (i.e. left/right padding) are spaces.
     * DOES NOT ACCOUNT for the formatting required by JANSI!
     *
     * @param width
     *  The number of characters for width
     * @param s
     *  The string to be centered
     * @return
     *  The centered string
     */
    public static String centerString(int width, String s) {
        return String.format("%-" + width  + "s",
                String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    /**
     * Overloaded centerString that defaults width to MAX_WIDTH.
     * Accounts for the formatting required by JANSI.
     *
     * @param s
     *  The string to be centered
     * @return
     *  The centered string
     */
    public static String centerString(String s) {
        String temp = s;
        if (s.contains("@|")) {
            // If the string is "a@|...b|@c",
            String[] splitFrontFormat = s.split("@\\S*\\s+", 0); // this is [a, b|@c]
            String[] splitBackFormat = splitFrontFormat[1].split("\\|@", 0); // this is [b, c]
            temp = splitFrontFormat[0] + splitBackFormat[0]
                    + (splitBackFormat.length == 2 ? splitBackFormat[1] : ""); // Get a + b + c (c might be null)
        }
        int numSpaces = (MAX_WIDTH - temp.length()) / 2;
        String spaces = String.format("%" + numSpaces + "s", "");
        return spaces + s;
    }
}
