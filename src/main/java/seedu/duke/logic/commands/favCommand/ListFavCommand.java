package seedu.duke.logic.commands.favCommand;

import seedu.duke.favorite.FavList;
import seedu.duke.logic.commands.Command;


public class ListFavCommand extends Command {
    @Override
    public void executeCommand() {
        FavList.listFav();
    }
}
