package seedu.duke.command.planner;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.Module;
import seedu.duke.slot.Slot;
import seedu.duke.slot.Timetable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class LoadPlannerCommand extends Command {
    public static final String LOAD_KW = "load";
    private Storage<Timetable> storage;

    public LoadPlannerCommand() throws DukeException {

    }

    /**
     * Loads the slots to the planner.
     *
     * @param bookmarks The list of bookmarks
     * @param planner The timetable
     * @param ui The user interface
     * @throws DukeException Some exception
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable planner, Ui ui) throws DukeException {
        storage = new Storage<>("./data/planner/", Timetable.class);
        Timetable temp = storage.loadPlanner();
        planner.addModule(initialiseEmptySlots(temp));
        ui.print("Planner loaded" + System.lineSeparator());
    }

    public Module initialiseEmptySlots(Timetable timetable) {
        ArrayList<ArrayList<Integer>> array = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            array.add(new ArrayList<>(Collections.nCopies(300, 0)));
        }
        ArrayList<Slot> slots = new ArrayList<>(timetable.getFullSlotList());
        for (Slot s: slots) {
            markAsFull(array, s.getStartMinutes(), s.getEndMinutes(), s.getDay());
        }
        Module module = new Module("EMPTY");
        generateEmptySlots(array, module);
        return module;
    }

    private static void markAsFull(ArrayList<ArrayList<Integer>> array, int start, int end, String day) {
        int count = 0;
        for (String d: Slot.days) {
            if (d.equals(day)) {
                for (int i = start / 5; i < end / 5; i++) {
                    array.get(count).set(i, 1);
                }
            }
            count++;
        }
    }

    private static void generateEmptySlots(ArrayList<ArrayList<Integer>> array, Module module) {
        int count = 0;
        for (String s: Slot.days) {
            for (int i = 0; i < 287; i++) {
                if (array.get(count).get(i) == 0) {
                    int startHours = (i * 5) / 60;
                    int startMinutes = (i * 5) % 60;
                    LocalTime start = Slot.convertIntToLocalTime(startHours % 288, startMinutes % 288);
                    do {
                        i++;
                    } while (array.get(count).get(i) == 0 && i < 287);
                    int endHours = (i * 5) / 60;
                    int endMinutes = (i * 5) % 60;
                    LocalTime end = Slot.convertIntToLocalTime(endHours % 288, endMinutes % 288);
                    module.addSlot(new Slot(start, end, s, "<empty slot>"));
                }
            }
            count++;
        }
    }
}
