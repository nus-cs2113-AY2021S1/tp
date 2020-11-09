package scheduler;

import manager.card.Card;
import manager.chapter.CardList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.lang.Math;
import java.time.format.DateTimeParseException;

public class Scheduler {
    public static final double EASY_MULTIPLIER = 4.4;
    public static final double MEDIUM_MULTIPLIER = 2.2;
    public static final double HARD_MULTIPLIER = 1.1;
    public static final int MAX_INTERVAL = 365;

    /**
     * Returns if the specified deadline is due (current day or before current day).
     * @param dueBy deadline to be checked.
     * @return boolean for if the deadline is due.
     */
    public static boolean isDeadlineDue(LocalDate dueBy) {
        if (dueBy == null) {
            return false;
        }
        return dueBy.isBefore(getCurrentDate()) || dueBy.isEqual(getCurrentDate());
    }

    /**
     * Returns if the specified deadline is due in "increment" number of days from today
     * @param dueBy deadline to be checked.
     * @return boolean for if the deadline is due in "increment" number of days from today.
     */
    public static boolean isDeadlineDueIn(LocalDate dueBy, int increment) {
        if (dueBy == null) {
            return false;
        }
        if (increment == 0) {
            return dueBy.isBefore(getCurrentDate()) || dueBy.isEqual(getCurrentDate());
        } else {
            return dueBy.isEqual(getCurrentDate().plusDays(increment));
        }
    }

    /**
     * Provides the current date.
     * @return LocalDate object containing todays date.
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * Provides the date "increment" days from the current date.
     * @param increment number of days to be incremented by.
     * @return LocalDate object containing the date "increment" days from todays date.
     */
    public static String getIncrementedDate(int increment) {
        LocalDate deadline = getCurrentDate().plusDays(increment);
        return convertDueByToString(deadline);
    }

    /**
     * Parses the savedDate entry into a LocalDate object if possible, else todays date will be returned.
     * @param savedDate savedDate entry to be parsed
     * @return LocalDate object containing savedDate entry's date or todays date
     */
    public static LocalDate parseDate(String savedDate) {
        try {
            if (savedDate.equals("Invalid Date")) {
                return LocalDate.now();
            } else {
                return LocalDate.parse(savedDate);
            }
        } catch (DateTimeParseException e) {
            return LocalDate.now();
        }
    }

    /**
     * Converts a LocalDate object into String for saving into Chapter Deadline files.
     * @param dueBy LocalDate object to be converted
     * @return String form of LocalDate object
     */
    public static String convertDueByToString(LocalDate dueBy) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dueBy.format(formatter);
    }

    /**
     * Rounds the product of the previousInterval and the EASY_MULTIPLIER to the nearest integer and returns it.
     * @param previousInterval previous incremented interval to be operated on.
     * @return product of the previousInterval and the EASY_MULTIPLIER to the nearest integer.
     */
    public static int computeEasyInterval(int previousInterval) {
        int newInterval = (int) Math.round(previousInterval * EASY_MULTIPLIER);
        assert newInterval > 0 : "Invalid new Interval";
        if (newInterval > MAX_INTERVAL) {
            return previousInterval;
        } else {
            return newInterval;
        }
    }

    /**
     * Rounds the product of the previousInterval and the MEDIUM_MULTIPLIER to the nearest integer and returns it.
     * @param previousInterval previous incremented interval to be operated on.
     * @return product of the previousInterval and the MEDIUM_MULTIPLIER to the nearest integer.
     */
    public static int computeMediumInterval(int previousInterval) {
        int newInterval = (int) Math.round(previousInterval * MEDIUM_MULTIPLIER);
        assert newInterval > 0 : "Invalid new Interval";
        if (newInterval > MAX_INTERVAL) {
            return previousInterval;
        } else {
            return newInterval;
        }
    }

    /**
     * Rounds the product of the previousInterval and the HARD_MULTIPLIER to the nearest integer and returns it.
     * @param previousInterval previous incremented interval to be operated on.
     * @return product of the previousInterval and the HARD_MULTIPLIER to the nearest integer.
     */
    public static int computeHardInterval(int previousInterval) {
        int newInterval = (int) Math.round(previousInterval * HARD_MULTIPLIER);
        assert newInterval > 0 : "Invalid new Interval";
        if (newInterval > MAX_INTERVAL) {
            return previousInterval;
        } else {
            return newInterval;
        }
    }

    /**
     * Computes the mean of the intervals of every card in the Chapter and rounds it off to the nearest integer.
     * @param cards CardList of cards to be operated on
     * @return mean of the intervals of every card in the Chapter and rounds it off to the nearest integer.
     */
    public static int computeChapterInterval(CardList cards) {
        double averageInterval = 0;
        for (Card c : cards.getAllCards()) {
            averageInterval += c.getPreviousInterval();
        }
        averageInterval /= cards.getCardCount();
        return (int) Math.round(averageInterval);
    }

    /**
     * Computes the new Deadline of the Chapter, which is ChapterInterval days away from the current date.
     * @param cards CardList of cards to be operated on
     * @return LocalDate object containing the new deadline of the Chapter.
     */
    public static LocalDate computeChapterDeadline(CardList cards) {
        int interval = computeChapterInterval(cards);
        return getCurrentDate().plusDays(interval);
    }
}
