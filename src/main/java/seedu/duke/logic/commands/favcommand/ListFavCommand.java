package seedu.duke.logic.commands.favcommand;

import seedu.duke.exceptions.CustomException;
import seedu.duke.favorite.FavList;
import seedu.duke.logic.commands.Command;

public class ListFavCommand extends Command {

    @Override
    public void executeCommand() throws CustomException {
        FavList.listFav();
    }
}
