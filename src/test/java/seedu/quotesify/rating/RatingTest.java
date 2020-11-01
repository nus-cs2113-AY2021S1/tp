package seedu.quotesify.rating;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RatingTest {

    private RatingList ratings;
    private BookList books;
    private Book book;
    private TextUi ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        ListManager.initialiseAllLists();
        books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
        ui = new TextUi();
        storage = new Storage("");
        String title = "Harry Potter";
        String name = "JK Rowling";
        Author author = new Author(name);
        book = new Book(author, title);
        books.add(book);
    }

    @Test
    public void addRating_validRatingScore_ratingAdded() {
        String ratingValue = "3";
        int ratingScore = RatingParser.checkValidityOfRatingScore(ratingValue);
        assertEquals(ratingScore, 3);
        Rating rating = new Rating(book, ratingScore);
        ratings.add(rating);
        assertEquals(ratings.toString(), "[Harry Potter] by JK Rowling: 3 star" + System.lineSeparator());
    }

    @Test
    public void addRating_invalidRatingScore_throwsQuotesifyException() {
        String ratingValue = "6";
        String secondRatingValue = "-1";
        String thirdRatingValue = "a string";
        assertEquals(RatingParser.checkValidityOfRatingScore(ratingValue), 0);
        assertEquals(RatingParser.checkValidityOfRatingScore(secondRatingValue), 0);
        assertEquals(RatingParser.checkValidityOfRatingScore(thirdRatingValue), 0);
    }

    @Test
    public void checkForUserInput_emptyInput_throwsQuotesifyException() {
        String information = "";
        assertTrue(RatingParser.checkUserInput(information));
    }

    @Test
    public void checkForUserInput_validInput_success() {
        String informationForAddDeleteEdit = "3 1";
        String secondInformationForFind = "POT";
        String thirdInformationForList = "5";
        assertFalse(RatingParser.checkUserInput(informationForAddDeleteEdit));
        assertFalse(RatingParser.checkUserInput(secondInformationForFind));
        assertFalse(RatingParser.checkUserInput(thirdInformationForList));
    }

    @Test
    public void checkBookExists_invalidBook_throwsQuotesifyException() {
        String bookNumber = "2";
        Book bookToRate = RatingParser.checkBookExists(bookNumber);
        assertNull(bookToRate);
    }

    @Test
    public void checkBookExists_validBook_throwsQuotesifyException() {
        String bookNumber = "1";
        Book bookToRate = RatingParser.checkBookExists(bookNumber);
        assertNotNull(bookToRate);
    }

    @Test
    public void deleteRating_validBook_ratingDeleted() {
        book.setRating(0);
        assertEquals(book.getRating(), 0);
        assertEquals(ratings.getList().size(), 0);
    }

    @Test
    public void listRatings_emptyList_success() {
        assertEquals(ratings.toString(), "");
    }
}
