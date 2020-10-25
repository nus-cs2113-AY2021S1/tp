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
import seedu.quotesify.rating.RatingParser;
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
            RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
            deleteRating(ratings, ui);
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

    private void deleteRating(RatingList ratings, TextUi ui) {
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

        Rating ratingToBeDeleted = null;
        for (Rating rating : ratings.getList()) {
            if (rating.getTitleOfRatedBook().equals(title)
                    && rating.getAuthorOfRatedBook().equals(author)) {
                ratingToBeDeleted = rating;
                break;
            }
        }

        if (ratingToBeDeleted == null) {
            System.out.println(ERROR_RATING_NOT_FOUND);
            return;
        }
        ratingToBeDeleted.getRatedBook().setRating(0);
        ratings.delete(ratings.getList().indexOf(ratingToBeDeleted));
        ui.printDeleteRating(title, author);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}