package seedu.quotesify.commands.delete;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.bookmark.Bookmark;
import seedu.quotesify.bookmark.BookmarkList;
import seedu.quotesify.commands.Command;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.store.Storage;
import seedu.quotesify.todo.ToDo;
import seedu.quotesify.todo.ToDoList;
import seedu.quotesify.ui.TextUi;

public class DeleteCommand extends Command {
    public String type;
    public String information;
    private String arguments;

    public DeleteCommand(String arguments) {
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
        case TAG_CATEGORY:
            new DeleteCategoryCommand(arguments).execute(ui, storage);
            break;
        case TAG_BOOK:
            new DeleteBookCommand(arguments).execute(ui, storage);
            break;
        case TAG_RATING:
            new DeleteRatingCommand(arguments).execute(ui, storage);
            break;
        case TAG_TODO:
            new DeleteToDoCommand(arguments).execute(ui, storage);
            break;
        case TAG_BOOKMARK:
            new DeleteBookmarkCommand(arguments).execute(ui, storage);
            break;
        case TAG_QUOTE:
            new DeleteQuoteCommand(arguments).execute(ui, storage);
            break;
        case TAG_QUOTE_REFLECTION:
            QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
            deleteQuoteReflection(quoteList, ui, information);
            break;
        default:
            ui.printListOfDeleteCommands();
            break;
        }
        storage.save();
    }

    private void deleteQuoteReflection(QuoteList quoteList, TextUi ui, String information) {
        try {
            int quoteNumber = Integer.parseInt(information.trim()) - 1;
            quoteList.deleteReflection(quoteNumber);
            ui.printDeleteQuoteReflection(quoteList.getQuote(quoteNumber).getQuote());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(ERROR_INVALID_QUOTE_NUM);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}