package seedu.duke.logic.commands;

import seedu.duke.Favourites;
import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;

public class AddFavCommand extends Command {


    private final String input;
    private final String description;

    public  AddFavCommand(String input, String description) {
        this.input = input;
        this.description = description;
    }
    
    @Override
    public void executeCommand() throws CustomException {
        if (input == null) {
            throw new CustomException(ExceptionType.INVALID_FAVOURITE);
        }
        Favourites.addFav(input, description);
    }
}
