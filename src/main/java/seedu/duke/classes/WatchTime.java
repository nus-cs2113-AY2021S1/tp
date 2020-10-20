package seedu.duke.classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WatchTime {
    private static LocalDate recordedDate;
    private static int durationWatchedToday;
    private static int dailyWatchLimit;

    /**
     * If no data, set durationWatchedToday to 0 and set recordedDate to LocalDate.now()
     * Default dailyWatchLimit to 60 minutes until user provides input
     *
     * @param recordedDate         the last date recorded when durationWatched is updated
     * @param durationWatchedToday duration of shows watched today
     * @param dailyWatchLimit      total limit set by user
     */
    public WatchTime(LocalDate recordedDate, int durationWatchedToday, int dailyWatchLimit) {
        this.recordedDate = recordedDate;
        this.durationWatchedToday = durationWatchedToday;
        this.dailyWatchLimit = dailyWatchLimit;
    }

    public boolean isNewDay() {
        LocalDate currentDate = LocalDate.now();
        if (currentDate == this.recordedDate) {
            return false;
        } else {
            return true;
        }
    }

    public void watchDurationUpdate(int showMinutes) {
        if (isNewDay()) {
            this.recordedDate = LocalDate.now();
            durationWatchedToday = showMinutes;
        } else {
            durationWatchedToday += showMinutes;
        }
        //TODO: Ui.printDailyWatchTimeLeft();
    }

    public String userReportString() {
        String response = "Date : ";
        response += recordedDate.toString();
        response += System.lineSeparator();
        response += "Time left today : ";
        response += (dailyWatchLimit - durationWatchedToday);
        response += " minutes. To update the time allocated to watching shows, use the 'updateTimeLimit' command";
        return response;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        String recordedDate = this.recordedDate.format(fmt);
        return recordedDate;

        //TODO: Jiqing to update for saving purposes
        //Use the LocalDate.parse(String) to convert I think. <3 jaz
    }

}
