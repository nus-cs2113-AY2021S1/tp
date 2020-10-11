package seedu.duke.command;

import seedu.duke.exception.AniException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;

public abstract class Command {
    protected String description;
    
    public Command(String description) {
        setDescription(description);
    }
    
    public Command() {
        
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }

    public void execute(Ui ui, Storage storage, Watchlist currentWatchlist, 
                        ArrayList<Watchlist> watchlists) throws AniException {
        throw new AniException("This method should be implemented in the child class");
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
