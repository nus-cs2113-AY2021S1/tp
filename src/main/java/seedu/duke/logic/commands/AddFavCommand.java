package seedu.duke.logic.commands;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
import seedu.duke.favorite.Fav;
import seedu.duke.favorite.FavList;

import static seedu.duke.ui.Ui.addFavMessage;

public class AddFavCommand extends Command {


    private final String input;
    private final String description;
    private Fav item;

    public AddFavCommand(String input, String description) {
        this.input = input;
        this.description = description;
    }
    
    @Override
    public void executeCommand() throws CustomException {
        if (input == null) {
            throw new CustomException(ExceptionType.INVALID_FAVOURITE);
        }
        createFav();
        addToFavList();
    }

    private void createFav() {
        if (description.equals(" ")) {
            item = new Fav(input, input);
        } else {
            item = new Fav(input, description);
        }
    }

    private void addToFavList() throws CustomException {
        if (FavList.contains(item)) {
            throw new CustomException(ExceptionType.DUPLICATE_FAVOURITE);
        } else {
            FavList.addFav(item);
            addFavMessage(input);
        }
    }
}
