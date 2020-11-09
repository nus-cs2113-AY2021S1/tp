package seedu.quotesify.bookmark;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookmarkTest {
    private Book book;
    private Author author;
    private Bookmark bookmark;

    private int pageNum = 66;
    private String bookName = "Harry Potter";
    private String authorName = "JK Rowling";

    @BeforeEach
    public void SetUp() {
        author = new Author(authorName);
        book = new Book(author, bookName);
        bookmark = new Bookmark(book, pageNum);
    }

    @Test
    public void toStringTest() {
        assertEquals("\"Harry Potter\" at page: 66", bookmark.toString() );
    }
}
