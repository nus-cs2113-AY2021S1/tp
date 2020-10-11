package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;

public class ExitCommand extends Command {
    
    public ExitCommand() {
        
    }

    @Override
    public void execute(Ui ui, Storage storage, Watchlist currentWatchlist,
                        ArrayList<Watchlist> watchlists) {
        new Ui().printGoodbyeMessage();
    }
}
