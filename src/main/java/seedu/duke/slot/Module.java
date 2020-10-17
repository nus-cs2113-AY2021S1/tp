package seedu.duke.slot;

import seedu.duke.bookmark.Bookmark;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Module {
    private String moduleCode;
    private BookmarkList bookmarks;
    private List<Slot> slots;

    public Module(String moduleCode) {
        this.moduleCode = moduleCode.toUpperCase();
        bookmarks = new BookmarkList();
        slots = new ArrayList<>();
    }

    public void addSlot(Slot slot) {
        slots.add(slot);
    }

    public boolean slotExists(String lesson, String day, LocalTime startTime, LocalTime endTime) {
        for (Slot slot : slots) {
            if (slot.match(lesson, day, startTime, endTime)) {
                return true;
            }
        }
        return false;
    }

    public Slot getSlot(String lesson, String day, LocalTime startTime, LocalTime endTime) {
        for (Slot slot : slots) {
            if (slot.match(lesson, day, startTime, endTime)) {
                return slot;
            }
        }
        return null;
    }

    public Slot getSlot(int index) throws DukeException {
        Slot slot;
        try {
            slot = slots.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeExceptionType.INVALID_SLOT_NUMBER, "" + slots.size());
        }
        return slot;
    }

    public Slot createSlotNew(String lesson, String day, LocalTime startTime, LocalTime endTime) throws DukeException {
        Slot slot = new Slot(startTime, endTime, day, lesson);
        return slot;
    }

    public void removeSlot(Slot slot) {
        assert slots.contains(slot) : "Use getSlot to get reference of slot to be deleted before calling this method";
        slots.remove(slot);
    }

    public boolean isModule(String moduleCode) {
        boolean isModule = false;
        if (this.moduleCode.compareToIgnoreCase(moduleCode) == 0 ) {
            isModule = true;
        }
        return isModule;
    }

    public void addBookmark(Bookmark bookmark) {
        bookmarks.addBookmark(bookmark);
    }

    public void removeAllBookmarks() {
        bookmarks = new BookmarkList();
    }

    public List<Slot> getSlotList() {
        return slots;
    }

    public String getBookmarks() {
        String message = "";
        List<Bookmark> bookmarkList = bookmarks.getBookmarkList();
        for (Bookmark bookmark : bookmarkList) {
            message += bookmark.getBookmarkAsString() + "\n";
        }
        for (Slot slot : slots) {
            message += slot.toString() + "\n";
            List<Bookmark> slotBookmarkList = slot.getBookmarkList();
            for (Bookmark bookmark : slotBookmarkList) {
                message += bookmark.getBookmarkAsString() + "\n";
            }
        }
        return message;
    }

    public String getModulecode() {
        return moduleCode;
    }
}
