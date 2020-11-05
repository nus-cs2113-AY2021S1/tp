package seedu.quotesify.rating;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.commands.add.AddCommand;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;

import java.util.logging.Level;

/**
 * Represents a Rating Parser.
 */
public class RatingParser {

    /** Boundary, invalid and unrated values for rating score. */
    public static final int RATING_ONE = 1;
    public static final int RATING_FIVE = 5;
    public static final int UNRATED = 0;
    public static final int INVALID_RATING = 0;

    /** Error messages for inputs checked by rating parser. */
    public static final String ERROR_INVALID_RATING_SCORE = "That score is out of our range my friend!";
    public static final String ERROR_INVALID_FORMAT_RATING = "Sorry I don't understand you";
    public static final String ERROR_RATING_MISSING_INPUTS = "I need more details from you!";
    private static final String ERROR_NO_BOOK_FOUND = "There is no such book!";

    /**
     * Checks the validity of rating score.
     *
     * @param rating Rating to be validated.
     * @return Value of rating score.
     * @throws QuotesifyException If rating score is invalid.
     */
    public static int checkValidityOfRatingScore(String rating) throws QuotesifyException {
        int ratingScore;
        try {
            ratingScore = Integer.parseInt(rating);
        } catch (NumberFormatException e) {
            AddCommand.quotesifyLogger.log(Level.INFO, "invalid format provided");
            throw new QuotesifyException(ERROR_INVALID_FORMAT_RATING);
        }
        if (!(ratingScore >= RATING_ONE && ratingScore <= RATING_FIVE)) {
            AddCommand.quotesifyLogger.log(Level.INFO, "rating score out of range");
            throw new QuotesifyException(ERROR_INVALID_RATING_SCORE);
        }
        return ratingScore;
    }

    /**
     * Checks if user input is empty.
     *
     * @param information Input entered by user.
     * @throws QuotesifyException If user input is empty.
     */
    public static void checkUserInput(String information) throws QuotesifyException {
        if (information.isEmpty()) {
            AddCommand.quotesifyLogger.log(Level.INFO, "user input is missing");
            throw new QuotesifyException(ERROR_RATING_MISSING_INPUTS);
        }
    }

    /**
     * Checks if book exists.
     *
     * @param bookNumber Index of book.
     * @return Book with the specified index.
     * @throws QuotesifyException If book does not exist.
     */
    public static Book checkBookExists(String bookNumber) throws QuotesifyException {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        assert bookList != null : "book list should not be null";
        Book bookToRate;
        try {
            int indexOfBook = Integer.parseInt(bookNumber) - 1;
            bookToRate = bookList.getBook(indexOfBook);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            AddCommand.quotesifyLogger.log(Level.INFO, "book does not exist");
            throw new QuotesifyException(ERROR_NO_BOOK_FOUND);
        }
        return bookToRate;
    }
}
