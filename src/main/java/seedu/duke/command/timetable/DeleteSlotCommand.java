package seedu.duke.command.timetable;

import seedu.duke.ItemList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.command.Command;
import seedu.duke.slot.Slot;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.bookmark.BookmarkList;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.SlotList;

public class DeleteSlotCommand extends Command {
    public static final String DEL_KW = "slotdelete";
    private int index;

    /**
     * Constructs a new DeleteSlotCommand instance and stores the information of the Slot given by the input.
     *
     * @param command The user input command.
     * @throws DukeException thrown if input command is invalid.
     */
    public DeleteSlotCommand(String command) throws DukeException {
        String details = command.substring(DEL_KW.length());
        if (!details.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }
        try {
            index = Integer.parseInt(details.trim()) - 1;
        } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(DukeExceptionType.INVALID_SLOT_NUMBER);
        }
    }

    /**
     * Deletes the Slot in the Slot list.
     *
     * @param slotList The list of Slots.
     * @param ui The user interface.
     */
    @Override
    public void execute(BookmarkList bookmarks, SlotList slotList, Ui ui, Storage bookmarkStorage, Storage slotStorage)
            throws DukeException {
        SlotList slots = (SlotList) slotList;
        try {
            Slot slot = slots.getSlot(index);
            slots.deleteSlot(slots.getSlot(index));
            ui.print(getMessage(slot));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeExceptionType.INVALID_SLOT_NUMBER);
        }
    }

    private String getMessage(Slot slot) {
        String message = "\tI've deleted this slot!:\n"
                + slot.getDay() + " [" + slot.getStartTime()
                + "-" + slot.getEndTime() + "] " + slot.getTitle() + System.lineSeparator();
        return message;
    }
}
