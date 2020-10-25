package seedu.zoomaster.command.timetable;

import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Module;
import seedu.zoomaster.slot.Slot;
import seedu.zoomaster.slot.Timetable;

public class DeleteSlotCommand extends Command {
    public static final String DEL_KW = "delete";
    private String moduleCode;
    private Integer slotIndex = null;
    private boolean deleteBookmarks = false;

    /**
     * Constructs a new DeleteSlotCommand instance and stores the information of the Slot given by the input.
     *
     * @param command The user input command.
     * @throws ZoomasterException if input command is invalid.
     */
    public DeleteSlotCommand(String command) throws ZoomasterException {
        assert command.startsWith(DEL_KW);

        String details = command.substring(DEL_KW.length());
        if (details.isBlank()) {
            throw new ZoomasterException(ZoomasterExceptionType.EMPTY_COMMAND, DEL_KW);
        }
        if (!details.startsWith(" ")) {
            throw new ZoomasterException(ZoomasterExceptionType.UNKNOWN_INPUT);
        }
        String[] deleteCommands = details.trim().split(" ", 3);
        moduleCode = deleteCommands[0];

        try {
            String something = deleteCommands[1];
            if (something.trim().compareTo("bookmarks") == 0) {
                deleteBookmarks = true;
            } else {
                slotIndex = Integer.parseInt(something) - 1;
                if (deleteCommands[2].trim().compareTo("bookmarks") == 0) {
                    deleteBookmarks = true;
                }
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            // No slot index or delete bookmark keyword provided.
        } catch (NumberFormatException e) {
            throw new ZoomasterException(ZoomasterExceptionType.NON_INTEGER_INPUT);
        }

    }

    /**
     * Deletes either module or slot depending on the deleteBookmarks flag. // ADD MORE COMMENTS
     *
     * @param bookmarks The list of bookmarks
     * @param timetable The timetable
     * @param ui The user interface
     * @throws ZoomasterException INVALID_SLOT_NUMBER // ADD MORE COMMENTS
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui)
            throws ZoomasterException {
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
                message += "deleted bookmarks from " + moduleCode + "\n";
            } else if (slotIndex != null && deleteBookmarks) { // delete slot bookmark
                Slot slot = module.getSlot(slotIndex);
                slot.removeAllBookmarks();
                message += "deleted bookmarks from " + slot.getDay() + " " + slot + " from " + moduleCode + "\n";
            }

        }
        ui.print(message);
    }

}
