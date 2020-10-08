package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.slot.Slot;
import seedu.duke.slot.SlotList;
import seedu.duke.exception.DukeException;

public class ShowTimetableCommand extends Command {
    public static final String SHOW_KW = "show";
    public String day;

    /**
     * Constructs a new ShowTimetableCommand instance.
     */
    public ShowTimetableCommand(String command) {
        day = getDayFromCommand(command);
    }

    /**
     * Prints the exit screen before the program exits.
     *
     * @param bookmarks The list of bookmarks.
     * @param ui The user interface.
     * @param storage The storage for saving and loading.
     */

    @Override
    public void execute(BookmarkList bookmarks, SlotList slotList, Ui ui, Storage storage) throws DukeException {
        slotList.printLessonAtTime(slotList.getSlotsList(), day);
    }

    public String getDayFromCommand(String input) {
        String outputData;

        if (input.length() == 4){
            return "ALL";
        } else if (input.substring(4 , 5).compareToIgnoreCase(" ") == 0) {
            return null;
        }

        String dayData = input.substring(5);

        switch (dayData) {
        case Slot.MON:
            outputData = Slot.MON;
        case Slot.TUE:
            outputData = Slot.TUE;
        case Slot.WED:
            outputData = Slot.WED;
        case Slot.THU:
            outputData = Slot.THU;
        case Slot.FRI:
            outputData = Slot.FRI;
        case Slot.SAT:
            outputData = Slot.SAT;
        case Slot.SUN:
            outputData = Slot.SUN;
        default:
            outputData = null;
        }

        return outputData;
    }
}
