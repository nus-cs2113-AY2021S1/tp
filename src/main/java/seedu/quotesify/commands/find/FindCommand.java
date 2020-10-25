package seedu.quotesify.commands.find;

import seedu.quotesify.book.BookList;
import seedu.quotesify.commands.Command;
import seedu.quotesify.commands.find.FindQuoteCommand;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.rating.RatingParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

public class FindCommand extends Command {

    public String type;
    public String information;
    private String arguments;

    public FindCommand(String arguments) {
        this.arguments = arguments;
        String[] details = arguments.split(" ", 2);

        // if user did not provide arguments, let details[1] be empty string
        if (details.length == 1) {
            details = new String[]{details[0], ""};
        }
        type = details[0];
        information = details[1];
    }

    @Override
    public void execute(TextUi ui, Storage storage) {
        switch (type) {
        case TAG_RATING:
            RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
            findRating(ratings, ui);
            break;
        case TAG_BOOK:
            BookList books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
            findBooks(books, ui);
            break;
        case TAG_QUOTE:
            new FindQuoteCommand(arguments).execute(ui, storage);
            break;
        default:
            ui.printListOfFindCommands();
            break;
        }
        storage.save();
    }

    private void findBooks(BookList books, TextUi ui) {
        try {
            String keyword = information.trim();
            if (keyword.isEmpty()) {
                throw new QuotesifyException(ERROR_MISSING_KEYWORD);
            }

            BookList filteredBooks = books.findByKeyword(keyword);

            if (filteredBooks.isEmpty()) {
                throw new QuotesifyException(ERROR_NO_BOOKS_IN_LIST);
            }

            ui.printBooksByKeyword(filteredBooks, keyword);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private void findRating(RatingList ratings, TextUi ui) {
        if (information.isEmpty()) {
            System.out.println(ERROR_RATING_MISSING_INPUTS);
            return;
        }

        String[] titleAndAuthor;
        String title;
        String author;
        try {
            titleAndAuthor = information.split(Command.FLAG_AUTHOR, 2);
            title = titleAndAuthor[0].trim();
            author = titleAndAuthor[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(RatingParser.ERROR_INVALID_FORMAT_RATING);
            return;
        }

        for (Rating rating : ratings.getList()) {
            if (rating.getTitleOfRatedBook().equals(title)
                    && rating.getAuthorOfRatedBook().equals(author)) {
                ui.printFoundRating(rating, title, author);
                return;
            }
        }
        System.out.println(ERROR_RATING_NOT_FOUND);
    }

    public boolean isExit() {
        return false;
    }
}
