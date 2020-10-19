package seedu.duke.command.timetable;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.Module;
import seedu.duke.slot.Slot;
import seedu.duke.slot.Timetable;

public class DeleteSlotCommand extends Command {
    public static final String DEL_KW = "delete";
    private String moduleCode;
    private Integer slotIndex = null;
    private boolean deleteBookmarks = false;

    /**
     * Constructs a new DeleteSlotCommand instance and stores the information of the Slot given by the input.
     *
     * @param command The user input command.
     * @throws DukeException if input command is invalid.
     */
    public DeleteSlotCommand(String command) throws DukeException {
        assert command.startsWith(DEL_KW);

        String details = command.substring(DEL_KW.length());
        if (details.isBlank()) {
            throw new DukeException(DukeExceptionType.EMPTY_COMMAND, DEL_KW);
        }
        if (!details.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }
        String[] deleteCommands = details.trim().split(" ", 3);
        moduleCode = deleteCommands[0];

        try {
            String something = deleteCommands[1];
            if (something.trim().compareTo("bookmarks") == 0) {
                deleteBookmarks = true;
            }
            slotIndex = Integer.parseInt(something) - 1;
            if (deleteCommands[2].trim().compareTo("bookmarks") == 0) {
                deleteBookmarks = true;
            }
        } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
            // No slot index or delete bookmark keyword provided.
        }

    }

    /**
     * Deletes either module or slot depending on the deleteBookmarks flag. // ADD MORE COMMENTS
     *
     * @param bookmarks The list of bookmarks
     * @param timetable The timetable
     * @param ui The user interface
     * @param bookmarkStorage The storage for the bookmark list
     * @param slotStorage The storage for the slot list  // ADD MORE COMMENTS
     * @throws DukeException INVALID_SLOT_NUMBER // ADD MORE COMMENTS
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui,
                        Storage bookmarkStorage, Storage slotStorage)
            throws DukeException {
        String message = "";
        if (!timetable.moduleExists(moduleCode)) {
            message += "module does not exists\n";
        } else {
            Module module = timetable.getModule(moduleCode);

            if (slotIndex == null && !deleteBookmarks) { // delete module
                timetable.deleteModule(module);
                message += "deleted module: " + moduleCode + "\n";
            } else if (slotIndex != null && !deleteBookmarks) { // delete slot
                Slot slot = module.getSlot(slotIndex);
                module.removeSlot(slot);
                message += "deleted " + slot + " from " + moduleCode + "\n";
            } else if (slotIndex == null && deleteBookmarks) { // delete module bookmark
                module.removeAllBookmarks();
                message += "deleted bookmark from " + moduleCode + "\n";
            } else if (slotIndex != null && deleteBookmarks) { // delete slot bookmark
                Slot slot = module.getSlot(slotIndex);
                slot.removeAllBookmarks();
                message += "deleted bookmarks from " + slot + " from " + moduleCode + "\n";
            }
        }
        ui.print(message);
    }

    private String getMessage(Slot slot) {
        String message = "\tI've deleted this slot!:\n"
                + slot.getDay() + " [" + slot.getStartTime()
                + "-" + slot.getEndTime() + "] " + slot.getTitle() + System.lineSeparator();
        return message;
    }
}
