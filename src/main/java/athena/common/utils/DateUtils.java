package athena.common.utils;

import athena.Forecast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

public class DateUtils {

    /**
     * Utility method to get the first day of this week.
     *
     * @return The first day of this week.
     */
    public static LocalDate getFirstDayOfWeek() {
        LocalDate now = LocalDate.now();
        TemporalField weekField = WeekFields.of(Locale.getDefault()).dayOfWeek();
        return now.with(weekField, 1);
    }

    /**
     * Utility method to get the first day of this month.
     *
     * @return The first day of this month.
     */
    public static LocalDate getFirstDayOfMonth() {
        LocalDate now = LocalDate.now();
        return now.withDayOfMonth(1);
    }

    /**
     * Gets a list of dates based on the given forecast.
     * TODAY - Today's date.
     * WEEK - This week's dates starting from the first day of the week (Monday).
     * ALL - This month's dates starting from the first day of the month.
     *
     * @param forecast Forecast for dates
     * @return List of dates based on the given forecast
     */
    public static ArrayList<LocalDate> getDatesBasedOnForecast(Forecast forecast) {
        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
        LocalDate startDate = LocalDate.now();
        LocalDate endDate;

        if (forecast == Forecast.WEEK) {
            endDate = startDate.plusWeeks(1);
        } else if (forecast == Forecast.DAY) {
            endDate = startDate.plusDays(1);
        } else {
            endDate = startDate.plusMonths(1);
        }

        for (LocalDate currentDate = startDate; currentDate.compareTo(endDate) != 0;
             currentDate = currentDate.plusDays(1)) {
            dates.add(currentDate);
        }

        return dates;
    }

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }

    public static boolean isDateIncluded(LocalDate taskDate, Forecast forecast) {
        if (forecast == Forecast.ALL) {
            return true;
        } else if (forecast == Forecast.WEEK) {
            LocalDate oneWeekLater = LocalDate.now().plusWeeks(1);
            return oneWeekLater.compareTo(taskDate) > 0 && LocalDate.now().compareTo(taskDate) <= 0;
        } else {
            return taskDate.equals(LocalDate.now());
        }
    }
}
