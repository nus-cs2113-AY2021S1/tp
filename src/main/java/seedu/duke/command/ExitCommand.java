package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.human.User;

public class ExitCommand extends Command {
    protected static final String NULL_STRING = "";

    @Override
    public String execute(AnimeData animeData, User user) {
        setShouldExit();
        return NULL_STRING;
    }
}
