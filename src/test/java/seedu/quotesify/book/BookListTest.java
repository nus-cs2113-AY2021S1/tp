package seedu.quotesify.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.quotesify.author.Author;
import seedu.quotesify.exception.QuotesifyException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookListTest {
    private BookList bookList;
    private Book book1, book2;
    private Author author1, author2;

    @BeforeEach
    void setUp() {
        bookList = new BookList();

        author1 = new Author("JK Rowling");
        book1 = new Book(author1, "Harry Potter");
        bookList.add(book1);

        author2 = new Author("JM Barrie");
        book2 = new Book(author2, "Peter Pan");
        bookList.add(book2);
    }

    @Test
    void findByKeyword_panKeyword_oneBook() {
        BookList actualOutput = bookList.findByKeyword("pan");
        assertEquals(1, actualOutput.getSize());
        assertEquals(book2, actualOutput.getBook(0));
    }

    @Test
    void findByKeyword_invalidKeyword_emptyList() {
        BookList actualOutput = bookList.findByKeyword("nothing");
        assertTrue(actualOutput.isEmpty());
    }

    @Test
    void filterDone_true_emptyList() {
        BookList actualOutput = bookList.filterDone(true);
        assertTrue(actualOutput.isEmpty());
    }

    @Test
    void filterDone_false_twoBooks() {
        BookList actualOutput = bookList.filterDone(false);
        assertEquals(2, actualOutput.getSize());
    }

    @Test
    void ensureNoSimilarBooks_existingBook_throwsQuotesifyException() {
        Throwable exception = assertThrows(QuotesifyException.class, () -> {
            bookList.ensureNoSimilarBooks("Harry Potter", "JK Rowling");
        });
        assertEquals("The book already exists!", exception.getMessage());
    }

    @Test
    void findByTitle_lowerCaseExistingTitle_oneBook() {
        Book actual = bookList.findByTitle("harry potter");
        assertEquals(book1, actual);
    }

    @Test
    void findByTitle_invalidTitle_null() {
        Book actual = bookList.findByTitle("invalid");
        assertNull(actual);
    }

    @Test
    void findExistingAuthor_lowerCaseExistingAuthor_oneAuthor() {
        Author actual = bookList.findExistingAuthor("jm barrie");
        assertEquals(author2, actual);
    }

    @Test
    void findByNum_invalidNum_null() {
        Book actual = bookList.findByNum(100);
        assertNull(actual);
    }
}