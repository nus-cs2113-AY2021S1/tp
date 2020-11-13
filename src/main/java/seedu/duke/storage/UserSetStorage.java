package seedu.duke.storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import static seedu.duke.Trakcal.jarFilePath;
import static seedu.duke.Trakcal.logging;
import static seedu.duke.ui.ExceptionMessages.displayMissingAddSetInfoMessage;
import static seedu.duke.ui.ExceptionMessages.displayInvalidCalorieMessage;
import static seedu.duke.ui.ExceptionMessages.displayInvalidCreateSetCommandMessage;
import static seedu.duke.ui.ExceptionMessages.displayIncompleteSetMessage;
import static seedu.duke.ui.ExceptionMessages.displayCalorieMustBeIntegerMessage;
import static seedu.duke.ui.ExceptionMessages.displayMissingFileNameMessage;

import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.FileAlreadyExistException;
import seedu.duke.exception.InvalidCalorieException;
import seedu.duke.exception.InvalidCreateSetCommandException;
import seedu.duke.exception.NoFileNameException;
import seedu.duke.ui.Ui;

import static seedu.duke.ui.ExceptionMessages.displayExistingShortcutMessage;
import static seedu.duke.ui.ExceptionMessages.displayIoExceptionMessage;


/**
 * Updates the text file with short cut info.
 */
public class UserSetStorage {
    private static final Integer MAX_CALORIES = 3000;
    private static final Integer MIN_CALORIES = 0;
    private static final String WHITE_SPACE = " ";
    private static final String FOOD_TAG = "f/";
    private static final String CALORIE_TAG = "c/";
    private static final String EXERCISE_TAG = "e/";
    private static final String SLASH = "/";

    /**
     * Checks if tags and file are given by user.
     *
     * @param userInput command given by user to createSet
     */
    public static void prepareNewSet(String userInput) {
        try {
            checkFileNameNotPresent(userInput);
            checkActivityAndCalorieTag(userInput);
            String fileName = userInput.substring(0, userInput.indexOf(SLASH) - 2);
            createNewTextFile(SLASH + fileName + ".txt", userInput.substring(userInput.indexOf(SLASH) - 1));
        } catch (NoFileNameException e) {
            displayMissingFileNameMessage();
        } catch (InvalidCreateSetCommandException e) {
            displayInvalidCreateSetCommandMessage();
            displayIncompleteSetMessage();
        }
    }

    /**
     * Creates a new text file with shortcut surname.
     *
     * @param fileName shortcut name
     * @param toTrim contents to be added into shortcut
     */
    public static void createNewTextFile(String fileName, String toTrim) {
        String filePath = jarFilePath + "/tpdata" + fileName;

        try {
            logging.writeToLogInfo("Attempting to create shortcut file.");
            checkExistingFile(filePath);
            File file = new File(filePath);
            file.createNewFile();
            logging.writeToLogInfo("Shortcut file successfully created.");

        } catch (IOException e) {
            displayIoExceptionMessage();
        } catch (FileAlreadyExistException e) {
            displayExistingShortcutMessage();
            return;
        }
        updateTextFile(filePath, toTrim);
    }

    /**
     * Deletes an corrupted text file.
     *
     * @param filePath name of shortcut to be deleted
     */
    public static void deleteInvalidSetFile(String filePath) {
        logging.writeToLogInfo("Attempting to deleted corrupted shortcut file.");
        File file = new File(filePath);
        file.delete();
        logging.writeToLogInfo("Corrupted shortcut file deleted successfully.");
    }

    /**
     * Writes shortcut content to text file.
     *
     * @param path shortcut name
     * @param toTrim contents to be added into shortcut
     */
    public static void updateTextFile(String path, String toTrim) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            String[] activity = toTrim.split("\\+");
            int index = 1;

