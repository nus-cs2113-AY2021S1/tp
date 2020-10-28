package seedu.financeit.utils;

import java.time.LocalDateTime;

public class RunHistory {
    private static LocalDateTime lastRunDateTime;
    private static LocalDateTime currentRunDateTime;

    private static void setLastRunDateTime(LocalDateTime dateTime) {
        RunHistory.lastRunDateTime = dateTime;
    }

    public static LocalDateTime getLastRunDateTime() {
        return lastRunDateTime;
    }

    public static void setCurrentRunDateTime() {
        RunHistory.currentRunDateTime = LocalDateTime.now();
    }

    public static LocalDateTime getCurrentRunDateTime() {
        return RunHistory.currentRunDateTime;
    }

    public static int getCurrentDayOfMonth() {
        return RunHistory.currentRunDateTime.getDayOfMonth();
    }

    /**
     * Checks if it is currently a new month since user last opened the program.
     * Assumes no changes have been made to system date or time
     *
     * @return boolean to indicate whether it has been a new month
     */
    public boolean isNewMonth() {
        int lastRunMonth = lastRunDateTime.getMonthValue();
        int lastRunYear = lastRunDateTime.getYear();
        int currentRunMonth = currentRunDateTime.getMonthValue();
        int currentRunYear = currentRunDateTime.getYear();

        if(currentRunYear == lastRunYear) {
            return currentRunMonth > lastRunMonth;
        }

        //If the year is not the same, then user is definitely opening in a new month
        return true;
    }
}
