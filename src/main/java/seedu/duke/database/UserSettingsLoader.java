package seedu.duke.database;

import seedu.duke.exceptions.SettingObjectWrongFormatException;
import seedu.duke.parsers.Parsers;

import java.io.FileNotFoundException;

import static seedu.duke.constants.ClickerMessages.ERROR_READING_FILE_ON_LINE_MSG_FORMAT;
import static seedu.duke.constants.ClickerMessages.LOADING_SETTINGS_MSG;
import static seedu.duke.constants.ClickerMessages.SETTINGS_FILE_NOT_FOUND_MSG;
import static seedu.duke.constants.FilePaths.DEFAULT_USER_SETTINGS_FILE_PATH;
import static seedu.duke.constants.FilePaths.TEST_FILE_PATH;
import static seedu.duke.constants.Tags.USERNAME_TAG;
import static seedu.duke.database.FileFunctions.autoCreateNewFile;
import static seedu.duke.database.FileFunctions.readFileUntilLineContainsString;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class UserSettingsLoader {
    public static void loadUserSettings(ArrayList<String> savedSettings) {
        System.out.println(LOADING_SETTINGS_MSG);

        try {
            File userSettingsFile = FileFunctions.getFileFromFilePath(DEFAULT_USER_SETTINGS_FILE_PATH);
            FileFunctions.checkFileExists(userSettingsFile);
            readUserSettingsFile(savedSettings, userSettingsFile);
        } catch (FileNotFoundException e) {
            System.out.println(SETTINGS_FILE_NOT_FOUND_MSG);
            autoCreateNewFile(DEFAULT_USER_SETTINGS_FILE_PATH);
            //autoCreateNewFile(TEST_FILE_PATH);
        }
    }

    private static void readUserSettingsFile(ArrayList<String> savedSettings, File userSettingsFile)
            throws FileNotFoundException {
        Scanner userSettingsFileScanner = new Scanner(userSettingsFile);
        String fileLine = "";
        String username = "temp";

        try {
            String parsedString;

            fileLine = readFileUntilLineContainsString(USERNAME_TAG, userSettingsFileScanner);
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
     * Sets the default settings to saved settings array.
     *
     * @param savedSettings array of saved settings
     * @param username      user input name
     */
    public static void setUserSettingsArrayUsername(ArrayList<String> savedSettings, String username) {
        savedSettings.set(0, username);
    }

}
