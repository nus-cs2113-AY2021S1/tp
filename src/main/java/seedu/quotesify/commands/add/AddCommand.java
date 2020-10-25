package seedu.quotesify.commands.add;

import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.commands.Command;
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
            BookList books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
            addBook(books, ui);
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
            ToDoList toDos = (ToDoList) ListManager.getList(ListManager.TODO_LIST);
            ToDo newToDo = addToDo(toDos, ui);
            ui.printAddToDo(newToDo);
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

    private void addBook(BookList books, TextUi ui) {
        try {
            String[] titleAndAuthor = information.split(Command.FLAG_AUTHOR, 2);
            // if user did not provide author, let titleAndAuthor[1] be empty string
            if (titleAndAuthor.length == 1) {
                titleAndAuthor = new String[]{titleAndAuthor[0], ""};
            }

            String title = titleAndAuthor[0].trim();
            String authorName = titleAndAuthor[1].trim();

            checkMissingInformation(title, authorName);
            Book newBook = createNewBook(books, title, authorName);

            books.add(newBook);
            books.sort();
            ui.printAddBook(newBook);

        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            addLogger.log(Level.INFO, "add book to booklist failed");
        }
    }

    private Book createNewBook(BookList books, String title, String authorName) throws QuotesifyException {
        Book newBook;
        Author existingAuthor = books.findExistingAuthor(authorName);

        if (existingAuthor == null) {
            // Book is definitely unique
            newBook = new Book(new Author(authorName), title);
        } else {
            books.ensureNoSimilarBooks(title, existingAuthor.getName());
            newBook = new Book(existingAuthor, title);
        }

        return newBook;
    }

    private void checkMissingInformation(String title, String authorName) throws QuotesifyException {
        if (title.isEmpty()) {
            throw new QuotesifyException(ERROR_BOOK_TITLE_MISSING);
        }
        if (authorName.isEmpty()) {
            throw new QuotesifyException(ERROR_NO_AUTHOR_NAME);
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

    private ToDo addToDo(ToDoList toDos, TextUi ui) {
        String[] taskNameAndDeadline = information.split("/by", 2);
        ToDo newToDo = null;

        try {
            // if user did not provide deadline, let titleAndAuthor[1] be "not specified"
            if (taskNameAndDeadline.length == 1) {
                taskNameAndDeadline = new String[]{taskNameAndDeadline[0], "not specified"};
            }
            if (taskNameAndDeadline[0].isEmpty()) {
                throw new QuotesifyException(ERROR_NO_TASK_NAME);
            }

            String taskName = taskNameAndDeadline[0].trim();
            assert !taskName.isEmpty() : "task name should not be empty";
            String deadline = taskNameAndDeadline[1].trim();
            assert !deadline.isEmpty() : "deadline should not be empty";
            newToDo = new ToDo(taskName, deadline);
            newToDo.updateDateFormat();
            toDos.add(newToDo);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            addLogger.log(Level.INFO, "add toDo to toDoList failed");
        }

        return newToDo;
    }


    public boolean isExit() {
        return false;
    }
}
