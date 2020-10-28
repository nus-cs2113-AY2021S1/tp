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

    public static boolean isDeadlineDue(LocalDate dueBy) {
        if (dueBy == null) {
            return false;
        }
        return dueBy.isBefore(getCurrentDate()) || dueBy.isEqual(getCurrentDate());
    }

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

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static String getIncrementedDate(int increment) {
        LocalDate deadline = getCurrentDate().plusDays(increment);
        return convertDueByToString(deadline);
    }

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

    public static String convertDueByToString(LocalDate dueBy) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dueBy.format(formatter);
    }

    public static int computeEasyInterval(int previousInterval) {
        int newInterval = (int) Math.round(previousInterval * EASY_MULTIPLIER);
        assert newInterval > 0 : "Invalid new Interval";
        if (newInterval > MAX_INTERVAL) {
            return previousInterval;
        } else {
            return newInterval;
        }
    }

    public static int computeMediumInterval(int previousInterval) {
        int newInterval = (int) Math.round(previousInterval * MEDIUM_MULTIPLIER);
        assert newInterval > 0 : "Invalid new Interval";
        if (newInterval > MAX_INTERVAL) {
            return previousInterval;
        } else {
            return newInterval;
        }
    }

    public static int computeHardInterval(int previousInterval) {
        int newInterval = (int) Math.round(previousInterval * HARD_MULTIPLIER);
        assert newInterval > 0 : "Invalid new Interval";
        if (newInterval > MAX_INTERVAL) {
            return previousInterval;
        } else {
            return newInterval;
        }
    }

    public static int computeChapterInterval(CardList cards) {
        double averageInterval = 0;
        for (Card c : cards.getAllCards()) {
            averageInterval += c.getPreviousInterval();
        }
        averageInterval /= cards.getCardCount();
        return (int) Math.round(averageInterval);
    }

    public static LocalDate computeChapterDeadline(CardList cards) {
        int interval = computeChapterInterval(cards);
        return getCurrentDate().plusDays(interval);
    }
}
