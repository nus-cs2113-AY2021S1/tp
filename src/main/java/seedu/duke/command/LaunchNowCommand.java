package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.exception.DukeException;
import seedu.duke.slot.Day;
import seedu.duke.slot.Slot;
import seedu.duke.slot.Timetable;

import java.time.LocalTime;
import java.util.List;

public class LaunchNowCommand extends Command {
    public static final String LAUNCH_NOW_KW = "launch now";

    public LaunchNowCommand() {

    }

    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws DukeException {
        LocalTime currentTime = LocalTime.now();
        String message = "";

        List<Slot> slots = timetable.getFullSlotList();
        for (Slot slot : slots) {
            if (slot.getStartTime().isBefore(currentTime) && slot.getEndTime().isAfter(currentTime)
                    && slot.getDay().compareToIgnoreCase(Day.getDayToday()) == 0) {
                message = slot.getBookmarkList().launchAllBookmarks();
                break;
            }
        }

        if (message.isBlank()) {
            message = "no lessons now." + System.lineSeparator();
        }
        ui.print(message);
    }
}
