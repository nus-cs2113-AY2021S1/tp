package seedu.lifeasier.ui;

import org.fusesource.jansi.AnsiConsole;
import seedu.lifeasier.model.tasks.Deadline;
import seedu.lifeasier.model.tasks.Task;
import seedu.lifeasier.model.tasks.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * The TimetableUi class handles the displaying of the schedule in a tabulated format.
 */
public class TimetableUi {
    private static final String TIME_COLUMN_NAME = "TIME";
    private static final String TIME_FORMAT = "%02d:00";
    private static final int DEFAULT_START_HOUR = 8;
    private static final int DEFAULT_END_HOUR = 18;

    private static final int DAYS_COLUMN_COUNT = 7;
    private static final int MAX_COLUMN_WIDTH = 15;

    private static final String ROW_FORMAT = "|%-11s| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |";
    private static final String ROW_SEPARATOR = "+-----------+" + "-----------------+".repeat(DAYS_COLUMN_COUNT);
    public static final String TIME_DELIMITER = ":";
    public static final String ROW_COMPONENT_DELIMITER = "-";
    public static final String ROW_DELIMITER = "\\|";
    public static final int MIDNIGHT_HOUR = 24;
    public static final int VALID_TIME_LENGTH = 2;
    public static final String MIDNIGHT_TIME = "00:00";

    private static TimetableUi timetableUi = null;
    private static ArrayList<String> timetableRows;
    private Ui ui;

    private TimetableUi() {
        AnsiConsole.systemInstall();
        timetableRows = new ArrayList<>();
        this.ui = new Ui();
    }

    public static TimetableUi getInstance() {
        if (timetableUi == null) {
            timetableUi = new TimetableUi();
        }
        return timetableUi;
    }

    public void showTimetable(TaskList tasks) {
        generateTimetable(tasks);
        LocalTime currentTime = LocalTime.now();
        System.out.println(ROW_SEPARATOR);
        for (String row: timetableRows) {
            if (determineIfCurrentHour(row, currentTime)) {
                System.out.println(ui.colourTextCyan(row));
            } else {
                System.out.println(row);
            }
            System.out.println(ROW_SEPARATOR);
        }
    }

    /**
     * Fills the timetable with the contents of the TaskList.
     */
    public void generateTimetable(TaskList tasks) {
        timetableRows.clear();
        timetableRows.add(getColumnTitlesString());

        LocalTime[] timeRange = getTimetableTimeRange(tasks);
        int firstHour = timeRange[0].getHour();
        int lastHour = timeRange[1].getHour();

        int currHour = firstHour;
        while (currHour <= lastHour) {
            timetableRows.add(generateRowString(currHour, tasks));
            currHour++;
        }
    }

    /**
     * Formats and returns the table header with the days of the week,
     * starting from the current day.
     */
    public String getColumnTitlesString() {
        String[] columnTitles = new String[8];
        columnTitles[0] = TIME_COLUMN_NAME;
        for (int i = 0; i < 7; i++) {
            LocalDateTime datePointer = LocalDateTime.now().plus(i, ChronoUnit.DAYS);
            columnTitles[i + 1] = ScheduleUi.getDayOfWeek(datePointer);
        }
        return String.format(ROW_FORMAT, (Object[]) columnTitles);
    }

    /**
     * Formats the contents of a table row.
     */
    public String generateRowString(int hour, TaskList tasks) {
        String[] rowContents = generateRowContents(hour, tasks);
        return String.format(ROW_FORMAT, (Object[]) rowContents);
    }

    /**
     * Returns the contents of each row of the timetable into an array.
     * Each row represents the tasks that fall within a certain time slot.
     */
    public String[] generateRowContents(int hour, TaskList tasks) {
        String[] rowContents = new String[8];
        LocalDate todayDate = LocalDate.now();

        rowContents[0] = getTimeSlotString(hour);

        for (int dayIncrement = 0; dayIncrement < 7; dayIncrement++) {
            LocalDate currDate = todayDate.plus(dayIncrement, ChronoUnit.DAYS);
            rowContents[dayIncrement + 1] = getCellString(currDate, hour, tasks);
        }

        return rowContents;
    }

