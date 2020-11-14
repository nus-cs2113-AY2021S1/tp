package seedu.quotesify.rating;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents a test class for Rating.
 */
public class RatingTest {

    private Rating rating;
    private BookList books;
    private Book book;

    /**
     * Sets up the book.
     */
    @BeforeEach
    public void setUp() {
        books = new BookList();
        String title = "Harry Potter";
        String name = "JK Rowling";
        Author author = new Author(name);
        book = new Book(author, title);
        books.add(book);
    }

    @Test
    public void accessingAttributes_valid_success() {
        rating = new Rating(book, 5);
        assertEquals(rating.getTitle(), "Harry Potter");
        assertEquals(rating.getAuthor(), "JK Rowling");
        assertEquals(rating.getRating(), 5);
        rating.setRating(1);
        assertEquals(rating.getRating(), 1);
    }
}
