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
    public LocalTime startTime;
    public LocalTime endTime;
    public String day;
    public String title;
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
        String stringArray[] = details.trim().split(" ", 2);
        title = stringArray[0];
        if (stringArray.length > 1) {
            commands = Arrays.asList(stringArray[1].split(","));
        }



//        String[] parts = command.split(" ");
//        try {
//            startTime = LocalTime.parse(parts[1]);
//            endTime = LocalTime.parse(parts[2]);
//            day = parts[3];
//            title = command.substring(command.indexOf(parts[3]) + parts[3].length()).trim();
//        } catch (DateTimeParseException e) {
//            throw new DukeException(DukeExceptionType.INVALID_TIME_FORMAT);
//        } catch (IndexOutOfBoundsException e) {
//            throw new DukeException(DukeExceptionType.INVALID_SLOT_INPUT);
//        }
    }

    /**
     * Adds the slot to the slot list and saves the slots list in the text file.
     *
     * @param slots The list of slots.
     * @param ui The user interface.
     * @param slotStorage The storage for saving and loading.
     */

    /**
     * Adds the slot to the slot list and saves the slots list in the text file.
     *
     * @param bookmarks
     * @param timetable
     * @param ui
     * @param bookmarkStorage
     * @param slotStorage
     * @throws DukeException
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui, Storage bookmarkStorage,
                        Storage slotStorage) throws DukeException {
        String message = "";
        Module module;
        if (timetable.moduleExists(title)) {
            module = timetable.getModule(title);
            message += title + " already exists.\n";
        } else {
            module = timetable.addModule(title);
            message += title + " added\n";
        }

        if (commands != null) {
            for (String command : commands) {
                message += createSlotAndBookmark(module, command.trim());
            }

        }
        ui.print(message);

//        Slot slot = new Slot(startTime, endTime, day, title);
//        slots.addSlot(slot);
//        ui.print("Added slot: " + day + " [" + startTime + "-" + endTime + "] "
//                + title + System.lineSeparator());
//        try {
//            slotStorage.save(slots.getData());
//        } catch (DukeException e) {
//            e.printStackTrace();
//        }
    }

    private String createSlotAndBookmark(Module module, String command) {
        assert module != null : "module shouldnt be null";
        String message = "";
        try {
            List<String> slotAndBookmark = Arrays.asList(command.trim().split(" "));
            if ((slotAndBookmark.get(1).startsWith("www.") || slotAndBookmark.get(1).startsWith("https://"))
                    && slotAndBookmark.size() == 2) {
                String description = slotAndBookmark.get(0);
                String url = slotAndBookmark.get(1);
                Bookmark bookmark = new Bookmark(description,"dummy", url);
                module.addBookmark(bookmark);
                message += "bookmark added to module\n";
            } else {
                String lesson = slotAndBookmark.get(0);
                String day = slotAndBookmark.get(1);
                LocalTime startTime = LocalTime.parse(slotAndBookmark.get(2));
                LocalTime endTime = LocalTime.parse(slotAndBookmark.get(3));

                Slot newSlot;
                if (module.slotExists(lesson, day, startTime, endTime)) {
                    newSlot = module.getSlot(lesson, day, startTime, endTime);
                    message += "slot already exists\n";
                } else {
                    newSlot = module.createSlotNew(lesson, day, startTime, endTime);
                    module.addSlot(newSlot);
                    message += "slot added\n";
                }

                if (slotAndBookmark.size() == 5) {
                    createBookmark(slotAndBookmark.get(4), lesson, newSlot);
                    message += "bookmark added\n";
                } else if (slotAndBookmark.size() > 5) {
                    throw new DukeException(DukeExceptionType.INVALID_URL, "invalid url");
                }
                System.out.println("success\n");
            }
        } catch (DukeException e) {
            message += e.getInfo() + "\n";
        } catch (IndexOutOfBoundsException e) {
            message += "incorrect format for slot: " + command + "\n";
        }
        return message;
    }

    private void createBookmark(String url, String lesson, Slot newSlot) throws DukeException {
        if (!url.startsWith("www.") && !url.startsWith("https://")) {
            throw new DukeException(DukeExceptionType.INVALID_URL, "invalid url format: " + url);
        }
        Bookmark bookmark = new Bookmark(lesson, "dummy", url);
        newSlot.addBookmark(bookmark);
    }
}
