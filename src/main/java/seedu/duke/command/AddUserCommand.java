package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;

public class AddUserCommand extends Command {

    public AddUserCommand(String description) {
        super(description);
    }

    /**
     * Adds an anime to current watchlist.
     */
    @Override
    public void execute(Ui ui, Storage storage, Watchlist currentWatchlist, ArrayList<Watchlist> watchlists) {
        String[] descriptionSplit = description.split(" ", 2);

        try {
            String commandOption = descriptionSplit[0];
            String animeName = descriptionSplit[1];

            if (commandOption.equals("-a") && animeName != null && !animeName.trim().isEmpty()) {
                currentWatchlist.addAnimeToList(animeName);
            } else {
                ui.showInvalidDescription("addToWatchlist");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showInvalidDescription("addToWatchlist");
        }
    }
}
