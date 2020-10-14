package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.data.notebook.Notebook;
import seedu.duke.data.notebook.TagManager;
import seedu.duke.data.timetable.DailyEvent;
import seedu.duke.data.timetable.Event;
import seedu.duke.data.timetable.MonthlyEvent;
import seedu.duke.data.timetable.Timetable;
import seedu.duke.data.timetable.WeeklyEvent;
import seedu.duke.data.timetable.YearlyEvent;
import seedu.duke.storage.StorageManager;
import seedu.duke.ui.InterfaceManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemindCommandTest {
    private static final String TEST_TITLE_1 = "CS2113 Tutorial";
    private static final String TEST_TITLE_2 = "CS2113 Lecture";
    private static final String TEST_TITLE_3 = "CS2113 Meeting";
    private static final String TEST_TITLE_4 = "CS2113 Coding";
    private static final LocalDateTime TEST_DATE_TIME = LocalDateTime.now();
    private static final boolean TEST_REMINDER = true;
    private static final ArrayList<Integer> TEST_TIME_PERIODS = new ArrayList<>(List.of(1));
    private static final ArrayList<String> TEST_TIME_UNITS
            = new ArrayList<>(List.of(Event.REMINDER_DAY));
    private final DailyEvent dailyEvent = new DailyEvent(TEST_TITLE_4, TEST_DATE_TIME,
            TEST_REMINDER, TEST_TIME_PERIODS, TEST_TIME_UNITS);

    private static final StorageManager STORAGE_MANAGER = new StorageManager();
    private static final Notebook NOTEBOOK = new Notebook();
    private static final TagManager TAG_MANAGER = new TagManager();
    private static Timetable timetable = new Timetable();



    private RemindCommand command = new RemindCommand();

    @Test
    void execute_singleEvent_success() {
        timetable.addEvent(dailyEvent);
        command.setData(NOTEBOOK, timetable, TAG_MANAGER, STORAGE_MANAGER);
        DailyEvent reminderEvent = new DailyEvent(dailyEvent.getTitle(), TEST_DATE_TIME.plusDays(1),
                TEST_REMINDER, TEST_TIME_PERIODS, TEST_TIME_UNITS);
        assertEquals("Reminders:" + InterfaceManager.LS + reminderEvent.toReminderString(), command.execute());

    }
}
