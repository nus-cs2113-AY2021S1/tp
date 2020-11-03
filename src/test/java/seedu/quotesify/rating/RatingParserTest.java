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

public class RatingParserTest {

    private BookList books;
    private Book book;

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

    @Test
    public void checkValidityOfRatingScore_invalidRatingScore_error() {
        String ratingValue = "6";
        String secondRatingValue = "-1";
        String thirdRatingValue = "a string";
        assertEquals(RatingParser.checkValidityOfRatingScore(ratingValue), 0);
        assertEquals(RatingParser.checkValidityOfRatingScore(secondRatingValue), 0);
        assertEquals(RatingParser.checkValidityOfRatingScore(thirdRatingValue), 0);
    }

    @Test
    public void checkValidityOfRatingScore_validRatingScore_success() {
        String ratingValue = "1";
        String secondRatingValue = "5";
        assertEquals(RatingParser.checkValidityOfRatingScore(ratingValue), 1);
        assertEquals(RatingParser.checkValidityOfRatingScore(secondRatingValue), 5);
    }

    @Test
    public void checkUserInput_emptyInput_returnTrue() {
        String information = "";
        assertTrue(RatingParser.checkUserInput(information));
    }

    @Test
    public void checkForUserInput_validInput_returnFalse() {
        String informationForAddDeleteEdit = "3 1";
        String secondInformationForFind = "POT";
        String thirdInformationForList = "5";
        assertFalse(RatingParser.checkUserInput(informationForAddDeleteEdit));
        assertFalse(RatingParser.checkUserInput(secondInformationForFind));
        assertFalse(RatingParser.checkUserInput(thirdInformationForList));
    }

    @Test
    public void checkBookExists_invalidBook_returnNull() {
        String bookNumber = "2";
        Book bookToRate = RatingParser.checkBookExists(bookNumber);
        assertNull(bookToRate);
    }

    @Test
    public void checkBookExists_validBook_returnNotNull() {
        String bookNumber = "1";
        Book bookToRate = RatingParser.checkBookExists(bookNumber);
        assertNotNull(bookToRate);
    }
}
