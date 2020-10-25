package seedu.quotesify.commands.find;

import seedu.quotesify.book.BookList;
import seedu.quotesify.commands.Command;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

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
            new FindRatingCommand(arguments).execute(ui, storage);
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

    public boolean isExit() {
        return false;
    }
}
