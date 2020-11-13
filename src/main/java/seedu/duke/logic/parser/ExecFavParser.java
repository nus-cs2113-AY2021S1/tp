package seedu.duke.logic.parser;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;

/**
 * Parses inputs by users into execfav command.
 */
public class ExecFavParser extends Parser {

    private String userInput;
    private int index;

    public ExecFavParser(String userInput) throws CustomException {
        super(userInput);
        this.userInput = userInput;
        setIndex();
    }

    public int getIndex() {
        return index;
    }

    /**
     * Checks and sets index input by user.
     *
     * @throws CustomException if user input cannot be parsed into integer or blank
     */
    private void setIndex() throws CustomException {
        assert ! (userInput == null) : "userInput not declared";
        if (userInput.isBlank()) {
            throw new CustomException(ExceptionType.EMPTY_INDEX);
        }
        try {
            int index = Integer.parseInt(userInput) - 1;
            this.index = index;
        } catch (NumberFormatException e) {
            throw new CustomException(ExceptionType.NOT_A_NUMBER);
        }
    }


}
