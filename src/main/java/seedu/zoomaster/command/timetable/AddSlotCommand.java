package seedu.zoomaster.command.timetable;

import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.Bookmark;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.slot.Module;
import seedu.zoomaster.slot.Slot;
import seedu.zoomaster.slot.Timetable;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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
     * @throws ZoomasterException if input command is invalid.
     */
    public AddSlotCommand(String command) throws ZoomasterException {
        assert command.startsWith(ADD_KW);
        String details = command.substring(ADD_KW.length());
        if (details.isBlank()) {
            throw new ZoomasterException(ZoomasterExceptionType.EMPTY_COMMAND, ADD_KW);
        } else if (!details.startsWith(" ")) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_ADD_SLOT);
        }
        String[] stringArray = details.trim().split(" ", 2);
        moduleCode = stringArray[0].toUpperCase();
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
     * @throws ZoomasterException Some exception // ADD MORE COMMENTS
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws ZoomasterException {
        String message = "";
        Module module;
        if (timetable.moduleExists(moduleCode)) {
            module = timetable.getModule(moduleCode);
            message += moduleCode + " already exists\n";
        } else if (!isValidModule(moduleCode)) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_MODULE);
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

    protected String createSlotAndBookmark(Module module, String command) {
        assert module != null : "module should mot be null";
        String message = "";
        try {
            message += create(command, module);
        } catch (ZoomasterException e) {
            message += e.getInfo() + "\n";
        } catch (IndexOutOfBoundsException e) {
            message += "incorrect format for slot: " + command + "\n";
        }
        return message;
    }

    protected String create(String command, Module module) throws ZoomasterException {
        String message = "";
        List<String> slotAndBookmark = Arrays.asList(command.trim().split(" "));
        if (isAddModuleBookmark(slotAndBookmark)) {
            message = addBookmarkToModule(module, slotAndBookmark);
        } else {
            String lesson = slotAndBookmark.get(0);
            String day = slotAndBookmark.get(1);
            LocalTime startTime;
            LocalTime endTime;
            try {
                startTime = LocalTime.parse(slotAndBookmark.get(2));
                endTime = LocalTime.parse(slotAndBookmark.get(3));
            } catch (DateTimeParseException e) {
                throw new ZoomasterException(ZoomasterExceptionType.INVALID_TIME_FORMAT,
                        "  Invalid time format for slot. (" + slotAndBookmark.get(2)
                        + " " + slotAndBookmark.get(3) + ") Please check format.");
            }
            if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
                throw new ZoomasterException(ZoomasterExceptionType.INVALID_TIME_FORMAT, "  Invalid time for slot. ("
                        + slotAndBookmark.get(2) + " " + slotAndBookmark.get(3) + ")");
            }
            Slot newSlot;
            if (module.isOverlapTimeSlot(day, startTime, endTime)) {
                newSlot = module.getSlot(lesson, day, startTime, endTime);
                message +=  "  " + lesson + " overlaps with an existing timeslot\n";
            } else {
                newSlot = module.createSlotNew(lesson, day, startTime, endTime);
                module.addSlot(newSlot);
                message +=  "  " + lesson + " slot added\n";
            }
            message += checkForAndAddBookmarkToSlot(slotAndBookmark, lesson, newSlot);
        }
        return message;
    }

    protected String checkForAndAddBookmarkToSlot(List<String> slotAndBookmark,
                                                  String lesson, Slot newSlot) throws ZoomasterException {
        String message = "";
        if (slotAndBookmark.size() == 5) {
            createBookmark(slotAndBookmark.get(4), lesson, newSlot);
            message = "    bookmark added to " + moduleCode + " " + lesson + "\n";
        } else if (slotAndBookmark.size() > 5) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_URL, "invalid url");
        }
        return message;
    }

    protected String addBookmarkToModule(Module module, List<String> slotAndBookmark) {
        String description = slotAndBookmark.get(0);
        String url = slotAndBookmark.get(1);
        Bookmark bookmark = new Bookmark(description, url);
        module.addBookmark(bookmark);
        String message = "  bookmark added to module\n";
        return message;
    }

    protected boolean isAddModuleBookmark(List<String> slotAndBookmark) {
        return (slotAndBookmark.get(1).startsWith("www.") || slotAndBookmark.get(1).startsWith("https://"))
                && slotAndBookmark.size() == 2;
    }

    private void createBookmark(String url, String lesson, Slot newSlot) throws ZoomasterException {
        if (!url.startsWith("www.") && !url.startsWith("https://")) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_URL, "invalid url format: " + url);
        }
        Bookmark bookmark = new Bookmark(lesson, url);
        newSlot.addBookmark(bookmark);
    }

    /**
     * Validates the module code with the list of modules moduleList.
     *
     * @param module The module code to be added.
     *
     * @return
     * true if module exist in the list or list is null.
     *     false if module does not exists in the list.
     */
    private boolean isValidModule(String module) {
        if (Module.getModuleList() == null) { // If unable to get list of modules, always return true.
            return true;
        }

        if (Module.getModuleList().contains(module)) {
            return true;
        }
        return false;
    }
}
