package seedu.quotesify.commands.add;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.commands.Command;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.rating.RatingParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.ArrayList;
import java.util.logging.Level;

public class AddRatingCommand extends AddCommand {

    public AddRatingCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TextUi ui, Storage storage) {
        RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
        addRating(ratings, ui);
    }

    private void addRating(RatingList ratings, TextUi ui) {

        boolean hasMissingInput = RatingParser.checkUserInput(information);
        if (hasMissingInput) {
            return;
        }

        String[] ratingDetails;
        String title;
        String author;
        try {
            ratingDetails = information.split(" ", 2);
            String[] titleAndAuthor = ratingDetails[1].split(Command.FLAG_AUTHOR, 2);
            title = titleAndAuthor[0].trim();
            author = titleAndAuthor[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(RatingParser.ERROR_INVALID_FORMAT_RATING);
            return;
        }

        int ratingScore = RatingParser.checkValidityOfRatingScore(ratingDetails[0]);
        Book bookToRate = checkBookExists(title, author);
        boolean isRated = isRated(bookToRate);
        boolean isValid = (ratingScore != RatingParser.INVALID_RATING) && (bookToRate != null) && (!isRated);
        if (isValid) {
            bookToRate.setRating(ratingScore);
            ratings.add(new Rating(bookToRate, ratingScore));
            ui.printAddRating(ratingScore, title, author);
        }
    }

    private boolean isRated(Book bookToRate) {
        if (bookToRate != null && bookToRate.getRating() != RatingParser.UNRATED) {
            addLogger.log(Level.INFO, "book has been rated");
            System.out.println(ERROR_RATING_EXIST);
            return true;
        }
        return false;
    }

    private Book checkBookExists(String titleOfBookToRate, String authorOfBookToRate) {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        ArrayList<Book> existingBooks = bookList.getList();
        Book bookToRate = null;
        String author;
        for (Book book : existingBooks) {
            author = book.getAuthor().getName();
            if (book.getTitle().equals(titleOfBookToRate) && author.equals(authorOfBookToRate)) {
                bookToRate = book;
            }
        }
        if (bookToRate == null) {
            addLogger.log(Level.INFO, "book does not exist");
            System.out.println(ERROR_BOOK_TO_RATE_NOT_FOUND);
        }
        return bookToRate;
    }
}
