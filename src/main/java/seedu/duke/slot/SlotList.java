package seedu.duke.slot;

import java.util.ArrayList;

import seedu.duke.Duke;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.Slot;

public class SlotList {

    private ArrayList<Slot> slots = new ArrayList<>();

    public SlotList() {
        this.slots = new ArrayList<>();
    }

    public static void printSlotsInADay(ArrayList<Slot> slots, String day) {
        for (Slot s: slots) {
            if (s.getDay().equals(day)) {
                System.out.println(s.toString());
            }
        }
    }

    public void printTimetable(ArrayList<Slot> slots) {
        for (String d: Slot.days) {
            printSlotsInADay(slots, d);
        }
    }

    public void printLessonAtTime(ArrayList<Slot> slots, String dayInput) throws DukeException {
        if (slots.size() == 0) {
            throw new DukeException(DukeExceptionType.EMPTY_TIMETABLE);
        } else if(dayInput.equals("ALL") == true) {
            printTimetable(slots);
            return;
        } else if (dayInput == null) {
            throw new DukeException(DukeExceptionType.INVALID_TIMETABLE_DAY);
        }

        System.out.println("Lessons for " + dayInput);
        printSlotsInADay(slots, dayInput);
    }

    public ArrayList<Slot> getSlotsList() {
        return slots;
    }

}
