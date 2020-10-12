package seedu.duke.parsers;

import seedu.duke.exceptions.SettingObjectWrongFormatException;
import seedu.duke.exceptions.MissingParamsException;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

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

    /**
     * Parse parameters for double letter tags.
     *
     * @param userInput    line read from the console
     * @param parsedParams parameters parsed from the line with the tag as key and argument as value
     * @throws MissingParamsException line has no missing parameters
     */
    public static void parseDoubleCharacterTaggedParamsFromUserInput(String userInput,
                                                                     HashMap<String, String> parsedParams)
            throws MissingParamsException {

        String parsedOption;
        String optionIndicator;

        int startPositionIndex = 0;
        int endPositionIndex;

        // clear filter options
        parsedParams.clear();

        if (!userInput.contains("/")) {
            throw new MissingParamsException();
        }

        while (userInput.indexOf("/", startPositionIndex) != -1) {
            // identify placement
            startPositionIndex = userInput.indexOf("/", startPositionIndex + 1);
            endPositionIndex = userInput.indexOf("/", startPositionIndex + 1);
            // if reached end of string
            if (endPositionIndex == -1) {
                break;
            }
            // extract the option
            parsedOption = userInput.substring(startPositionIndex + 1, endPositionIndex - 2);
            optionIndicator = userInput.substring(startPositionIndex - 2, startPositionIndex);
            // store the option
            parsedParams.put(optionIndicator, parsedOption);
        }

        // extract the option
        parsedOption = userInput.substring(startPositionIndex + 1);
        optionIndicator = userInput.substring(startPositionIndex - 2, startPositionIndex);
        // store the option
        parsedParams.put(optionIndicator.toLowerCase(), parsedOption);
    }

    /**
     * Parse parameters for single letter tags.
     *
     * @param userInput    line read from the console
     * @param parsedParams parameters parsed from the line with the tag as key and argument as value
     * @throws MissingParamsException line has no missing parameters
     */
    public static void parseSingleCharacterTaggedParamsFromUserInput(String userInput,
                                                                     HashMap<String, String> parsedParams)
            throws MissingParamsException {

        String parsedOption;
        String optionIndicator;

        int startPositionIndex = 0;
        int endPositionIndex;

        // clear filter options
        parsedParams.clear();

        if (!userInput.contains("/")) {
            throw new MissingParamsException();
        }

        while (userInput.indexOf("/", startPositionIndex) != -1) {
            // identify placement
            startPositionIndex = userInput.indexOf("/", startPositionIndex + 1);
            endPositionIndex = userInput.indexOf("/", startPositionIndex + 1);
            // if reached end of string
            if (endPositionIndex == -1) {
                break;
            }
            // extract the option
            parsedOption = userInput.substring(startPositionIndex + 1, endPositionIndex - 1);
            optionIndicator = userInput.substring(startPositionIndex - 1, startPositionIndex);
            // store the option
            parsedParams.put(optionIndicator, parsedOption);
        }

        // extract the option
        parsedOption = userInput.substring(startPositionIndex + 1);
        optionIndicator = userInput.substring(startPositionIndex - 1, startPositionIndex);
        // store the option
        parsedParams.put(optionIndicator.toLowerCase(), parsedOption);
    }

    /** Parse an integer from a string containing an integer */
    public static int getIntFromString(String stringContainingInt) throws NumberFormatException {
        return Integer.parseInt(stringContainingInt.replaceAll("[\\D]", ""));
    }

}
