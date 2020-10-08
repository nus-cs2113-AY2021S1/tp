package seedu.duke.parsers;

import seedu.duke.exceptions.SettingObjectWrongFormatException;

import java.util.Scanner;

import static seedu.duke.constants.InputMarkers.INPUT_COMMENT_MARKER;
import static seedu.duke.constants.RegexStrings.BLANK_STRING_REGEX;

public class Parsers {
    /**
     * Extract the value string from line in settings save file.
     *
     * @param fileLine    a line read from the file
     * @param objectTitle the string indicating the type of object
     * @return returns the setting extracted from line in the settings file
     * @throws SettingObjectWrongFormatException the linel in the settings file was wrongly formatted
     */
    public static String parseFileObject(String fileLine, String objectTitle)
            throws SettingObjectWrongFormatException {

        int settingTitleLength = objectTitle.length();
        String fileObject;

        // identify placements
        int settingObjectPosition = fileLine.indexOf(objectTitle);

        // check if placement is correct
        if (settingObjectPosition == -1) {
            throw new SettingObjectWrongFormatException();
        } else {
            try {
                fileObject = fileLine.substring(settingObjectPosition + settingTitleLength).trim();
            } catch (StringIndexOutOfBoundsException exception) {
                throw new SettingObjectWrongFormatException();
            }
        }
        return fileObject;
    }

    /**
     * Gets user input, ignore comments and blank lines.
     *
     * @param scanner Scanner object for console inputs
     * @return raw user input
     */
    public static String getUserInput(Scanner scanner) {
        String userInput;
        do {
            userInput = scanner.nextLine();
        } while (userInput.matches(BLANK_STRING_REGEX)
                || userInput.startsWith(INPUT_COMMENT_MARKER));
        return userInput;
    }
}
