package seedu.ui;

import seedu.commons.Util;
import seedu.data.TaskMap;
import seedu.task.Task;

import java.time.LocalDate;

public class WeekStructure extends DisplayDateStructure {
    private static final int DISPLAY_LENGTH = 141;
    private static final int DISPLAY_HEIGHT = 11;
    private static final int DAY_COLUMN_WIDTH = 20;

    @Override
    protected void generateScreen(TaskMap tasks) {

        String monthString = currentMonth.getDisplayName(MONTH_TEXT_STYLE, LOCALE);

        // Set screen with border
        screen = new char[DISPLAY_HEIGHT][DISPLAY_LENGTH];
        generateScreenWithBorder();

        int currentCol = 1;
        int currentRow = 1;

        // Display month name
        putsIntoArrayWithCentralise(monthString, screen[currentRow++], 0, DISPLAY_LENGTH);

        // Display weekdays
        LocalDate startDate = currentDate.minusDays(currentDateDayOfWeek.getValue() - 1);
        LocalDate endDate = startDate.plusDays(DAYS_PER_WEEK);

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {

            // for each day
            String dayOfWeek = date.getDayOfWeek().getDisplayName(WEEK_TEXT_STYLE, LOCALE);
            putsIntoArrayWithCentralise(dayOfWeek,
                screen[currentRow++], currentCol, currentCol + DAY_COLUMN_WIDTH);

            String dayOfMonth = String.valueOf(date.getDayOfMonth());
            putsIntoArrayWithCentralise(dayOfMonth,
                screen[currentRow++], currentCol, currentCol + DAY_COLUMN_WIDTH);

            for (Task task : tasks.searchByDate(date).getValues()) {
                String temp;
                if (currentRow == DISPLAY_HEIGHT - 2) {
                    temp = "...";
                    putsIntoArray(temp, screen[currentRow], currentCol);
                    break;
                }
                temp = "#" + task.getTaskID();
                temp += Util.padString(' ', 6 - temp.length());
                temp += task.getDescription();
                temp = temp.length() > 20 ? temp.substring(0, 16) + "..." : temp;
                putsIntoArray(temp, screen[currentRow++], currentCol);
            }

            currentCol += 20;
            currentRow = 2;    // Row0: Month str
        }
    }

    private void generateScreenWithBorder() {
        for (int i = 0; i < DISPLAY_HEIGHT; i++) {
            for (int j = 0; j < DISPLAY_LENGTH; j++) {
                if (i % 10 == 0 || j % DAY_COLUMN_WIDTH == 0) {
                    screen[i][j] = '*';
                } else {
                    screen[i][j] = ' ';
                }
            }
        }
    }

    private void putsIntoArray(String string, char[] arr, int start) {
        string.getChars(0, string.length(), arr, start);
    }

    private void putsIntoArrayWithCentralise(String string, char[] arr, int start, int end) {
        // assert within range
        assert (end - start) > string.length();
        int dstBegin = start + (end - start - string.length()) / 2;
        string.getChars(0, string.length(), arr, dstBegin);
    }

}
