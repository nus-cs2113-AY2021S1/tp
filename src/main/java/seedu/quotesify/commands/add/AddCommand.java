package seedu.quotesify.commands.add;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.commands.Command;
import seedu.quotesify.commands.add.AddCategoryCommand;
import seedu.quotesify.commands.add.AddToDoCommand;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.quote.QuoteParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.todo.ToDo;
import seedu.quotesify.todo.ToDoList;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Logger;
import java.util.logging.Level;

public class AddCommand extends Command {
    public static Logger addLogger = Logger.getLogger("QuotesifyLogger");

    public String type;
    public String information;
    private String arguments;

    public AddCommand(String arguments) {
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
        case TAG_BOOK:
            addLogger.log(Level.INFO, "going to add book to booklist");
            new AddBookCommand(arguments).execute(ui, storage);
            addLogger.log(Level.INFO, "added book to booklist");
            break;
        case TAG_QUOTE:
            addLogger.log(Level.INFO, "going to add quote to quote list");
            QuoteList quotes = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
            addQuote(quotes, ui);
            break;
        case TAG_QUOTE_REFLECTION:
            addLogger.log(Level.INFO, "going to add reflection to quote list");
            QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
            addQuoteReflection(quoteList, ui);
            break;
        case TAG_CATEGORY:
            addLogger.log(Level.INFO, "going to add category to book/quote");
            new AddCategoryCommand(arguments).execute(ui, storage);
            break;
        case TAG_RATING:
            addLogger.log(Level.INFO, "going to add rating to book");
            new AddRatingCommand(arguments).execute(ui, storage);
            addLogger.log(Level.INFO, "rating of book has completed");
            break;
        case TAG_TODO:
            addLogger.log(Level.INFO, "going yo add task to ToDoList");
            new AddToDoCommand(arguments).execute(ui, storage);
            break;
        default:
            ui.printListOfAddCommands();
            break;
        }
        storage.save();
    }

    private void addQuoteReflection(QuoteList quoteList, TextUi ui) {
        try {
            int quoteNum = QuoteParser.parseQuoteNumber(information, quoteList, Command.FLAG_REFLECT);
            String reflection = QuoteParser.getReflectionToAdd(information);
            if (!reflection.isEmpty()) {
                Quote quoteWithReflection = quoteList.addReflection(reflection, quoteNum);
                ui.printAddReflection(quoteWithReflection, quoteWithReflection.getReflection());
            } else {
                throw new QuotesifyException(ERROR_MISSING_REFLECTION);
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            addLogger.log(Level.INFO, "add reflection to quote failed");
        }
    }

    private void addQuote(QuoteList quotes, TextUi ui) {
        try {
            Quote quote = QuoteParser.parseAddParameters(information);
            quotes.add(quote);
            ui.printAddQuote(quote);
            addLogger.log(Level.INFO, "add quote to quote list success");
        } catch (QuotesifyException e) {
            System.out.println(e.getMessage());
            addLogger.log(Level.INFO, "add quote to quote list failed");
            addLogger.log(Level.WARNING, e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
