package seedu.duke.commands;

import seedu.duke.author.Author;
import seedu.duke.book.Book;
import seedu.duke.book.BookList;
import seedu.duke.category.Category;
import seedu.duke.category.CategoryList;
import seedu.duke.category.CategoryParser;
import seedu.duke.exception.QuotesifyException;
import seedu.duke.lists.ListManager;
import seedu.duke.quote.Quote;
import seedu.duke.quote.QuoteList;
import seedu.duke.quote.QuoteParser;
import seedu.duke.rating.Rating;
import seedu.duke.rating.RatingList;
import seedu.duke.rating.RatingParser;
import seedu.duke.todo.ToDo;
import seedu.duke.todo.ToDoList;
import seedu.duke.ui.TextUi;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.ArrayList;

public class AddCommand extends Command {
    private String type;
    private String information;

    public static Logger addLogger = Logger.getLogger("QuotesifyLogger");

    public AddCommand(String arguments) {
        String[] details = arguments.split(" ", 2);

        // if user did not provide arguments, let details[1] be empty string
        if (details.length == 1) {
            details = new String[]{details[0], ""};
        }
        type = details[0];
        information = details[1];
    }

    public void execute(TextUi ui) {
        switch (type) {
        case TAG_BOOK:
            BookList books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
            Book newBook = addBook(books);
            ui.printAddBook(newBook);
            break;
        case TAG_QUOTE:
            QuoteList quotes = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
            addQuote(quotes, ui);
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
            ToDo newToDo = addToDo(toDos);
            ui.printAddToDo(newToDo);
            break;
        default:
        }
    }

    private Book addBook(BookList books) {
        String[] titleAndAuthor = information.split(Command.FLAG_AUTHOR, 2);
        Author author = new Author(titleAndAuthor[1].trim());
        Book newBook = new Book(author, titleAndAuthor[0].trim());
        books.add(newBook);

        return newBook;
    }

    private void addQuote(QuoteList quotes, TextUi ui) {
        try {
            Quote quote = QuoteParser.parseAddParameters(information);
            quotes.add(quote);
            ui.printAllQuotes(quotes);
        } catch (QuotesifyException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addCategoryToBookOrQuote(CategoryList categories, TextUi ui) {
        String[] tokens = information.split(" ");
        String[] parameters = CategoryParser.getRequiredParameters(tokens);
        if (CategoryParser.isValidParameters(parameters)) {
            executeParameters(categories, parameters, ui);
        }
    }

    private void executeParameters(CategoryList categories, String[] parameters, TextUi ui) {
        try {
            String categoryName = parameters[0];
            String bookTitle = parameters[1];
            int quoteNum = Integer.parseInt(parameters[2]) - 1;

            assert !categoryName.isEmpty() : "category name should not be empty";

            addCategoryToList(categories, categoryName);
            Category category = categories.getCategoryByName(categoryName);

            if (addCategoryToBook(category, bookTitle)) {
                ui.printAddCategoryToBook(bookTitle, category.getCategoryName());
                addLogger.log(Level.INFO, "add category to book success");
            } else {
                addLogger.log(Level.INFO, "add category to book failed");
            }

            if (addCategoryToQuote(category, quoteNum)) {
                QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
                ArrayList<Quote> quotes = quoteList.getList();
                ui.printAddCategoryToQuote(quotes.get(quoteNum).getQuote(), category.getCategoryName());
                addLogger.log(Level.INFO, "add category to quote success");
            } else {
                addLogger.log(Level.INFO, "add category to quote failed");
            }
            // ui.printCategorySize(category);
        } catch (NumberFormatException e) {
            addLogger.log(Level.WARNING, ERROR_INVALID_QUOTE_NUM);
            System.out.println(ERROR_INVALID_QUOTE_NUM);
        } catch (NullPointerException e) {
            addLogger.log(Level.WARNING, e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    private void addCategoryToList(CategoryList categories, String categoryName) {
        if (!categories.isExistingCategory(categoryName)) {
            categories.add(new Category(categoryName));
        }
    }

    private boolean addCategoryToBook(Category category, String bookTitle) {
        if (bookTitle == null || category == null) {
            return false;
        }

        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        try {
            Book book = bookList.findByTitle(bookTitle);
            book.setCategory(category);
        } catch (NullPointerException e) {
            addLogger.log(Level.WARNING, ERROR_NO_BOOK_FOUND);
            System.out.println(ERROR_NO_BOOK_FOUND);
            return false;
        }
        return true;
    }

    private boolean addCategoryToQuote(Category category, int quoteNum) {
        if (quoteNum < 0 || category == null) {
            return false;
        }

        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        ArrayList<Quote> quotes = quoteList.getList();
        try {
            Quote quote = quotes.get(quoteNum);
            quote.setCategory(category);
        } catch (IndexOutOfBoundsException e) {
            addLogger.log(Level.WARNING, ERROR_INVALID_QUOTE_NUM);
            System.out.println(ERROR_INVALID_QUOTE_NUM);
            return false;
        }
        return true;
    }

    private void addRating(RatingList ratings, TextUi ui) {
        String[] ratingDetails = information.split(" ", 2);
        String titleOfBookToRate = ratingDetails[1].trim();

        int ratingScore = RatingParser.checkFormatOfRatingValue(ratingDetails[0]);
        if (ratingScore == 0) {
            return;
        }
        boolean isValid = RatingParser.checkRangeOfRatingValue(ratingScore);
        if (isValid && !isRated(ratings, titleOfBookToRate) && isExistingBook(titleOfBookToRate)) {
            ratings.add(new Rating(ratingScore, titleOfBookToRate));
            ui.printAddRatingToBook(ratingScore, titleOfBookToRate);
        }
    }

    private boolean isExistingBook(String titleOfBookToRate) {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        ArrayList<Book> existingBooks = bookList.getList();
        boolean doesExist = false;
        assert existingBooks.size() != 0 : "List of books should not be empty";
        for (Book existingBook : existingBooks) {
            if (existingBook.getTitle().equals(titleOfBookToRate)) {
                doesExist = true;
                break;
            }
        }
        if (!doesExist) {
            addLogger.log(Level.INFO, "book does not exist");
            System.out.println(ERROR_BOOK_TO_RATE_NOT_FOUND);
        }
        return doesExist;
    }

    private boolean isRated(RatingList ratings, String titleOfBookToRate) {
        boolean isRated = false;
        String titleOfRatedBook;
        for (Rating rating : ratings.getList()) {
            titleOfRatedBook = rating.getTitleOfRatedBook();
            if (titleOfRatedBook.equals(titleOfBookToRate)) {
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

    private ToDo addToDo(ToDoList toDos) {
        String[] taskNameAndDeadline = information.split("/by", 2);
        String taskName = taskNameAndDeadline[0].trim();
        String deadline = taskNameAndDeadline[1].trim();
        ToDo newToDo = new ToDo(taskName,deadline);
        toDos.add(newToDo);

        return newToDo;
    }

    public boolean isExit() {
        return false;
    }
}
