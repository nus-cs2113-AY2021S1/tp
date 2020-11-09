package seedu.quotesify.bookmark;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;

import static org.junit.Assert.assertEquals;

public class BookmarkListTest {
    private BookList bookList;
    private BookmarkList bookmarkList;
    private Book book1;
    private Author author1;
    private Bookmark bookmark1;
    private Book book2;
    private Author author2;
    private Bookmark bookmark2;

    private int pageNum1 = 66;
    private String bookName1 = "Harry Potter";
    private String authorName1 = "JK Rowling";

    private int pageNum2 = 99;
    private String bookName2 = "JM Barrie";
    private String authorName2 = "Peter Pan";

    @BeforeEach
    void setUp() {
        author1 = new Author(authorName1);
        book1 = new Book(author1, bookName1);
        bookList.add(book1);
        bookmark1 = new Bookmark(book1, pageNum1);
        bookmarkList.add(bookmark1);

        author2 = new Author(authorName2);
        book2 = new Book(author2, bookName2);
        bookList.add(book2);
        bookmark2 = new Bookmark(book2, pageNum2);
        bookmarkList.add(bookmark2);
    }

    @Test
    public void findByIndex_LargerThanSize() {
        Bookmark bookmarkResult = bookmarkList.findByIndex(2);
        assertEquals(null, bookmarkResult);
    }

    @Test
    public void findByIndex_withinSize() {
        Bookmark bookmarkResult = bookmarkList.findByIndex(0);
        assertEquals(book1, bookmarkResult);
    }

    @Test
    public void toStringTest() {
        String output = "1. \"Harry Potter\" at page: 66" + System.lineSeparator()
                + "2. \"JM Barrie\" at page: 99" + System.lineSeparator();
        assertEquals(output, bookmarkList.toString());
    }




}
