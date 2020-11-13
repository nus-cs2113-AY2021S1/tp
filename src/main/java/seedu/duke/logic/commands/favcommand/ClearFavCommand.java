package seedu.duke.logic.commands.favcommand;

import seedu.duke.logic.commands.commons.Command;
import seedu.duke.model.favorite.FavList;

public class ClearFavCommand extends Command {

    public ClearFavCommand() {
        super.isValid = false;
    }

    @Override
    public void executeCommand() {
        FavList.clearFav();
    }

}