            for (String s : activity) {
                logging.writeToLogInfo("Check if entry is of valid format.");
                s = removeWhiteSpaces(s);
                bw.write(s);
                String calories = s.substring(s.indexOf(CALORIE_TAG) + 2);
                calories = removeWhiteSpaces(calories);
                String description = s.substring(2,s.indexOf(CALORIE_TAG) - 1);
                description = removeWhiteSpaces(description);
                checkEmptyDescription(description);
                checkEmptyDescription(calories);
                checkCalorieType(calories);
                checkValidCalorieRange(calories);
                Integer.parseInt(calories);
                logging.writeToLogInfo("Entry is of valid format.");

                if (index == 1) {
                    Ui.drawDivider();
                    System.out.println("You have created a shortcut containing:");
                }

                if (s.indexOf(FOOD_TAG) == 0) {
                    System.out.println(index + ". " + "Food: " + description
                            + ", Calories: " + calories);
                } else if (s.indexOf(EXERCISE_TAG) == 0) {
                    System.out.println(index + ". " + "Exercise: " + description
                            + ", Calories: " + calories);
                }

                index++;
                bw.newLine();
            }

            Ui.drawDivider();
            System.out.println();
            bw.close();

        } catch (IOException e) {
            displayIncompleteSetMessage();
            deleteInvalidSetFile(path);
        }  catch (InvalidCalorieException e) {
            displayInvalidCalorieMessage();
            displayIncompleteSetMessage();
            deleteInvalidSetFile(path);
        } catch (EmptyDescriptionException e) {
            displayMissingAddSetInfoMessage();
            displayIncompleteSetMessage();
            deleteInvalidSetFile(path);
        } catch (NumberFormatException e) {
            displayCalorieMustBeIntegerMessage();
            displayIncompleteSetMessage();
            deleteInvalidSetFile(path);
        }
    }

    /**
     * Checks whether a description given by user is empty or null.
     *
     * @param description user input
     */
    private static void checkEmptyDescription(String description) throws EmptyDescriptionException {
        if (description.isBlank() || description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Check that an existing shortcut exists.
     *
     * @param fileName short cut name
     */
    private static void checkExistingFile(String fileName) throws FileAlreadyExistException {
        File file = new File(fileName);
        if (file.exists()) {
            throw new FileAlreadyExistException();
        }
    }

    /**
     * Check that there are activity and calorie has tag.
     *
     * @param input user input
     */
    private static void checkActivityAndCalorieTag(String input) throws InvalidCreateSetCommandException {
        if (!input.contains(CALORIE_TAG) || !(input.contains(FOOD_TAG) | input.contains(EXERCISE_TAG))) {
            throw new InvalidCreateSetCommandException();
        }
    }

    /**
     * Check that calorie is within 0 and 3000 kcal.
     *
     * @param input user input
     */
    private static void checkValidCalorieRange(String input) throws InvalidCalorieException {
        int calories = Integer.parseInt(input);
        if (calories < MIN_CALORIES || calories > MAX_CALORIES) {
            throw new InvalidCalorieException();
        }
    }

    /**
     * Check that calorie is of integer type.
     *
     * @param input user input
     */
    private static void checkCalorieType(String input) throws NumberFormatException {
        Integer.parseInt(input);
    }

    /**
     * Check if user gave short cut name.
     *
     * @param input user input
     */
    private static void checkFileNameNotPresent(String input) throws NoFileNameException {
        if (input.indexOf(FOOD_TAG) == 0 || input.indexOf(EXERCISE_TAG) == 0) {
            throw new NoFileNameException();
        }
    }

    /**
     * Removes all white spaces at front and back of input.
     *
     * @param input user input
     * @return input of edited param without white spaces
     */
    private static String removeWhiteSpaces(String input) {
        while (input.startsWith(WHITE_SPACE)) {
            input = input.substring(1);
        }

        while (input.endsWith(WHITE_SPACE)) {
            input = input.substring(0, input.length() - 1);
        }
        return input;
    }
}