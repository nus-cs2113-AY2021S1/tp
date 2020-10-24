package seedu.duke.command.planner;

import seedu.duke.Ui;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.command.timetable.AddSlotCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.Module;
import seedu.duke.slot.Slot;
import seedu.duke.slot.Timetable;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddMeetingCommand extends AddSlotCommand {
    public static final String ADD_KW = "add";
    private Timetable localTimetable;
    private List<Slot> slots;

    /**
     * Constructs a new AddSlotCommand instance and stores the information of the slot given by the input.
     *
     * @param command The user input command.
     * @throws DukeException if input command is invalid.
     */
    public AddMeetingCommand(String command) throws DukeException {

        super(command);
    }

    /**
     * Adds the slot to the slot list and saves the slots list in the text file.
     *
     * @param bookmarks The list of bookmarks
     * @param timetable The timetable
     * @param ui The user interface
     * @throws DukeException Some exception // ADD MORE COMMENTS
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws DukeException {
        localTimetable = timetable;
        slots = timetable.getFullSlotList();
        super.execute(bookmarks, timetable, ui);
    }

    @Override
    protected String create(String command, Module module) throws DukeException {
        String message = "";
        List<String> slotAndBookmark = Arrays.asList(command.trim().split(" "));
        if (isAddModuleBookmark(slotAndBookmark)) {
            message = addBookmarkToModule(module, slotAndBookmark);
        } else {
            String lesson = slotAndBookmark.get(0);
            String day = slotAndBookmark.get(1);
            LocalTime startTime;
            LocalTime endTime;
            try {
                startTime = LocalTime.parse(slotAndBookmark.get(2));
                endTime = LocalTime.parse(slotAndBookmark.get(3));
            } catch (DateTimeParseException e) {
                throw new DukeException(DukeExceptionType.INVALID_TIME_FORMAT, "Invalid time format. ("
                        + slotAndBookmark.get(2) + " " + slotAndBookmark.get(3) + ") Please check format.");
            }
            Slot newSlot;
            if (module.slotExists(lesson, day, startTime, endTime)) {
                newSlot = module.getSlot(lesson, day, startTime, endTime);
                message +=  "  " + lesson + " slot already exists\n";
                message += checkForAndAddBookmarkToSlot(slotAndBookmark, lesson, newSlot);
            } else if (isAvailable(day, startTime, endTime)) {
                newSlot = module.createSlotNew(lesson, day, startTime, endTime);
                module.addSlot(newSlot);
                message +=  "  " + lesson + " slot added\n";
                message += checkForAndAddBookmarkToSlot(slotAndBookmark, lesson, newSlot);
            } else {
                message +=  "  " + "slot is full" + System.lineSeparator();
            }
        }
        return message;
    }

    private boolean isAvailable(String day, LocalTime startTime, LocalTime endTime) {
        for (Slot s: slots) {
            if (s.getDay().equals(day) && s.getTitle().equals("<empty slot>")
                    && s.getStartTime().compareTo(startTime) <= 0 && s.getEndTime().compareTo(endTime) >= 0) {
                updateEmptySlot(s, startTime, endTime);
                return true;
            }
        }
        return false;
    }

    private void updateEmptySlot(Slot slot, LocalTime startTime, LocalTime endTime) {
        if (slot.getStartTime().compareTo(startTime) != 0) {
            Slot slot1 = new Slot(slot.getStartTime(), startTime, slot.getDay(), slot.getTitle());
            Slot slot2 = new Slot(endTime, slot.getEndTime(), slot.getDay(), slot.getTitle());
            localTimetable.getModule("EMPTY").addSlot(slot1);
            localTimetable.getModule("EMPTY").addSlot(slot2);
        }
        localTimetable.getModule("EMPTY").removeSlot(slot);
    }
}
