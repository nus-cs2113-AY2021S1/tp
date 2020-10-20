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

import java.util.ArrayList;
import java.util.List;

public class ShowTimetableCommand extends Command {
    public static final String SHOW_KW = "show";
    private String day;
    private boolean showBookmarks = false;
    private String module = null;

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
            String details = command.substring(SHOW_KW.length() + 1).trim();
            if (isDay(details)) {
                day = getDayFromCommand(details);
            } else {
                String[] something = details.split(" ", 2);
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
    }

    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui,
                        Storage bookmarkStorage, Storage slotStorage) throws DukeException {
        String message = "";
        List<Module> modules = timetable.getFullModuleList();
        if (day != null) { // show and show day
            List<Slot> list = new ArrayList<>(timetable.getFullSlotList());
            message += getMessageLessonAtTime(modules, list, day);
        } else if (module != null && !showBookmarks) {
            if (!timetable.moduleExists(module)) {
                throw new DukeException(DukeExceptionType.INVALID_MODULE);
            }
            Module matchedModule = timetable.getModule(module);
            message += getMessageForModule(matchedModule);
        } else if (module != null && showBookmarks) {
            if (!timetable.moduleExists(module)) {
                throw new DukeException(DukeExceptionType.INVALID_MODULE);
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

    private String getMessageSlotsInADay(List<Module> modules, List<Slot> slots, String day) {
        StringBuilder message = new StringBuilder();
        boolean hasSlotOnDay = false;
        for (Slot s: slots) {
            for (Module module : modules) {
                if (module.slotExists(s) && s.getDay().equals(day)) {
                    message.append(s.toString()).append(" ").append(module.getModuleCode()).append("\n");
                    hasSlotOnDay = true;
                }
            }
        }
        if (!hasSlotOnDay) {
            message.append("No lessons" + "\n");
        }
        message.append("\n");
        return message.toString();
    }

    private String getMessageTimetable(List<Module> modules, List<Slot> slots) {
        StringBuilder message = new StringBuilder();
        for (String d: Slot.days) {
            message.append(d).append("\n");
            message.append(getMessageSlotsInADay(modules, slots, d));
        }
        return message.toString();
    }

    private String getMessageLessonAtTime(List<Module> modules, List<Slot> slots,
                                          String dayInput) throws DukeException {
        String message = "";
        if (slots.isEmpty()) {
            throw new DukeException(DukeExceptionType.EMPTY_TIMETABLE);
        } else if (dayInput == null) {
            throw new DukeException(DukeExceptionType.INVALID_TIMETABLE_DAY);
        } else if (dayInput.compareTo("ALL") == 0) {
            return getMessageTimetable(modules,slots);
        }
        message += "Lessons for " + dayInput + "\n";
        message += getMessageSlotsInADay(modules, slots, dayInput);
        return message;
    }

    private String getMessageForModule(Module module) {
        String message = "";
        List<Slot> slots = module.getSlotList();
        if (!slots.isEmpty()) {
            message += module.getModuleCode() + "\n";
            for (int i = 0; i < slots.size(); i++) {
                message += "  " + (i + 1) + ". " + slots.get(i).toString() + "\n";
            }
        } else {
            message += "no slots for " + module.getModuleCode() + "\n";
        }
        return message;
    }
}
