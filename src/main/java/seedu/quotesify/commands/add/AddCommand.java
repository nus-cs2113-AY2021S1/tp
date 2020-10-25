package seedu.quotesify.commands.add;

import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.category.Category;
import seedu.quotesify.category.CategoryList;
import seedu.quotesify.category.CategoryParser;
import seedu.quotesify.commands.add.AddBookCommand;
import seedu.quotesify.commands.Command;
import seedu.quotesify.commands.add.AddCategoryCommand;
import seedu.quotesify.commands.add.AddToDoCommand;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.quote.QuoteParser;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.rating.RatingParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.todo.ToDo;
import seedu.quotesify.todo.ToDoList;
import seedu.quotesify.ui.TextUi;

import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.ArrayList;

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
            RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
            addRating(ratings, ui);
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

    private void addRating(RatingList ratings, TextUi ui) {
        if (information.isEmpty()) {
            System.out.println(ERROR_RATING_MISSING_INPUTS);
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
        boolean isValid = (ratingScore != 0) && (bookToRate != null) && (!isRated);
        if (isValid) {
            bookToRate.setRating(ratingScore);
            ratings.add(new Rating(bookToRate, ratingScore));
            ui.printAddRatingToBook(ratingScore, title, author);
        }
    }

    private boolean isRated(Book bookToRate) {
        if (bookToRate != null && bookToRate.getRating() != 0) {
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


    public boolean isExit() {
        return false;
    }
}
