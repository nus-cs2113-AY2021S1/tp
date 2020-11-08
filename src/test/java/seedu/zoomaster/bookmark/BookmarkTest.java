package seedu.zoomaster.bookmark;

import org.junit.jupiter.api.Test;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author
class BookmarkTest {

    @Test
    void extractModuleDescriptionAndUrl_returnsDescAndUrl() throws ZoomasterException {
        List<String> expectedUrlAndDescription = new ArrayList<>();
        expectedUrlAndDescription.add("tutorial");
        expectedUrlAndDescription.add("www.google.com");
        String input = "tutorial www.google.com";
        assertEquals(expectedUrlAndDescription, Bookmark.extractDescriptionAndUrl(input));
    }

    @Test
    void extractModuleDescriptionAndUrl_missingDescription_throwsZoomasterException() {
        String input = "www.google.com";
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> Bookmark.extractDescriptionAndUrl(input));
        assertEquals(ZoomasterExceptionType.INVALID_ADD_BOOKMARK_INPUT, e.getError());
    }

    @Test
    void extractModuleDescriptionAndUrl_invalidUrl_throwsZoomasterException() {
        String input = "CS2113T google.com";
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> Bookmark.extractDescriptionAndUrl(input));
        assertEquals(ZoomasterExceptionType.INVALID_URL, e.getError());
    }

    @Test
    void launch() throws ZoomasterException {
        String description = "desc";
        String url = "www.google.com";
        Bookmark bookmark = new Bookmark(description, url);
        String expectedOutput = "  [" + description + "] " + url;
        assertEquals(expectedOutput, bookmark.launch());
    }

    @Test
    void getUrl_validModuleDescriptionAndUrl_returnsUrl() {
        String description = "something";
        String url = "www.google.com";
        Bookmark bookmark = new Bookmark(description, url);
        assertEquals(url, bookmark.getUrl());
    }

    @Test
    void getDescription_validModuleDescriptionAndUrl_returnsDescription() {
        String description = "something";
        String url = "www.google.com";
        Bookmark bookmark = new Bookmark(description, url);
        assertEquals(description, bookmark.getDescription());
    }

    @Test
    void getBookmarkAsString_returnsDescriptionAndUrl() {
        String description = "something";
        String url = "www.google.com";
        Bookmark bookmark = new Bookmark(description, url);
        String expectedString = "[something] www.google.com" + System.lineSeparator();
        assertEquals(expectedString, bookmark.getBookmarkAsString());
    }

}