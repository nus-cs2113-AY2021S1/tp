package seedu.quotesify.commands;

import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.category.Category;
import seedu.quotesify.category.CategoryList;
import seedu.quotesify.category.CategoryParser;
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

    private String type;
    private String information;

    public AddCommand(String arguments) {
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
            CategoryList categories = (CategoryList) ListManager.getList(ListManager.CATEGORY_LIST);
            addCategoryToBookOrQuote(categories, ui);
            break;
        case TAG_RATING:
            addLogger.log(Level.INFO, "going to add rating to book");
            RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
            addRating(ratings, ui);
            addLogger.log(Level.INFO, "rating of book has completed");
            break;
        case TAG_TODO:
            ToDoList toDos = (ToDoList) ListManager.getList(ListManager.TODO_LIST);
            ToDo newToDo = addToDo(toDos, ui);
            ui.printAddToDo(newToDo);
            break;
        default:
            ui.printListOfAddComnmands();
            break;
        }
        storage.save();
    }

    private void addQuoteReflection(QuoteList quoteList, TextUi ui) {
        try {
            int quoteNum = QuoteParser.parseQuoteNumber(information, quoteList, Command.FLAG_REFLECT);
            String reflection = QuoteParser.getReflectionToAdd(information);
            Quote quoteWithReflection = quoteList.addReflection(reflection, quoteNum);
            ui.printAddReflection(quoteWithReflection, quoteWithReflection.getReflection());
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
            if (authorName.isEmpty()) {
                throw new QuotesifyException(ERROR_NO_AUTHOR_NAME);
            }

            books.ensureNoSimilarBooks(title, authorName);
            Book newBook = createNewBook(title, authorName);
            books.add(newBook);
            ui.printAddBook(newBook);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            addLogger.log(Level.INFO, "add book to booklist failed");
        }
    }

    public Book createNewBook(String title, String authorName) {
        Author author = new Author(authorName);
        Book newBook = new Book(author, title);

        return newBook;
    }

    private void addQuote(QuoteList quotes, TextUi ui) {
        try {
            Quote quote = QuoteParser.parseAddParameters(information);
            quotes.add(quote);
            ui.printAllQuotes(quotes);
            addLogger.log(Level.INFO, "add quote to quote list success");
        } catch (QuotesifyException e) {
            System.out.println(e.getMessage());
            addLogger.log(Level.INFO, "add quote to quote list failed");
            addLogger.log(Level.WARNING, e.getMessage());
        }
    }

    private void addCategoryToBookOrQuote(CategoryList categories, TextUi ui) {
        String[] tokens = information.split(" ");
        String[] parameters = CategoryParser.getRequiredParameters(tokens);
        if (CategoryParser.isValidParameters(parameters)) {
            executeParameters(categories, parameters, ui);
        }
    }

    private void executeParameters(CategoryList categoryList, String[] parameters, TextUi ui) {
        try {
            String categoryNames = parameters[0];
            assert !categoryNames.isEmpty() : "category name should not be empty";

            List<String> categories = CategoryParser.parseCategoriesToList(categoryNames);
            for (String categoryName : categories) {
                addCategoryToList(categoryList, categoryName);
                Category category = categoryList.getCategoryByName(categoryName);

                String bookNum = parameters[1];
                String quoteNum = parameters[2];

                addCategoryToBook(category, bookNum, ui);
                addCategoryToQuote(category, quoteNum, ui);
                categoryList.updateListInCategory(category);
            }
        } catch (QuotesifyException e) {
            addLogger.log(Level.WARNING, e.getMessage());
            ui.printErrorMessage(e.getMessage());
        }
    }

    private void addCategoryToList(CategoryList categories, String categoryName) {
        if (!categories.isExistingCategory(categoryName)) {
            categories.add(new Category(categoryName));
        }
    }

    private void addCategoryToBook(Category category, String bookNum, TextUi ui) {
        // ignore this action if user did not provide book title
        if (bookNum.isEmpty()) {
            return;
        }

        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        try {
            int bookIndex = Integer.parseInt(bookNum) - 1;
            Book book = bookList.getBook(bookIndex);
            book.getCategories().add(category.getCategoryName());
            ui.printAddCategoryToBook(book.getTitle(), category.getCategoryName());
            addLogger.log(Level.INFO, "add category to book success");
        } catch (IndexOutOfBoundsException e) {
            addLogger.log(Level.WARNING, ERROR_NO_BOOK_FOUND);
            ui.printErrorMessage(ERROR_NO_BOOK_FOUND);
        } catch (NumberFormatException e) {
            addLogger.log(Level.WARNING, ERROR_INVALID_BOOK_NUM);
            ui.printErrorMessage(ERROR_INVALID_BOOK_NUM);
        }
    }

    private void addCategoryToQuote(Category category, String index, TextUi ui) {
        // ignore this action if user did not provide quote number
        if (index.isEmpty()) {
            return;
        }

        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        ArrayList<Quote> quotes = quoteList.getList();
        try {
            int quoteNum = Integer.parseInt(index) - 1;
            Quote quote = quotes.get(quoteNum);
            quote.getCategories().add(category.getCategoryName());
            ui.printAddCategoryToQuote(quote.getQuote(), category.getCategoryName());
            addLogger.log(Level.INFO, "add category to quote success");
        } catch (IndexOutOfBoundsException e) {
            addLogger.log(Level.WARNING, ERROR_NO_QUOTE_FOUND);
            ui.printErrorMessage(ERROR_NO_QUOTE_FOUND);
        } catch (NumberFormatException e) {
            addLogger.log(Level.WARNING, ERROR_INVALID_QUOTE_NUM);
            ui.printErrorMessage(ERROR_INVALID_QUOTE_NUM);
        }
    }

    private void addRating(RatingList ratings, TextUi ui) {

        String[] ratingDetails;
        String titleOfBookToRate;

        try {
            ratingDetails = information.split(" ", 2);
            titleOfBookToRate = ratingDetails[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ERROR_RATING_MISSING_BOOK_TITLE_OR_RATING_SCORE);
            return;
        }

        int ratingScore = RatingParser.checkValidityOfRatingScore(ratingDetails[0]);
        boolean isValid = ((ratingScore != 0) && (!isRated(ratings, titleOfBookToRate))
                && isExistingBook(titleOfBookToRate));

        if (isValid) {
            ratings.add(new Rating(ratingScore, titleOfBookToRate));
            ui.printAddRatingToBook(ratingScore, titleOfBookToRate);
        }
    }

    private boolean isExistingBook(String titleOfBookToRate) {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        ArrayList<Book> existingBooks = bookList.getList();

        boolean isFound = false;
        for (Book existingBook : existingBooks) {
            if (existingBook.getTitle().equals(titleOfBookToRate)) {
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            addLogger.log(Level.INFO, "book does not exist");
            System.out.println(ERROR_BOOK_TO_RATE_NOT_FOUND);
        }
        return isFound;
    }

    private boolean isRated(RatingList ratings, String titleOfBookToRate) {
        boolean isRated = false;
        for (Rating rating : ratings.getList()) {
            if (rating.getTitleOfRatedBook().equals(titleOfBookToRate)) {
                isRated = true;
                break;
            }
        }

        if (isRated) {
            addLogger.log(Level.INFO, "book has been rated");
            System.out.println(ERROR_RATING_EXIST);
            return true;
        }
        return false;
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
