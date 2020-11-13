package seedu.duke.controller.parser;

import seedu.duke.common.LogManager;
import seedu.duke.exception.InvalidModelException;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author AndreWongZH
/**
 * Represents the process of extracting out the model to be performed on.
 */
public class ModelParser {
    public static final String INPUT_SCHEDULE_CLASS = "class";
    public static final String INPUT_SCHEDULE_TEST = "test";
    public static final String INPUT_SCHEDULE_CCA = "cca";
    public static final String INPUT_SCHEDULE_TUITION = "tuition";
    public static final String INPUT_QUIZ = "quiz";
    public static final String INPUT_CONTACT = "contact";
    public static final String INPUT_EVENT = "event";
    public static final String INPUT_HOURS = "hours";
    public static final int SUB_COMMAND_INDEX = 1;
    public static final String INPUT_SPACES = " ";
    public static final int MAIN_COMMAND_INDEX = 0;

    private final String[] separatedInputs;

    private ModelType modelType;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();

    public ModelParser(String userInput) {
        separatedInputs = userInput.toLowerCase().split(INPUT_SPACES);
        modelType = null;
    }

    /**
     * Checks if the second word in the input string matches any model word.
     * If it contains any model word, returns the respective modelType.
     *
     * @return ModelType corresponding to the model.
     * @throws InvalidModelException If the second word does not match any model word.
     */
    public ModelType extractModel() throws InvalidModelException {
        logger.log(Level.INFO, "Extracting model now...");

        if (separatedInputs[MAIN_COMMAND_INDEX].equals("quiz")) {
            return modelType = ModelType.QUIZ;
        }

        if (separatedInputs.length <= 1) {
            return null;
        }

        switch (separatedInputs[SUB_COMMAND_INDEX]) {
        case INPUT_SCHEDULE_CLASS:
            modelType = ModelType.CLASS;
            break;
        case INPUT_SCHEDULE_CCA:
            modelType = ModelType.CCA;
            break;
        case INPUT_SCHEDULE_TEST:
            modelType = ModelType.TEST;
            break;
        case INPUT_SCHEDULE_TUITION:
            modelType = ModelType.TUITION;
            break;
        case INPUT_CONTACT:
            modelType = ModelType.CONTACT;
            break;
        case INPUT_QUIZ:
            modelType = ModelType.QUIZ;
            break;
        case INPUT_EVENT:
            modelType = ModelType.EVENT;
            break;
        case INPUT_HOURS:
            modelType = ModelType.HOURS;
            break;
        default:
            throw new InvalidModelException();
        }

        return modelType;
    }
}
