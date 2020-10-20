package seedu.duke.slot;

import seedu.duke.bookmark.Bookmark;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;

import java.util.ArrayList;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class Slot {

    public static final String MON = "mon";
    public static final String TUE = "tue";
    public static final String WED = "wed";
    public static final String THU = "thu";
    public static final String FRI = "fri";
    public static final String SAT = "sat";
    public static final String SUN = "sun";

    public static final String[] days = { MON, TUE, WED, THU, FRI, SAT, SUN };

    private static final String SEPARATOR = " | ";
    private LocalTime startTime;
    private LocalTime endTime;
    private String day;
    private String title;
    private BookmarkList bookmarks;



    public Slot(LocalTime startTimeInput, LocalTime endTimeInput, String dayInput, String titleInput) {
        startTime = startTimeInput;
        endTime = endTimeInput;
        day = dayInput;
        title = titleInput;
        bookmarks = new BookmarkList();
    }

    public boolean match(String lesson, String day, LocalTime startTime, LocalTime endTime) {
        if (title.compareTo(lesson) == 0
                && this.day.compareTo(day) == 0
                && this.startTime.equals(startTime)
                && this.endTime.equals(endTime)) {
            return true;
        }
        return false;
    }

    public Bookmark getBookmark(int index) throws DukeException {
        Bookmark bookmark;
        try {
            bookmark = bookmarks.getBookmark(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeExceptionType.BOOKMARK_NUMBER_OUT_OF_BOUNDS, "" + bookmarks.getSize());
        }
        return bookmark;
    }

    public List<Bookmark> getBookmarkList() {
        return bookmarks.getBookmarkList();
    }

    public void removeBookmark(Bookmark bookmark) {
        bookmarks.deleteBookmark(bookmark);
    }

    public void removeAllBookmarks() {
        bookmarks = new BookmarkList();
    }

    public void addBookmark(Bookmark bookmark) {
        bookmarks.addBookmark(bookmark);
    }

    public void setStartTime(LocalTime timeInput) {
        startTime = timeInput;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setEndTime(LocalTime timeInput) {
        endTime = timeInput;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setDay(String dayInput) {
        day = dayInput;
    }

    public String getDay() {
        return day;
    }

    public void setTitle(String titleInput) {
        title = titleInput;
    }

    public String getTitle() {
        return title;
    }

    public String getExport() {
        return startTime.toString() + SEPARATOR + endTime.toString() + SEPARATOR + day + SEPARATOR + title;
    }

    public static Slot initSlot(String data) {
        List<String> details = Arrays.asList(data.split("\\|"));

        LocalTime startTime = LocalTime.parse(details.get(0).trim());
        LocalTime endTime = LocalTime.parse(details.get(1).trim());
        String day = details.get(2).trim();
        String title = details.get(3).trim();

        return new Slot(startTime, endTime, day, title);
    }

    @Override
    public String toString() {
        return String.format(startTime.toString() + "-" + endTime.toString() + " " + title);
    }
}
