package ui.diet.dietmanager;

import ui.CommonUi;

//@@author CFZeon
/**
 * A class that is responsible for user interface of Diet Manager.
 */
public class DietManagerUi extends CommonUi {
    public static String DIET_NO_SESSION_SAVED = "Sorry! It seems like you have no diet sessions saved!";
    public static String DIET_IO_WRONG_FORMAT = "It seems like we ran into some problems saving your session...";
    public static String DIET_FILE_ARRAY_OUT_OF_BOUND = "Sorry, there is no file at that index.";
    public static String DIET_FILE_NOT_FOUND = "Sorry, there seems to be no file.";
    public static String DIET_FILE_CORRUPTED = "Sorry, it seems like your file is corrupted...";
    public static String DIET_NEW_SUCCESS = "Exiting Diet Session!";
    public static String DIET_DELETE_SUCCESS = "You have deleted that diet session!";
    public static String DIET_EDIT_WRONG_FORMAT = "Wrong format, please enter in the format:\n\t "
            + "edit [INDEX_OF_SESSION]";
    public static String DIET_DELETE_WRONG_FORMAT = "Wrong format, please enter in the format:\n\t delete [INDEX]";
    public static String DIET_CREATE_WRONG_FORMAT = "Wrong format, please enter in the format: \n\t "
            + "new </d [DATE]> </t [TAG]>";
    public static String DIET_DATE_WRONG_FORMAT = "Wrong date format, please enter in the format:\n\t /d yyyy-MM-dd";
    public static String DIET_NO_SESSIONS_SAVED = "It looks like you have no sessions saved!";
    public static String DIET_SEARCH_RESULTS_MESSAGE = "Here are the search result(s)!";
    public static String DIET_SEARCH_EMPTY_TAG = "Tag is empty, "
            + "all the sessions within input dates will be shown.";
    public static String DIET_MENU_NAME = "Diet Menu";
    public static String CLEAR_RECORD = "clear all records";
    public static String DIET_CLEAR_MSG = "diet sessions have";
    public static String DIET_NOTHING_TO_CLEAR_MSG = "Sorry, there is no diet session to be cleared!";
    public static String DIET_FILE_CORRUPTED_MSG = "File Corrupted... Returning to Diet Menu...";
    public static String EMPTY_STRING = "";
}
