package seedu.ui;

import seedu.data.TaskMap;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class DisplayDateStructure {
    protected static final TextStyle MONTH_TEXT_STYLE = TextStyle.FULL;
    protected static final TextStyle WEEKDAY_TEXT_STYLE = TextStyle.SHORT;
    protected static final Locale LOCALE = Locale.ENGLISH;
    protected static final int DAYS_PER_WEEK = 7;
    protected static final int HASHCODE_STRING_LENGTH = 6;
    protected static final int DISPLAY_LENGTH = 141;
    protected static final int DAY_COLUMN_WIDTH = 20;
    protected static final int WEEK_ROW_WIDTH = 10;

    protected LocalDate currentDate = LocalDate.now();
    protected Month currentMonth = currentDate.getMonth();
    protected int currentDateDayOfMonth = currentDate.getDayOfMonth();
    protected DayOfWeek currentDateDayOfWeek = currentDate.getDayOfWeek();

    protected char[][] screen;

    public char[][] getScreen() {
        return screen;
    }

    protected void generateScreen(TaskMap tasks){

    }
    
}
