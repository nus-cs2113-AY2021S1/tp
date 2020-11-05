package seedu.quotesify.rating;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Represents a test class for RatingParser.
 */
public class RatingParserTest {

    private BookList books;
    private Book book;

    /**
     * Sets up the book.
     */
    @BeforeEach
    void setUp() {
        ListManager.initialiseAllLists();
        books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        String title = "Harry Potter";
        String name = "JK Rowling";
        Author author = new Author(name);
        book = new Book(author, title);
        books.add(book);
    }

    /**
     * Tests if the rating score is valid.
     * Invalid values of -1 and 6 are used based on EP and BVA.
     * Invalid value of non-int format is used.
     */
    @Test
    public void checkValidityOfRatingScore_invalidRatingScore_throwsQuotesifyException() {
        String ratingValue = "6";
        String secondRatingValue = "-1";
        String thirdRatingValue = "a string";
        Throwable exception = assertThrows(QuotesifyException.class, () -> {
            RatingParser.checkValidityOfRatingScore(ratingValue);
        });
        Throwable exception2 = assertThrows(QuotesifyException.class, () -> {
            RatingParser.checkValidityOfRatingScore(secondRatingValue);
        });
        Throwable exception3 = assertThrows(QuotesifyException.class, () -> {
            RatingParser.checkValidityOfRatingScore(thirdRatingValue);
        });
        assertEquals(RatingParser.ERROR_INVALID_RATING_SCORE, exception.getMessage());
        assertEquals(RatingParser.ERROR_INVALID_RATING_SCORE, exception2.getMessage());
        assertEquals(RatingParser.ERROR_INVALID_FORMAT_RATING, exception3.getMessage());
    }

    /**
     * Tests if the rating score is valid.
     * Valid values of 1 and 5 are used based on BVA.
     * Checks if the rating score returned is that of the user input.
     */
    @Test
    public void checkValidityOfRatingScore_validRatingScore_success() throws QuotesifyException {
        String ratingValue = "1";
        String secondRatingValue = "5";
        assertEquals(RatingParser.checkValidityOfRatingScore(ratingValue), 1);
        assertEquals(RatingParser.checkValidityOfRatingScore(secondRatingValue), 5);
    }

    /**
     * Tests if the user input is empty.
     * Checks for true if the user input is empty.
     */
    @Test
    public void checkUserInput_emptyInput_throwsQuotesifyException() {
        String information = "";
        Throwable exception = assertThrows(QuotesifyException.class, () -> {
            RatingParser.checkUserInput(information);
        });
        assertEquals(RatingParser.ERROR_RATING_MISSING_INPUTS, exception.getMessage());
    }

    /**
     * Tests if the book exists in Quotesify.
     * If the book does not exist, Quotesify Exception is thrown.
     * Uses an invalid book in this case (negative test).
     */
    @Test
    public void checkBookExists_invalidBook_throwsQuotesifyException() {
        String bookNumber = "2";
        Throwable exception = assertThrows(QuotesifyException.class, () -> {
            RatingParser.checkBookExists(bookNumber);
        });
        assertEquals(RatingParser.ERROR_NO_BOOK_FOUND, exception.getMessage());
    }

    /**
     * Tests if the book exists in Quotesify.
     * If the book exists, the book will be returned.
     * Uses a valid book in this case (positive test).
     */
    @Test
    public void checkBookExists_validBook_returnNotNull() throws QuotesifyException {
        String bookNumber = "1";
        Book bookToRate = RatingParser.checkBookExists(bookNumber);
        assertNotNull(bookToRate);
    }
}
