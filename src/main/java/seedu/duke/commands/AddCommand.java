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

import java.util.ArrayList;

public class AddCommand extends Command {
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

    public void execute(TextUi ui, ListManager listManager) {
        switch (type) {
        case TAG_BOOK:
            BookList books = (BookList) listManager.getList(ListManager.BOOK_LIST);
            Book newBook = addBook(books);
            ui.printAddBook(newBook);
            break;
        case TAG_QUOTE:
            QuoteList quotes = (QuoteList) listManager.getList(ListManager.QUOTE_LIST);
            addQuote(quotes, ui);
            break;
        case TAG_CATEGORY:
            CategoryList categories = (CategoryList) listManager.getList(ListManager.CATEGORY_LIST);
            addCategoryToBookOrQuote(categories, ui, listManager);
            break;
        case TAG_RATING:
            RatingList ratings = (RatingList) listManager.getList(ListManager.RATING_LIST);
            addRating(ratings, ui, listManager);
            break;
        case TAG_TODO:
            ToDoList toDos = (ToDoList) listManager.getList(ListManager.TODO_LIST);
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

    private void addCategoryToBookOrQuote(CategoryList categories, TextUi ui, ListManager listManager) {
        String[] tokens = information.split(" ");
        String[] parameters = CategoryParser.getRequiredParameters(tokens);
        executeParameters(categories, parameters, ui, listManager);
    }

    private void executeParameters(CategoryList categories, String[] parameters, TextUi ui, ListManager listManager) {
        try {
            String categoryName = parameters[0];
            String bookTitle = parameters[1];
            int quoteNum = Integer.parseInt(parameters[2]) - 1;

            addCategoryToList(categories, categoryName);

            Category category = categories.getCategoryByName(categoryName);
            if (addCategoryToBook(category, bookTitle, listManager)) {
                ui.printAddCategoryToBook(bookTitle, category.getCategoryName());
            }

            if (addCategoryToQuote(category, quoteNum, listManager)) {
                QuoteList quoteList = (QuoteList) listManager.getList(ListManager.QUOTE_LIST);
                ArrayList<Quote> quotes = quoteList.getList();
                ui.printAddCategoryToQuote(quotes.get(quoteNum).getQuote(), category.getCategoryName());
            }
            ui.printCategorySize(category);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_QUOTE_NUM);
        }
    }

    private void addCategoryToList(CategoryList categories, String categoryName) {
        if (!categories.doesCategoryExist(categoryName)) {
            categories.add(new Category(categoryName));
        }
    }

    private boolean addCategoryToBook(Category category, String bookTitle, ListManager listManager) {
        if (bookTitle == null || category == null) {
            return false;
        }

        BookList bookList = (BookList) listManager.getList(ListManager.BOOK_LIST);
        ArrayList<Book> books = bookList.getList();
        for (Book book : books) {
            if (book.getTitle().equals(bookTitle)) {
                book.setCategory(category);
                category.getBooks().add(book);
                return true;
            }
        }
        return false;
    }

    private boolean addCategoryToQuote(Category category, int quoteNum, ListManager listManager) {
        if (quoteNum < 0 || category == null) {
            return false;
        }

        QuoteList quoteList = (QuoteList) listManager.getList(ListManager.QUOTE_LIST);
        ArrayList<Quote> quotes = quoteList.getList();
        try {
            Quote quote = quotes.get(quoteNum);
            quote.setCategory(category);
            category.getQuotes().add(quote);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ERROR_INVALID_QUOTE_NUM);
            return false;
        }
        return true;
    }

    private void addRating(RatingList ratings, TextUi ui, ListManager listManager) {
        String[] ratingDetails = information.split(" ", 2);
        String titleOfBookToRate = ratingDetails[1].trim();

        int ratingScore = RatingParser.checkFormatOfRatingValue(ratingDetails[0]);
        if (ratingScore == 0) {
            return;
        }
        boolean isValid = RatingParser.checkRangeOfRatingValue(ratingScore);
        if (isValid && !isRated(ratings, titleOfBookToRate) && isExistingBook(listManager, titleOfBookToRate)) {
            ratings.add(new Rating(ratingScore, titleOfBookToRate));
            ui.printAddRatingToBook(ratingScore, titleOfBookToRate);
        }
    }

    private boolean isExistingBook(ListManager listManager, String titleOfBookToRate) {
        BookList bookList = (BookList) listManager.getList(ListManager.BOOK_LIST);
        ArrayList<Book> existingBooks = bookList.getList();
        boolean doesExist = false;
        for (Book existingBook : existingBooks) {
            if (existingBook.getTitle().equals(titleOfBookToRate)) {
                doesExist = true;
                break;
            }
        }
        if (!doesExist) {
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
