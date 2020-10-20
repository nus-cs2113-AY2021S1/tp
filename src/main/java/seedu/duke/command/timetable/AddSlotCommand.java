package seedu.duke.command.timetable;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.slot.Module;
import seedu.duke.slot.Slot;
import seedu.duke.slot.Timetable;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;


/**
 * Represents the user command exit the Duke program.
 */
public class AddSlotCommand extends Command {
    public static final String ADD_KW = "add";
    public String moduleCode;
    private List<String> commands;

    /**
     * Constructs a new AddSlotCommand instance and stores the information of the slot given by the input.
     *
     * @param command The user input command.
     * @throws DukeException if input command is invalid.
     */
    public AddSlotCommand(String command) throws DukeException {
        assert command.startsWith(ADD_KW);
        String details = command.substring(ADD_KW.length());
        if (details.isBlank()) {
            throw new DukeException(DukeExceptionType.EMPTY_COMMAND, ADD_KW);
        } else if (!details.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.INVALID_ADD_SLOT);
        }
        String[] stringArray = details.trim().split(" ", 2);
        moduleCode = stringArray[0];
        if (stringArray.length > 1) {
            commands = Arrays.asList(stringArray[1].split(","));
        }
    }

    /**
     * Adds the slot to the slot list and saves the slots list in the text file.
     *
     * @param bookmarks The list of bookmarks
     * @param timetable The timetable
     * @param ui The user interface
     * @param bookmarkStorage The storage for the bookmark list
     * @param slotStorage The storage for the slot list  // ADD MORE COMMENTS
     * @throws DukeException Some exception // ADD MORE COMMENTS
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui, Storage bookmarkStorage,
                        Storage slotStorage) throws DukeException {
        String message = "";
        Module module;
        if (timetable.moduleExists(moduleCode)) {
            module = timetable.getModule(moduleCode);
            message += moduleCode + " already exists\n";
        } else {
            module = timetable.addModule(moduleCode);
            message += moduleCode + " added\n";
        }

        if (commands != null) {
            for (String command : commands) {
                message += createSlotAndBookmark(module, command.trim());
            }
        }
        ui.print(message);
    }

    private String createSlotAndBookmark(Module module, String command) {
        assert module != null : "module should mot be null";
        String message = "";
        try {
            message += create(command, module);
        } catch (DukeException e) {
            message += e.getInfo() + "\n";
        } catch (IndexOutOfBoundsException e) {
            message += "incorrect format for slot: " + command + "\n";
        }
        return message;
    }

    private String create(String command, Module module) throws DukeException {
        String message = "";
        List<String> slotAndBookmark = Arrays.asList(command.trim().split(" "));
        if (isAddModuleBookmark(slotAndBookmark)) {
            message = addBookmarkToModule(module, slotAndBookmark);
        } else {
            String lesson = slotAndBookmark.get(0);
            String day = slotAndBookmark.get(1);
            LocalTime startTime = LocalTime.parse(slotAndBookmark.get(2));
            LocalTime endTime = LocalTime.parse(slotAndBookmark.get(3));
            Slot newSlot;
            if (module.slotExists(lesson, day, startTime, endTime)) {
                newSlot = module.getSlot(lesson, day, startTime, endTime);
                message +=  "  " + lesson + " slot already exists\n";
            } else {
                newSlot = module.createSlotNew(lesson, day, startTime, endTime);
                module.addSlot(newSlot);
                message +=  "  " + lesson + " slot added\n";
            }
            message += checkForAndAddBookmarkToSlot(slotAndBookmark, lesson, newSlot);
        }
        return message;
    }

    private String checkForAndAddBookmarkToSlot(List<String> slotAndBookmark,
            String lesson, Slot newSlot) throws DukeException {
        String message = "";
        if (slotAndBookmark.size() == 5) {
            createBookmark(slotAndBookmark.get(4), lesson, newSlot);
            message = "    bookmark added to " + moduleCode + " " + lesson + "\n";
        } else if (slotAndBookmark.size() > 5) {
            throw new DukeException(DukeExceptionType.INVALID_URL, "invalid url");
        }
        return message;
    }

    private String addBookmarkToModule(Module module, List<String> slotAndBookmark) {
        String description = slotAndBookmark.get(0);
        String url = slotAndBookmark.get(1);
        Bookmark bookmark = new Bookmark(description, url);
        module.addBookmark(bookmark);
        String message = "  bookmark added to module\n";
        return message;
    }

    private boolean isAddModuleBookmark(List<String> slotAndBookmark) {
        return (slotAndBookmark.get(1).startsWith("www.") || slotAndBookmark.get(1).startsWith("https://"))
                && slotAndBookmark.size() == 2;
    }

    private void createBookmark(String url, String lesson, Slot newSlot) throws DukeException {
        if (!url.startsWith("www.") && !url.startsWith("https://")) {
            throw new DukeException(DukeExceptionType.INVALID_URL, "invalid url format: " + url);
        }
        Bookmark bookmark = new Bookmark(lesson, url);
        newSlot.addBookmark(bookmark);
    }
}
