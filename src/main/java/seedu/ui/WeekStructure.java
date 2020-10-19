package seedu.ui;

import seedu.data.TaskMap;
import seedu.task.Task;

import java.time.LocalDate;
import java.util.Arrays;

public class WeekStructure extends DisplayDateStructure {
    private static final int DISPLAY_LENGTH = 140;
    private static final int DISPLAY_HEIGHT = 10;
    private static final int DAY_COLUMN_WIDTH = 20;

    @Override
    protected void generateScreen(TaskMap tasks) {

        String monthString = currentMonth.getDisplayName(MONTH_TEXT_STYLE, LOCALE);

        int currentCol = 0;
        int currentRow = 0;

        // Set empty screen
        screen = new char[DISPLAY_HEIGHT][DISPLAY_LENGTH];
        setEmptyScreen();

        // Display month name
        putsIntoArrayWithCentrialise(monthString, screen[currentRow++], 0, DISPLAY_LENGTH);

        // Display weekdays
        LocalDate startDate = currentDate.minusDays(currentDateDayOfWeek.getValue() - 1);
        LocalDate endDate = startDate.plusDays(DAYS_PER_WEEK);

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {

            // for each day
            String dayOfWeek = date.getDayOfWeek().getDisplayName(WEEK_TEXT_STYLE, LOCALE);
            putsIntoArrayWithCentrialise(dayOfWeek,
                screen[currentRow++], currentCol, currentCol + DAY_COLUMN_WIDTH);

            String dayOfMonth = String.valueOf(date.getDayOfMonth());
            putsIntoArrayWithCentrialise(dayOfMonth,
                screen[currentRow++], currentCol, currentCol + DAY_COLUMN_WIDTH);

            for (Task task : tasks.searchByDate(date).getValues()) {
                String temp;
                if (currentRow == DISPLAY_HEIGHT - 1) {
                    temp = "...";
                    putsIntoArray(temp, screen[currentRow], currentCol);
                    break;
                }
                temp = "#" + task.getTaskID() + " " + task.getDescription();
                temp = temp.length() > 20 ? temp.substring(0, 16) + "..." : temp;
                putsIntoArray(temp, screen[currentRow++], currentCol);
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

    private void putsIntoArray(String string, char[] arr, int start) {
        string.getChars(0, string.length(), arr, start);
    }

    private void putsIntoArrayWithCentrialise(String string, char[] arr, int start, int end) {
        // assert within range
        assert (end - start) > string.length();
        int dstBegin = start + (end - start - string.length()) / 2;
        string.getChars(0, string.length(), arr, dstBegin);
    }

}
