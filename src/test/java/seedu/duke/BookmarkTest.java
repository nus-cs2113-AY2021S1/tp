package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.bookmark.Bookmark;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookmarkTest {

    @Test
    void extractUrlAndDescription_descriptionWithWhiteSpace_returnsUrlAndDescription() {
        String input = "www.google.com google website";
        List<String> expectedUrlAndDescription= new ArrayList<>();
        expectedUrlAndDescription.add("www.google.com");
        expectedUrlAndDescription.add("google website");
        assertEquals(expectedUrlAndDescription, Bookmark.extractUrlAndDescription(input));
    }

    @Test
    void launch_notImplemented() {
        // not yet implemented
        assertEquals("test", "test");
    }

    @Test
    void getUrl_validUrlAndDescription_returnsUrl() {
        String url = "asdf.com";
        String description = "something";
        Bookmark bookmark = new Bookmark(url, description);
        assertEquals(url, bookmark.getUrl());
    }

    @Test
    void getDescription_validUrlAndDescription_returnsUrl() {
        String url = "asdf.com";
        String description = "something";
        Bookmark bookmark = new Bookmark(url, description);
        assertEquals(description, bookmark.getDescription());
    }
}