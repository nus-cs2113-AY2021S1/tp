package scheduler;

import manager.card.Card;

import java.time.LocalDate;
import java.lang.Math;

public class Scheduler {
    public static final double EASY_MULTIPLIER = 1.1;
    public static final double MEDIUM_MULTIPLIER = 2.2;
    public static final double HARD_MULTIPLIER = 4.4;
    public static final int MAX_INTERVAL = 365;

    public static boolean isDeadlineDue(LocalDate dueBy) {
        if (dueBy == null) {
            return true;
        }
        return dueBy.isBefore(getCurrentDate()) || dueBy.isEqual(getCurrentDate());
    }

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static int computeEasyInterval(int previousInterval) {
        int newInterval = (int) Math.round(previousInterval * EASY_MULTIPLIER);
        if (newInterval > MAX_INTERVAL) {
            return previousInterval;
        } else {
            return newInterval;
        }
    }

    public static LocalDate computeEasyDeadline(Card c, int previousInterval) {
        int interval = computeEasyInterval(previousInterval);
        if (c.getDueBy() == null) {
            return getCurrentDate().plusDays(interval);
        }
        return c.getDueBy().plusDays(interval);
    }

    public static int computeMediumInterval(int previousInterval) {
        int newInterval = (int) Math.round(previousInterval * MEDIUM_MULTIPLIER);
        if (newInterval > MAX_INTERVAL) {
            return previousInterval;
        } else {
            return newInterval;
        }
    }

    public static LocalDate computeMediumDeadline(Card c, int previousInterval) {
        int interval = computeMediumInterval(previousInterval);
        if (c.getDueBy() == null) {
            return getCurrentDate().plusDays(interval);
        }
        return c.getDueBy().plusDays(interval);
    }

    public static int computeHardInterval(int previousInterval) {
        int newInterval = (int) Math.round(previousInterval * HARD_MULTIPLIER);
        if (newInterval > MAX_INTERVAL) {
            return previousInterval;
        } else {
            return newInterval;
        }
    }

    public static LocalDate computeHardDeadline(Card c, int previousInterval) {
        int interval = computeHardInterval(previousInterval);
        if (c.getDueBy() == null) {
            return getCurrentDate().plusDays(interval);
        }
        return c.getDueBy().plusDays(interval);
    }

    public static int computeDeckInterval(double totalMultiplier, int cardCount, int previousInterval) {
        double averageMultiplier = (totalMultiplier / cardCount);
        int newInterval = (int) Math.round(averageMultiplier * previousInterval);
        if (newInterval > MAX_INTERVAL) {
            return previousInterval;
        } else {
            return newInterval;
        }
    }

    public static LocalDate computeDeckDeadline(double totalMultiplier, int cardCount, int previousInterval) {
        int interval = computeDeckInterval(totalMultiplier, cardCount, previousInterval);
        return getCurrentDate().plusDays(interval);
    }
}
