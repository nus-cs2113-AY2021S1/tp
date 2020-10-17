package seedu.duke.command.timetable;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.Module;
import seedu.duke.slot.Slot;
import seedu.duke.exception.DukeException;
import seedu.duke.slot.Timetable;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class ShowTimetableCommand extends Command {
    public static final String SHOW_KW = "show";
    private String day;
    private boolean showBookmarks = false;
    private String module = null;
    private final Ui ui = new Ui();

    /**
     * Constructs a new ShowTimetableCommand instance.
     */
    public ShowTimetableCommand(String command) throws DukeException {
        assert command.startsWith(SHOW_KW) : "command should start with show keyword";
        if (command.compareTo(SHOW_KW) == 0) {
            day = "ALL";
        } else {
            if (command.charAt(SHOW_KW.length()) != ' ') {
                throw new DukeException(DukeExceptionType.INVALID_COMMAND_FORMAT);
            }
            String details = command.substring(SHOW_KW.length() + 1);
            if (isDay(details)) {
                day = getDayFromCommand(details);
            } else {
                String[] something = details.trim().split(" ", 2);
                module = something[0];
                if (something.length == 2) {
                    if (something[1].compareTo("bookmarks") == 0) {
                        showBookmarks = true;
                    } else {
                        throw new DukeException(DukeExceptionType.INVALID_COMMAND_FORMAT);
                    }
                }
            }
        }
//        day = getDayFromCommand(command);
    }

    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui,
                        Storage bookmarkStorage, Storage slotStorage) throws DukeException {
        String message = "";
        if (day != null) {
            List<Slot> list = new ArrayList<>();
            list.addAll(timetable.getSlotList());
            System.out.printf(day);
            message += getMessageLessonAtTime(list, day);
        } else if (module != null && !showBookmarks) {
            if (!timetable.moduleExists(module)) {
                throw new DukeException(DukeExceptionType.INVALID_COMMAND_FORMAT);
            }
            Module matchedModule = timetable.getModule(module);
            List<Slot> slotList = matchedModule.getSlotList();
            message += getMessageLessonAtTime(slotList, "ALL");
        } else if (module != null && showBookmarks) {
            if (!timetable.moduleExists(module)) {
                throw new DukeException(DukeExceptionType.INVALID_COMMAND_FORMAT);
            }
            Module matchedModule = timetable.getModule(module);
            message += matchedModule.getBookmarks();
        }


        ui.print(message);
    }

    private boolean isDay(String input) {
        boolean isDay = false;
        if (input.compareToIgnoreCase(Slot.MON) == 0) {
            isDay = true;
        } else if (input.compareToIgnoreCase(Slot.TUE) == 0) {
            isDay = true;
        } else if (input.compareToIgnoreCase(Slot.WED) == 0) {
            isDay = true;
        } else if (input.compareToIgnoreCase(Slot.THU) == 0) {
            isDay = true;
        } else if (input.compareToIgnoreCase(Slot.FRI) == 0) {
            isDay = true;
        } else if (input.compareToIgnoreCase(Slot.SAT) == 0) {
            isDay = true;
        } else if (input.compareToIgnoreCase(Slot.SUN) == 0) {
            isDay = true;
        }
        return isDay;
    }

    private String getDayFromCommand(String input) {
        String outputData;

        if (input.compareToIgnoreCase(Slot.MON) == 0) {
            outputData = Slot.MON;
        } else if (input.compareToIgnoreCase(Slot.TUE) == 0) {
            outputData = Slot.TUE;
        } else if (input.compareToIgnoreCase(Slot.WED) == 0) {
            outputData = Slot.WED;
        } else if (input.compareToIgnoreCase(Slot.THU) == 0) {
            outputData = Slot.THU;
        } else if (input.compareToIgnoreCase(Slot.FRI) == 0) {
            outputData = Slot.FRI;
        } else if (input.compareToIgnoreCase(Slot.SAT) == 0) {
            outputData = Slot.SAT;
        } else if (input.compareToIgnoreCase(Slot.SUN) == 0) {
            outputData = Slot.SUN;
        } else {
            outputData = null;
        }

        return outputData;
    }

    private String getMessageSlotsInADay(List<Slot> slots, String day) {
        String message = "";
        boolean hasSlotOnDay = false;
        for (Slot s: slots) {
            if (s.getDay().equals(day)) {
                message += s.toString() + "\n";
                hasSlotOnDay = true;
            }
        }
        if (!hasSlotOnDay) {
            message += "No lessons" + "\n";
        }
        return message;
    }

    private String getMessageTimetable(List<Slot> slots) {
        String message = "";
        for (String d: Slot.days) {
            message += d + "\n";
            message += getMessageSlotsInADay(slots, d);
        }
        return message;
    }

    public String getMessageLessonAtTime(List<Slot> slots, String dayInput) throws DukeException {
        String message = "";
        System.out.println(slots.size());
        if (slots.isEmpty()) {
            throw new DukeException(DukeExceptionType.EMPTY_TIMETABLE);
        } else if (dayInput == null) {
            throw new DukeException(DukeExceptionType.INVALID_TIMETABLE_DAY);
        } else if (dayInput.compareTo("ALL") == 0) {
            return getMessageTimetable(slots);
        }

        message += "Lessons for " + dayInput + "\n";
        message += getMessageSlotsInADay(slots, dayInput);
        return message;
    }
}
