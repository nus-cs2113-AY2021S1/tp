package seedu.duke.logic.parser;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;

public class ExecFavParser extends Parser {

    private String userInput;
    private int index;

    public ExecFavParser(String userInput) throws CustomException {
        super(userInput);
        this.userInput = userInput;
        checkInput();
    }

    public int getIndex() {
        return index;
    }

    private void checkInput() throws CustomException {
        if (super.getUserInput().isBlank()){
            throw new CustomException(ExceptionType.NO_INPUT);
        }
        try {
            int index = Integer.parseInt(super.getUserInput()) - 1;
            this.index=index;
        } catch (NumberFormatException e) {
            throw new CustomException(ExceptionType.NOT_A_NUMBER);
        }
    }


}
