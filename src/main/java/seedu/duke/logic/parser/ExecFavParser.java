package seedu.duke.logic.parser;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;

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

    private void setIndex() throws CustomException {
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
