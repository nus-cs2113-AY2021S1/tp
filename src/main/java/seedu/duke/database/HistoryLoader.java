package seedu.duke.database;

import seedu.duke.exceptions.SettingObjectWrongFormatException;
import seedu.duke.parsers.Parsers;
import seedu.duke.history.History;
import java.io.FileNotFoundException;
import static seedu.duke.constants.ClickerMessages.ERROR_READING_FILE_ON_LINE_MSG_FORMAT;
import static seedu.duke.constants.ClickerMessages.LOADING_HISTORY_MSG;
import static seedu.duke.constants.ClickerMessages.HISTORY_FILE_NOT_FOUND_MSG;
import static seedu.duke.constants.FilePaths.HISTORY_FILE_PATH;
import static seedu.duke.constants.Tags.USERNAME_TAG;
import static seedu.duke.database.FileFunctions.autoCreateNewFile;
import static seedu.duke.database.FileFunctions.readFileUntilLineContainsString;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import seedu.duke.history.History;

public class HistoryLoader {

    public static void loadHistorySettings(ArrayList<String> savedHistory) {
        System.out.println(LOADING_HISTORY_MSG);

        try {
            File userHistoryFile = FileFunctions.getFileFromFilePath(HISTORY_FILE_PATH);
            FileFunctions.checkFileExists(userHistoryFile);
            readUserHistoryFile(savedHistory, userHistoryFile);
        } catch (FileNotFoundException e) {
            System.out.println(HISTORY_FILE_NOT_FOUND_MSG);
            autoCreateNewFile(HISTORY_FILE_PATH);
        }
    }

    private static void readUserHistoryFile(ArrayList<String> savedHistory, File userHistoryfile)
            throws FileNotFoundException {
        Scanner s = new Scanner(userHistoryfile);
        String fileLine = "";
        String username = "temp";

        while(s.hasNext()) {

        }
    }

    public static void setUserHistoryArrayUsername(ArrayList<String> savedHistory, String username) {
        savedHistory.set(0, username);
    }

}
//package seedu.duke.database;
//
//import seedu.duke.exceptions.SettingObjectWrongFormatException;
//import seedu.duke.parsers.Parsers;
//
//import java.io.FileNotFoundException;
//import static seedu.duke.constants.ClickerMessages.ERROR_READING_FILE_ON_LINE_MSG_FORMAT;
//import static seedu.duke.constants.ClickerMessages.LOADING_HISTORY_MSG;
//import static seedu.duke.constants.ClickerMessages.HISTORY_FILE_NOT_FOUND_MSG;
//import static seedu.duke.constants.FilePaths.HISTORY_FILE_PATH;
//import static seedu.duke.constants.Tags.USERNAME_TAG;
//import static seedu.duke.database.FileFunctions.autoCreateNewFile;
//import static seedu.duke.database.FileFunctions.readFileUntilLineContainsString;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class HistoryLoader {
//
//    public static void loadHistorySettings(ArrayList<String> savedHistory) {
//        System.out.println(LOADING_HISTORY_MSG);
//
//        try {
//            File userHistoryFile = FileFunctions.getFileFromFilePath(HISTORY_FILE_PATH);
//            FileFunctions.checkFileExists(userHistoryFile);
//            readUserSettingsFile(savedHistory, userHistoryFile);
//        } catch (FileNotFoundException e) {
//            System.out.println(HISTORY_FILE_NOT_FOUND_MSG);
//            autoCreateNewFile(HISTORY_FILE_PATH);
//        }
//    }
//
//    private static void readUserSettingsFile(ArrayList<String> savedHistory, File userHistoryfile)
//            throws FileNotFoundException {
//        Scanner userHistoryFileScanner = new Scanner(userHistoryfile);
//        String fileLine = "";
//        String username = "temp";
//
//        try {
//            String parsedString;
//
//            fileLine = readFileUntilLineContainsString(USERNAME_TAG, userHistoryFileScanner);
//            parsedString = Parsers.parseFileObject(fileLine, USERNAME_TAG);
//            if (!parsedString.isBlank()) {
//                username = parsedString;
//                setUserHistoryArrayUsername(savedHistory, username);
//            }
//        } catch (SettingObjectWrongFormatException e) {
//            System.out.printf(ERROR_READING_FILE_ON_LINE_MSG_FORMAT, fileLine);
//        }
//    }
//
//    public static void setUserHistoryArrayUsername(ArrayList<String> savedHistory, String username) {
//        savedHistory.set(0, username);
//    }
//
//}
