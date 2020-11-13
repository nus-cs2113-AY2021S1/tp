package seedu.duke.command;

import seedu.duke.exception.CalorieCountException;
import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.EmptyTextFileException;
import seedu.duke.logic.parser.CommandParser;
import seedu.duke.ui.ExceptionMessages;
import seedu.duke.ui.Ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static seedu.duke.Trakcal.calList;
import static seedu.duke.Trakcal.executeCmd;
import static seedu.duke.Trakcal.in;
import static seedu.duke.Trakcal.jarFilePath;
import static seedu.duke.Trakcal.storage;
import static seedu.duke.ui.ExceptionMessages.displayCorruptedSetMessage;

//@@author jlifah
/**
 * Add Set Command.
 */
public class AddSetCommand extends Command {

    protected static final int MAXIMUM_CALORIE_COUNT = 3000;
    protected static final int MINIMUM_CALORIE_COUNT = 0;
    protected static final String DATE_FORMAT = "yyyy-MM-dd";
    protected static final String CALORIE_TAG = "c/";
    protected static final String FOOD_TAG = "f/";
    protected static final String EXERCISE_TAG = "e/";
    protected static final String DATE_TAG = "d/";
    protected String description;
    private static final String WHITE_SPACE = " ";

    public AddSetCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        String strDate = formatter.format(date);
        String filePath = jarFilePath + "/tpdata" + "/" + description + ".txt";

        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            if (file.exists()) {
                String line = reader.readLine();
                checkEmptyFile(filePath);

                boolean index = true;
                while (line != null) {
                    checkTags(line);
                    String description = line.substring(2, line.indexOf(CALORIE_TAG) - 1);
                    String calories = line.substring(line.indexOf(CALORIE_TAG) + 2);
                    calories = removeWhiteSpaces(calories);
                    description = removeWhiteSpaces(description);
                    checkEmptyDescription(description);
                    checkEmptyDescription(calories);
                    checkInteger(calories);
                    checkCalorieRange(calories);

                    if (index) {
                        Ui.displayAddSetConfirmationMessage();
                        index = false;
                    }

                    CommandParser parser = new CommandParser("add " + line + " " + DATE_TAG + " " + strDate);
                    Command cmd = parser.parseArgument();
                    executeCmd(cmd);
                    storage.updateFile(calList);
                    line = reader.readLine();
                }
            } else {
                throw new FileNotFoundException();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            ExceptionMessages.displayShortcutDoesNotExistMessage();
        } catch (IOException e) {
            ExceptionMessages.displayIoExceptionMessage();
        } catch (IllegalArgumentException | EmptyDescriptionException
                | CalorieCountException | EmptyTextFileException e) {
            displayCorruptedSetMessage();
            deleteInvalidSetFile(filePath);
        }

    }

    /**
     * Checks that the needed tags are in user input.
     *
     */
    private void checkTags(String input) throws IllegalArgumentException {
        if (!input.contains(CALORIE_TAG) || !(input.contains(FOOD_TAG) || input.contains(EXERCISE_TAG))) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks that user has input something following tags.
     *
     */
    private void checkEmptyDescription(String input) throws EmptyDescriptionException {
        if (input.isBlank() || input.isEmpty()) {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Checks that user input for "c/" can be converted to an integer.
     *
     */
    private void checkInteger(String input) throws NumberFormatException {
        Integer.parseInt(input);
    }

    /**
     * Checks that user input a calorie between 0 and 3000.
     *
     */
    private void checkCalorieRange(String input) throws CalorieCountException {
        if (Integer.parseInt(input) < MINIMUM_CALORIE_COUNT || Integer.parseInt(input) > MAXIMUM_CALORIE_COUNT) {
            throw new CalorieCountException();
        }
    }

    /**
     * Deletes any invalid/corrupted text file.
     *
     */
    public static void deleteInvalidSetFile(String filePath) {
        File file = new File(filePath);
        file.delete();
    }

    /**
     * Checks text file is not empty.
     *
     */
    public static void checkEmptyFile(String filePath) throws EmptyTextFileException {
        File file = new File(filePath);
        if (file.length() == 0) {
            throw new EmptyTextFileException();
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
//@@author jlifah
