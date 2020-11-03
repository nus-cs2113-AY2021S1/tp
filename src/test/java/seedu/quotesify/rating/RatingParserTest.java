package seedu.quotesify.rating;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.lists.ListManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
     * Checks if the rating score is invalid (0).
     */
    @Test
    public void checkValidityOfRatingScore_invalidRatingScore_error() {
        String ratingValue = "6";
        String secondRatingValue = "-1";
        String thirdRatingValue = "a string";
        assertEquals(RatingParser.checkValidityOfRatingScore(ratingValue), 0);
        assertEquals(RatingParser.checkValidityOfRatingScore(secondRatingValue), 0);
        assertEquals(RatingParser.checkValidityOfRatingScore(thirdRatingValue), 0);
    }

    /**
     * Tests if the rating score is valid.
     * Valid values of 1 and 5 are used based on BVA.
     * Checks if the rating score returned is that of the user input.
     */
    @Test
    public void checkValidityOfRatingScore_validRatingScore_success() {
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
    public void checkUserInput_emptyInput_returnTrue() {
        String information = "";
        assertTrue(RatingParser.checkUserInput(information));
    }

    /**
     * Tests if the user input is empty.
     * Checks for false if the user input is not empty.
     */
    @Test
    public void checkForUserInput_validInput_returnFalse() {
        String informationForAddDeleteEdit = "3 1";
        String secondInformationForFind = "POT";
        String thirdInformationForList = "5";
        assertFalse(RatingParser.checkUserInput(informationForAddDeleteEdit));
        assertFalse(RatingParser.checkUserInput(secondInformationForFind));
        assertFalse(RatingParser.checkUserInput(thirdInformationForList));
    }

    /**
     * Tests if the book exists in Quotesify.
     * If the book does not exists, Null is returned.
     * Uses an invalid book in this case (negative test).
     */
    @Test
    public void checkBookExists_invalidBook_returnNull() {
        String bookNumber = "2";
        Book bookToRate = RatingParser.checkBookExists(bookNumber);
        assertNull(bookToRate);
    }

    /**
     * Tests if the book exists in Quotesify.
     * If the book exists, the book will be returned instead of Null.
     * Uses a valid book in this case (positive test).
     */
    @Test
    public void checkBookExists_validBook_returnNotNull() {
        String bookNumber = "1";
        Book bookToRate = RatingParser.checkBookExists(bookNumber);
        assertNotNull(bookToRate);
    }
}
