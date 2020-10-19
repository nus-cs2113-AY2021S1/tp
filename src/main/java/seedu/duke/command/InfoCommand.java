package seedu.duke.command;

import seedu.duke.anime.Anime;
import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.User;
import seedu.duke.storage.StorageManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InfoCommand extends Command {
    protected static final String OUT_OF_BOUND_INDEX_ERROR = "Anime ID is invalid!";
    
    private Integer animeIndex;
    private static final Logger LOGGER = Logger.getLogger(InfoCommand.class.getName());

    public InfoCommand() {
        LOGGER.setLevel(Level.WARNING);
    }

    /**
     * Shows information of anime 
     */
    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) {
        animeData.printOne(animeIndex);

        return "Anime info shown";
    }

    public void setAnimeIndex(Integer animeIndex) {
        this.animeIndex = animeIndex - 1;
    }
}
