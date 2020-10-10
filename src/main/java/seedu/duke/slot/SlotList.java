package seedu.duke.slot;

import java.time.LocalTime;
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
        //temp create test examples
        slots.add(new Slot(LocalTime.parse("08:30"), LocalTime.parse("10:30"), Slot.MON, "CS2113 Tutorial"));
        slots.add(new Slot(LocalTime.parse("15:00"), LocalTime.parse("16:00"), Slot.MON, "CG2027 Tutorial"));
        slots.add(new Slot(LocalTime.parse("10:00"), LocalTime.parse("12:00"), Slot.TUE, "CCG2023 Lecture"));
        slots.add(new Slot(LocalTime.parse("15:00"), LocalTime.parse("18:00"), Slot.TUE, "CG2027 Lab"));
    }

    public static void printSlotsInADay(ArrayList<Slot> slots, String day) {
        boolean hasSlotOnDay = false;

        System.out.println(day);

        for (Slot s: slots) {
            if (s.getDay().equals(day)) {
                System.out.println(s.toString());
                hasSlotOnDay = true;
            }
        }

        if (hasSlotOnDay == false) {
            System.out.println("No lessons");
        }

        System.out.println();
    }

    public void printTimetable(ArrayList<Slot> slots) {
        for (String d: Slot.days) {
            printSlotsInADay(slots, d);
        }
    }

    public void printLessonAtTime(ArrayList<Slot> slots, String dayInput) throws DukeException {
        if (slots.size() == 0) {
            throw new DukeException(DukeExceptionType.EMPTY_TIMETABLE);
        } else if (dayInput == null) {
            throw new DukeException(DukeExceptionType.INVALID_TIMETABLE_DAY);
        } else if(dayInput.equals("ALL") == true) {
            printTimetable(slots);
            return;
        }

        System.out.println("Lessons for " + dayInput);
        printSlotsInADay(slots, dayInput);
    }

    public ArrayList<Slot> getSlotsList() {
        return slots;
    }

}
