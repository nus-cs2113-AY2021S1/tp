package seedu.eduke8.bookmark;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.question.Question;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BookmarkListTest extends Eduke8Test {

    BookmarkList bookmarkList = createTestBookmarkList();
    Question question = createTestQuestion("description");

    @Test
    void addBookmark_addOneBookmark_correctInput() {
        bookmarkList.add(question);
        assertTrue(question.isBookmarked());
    }

    @Test
    void addBookmark_addNull_assertExceptionThrown() {
        assertThrows(AssertionError.class, () -> {
            bookmarkList.add(null);
            ;
        });
    }

    @Test
    void deleteBookmark_deleteOneBookmark_correctInput() {
        bookmarkList.delete(1);
        assertEquals(bookmarkList.getCount(), 2);
    }

    @Test
    void deleteBookmark_deleteNonExistentHighNumberBookmark_assertionErrorThrown() {
        assertThrows(AssertionError.class, () -> {
            bookmarkList.delete(100);
            ;
        });
    }

    @Test
    void deleteBookmark_deleteBookmarkZero_assertionErrorThrown() {
        assertThrows(AssertionError.class, () -> {
            bookmarkList.delete(0);
            ;
        });
    }

    @Test
    void findBookmark_findOneBookmark_correctInput() {
        assertTrue(bookmarkList.find("First question description.") instanceof Question);
    }

    @Test
    void findBookmark_findOneBookmark_wrongInput() {
        assertNull(bookmarkList.find("Bad input"));
    }

    @Test
    void getInnerList_callAddOnList_willNotBeBookmarked() {
        bookmarkList.getInnerList().add(question);
        assertFalse(question.isBookmarked());
    }
}
