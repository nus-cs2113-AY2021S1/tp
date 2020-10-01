package seedu.duke.database;

import seedu.duke.exceptions.*;
import seedu.duke.parsers.*;

import java.util.*;

import java.io.FileNotFoundException;

import static seedu.duke.constants.ClickerMessages.*;
import static seedu.duke.constants.FilePaths.DEFAULT_USER_SETTINGS_FILE_PATH;
import static seedu.duke.constants.Tags.USERNAME_TAG;
import static seedu.duke.database.fileFunctions.readFileUntilLineContainsString;

import java.io.File;


public class UserSettingsLoader {
    public static void loadUserSettings(ArrayList<String> savedSettings) {
        System.out.println(LOADING_SETTINGS_MSG);

        try {
            File userSettingsFile = fileFunctions.getFileFromFilePath(DEFAULT_USER_SETTINGS_FILE_PATH);
            fileFunctions.checkFileExists(userSettingsFile);
            readUserSettingsFile(savedSettings, userSettingsFile);
        } catch (FileNotFoundException e) {
            System.out.println(SETTINGS_FILE_NOT_FOUND_MSG);
            //fileFunctions.autoCreateNewFile(DEFAULT_USER_SETTINGS_FILE_PATH);
        }
    }

    private static void readUserSettingsFile(ArrayList<String> savedSettings, File userSettingsFile)
            throws FileNotFoundException {
        Scanner USER_SETTINGS_FILE_SCANNER = new Scanner(userSettingsFile);
        String fileLine = "";
        String username = "temp";

        try {
            String parsedString;

            fileLine = readFileUntilLineContainsString(USERNAME_TAG, USER_SETTINGS_FILE_SCANNER);
            parsedString = Parsers.parseFileObject(fileLine, USERNAME_TAG);
            if (!parsedString.isBlank()) {
                username = parsedString;
                setUserSettingsArrayUsername(savedSettings, username);
            }

        } catch (SettingObjectWrongFormatException e) {
            System.out.printf(ERROR_READING_FILE_ON_LINE_MSG_FORMAT, fileLine);
        }
    }

    /**
     * Sets the default settings to saved settings array
     *
     * @param savedSettings array of saved settings
     * @param username      user input name
     */
    public static void setUserSettingsArrayUsername(ArrayList<String> savedSettings, String username) {
        savedSettings.set(0, username);
    }

}
