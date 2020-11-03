package seedu.quotesify.rating;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.lists.ListManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents a test class for RatingList.
 */
public class RatingListTest {

    private RatingList ratings;
    private BookList books;
    private Book book;
    private Rating rating;

    /**
     * Sets up the book and rating.
     */
    @BeforeEach
    void setUp() {
        ListManager.initialiseAllLists();
        ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
        books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        String title = "Harry Potter";
        String name = "JK Rowling";
        Author author = new Author(name);
        book = new Book(author, title);
        books.add(book);
        rating = new Rating(book, 5);
    }

    /**
     * Tests a rating added into the rating list.
     * Checks by returning a string of the book details.
     */
    @Test
    public void add_validRating_ratingAdded() {
        ratings.add(rating);
        assertEquals(ratings.toString(), "[Harry Potter] by JK Rowling: 5 star" + System.lineSeparator());
    }

    /**
     * Tests a rating deleted from the rating list.
     * Checks if the rating in book is set to unrated (0) and if the rating list is of size 0.
     */
    @Test
    public void delete_validRating_ratingDeleted() {
        ratings.add(rating);
        ratings.delete(0);
        book.setRating(0);
        assertEquals(book.getRating(), 0);
        assertEquals(ratings.getList().size(), 0);
    }
}
