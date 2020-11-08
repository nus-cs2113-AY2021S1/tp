package seedu.eduke8.option;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.exception.Eduke8Exception;

import static seedu.eduke8.exception.ExceptionMessages.ERROR_OPTION_DOES_NOT_EXIST;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_NO_RIGHT_ANSWER;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a list of 4 options of a certain question.
 */
public class OptionList implements DisplayableList {
    private final ArrayList<Displayable> options;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public OptionList(ArrayList<Displayable> options) {
        this.options = options;
    }

    /**
     * Returns the list of options in the OptionList object.
     *
     * @return ArrayList of options in the OptionList object.
     */
    @Override
    public ArrayList<Displayable> getInnerList() {
        return this.options;
    }

    /**
     * Finds option with the specific description in the OptionList object.
     *
     * @param description Description of option to be found.
     * @return Displayable Option
     * @throws Eduke8Exception If the option does not exist.
     */
    @Override
    public Displayable find(String description) throws Eduke8Exception {
        for (Displayable option : options) {
            if (description.equalsIgnoreCase(option.getDescription())) {
                return option;
            }
        }
        throw new Eduke8Exception(ERROR_OPTION_DOES_NOT_EXIST);
    }

    /**
     * Returns the index of the correct option in the OptionList object.
     *
     * @return Index of the correct option in the OptionList object.
     * @throws Eduke8Exception If there is no correct answer in the list of options.
     */
    public int findCorrectOptionIndex() throws Eduke8Exception {
        for (int i = 0; i < getCount(); i++) {
            if (((Option) options.get(i)).isCorrectAnswer()) {
                return i;
            }
        }

        LOGGER.log(Level.WARNING, "Error with question: No right answer specified");
        throw new Eduke8Exception(ERROR_NO_RIGHT_ANSWER);
    }

    /**
     * Returns the number of options in the OptionList object.
     *
     * @return Number of options in the OptionList object.
     */
    @Override
    public int getCount() {
        return options.size();
    }
}
