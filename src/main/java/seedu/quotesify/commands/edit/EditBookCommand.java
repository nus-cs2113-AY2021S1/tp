package seedu.quotesify.commands.edit;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.commands.edit.EditCommand;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class EditBookCommand extends EditCommand {

    public EditBookCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        BookList books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        editBook(books, ui);
    }

    private void editBook(BookList books, TextUi ui) {
        try {
            String[] bookDetails = information.split(FLAG_EDIT, 2);
            if (bookDetails.length == 1) {
                bookDetails = new String[]{bookDetails[0], ""};
            }

            int bookIndex = Integer.parseInt(bookDetails[0].trim()) - 1;
            String newTitle = bookDetails[1].trim();
            if (newTitle.isEmpty()) {
                throw new QuotesifyException(ERROR_BOOK_TITLE_MISSING);
            }

            Book book = books.getBook(bookIndex);
            String oldTitle = book.getTitle();
            String authorName = book.getAuthor().getName();

            books.ensureNoSimilarBooks(newTitle, authorName);
            book.setTitle(newTitle);
            ui.printEditBook(oldTitle, newTitle);

            checkRatingForOldTitle(book, oldTitle, authorName);

        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.printErrorMessage(ERROR_INVALID_BOOK_NUM);
        }
    }

    private void checkRatingForOldTitle(Book book, String oldTitle, String author) {
        // check ratings in rating list before editing the title.
        RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
        for (Rating rating : ratings.getList()) {
            if (rating.getTitle().equals(oldTitle) && rating.getAuthor().equals(author)) {
                int currentRatingOfBook = rating.getRating();
                ratings.delete(ratings.getList().indexOf(rating));
                ratings.add(new Rating(book, currentRatingOfBook));
                break;
            }
        }
    }
}
