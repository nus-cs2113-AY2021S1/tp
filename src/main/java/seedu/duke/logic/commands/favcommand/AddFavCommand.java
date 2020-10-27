package seedu.duke.logic.commands.favcommand;

import seedu.duke.exceptions.CustomException;
import seedu.duke.logic.commands.commons.Command;

import seedu.duke.exceptions.ExceptionType;
import seedu.duke.model.favorite.FavList;
import seedu.duke.model.favorite.Fav;

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

    private void addToFavList() {
        if (!FavList.contains(item)) {
            FavList.addFav(item);
            addFavMessage(input);
        }
    }
}
