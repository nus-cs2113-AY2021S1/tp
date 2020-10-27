package seedu.duke.classes;

import seedu.duke.utility.Ui;

import java.time.LocalDate;

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

    public static int getDurationWatchedToday() {
        return durationWatchedToday;
    }

    public static LocalDate getRecordedDate() {
        return recordedDate;
    }

    public static int getDailyWatchLimit() {
        return dailyWatchLimit;
    }

    public static int getTimeLeftToday() {
        return dailyWatchLimit - durationWatchedToday;
    }

    public static boolean isNewDay() {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.equals(recordedDate)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkIfDifferentDay(LocalDate recordedDate) {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.equals(recordedDate)) {
            return false;
        } else {
            return true;
        }
    }

    public static void watchDurationUpdate(int showMinutes) {
        if (isNewDay()) {
            recordedDate = LocalDate.now();
            durationWatchedToday = showMinutes;
        } else {
            durationWatchedToday += showMinutes;
        }
        if (dailyWatchLimit < durationWatchedToday) {
            Ui.printExceededWatchTimeLimit();
        } else if (dailyWatchLimit == durationWatchedToday) {
            Ui.printUsedUpWatchTimeLimit();
        }
        Ui.printDailyWatchTimeLeft();
    }

    public static void watchLimitUpdate(int timeLimit) {
        dailyWatchLimit = timeLimit;
        Ui.printUpdatedTimeLimit(timeLimit);
    }

    public static String userReportString() {
        String response = "Date : ";
        response += recordedDate.toString();
        response += System.lineSeparator();
        response += "Time left today : ";
        int timeLeft = dailyWatchLimit - durationWatchedToday;
        response += (timeLeft / 60);
        response += " hour(s) ";
        response += (timeLeft % 60);
        response += " minutes. To update the time allocated to watching shows, use the 'updateTimeLimit' command.";
        return response;
    }

    public static String saveStateFormat() {
        //DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");  //TODO: May not be necessary
        String resultString = String.format("recordedDate: %s", recordedDate);
        resultString += System.lineSeparator();
        resultString += String.format("durationWatchedToday: %d", durationWatchedToday);
        resultString += System.lineSeparator();
        resultString += String.format("dailyWatchLimit: %d", dailyWatchLimit);
        resultString += System.lineSeparator();
        return resultString;
    }

}
