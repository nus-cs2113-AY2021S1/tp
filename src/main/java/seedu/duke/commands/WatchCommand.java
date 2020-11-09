package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.classes.WatchTime;
import seedu.duke.utility.ShowList;
import seedu.duke.utility.Ui;

import java.util.ArrayList;

/**
 * Represents a Command to update the watch progress of the user after watching a show.
 */
public class WatchCommand extends Command {
    ArrayList<String> inputs;

    public static final Integer VALID_NUMBER_OF_ARGUMENTS = 2;
    public static final Integer SECOND_ARGUMENT_IN_INPUT = 1;
    public static final Integer FIRST_EPISODE_IN_NEW_SEASON = 1;
    public static final Integer INCREMENT_SEASON_OR_EPISODE = 1;

    public WatchCommand(String description, ArrayList<String> inputs) throws NullPointerException {
        super(description);
        this.inputs = inputs;
        if (inputs.size() != VALID_NUMBER_OF_ARGUMENTS) {
            throw new NullPointerException();
        }
    }

    /**
     * Notifies the application that user has finished his current episode of a show
     * in which the current episode will be incremented by 1, and watch time will be updated.
     * The watchlist updates the show to a new season if required.
     */
    public void processCommand() {

        String showName = inputs.get(SECOND_ARGUMENT_IN_INPUT);
        Show show = ShowList.getShow(showName);

        int showEpisode = show.getCurrentEpisode();
        int currentSeason = show.getCurrentSeason();
        int showSeason = show.getNumSeasons();
        int episodesInSeason = show.getEpisodesForSeason(currentSeason);
        boolean hasFinishedSeason = (showEpisode == episodesInSeason);
        boolean isLastSeason = (currentSeason == showSeason);
        boolean hasFinishedSeries = (isLastSeason && hasFinishedSeason);

        if (hasFinishedSeries) {
            Ui.printFinishedAllSeasons(showName);
            return;
        } else if (hasFinishedSeason) {
            int updatedSeason = currentSeason + INCREMENT_SEASON_OR_EPISODE;
            show.setCurrentSeason(updatedSeason);
            show.setEpisodeWatched(FIRST_EPISODE_IN_NEW_SEASON);
            ShowList.setShow(showName, show);
            Ui.printWatchingNewSeason(showName, updatedSeason);
            Ui.printChangeEpisode(showName);
        } else {
            show.setEpisodeWatched(showEpisode + INCREMENT_SEASON_OR_EPISODE);
            ShowList.setShow(showName, show);
            Ui.printChangeEpisode(showName);
        }
        int showDuration = show.getEpisodeDuration();
        WatchTime.watchDurationUpdate(showDuration);

    }
}
