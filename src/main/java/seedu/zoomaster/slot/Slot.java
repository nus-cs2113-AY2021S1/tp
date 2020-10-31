package seedu.zoomaster.slot;

import seedu.zoomaster.bookmark.Bookmark;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class Slot {

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

    public Bookmark getBookmark(int index) throws ZoomasterException {
        Bookmark bookmark;
        try {
            bookmark = bookmarks.getBookmark(index);
        } catch (IndexOutOfBoundsException e) {
            throw new ZoomasterException(ZoomasterExceptionType.BOOKMARK_NUMBER_OUT_OF_BOUNDS, "" 
                                         + bookmarks.getSize());
        }
        return bookmark;
    }

    public BookmarkList getBookmarkList() {
        return bookmarks;
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

    public int getStartMinutes() {
        int hours = Integer.parseInt(startTime.toString().substring(0,2));
        int minutes = Integer.parseInt(startTime.toString().substring(3));
        return hours * 60 + minutes;
    }

    public void setEndTime(LocalTime timeInput) {
        endTime = timeInput;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getEndMinutes() {
        int hours = Integer.parseInt(endTime.toString().substring(0,2));
        int minutes = Integer.parseInt(endTime.toString().substring(3));
        return hours * 60 + minutes;
    }

    public static LocalTime convertIntToLocalTime(int hours, int minutes) {
        String h = String.valueOf(hours);
        String m = String.valueOf(minutes);
        if (hours < 10) {
            h = "0" + h;
        }
        if (minutes < 10) {
            m = "0" + m;
        }
        return LocalTime.parse(h + ":" + m);
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
