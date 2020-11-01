package seedu.zoomaster.slot;

import seedu.zoomaster.bookmark.Bookmark;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Module {
    private String moduleCode;
    private BookmarkList bookmarks;
    private List<Slot> slots;
    private static ArrayList<String> moduleList; //List of all NUS module codes

    public static ArrayList<String> getModuleList() {
        return moduleList;
    }

    public static void setModuleList(ArrayList<String> moduleList) {
        Module.moduleList = moduleList;
    }

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

    public boolean slotExists(Slot slot) {
        return slots.contains(slot);
    }

    public Slot getSlot(String lesson, String day, LocalTime startTime, LocalTime endTime) {
        for (Slot slot : slots) {
            if (slot.match(lesson, day, startTime, endTime)) {
                return slot;
            }
        }
        return null;
    }

    public Slot getSlot(int index) throws ZoomasterException {
        Slot slot;
        if (slots.size() == 0) {
            throw new ZoomasterException(ZoomasterExceptionType.ZERO_SLOTS_IN_MODULE);
        }
        try {
            slot = slots.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_SLOT_NUMBER, "" + slots.size());
        }
        return slot;
    }

    public Slot createSlotNew(String lesson, String day, LocalTime startTime, LocalTime endTime) {
        Slot slot = new Slot(startTime, endTime, day, lesson);
        return slot;
    }

    public void removeSlot(Slot slot) {
        assert slots.contains(slot) : "Use getSlot to get reference of slot to be deleted before calling this method";
        slots.remove(slot);
    }

    public boolean isModule(String moduleCode) {
        boolean isModule = false;
        if (this.moduleCode.compareToIgnoreCase(moduleCode) == 0) {
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
        List<Bookmark> bookmarkList = bookmarks.getBookmarks();
        for (Bookmark bookmark : bookmarkList) {
            message += bookmark.getBookmarkAsString();
        }
        if (!message.isBlank()) {
            message += System.lineSeparator();
        }
        if (bookmarkList.isEmpty()) {
            message += "no bookmarks found in module itself" + System.lineSeparator() + System.lineSeparator();
        }
        for (int i = 0; i < slots.size(); i++) {
            Slot slot = slots.get(i);
            message += (i + 1) + ". " + slot.getDay() + " " + slot.toString() + System.lineSeparator();
            List<Bookmark> slotBookmarkList = slot.getBookmarkList().getBookmarks();
            for (Bookmark bookmark : slotBookmarkList) {
                message += "  " + bookmark.getBookmarkAsString();
            }
            if (slotBookmarkList.isEmpty()) {
                message += "  no bookmarks found in slot" + System.lineSeparator();
            }
            message += System.lineSeparator();
        }
        if (message.isBlank()) {
            message += "no bookmarks found in " + moduleCode + System.lineSeparator();
        }
        return message;
    }

    public String launchBookmarks() {
        String message = bookmarks.launchAllBookmarks();
        return message;
    }

    public String getModuleCode() {
        return moduleCode;
    }
}
