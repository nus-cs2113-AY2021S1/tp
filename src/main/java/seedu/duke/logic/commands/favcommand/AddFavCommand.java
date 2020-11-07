package seedu.duke.logic.commands.favcommand;

import seedu.duke.exceptions.CustomException;
import seedu.duke.logic.commands.commons.Command;

import seedu.duke.exceptions.ExceptionType;
import seedu.duke.model.favorite.FavList;
import seedu.duke.model.favorite.Fav;

import static seedu.duke.ui.Ui.addFavMessage;


public class AddFavCommand extends Command {

    /** Command to be saved by the user in the FavList.*/
    private final String command;
    /** Description for the command to be saved in the FavList.*/
    private final String description;
    /** Fav object that stores both command and description.*/
    private Fav item;

    public AddFavCommand(String inputCommand, String description) {
        this.command = inputCommand;
        this.description = description;
        super.isValid = false;
    }

    /**
     * Calls createFav() and addToFavList().
     *
     * @throws CustomException If command to be saved does not exist
     */
    @Override
    public void executeCommand() throws CustomException {
        if (command == null) {
            throw new CustomException(ExceptionType.INVALID_FAVOURITE);
        }
        createFav();
        addToFavList();
    }

    /**
     * creates a new Fav object.
     * If Fav object does not have a description, description will be set to "No description"
     */
    private void createFav() {
        if (description.equals(" ")) {
            item = new Fav(command.trim(), "No description");
        } else {
            item = new Fav(command.trim(), description.trim());
        }
    }

    /**
     * Adds the Fav object created from createFav() in to the FavList.
     * If a similar Fav object already exist in the FavList, Fav object will not be added.
     */
    private void addToFavList() {
        assert item != null : "No Fav object to be added.";
        if (!FavList.contains(item)) {
            FavList.addFav(item);
            addFavMessage(command);
        }
    }
}
