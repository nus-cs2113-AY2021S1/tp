package seedu.ui;

import seedu.data.TaskMap;
import seedu.task.Task;

import java.time.LocalDate;
import java.util.Arrays;

public class WeekStructure extends DisplayDateStructure {
    private final int DISPLAY_LENGTH = 140;
    private final int DISPLAY_HEIGHT = 10;

    @Override
    protected void generateScreen(TaskMap tasks) {
        LocalDate startDate = currentDate.minusDays(currentDateDayOfWeek.getValue());
        LocalDate endDate = startDate.plusDays(DAYS_PER_WEEK);

        String monthString = currentMonth.getDisplayName(MONTH_TEXT_STYLE, LOCALE);

        int currentCol = 0;
        int currentRow = 0;

        // Set empty screen
        screen = new char[DISPLAY_HEIGHT][DISPLAY_LENGTH];
        setEmptyScreen();

        // Display month name
        monthString.getChars(0, monthString.length(), screen[currentRow++],
            (DISPLAY_LENGTH - monthString.length()) / 2);       // centralise month string

        // Display weekdays
        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            // for each day

            String dayOfWeek = date.getDayOfWeek().getDisplayName(WEEK_TEXT_STYLE, LOCALE);
            dayOfWeek.getChars(0, dayOfWeek.length(), screen[currentRow++],
                (currentCol * 2 + 20 - dayOfWeek.length()) / 2);   // centralise month string

            String dayOfMonth = String.valueOf(date.getDayOfMonth());
            dayOfMonth.getChars(0, dayOfMonth.length(), screen[currentRow++],
                (currentCol * 2 + 20 - dayOfMonth.length()) / 2);   // centralise month string

            for (Task task : tasks.searchDate(date).getValues()) {
                String temp;
                if (currentRow == DISPLAY_HEIGHT - 1) {
                    temp = "...";
                    temp.getChars(0, temp.length(), screen[currentRow], currentCol);
                    break;
                }
                temp = "#" + task.getTaskID() + " " + task.getDescription();
                temp = temp.length() > 20 ? temp.substring(0, 16) + "..." : temp;
                temp.getChars(0, temp.length(), screen[currentRow++], currentCol);
            }

            currentCol += 20;
            currentRow = 1;    // Row0: Month str
        }
    }

    private void setEmptyScreen() {
        for (int i = 0; i < DISPLAY_HEIGHT; i++) {
            Arrays.fill(screen[i], ' ');
        }
    }
}