    private String getTimeSlotString(int startHour) {
        String startHourString = String.format(TIME_FORMAT, startHour);
        String endHourString = String.format(TIME_FORMAT, (startHour + 1) % 24);
        return startHourString + "-" + endHourString;
    }

    /**
     * Returns the contents of each cell of the timetable.
     */
    private String getCellString(LocalDate date, int hour, TaskList tasks) {
        ArrayList<String> cellContents = new ArrayList<>();
        for (Task task : tasks.getTaskList()) {
            if (!(task instanceof Deadline) && task.isHappeningOn(date) && task.isWithinTimeSlot(hour)) {
                cellContents.add(task.getDescription());
            }
        }
        return trimToFitTimetableCell(cellContents.toString().replace("[", "").replace("]", ""));
    }

    public String trimToFitTimetableCell(String fullString) {
        if (fullString.length() > MAX_COLUMN_WIDTH) {
            return fullString.substring(0, MAX_COLUMN_WIDTH - 3) + "...";
        }
        return fullString;
    }

    /**
     * Iterates through the TaskList to determine the earliest and
     * latest time that has a certain Task scheduled, so that the
     * timetable can display all Tasks within that range.
     */
    public LocalTime[] getTimetableTimeRange(TaskList tasks) {
        int earliestHour = DEFAULT_START_HOUR;
        int latestHour = DEFAULT_END_HOUR;

        for (int hour = 0; hour < 24; hour++) {
            for (Task task: tasks.getTaskList()) {
                if (!(task instanceof Deadline) && task.isWithinTimeSlot(hour)) {
                    int currTaskStartHour = task.getStart().toLocalTime().getHour();
                    int currTaskEndHour = task.getEnd().toLocalTime().getHour();

                    earliestHour = getEarlierTime(earliestHour, currTaskStartHour, currTaskEndHour);
                    latestHour = getLaterTime(latestHour, currTaskStartHour, currTaskEndHour);
                }
            }
        }

        LocalTime earliestTime = LocalTime.parse(String.format(TIME_FORMAT, earliestHour));
        LocalTime latestTime = LocalTime.parse(String.format(TIME_FORMAT, latestHour));

        return new LocalTime[] {earliestTime, latestTime};
    }

    public int getEarlierTime(int referenceHour, int taskStartHour, int taskEndHour) {
        return Math.min(Math.min(referenceHour, taskStartHour),Math.min(referenceHour, taskEndHour));
    }

    public int getLaterTime(int referenceHour, int taskStartHour, int taskEndHour) {
        return Math.max(Math.max(referenceHour, taskStartHour),Math.max(referenceHour, taskEndHour));
    }

    /**
     * Determines if the current time falls within the current time slot in the time table.
     *
     * @param row String with information of current row.
     * @param currentTime Current time from LocalTime object.
     * @return true when current time falls within the timetable slot.
     */
    private boolean determineIfCurrentHour(String row, LocalTime currentTime) {
        String[] rowComponents = row.split(ROW_DELIMITER);
        String[] timeRange = rowComponents[1].split(ROW_COMPONENT_DELIMITER);

        if (timeRange.length < VALID_TIME_LENGTH) {
            return false;
        }

        try {
            LocalTime startTime = LocalTime.parse(timeRange[0]);
            boolean isAfterStartTime = currentTime.compareTo(startTime) > 0;

            LocalTime endTime = LocalTime.parse(timeRange[1]);
            LocalTime midnight = LocalTime.parse(MIDNIGHT_TIME);
            boolean isBeforeEndTime = false;

            if (endTime.equals(midnight)) {
                String[] timeComponents = timeRange[1].split(TIME_DELIMITER);
                int hour = Integer.parseInt(timeComponents[0]);
                if (hour < MIDNIGHT_HOUR) {
                    isBeforeEndTime = true;
                }
            } else {
                isBeforeEndTime = currentTime.compareTo(endTime) < 0;
            }

            //Current time is within hourly range
            if (isAfterStartTime && isBeforeEndTime) {
                return true;
            }

        } catch (DateTimeParseException e) {
            System.out.println(ui.colourTextRed("There was an error parsing the times"));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ui.colourTextRed("There was an error getting the time ranges"));
        } catch (NumberFormatException e) {
            System.out.println(ui.colourTextRed("There was an error converting the hour"));
        }

        return false;
    }
}
