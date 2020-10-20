package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.classes.WatchTime;
import seedu.duke.utility.ShowList;
import seedu.duke.utility.Ui;

import java.util.ArrayList;


public class WatchCommand extends Command {
    ArrayList<String> inputs;

    public WatchCommand(String description, ArrayList<String> inputs) throws NullPointerException {
        super(description);
        this.inputs = inputs;
        if (inputs.size() != 2) {
            throw new NullPointerException();
        }
    }

    /**
     * Notifies the application that user has finished his current episode of a show
     * in which the current episode will be incremented by 1, and watch time will be updated.
     * The watchlist updates the show to a new season if required.
     */
    //INPUT : watch <show name>
    public void processCommand() {
        // todo: check if the date is still the same as the one in the save file,
        //  yes: increment the timeWatchedToday, Ui to include the total hours watched today and hours left.
        //  no: reset the timeWatchedToday to 0, set the date to today and increment and output the time left to
        //  the user.

        String showName = inputs.get(1);
        Show show = ShowList.getShow(showName);
        int showEpisode = show.getCurrentEpisode();
        int showSeason = show.getCurrentSeason();
        int episodesInSeason = show.getEpisodesForSeason(showSeason);
        boolean hasFinishedSeason = (showEpisode == episodesInSeason);

        if (!hasFinishedSeason) {
            show.setEpisodeWatched(showEpisode + 1);
            ShowList.setShow(showName, show);
        } else if (hasFinishedSeason) {
            show.setCurrentSeason(showSeason + 1);
            show.setEpisodeWatched(1);
            ShowList.setShow(showName, show);
            Ui.printChangeEpisode(showName);
            Ui.printChangeEpisode(showName);
        } else {
            Ui.printFinishedAllSeasons(showName);
        }
        int showDuration = show.getEpisodeDuration();
        WatchTime.watchDurationUpdate(showDuration);
    }
}
