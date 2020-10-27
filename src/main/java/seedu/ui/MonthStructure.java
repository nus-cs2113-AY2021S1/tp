package seedu.ui;

import seedu.commons.Util;
import seedu.data.TaskMap;
import seedu.task.Task;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static seedu.commons.Util.putsIntoArray;
import static seedu.commons.Util.putsIntoArrayWithCentralise;

public class MonthStructure extends DisplayDateStructure {
    private static final int DISPLAY_HEIGHT = 53;
    private static final int ROW_NUM_OFFSET = 2;    // Month name and Day of week

    @Override
    protected void generateScreen(TaskMap tasks) {

        String monthString = currentMonth.getDisplayName(MONTH_TEXT_STYLE, LOCALE);

        int currentCol = 1;
        int currentRow = 1;

        // Set empty screen
        screen = new char[DISPLAY_HEIGHT][DISPLAY_LENGTH];
        generateScreenWithBorder();

        // Display month name
        putsIntoArrayWithCentralise(monthString, screen[currentRow++], 0, DISPLAY_LENGTH);

        // Display day name
        for (int i = 1; i <= DAYS_PER_WEEK; i++) {
            String dayOfWeek = DayOfWeek.of(i).getDisplayName(WEEKDAY_TEXT_STYLE, LOCALE);
            putsIntoArrayWithCentralise(dayOfWeek,
                screen[currentRow], currentCol, currentCol + DAY_COLUMN_WIDTH);
            currentCol += DAY_COLUMN_WIDTH;
        }
        currentRow++;


        // Display days
        LocalDate startDate = currentDate.minusDays(currentDateDayOfMonth - 1);
        int lengthOfCurrentMonth = currentDate.lengthOfMonth();
        LocalDate endDate = startDate.plusDays(lengthOfCurrentMonth);

        currentCol = (startDate.getDayOfWeek().getValue() - 1) * DAY_COLUMN_WIDTH + 1;

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            int tempRowNum = currentRow;

            // for each day
            String dayOfMonth = String.valueOf(date.getDayOfMonth());
            putsIntoArrayWithCentralise(dayOfMonth,
                screen[tempRowNum++], currentCol, currentCol + DAY_COLUMN_WIDTH);

            for (Task task : tasks.searchByDate(date).getValues()) {
                String temp;
                if (tempRowNum == currentRow + WEEK_ROW_WIDTH - ROW_NUM_OFFSET) {
                    temp = "...";
                    putsIntoArray(temp, screen[tempRowNum], currentCol);
                    break;
                }
                temp = "#" + task.getTaskID();
                // # + 4 digits + " " = 6
                temp += Util.generatePadStringWithCharAndLength(' ', HASHCODE_STRING_LENGTH - temp.length());
                temp += task.getDescription();
                Util.limitStringWithDots(temp, DAY_COLUMN_WIDTH);
                putsIntoArray(temp, screen[tempRowNum++], currentCol);
            }

            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                currentCol = 1;
                currentRow += WEEK_ROW_WIDTH;
            } else {
                currentCol += DAY_COLUMN_WIDTH;
            }
        }
    }

    private void generateScreenWithBorder() {
        for (int i = 0; i < DISPLAY_HEIGHT; i++) {
            for (int j = 0; j < DISPLAY_LENGTH; j++) {
                screen[i][j] = (i == 0 || i == (DISPLAY_HEIGHT - 1)
                    || ((i - ROW_NUM_OFFSET) % WEEK_ROW_WIDTH == 0 && i != ROW_NUM_OFFSET)
                    || j % DAY_COLUMN_WIDTH == 0) ? '*' : ' ';
            }
        }
    }
}
