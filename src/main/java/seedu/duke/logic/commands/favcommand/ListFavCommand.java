package seedu.duke.logic.commands.favcommand;

import seedu.duke.model.favorite.FavList;
import seedu.duke.logic.commands.commons.Command;


public class ListFavCommand extends Command {
    @Override
    public void executeCommand() {
        FavList.listFav();
    }
}
