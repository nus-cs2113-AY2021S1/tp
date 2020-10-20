package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.User;
import seedu.duke.human.Workspace;
import seedu.duke.storage.StorageManager;

import java.util.logging.Logger;

import static seedu.duke.logger.AniLogger.getAniLogger;

public class EstimateCommand extends Command {
    private static final int NO_WORDS_PER_HOUR_PROVIDED = -1;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int[] AVERAGE_TRANSLATOR_WORDS_PER_HOUR = {400, 500, 600};

    private static final Logger LOGGER = getAniLogger(EstimateCommand.class.getName());

    private String scriptFileName;
    private int wordsPerHour;

    public EstimateCommand(String scriptFileName, int wordsPerHour) {
        this.scriptFileName = scriptFileName;
        this.wordsPerHour = wordsPerHour;
    }

    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) throws AniException {
        Workspace activeWorkspace = user.getActiveWorkspace();
        assert activeWorkspace.getWatchlistList() != null : "Watchlist list should not be null.";
        String fileString = storageManager.readScript(activeWorkspace.getName(), scriptFileName);
        int wordCount = fileString.split(" ").length;

        StringBuilder commandResult = new StringBuilder();
        if (wordsPerHour != NO_WORDS_PER_HOUR_PROVIDED) {
            double timeNeeded = wordCount / (double) wordsPerHour;
            commandResult.append("You would need ");
            commandResult.append(getHoursAndMinutesNeeded(timeNeeded));
        } else {
            for (int averageWordsPerHour : AVERAGE_TRANSLATOR_WORDS_PER_HOUR) {
                double timeNeeded = wordCount / (double) averageWordsPerHour;
                commandResult.append("Average translators (");
                commandResult.append(averageWordsPerHour);
                commandResult.append(" words per hour) takes: ");
                commandResult.append(getHoursAndMinutesNeeded(timeNeeded));
                commandResult.append(System.lineSeparator());
            }
            commandResult.setLength(commandResult.length() - 1);    // Replace last new line
        }

        return commandResult.toString();
    }

    private String getHoursAndMinutesNeeded(double timeNeeded) {
        double hoursNeeded = Math.floor(timeNeeded);
        double minutesNeeded = (timeNeeded - hoursNeeded) * MINUTES_PER_HOUR;
        String hoursAndMinutesNeeded = (int) hoursNeeded + " hour(s) " + (int) minutesNeeded + " minute(s).";
        return hoursAndMinutesNeeded;
    }
}
