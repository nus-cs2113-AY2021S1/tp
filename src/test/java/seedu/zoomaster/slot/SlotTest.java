package seedu.zoomaster.slot;

import org.junit.jupiter.api.Test;
import seedu.zoomaster.bookmark.Bookmark;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;

class SlotTest {
    Slot slotTest = new Slot(LocalTime.parse("10:00"), LocalTime.parse("11:00"), "mon", "tutorial");


    @Test
    void getDay_oneTimeSlot_returnsDay() {
        assertEquals("mon", slotTest.getDay());
    }

    @Test
    void match_validDetails_returnsTrue() {
        assertEquals(true, slotTest.match("tutorial", "mon",
                LocalTime.parse("10:00"), LocalTime.parse("11:00")));
    }

    @Test
    void match_invalidDetails_returnsFalse() {
        assertEquals(false, slotTest.match("lecture", "mon",
                LocalTime.parse("10:00"), LocalTime.parse("11:00")));
    }

    @Test
    void getBookmark() {
    }

    @Test
    void getBookmarkList() {
        assertEquals(new ArrayList<Bookmark>(), slotTest.getBookmarkList().getBookmarks());
    }

    @Test
    void removeBookmark() {
    }

    @Test
    void removeAllBookmarks() {
    }

    @Test
    void addBookmark() {
    }

    @Test
    void setStartTime() {
    }

    @Test
    void getStartTime() {
    }

    @Test
    void setEndTime() {
    }

    @Test
    void getEndTime() {
    }

    @Test
    void setDay() {
    }

    @Test
    void getDay() {
    }

    @Test
    void setTitle() {
    }

    @Test
    void getTitle() {
        assertEquals("tutorial", slotTest.getTitle());
    }

    @Test
    void getExport() {
        assertEquals("10:00 | 11:00 | mon | tutorial", slotTest.getExport());
    }

    @Test
    void initSlot() {
        assertEquals(slotTest.toString(), Slot.initSlot("10:00 | 11:00 | mon | tutorial").toString());
    }

    @Test
    void testToString() {
        assertEquals("10:00-11:00 tutorial", slotTest.toString());
    }
}