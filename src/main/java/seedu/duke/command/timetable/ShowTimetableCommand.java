package seedu.duke.command.timetable;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.command.Command;
import seedu.duke.slot.Slot;
import seedu.duke.slot.SlotList;
import seedu.duke.exception.DukeException;
import java.time.LocalDate;

public class ShowTimetableCommand extends Command {
    public static final String SHOW_KW = "show";
    public String day;
    private final Ui ui = new Ui();

    /**
     * Constructs a new ShowTimetableCommand instance.
     */
    public ShowTimetableCommand(String command) {
        day = getDayFromCommand(command);
    }

    @Override
    public void execute(BookmarkList bookmarks, SlotList slotList, Ui ui,
                        Storage bookmarkStorage, Storage slotStorage) throws DukeException {
        this.ui.printLessonAtTime(slotList.getSlotList(), day);
    }

    public String getDayFromCommand(String input) {
        String outputData;

        assert input.startsWith(ShowTimetableCommand.SHOW_KW) : "input should always start with \"show\"";
        if (input.length() == 4) {
            return "ALL";
        } else if (input.charAt(4) != ' ') {
            return null;
        }

        String dayData = input.substring(5);
        if (dayData.trim().toLowerCase().equals("today")) {
            dayData = Ui.getDayToday();
        }

        if (dayData.compareToIgnoreCase(Slot.MON) == 0) {
            outputData = Slot.MON;
        } else if (dayData.compareToIgnoreCase(Slot.TUE) == 0) {
            outputData = Slot.TUE;
        } else if (dayData.compareToIgnoreCase(Slot.WED) == 0) {
            outputData = Slot.WED;
        } else if (dayData.compareToIgnoreCase(Slot.THU) == 0) {
            outputData = Slot.THU;
        } else if (dayData.compareToIgnoreCase(Slot.FRI) == 0) {
            outputData = Slot.FRI;
        } else if (dayData.compareToIgnoreCase(Slot.SAT) == 0) {
            outputData = Slot.SAT;
        } else if (dayData.compareToIgnoreCase(Slot.SUN) == 0) {
            outputData = Slot.SUN;
        } else {
            outputData = null;
        }

        return outputData;
    }


}
