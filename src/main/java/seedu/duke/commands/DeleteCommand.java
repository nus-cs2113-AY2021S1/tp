package seedu.duke.commands;

import seedu.duke.book.Book;
import seedu.duke.book.BookList;
import seedu.duke.category.Category;
import seedu.duke.category.CategoryList;
import seedu.duke.category.CategoryParser;
import seedu.duke.lists.ListManager;
import seedu.duke.quote.Quote;
import seedu.duke.quote.QuoteList;
import seedu.duke.rating.Rating;
import seedu.duke.rating.RatingList;
import seedu.duke.todo.ToDo;
import seedu.duke.todo.ToDoList;
import seedu.duke.ui.TextUi;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private String type;
    private String information;

    public DeleteCommand(String arguments) {
        String[] details = arguments.split(" ", 2);

        // if user did not provide arguments, let details[1] be empty string
        if (details.length == 1) {
            details = new String[]{details[0], ""};
        }
        type = details[0];
        information = details[1];
    }

    @Override
    public void execute(TextUi ui, ListManager listManager) {
        switch (type) {
        case TAG_CATEGORY:
            CategoryList categories = (CategoryList) listManager.getList(ListManager.CATEGORY_LIST);
            deleteCategoryFromBookOrQuote(categories, ui, listManager);
            break;
        case TAG_BOOK:
            BookList books = (BookList) listManager.getList(ListManager.BOOK_LIST);
            deleteBook(books, ui, listManager);
            break;
        case TAG_RATING:
            RatingList ratings = (RatingList) listManager.getList(ListManager.RATING_LIST);
            String bookTitle = information.trim();
            deleteRating(ratings, ui, bookTitle);
            break;
        case TAG_TODO:
            ToDoList toDos = (ToDoList) listManager.getList(ListManager.TODO_LIST);
            int index = computeToDoIndex(information.trim());
            deleteToDo(toDos, index, ui);

        default:
        }
    }

    private void deleteRating(RatingList ratings, TextUi ui, String bookTitle) {
        Rating ratingToBeDeleted = null;
        for (Rating rating : ratings.getList()) {
            if (rating.getTitleOfRatedBook().equals(bookTitle)) {
                ratingToBeDeleted = rating;
                break;
            }
        }
        if (ratingToBeDeleted == null) {
            System.out.println(ERROR_RATING_NOT_FOUND);
            return;
        }
        ratings.delete(ratings.getList().indexOf(ratingToBeDeleted));
        ui.printDeleteRating(bookTitle);
    }

    private void deleteBook(BookList books, TextUi ui, ListManager listManager) {
        String[] titleAndAuthor = information.split("/by");
        String bookTitle = titleAndAuthor[0].trim();

        RatingList ratings = (RatingList) listManager.getList(ListManager.RATING_LIST);
        Rating ratingToBeDeleted;
        for (Rating rating : ratings.getList()) {
            if (rating.getTitleOfRatedBook().equals(bookTitle)) {
                ratingToBeDeleted = rating;
                ratings.delete(ratings.getList().indexOf(ratingToBeDeleted));
                break;
            }
        }

        try {
            ArrayList<Book> filteredBooks = books.find(bookTitle, titleAndAuthor[1].trim());
            books.deleteByBook(filteredBooks.get(0));
            ui.printDeleteBook(filteredBooks.get(0));
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage(ERROR_NO_BOOK_FOUND);
        }
    }

    private void deleteCategoryFromBookOrQuote(CategoryList categories, TextUi ui, ListManager listManager) {
        String[] tokens = information.split(" ");
        String[] parameters = CategoryParser.getRequiredParameters(tokens);
        executeParameters(categories, parameters, ui, listManager);
    }

    private void executeParameters(CategoryList categories, String[] parameters, TextUi ui, ListManager listManager) {
        try {
            String categoryName = parameters[0];
            String bookTitle = parameters[1];
            int quoteNum = Integer.parseInt(parameters[2]) - 1;
            Category category = categories.getCategoryByName(categoryName);

            if (deleteCategoryFromBook(category, bookTitle, listManager)) {
                ui.printRemoveCategoryFromBook(bookTitle, categoryName);
            }

            if (deleteCategoryFromQuote(category, quoteNum, listManager)) {
                QuoteList quoteList = (QuoteList) listManager.getList(ListManager.QUOTE_LIST);
                ArrayList<Quote> quotes = quoteList.getList();
                ui.printRemoveCategoryFromQuote(quotes.get(quoteNum).getQuote(), categoryName);
            }
            ui.printCategorySize(category);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_QUOTE_NUM);
        }
    }

    private boolean deleteCategoryFromBook(Category category, String bookTitle, ListManager listManager) {
        if (bookTitle == null || category == null) {
            return false;
        }

        BookList bookList = (BookList) listManager.getList(ListManager.BOOK_LIST);
        ArrayList<Book> books = bookList.getList();
        for (Book book : books) {
            if (book.getTitle().equals(bookTitle)) {
                book.setCategory(null);
                category.getBooks().getList().remove(book);
                return true;
            }
        }
        return false;
    }

    private boolean deleteCategoryFromQuote(Category category, int quoteNum, ListManager listManager) {
        if (quoteNum < 0 || category == null) {
            return false;
        }

        QuoteList quoteList = (QuoteList) listManager.getList(ListManager.QUOTE_LIST);
        ArrayList<Quote> quotes = quoteList.getList();

        try {
            Quote quote = quotes.get(quoteNum);
            quote.setCategory(null);
            category.getQuotes().getList().remove(quote);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ERROR_INVALID_QUOTE_NUM);
            return false;
        }
        return true;
    }

    private void deleteToDo(ToDoList toDos, int index, TextUi ui) {
        ToDo toDoToBeDeleted = toDos.find(index);
        if(toDoToBeDeleted != null) {
            toDos.delete(index);
            ui.printDeleteToDo(toDoToBeDeleted);
        }
        else {
            System.out.println(ERROR_TODO_NOT_FOUND);
        }
    }

    private int computeToDoIndex(String information) {
        int index = 0;
        try {
            index = Integer.parseInt(information);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_TODO_NUM);
        }

        return index;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
