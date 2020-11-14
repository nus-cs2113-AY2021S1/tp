package seedu.quotesify.commands.delete;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.category.Category;
import seedu.quotesify.category.CategoryList;
import seedu.quotesify.category.CategoryParser;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Represents the delete category command.
 */
public class DeleteCategoryCommand extends DeleteCommand {
    /**
     * Constructor for delete category command with user input arguments.
     *
     * @param arguments User input arguments.
     */
    public DeleteCategoryCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the delete category command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        CategoryList categories = (CategoryList) ListManager.getList(ListManager.CATEGORY_LIST);
        deleteCategoryFromBookOrQuote(categories, ui);
    }

    /**
     * Prepares the deletion of category from a book, quote, or both.
     *
     * @param categories List of categories.
     * @param ui Ui of the program.
     */
    private void deleteCategoryFromBookOrQuote(CategoryList categories, TextUi ui) {
        try {
            String[] parameters = CategoryParser.getRequiredParameters(information);
            int result = CategoryParser.validateParametersResult(parameters);
            if (result == 1) {
                executeParameters(categories, parameters, ui);
            } else if (result == 0) {
                deleteCategory(categories, parameters[0], ui);
            } else {
                ui.printErrorMessage(ERROR_MISSING_CATEGORY);
                quotesifyLogger.log(Level.WARNING, ERROR_MISSING_CATEGORY);
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * Executes user specified parameters for delete category.
     *
     * @param categoryList List of categories.
     * @param parameters User specified parameters.
     * @param ui Ui of the program.
     */
    private void executeParameters(CategoryList categoryList, String[] parameters, TextUi ui) {
        try {
            String categoryNames = parameters[0];
            assert !categoryNames.isEmpty() : "category name should not be empty";
            String bookTitle = parameters[1];
            String quoteNum = parameters[2];
            int bookTagCount = Integer.parseInt(parameters[3]);
            int quoteTagCount = Integer.parseInt(parameters[4]);

            List<String> categories = CategoryParser.parseCategoriesToList(categoryNames);
            for (String categoryName : categories) {
                categoryName = categoryName.toLowerCase();
                Category category = categoryList.getCategoryByName(categoryName);

                if (bookTagCount == 1) {
                    deleteCategoryFromBook(category, bookTitle, ui);
                }

                if (quoteTagCount == 1) {
                    deleteCategoryFromQuote(category, quoteNum, ui);
                }

                categoryList.updateListsInCategory(category);

                if (category.getSize() == 0) {
                    categoryList.remove(category);
                }
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * Deletes a category from a book.
     *
     * @param category Category object.
     * @param bookIndex Book number.
     * @param ui Ui of the program.
     */
    private void deleteCategoryFromBook(Category category, String bookIndex, TextUi ui) {
        // ignore this action if user did not provide book title
        if (bookIndex.isEmpty()) {
            ui.printErrorMessage(ERROR_NO_BOOK_NUMBER);
            return;
        }
        assert category != null;
        quotesifyLogger.log(Level.INFO, "removing category from book.");
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        try {
            int bookNum = Integer.parseInt(bookIndex) - 1;
            Book book = bookList.getBook(bookNum);
            ArrayList<String> categories = book.getCategories();

            if (!categories.contains(category.getCategoryName())) {
                String errorMessage = String.format(ERROR_CATEGORY_NOT_IN_BOOK,
                        category.getCategoryName(), book.getTitle());
                throw new QuotesifyException(errorMessage);
            }

            categories.remove(category.getCategoryName());
            ui.printRemoveCategoryFromBook(book.getTitle(), category.getCategoryName());
            quotesifyLogger.log(Level.INFO, "successfully removed category from book.");
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage(ERROR_NO_BOOK_FOUND);
            quotesifyLogger.log(Level.WARNING, ERROR_NO_BOOK_FOUND);
        } catch (NumberFormatException e) {
            ui.printErrorMessage(ERROR_INVALID_BOOK_NUM);
            quotesifyLogger.log(Level.WARNING, ERROR_INVALID_BOOK_NUM);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * Deletes a category from a quote.
     *
     * @param category Category object.
     * @param index Quote number.
     * @param ui Ui of the program.
     */
    private void deleteCategoryFromQuote(Category category, String index, TextUi ui) {
        // ignore this action if user did not provide quote number
        if (index.isEmpty()) {
            ui.printErrorMessage(ERROR_NO_QUOTE_NUMBER);
            return;
        }
        assert category != null;
        quotesifyLogger.log(Level.INFO, "removing category from quote.");
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        ArrayList<Quote> quotes = quoteList.getList();
        try {
            int quoteNum = Integer.parseInt(index) - 1;
            Quote quote = quotes.get(quoteNum);
            ArrayList<String> categories = quote.getCategories();

            if (!categories.contains(category.getCategoryName())) {
                String errorMessage = String.format(ERROR_CATEGORY_NOT_IN_QUOTE,
                        category.getCategoryName(), quote.getQuote());
                throw new QuotesifyException(errorMessage);
            }

            categories.remove(category.getCategoryName());
            ui.printRemoveCategoryFromQuote(quote.getQuote(), category.getCategoryName());
            quotesifyLogger.log(Level.INFO, "successfully removed category from quote.");
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage(ERROR_NO_QUOTE_FOUND);
            quotesifyLogger.log(Level.WARNING, ERROR_NO_QUOTE_FOUND);
        } catch (NumberFormatException e) {
            ui.printErrorMessage(ERROR_INVALID_QUOTE_NUM);
            quotesifyLogger.log(Level.WARNING, ERROR_INVALID_QUOTE_NUM);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * Prepares the deletion of category.
     *
     * @param categoryList List of categories.
     * @param categories User specified category names.
     * @param ui Ui of the program.
     */
    private void deleteCategory(CategoryList categoryList, String categories, TextUi ui) {
        quotesifyLogger.log(Level.INFO, "removing category from all books and quotes.");
        for (String name : categories.split(" ")) {
            name = name.toLowerCase();
            try {
                Category category = categoryList.getCategoryByName(name);
                deleteCategoryInBooksAndQuotes(name);
                categoryList.remove(category);
                ui.printRemoveCategory(name);
                quotesifyLogger.log(Level.INFO, "successfully removed category from all books and quotes.");
            } catch (QuotesifyException e) {
                ui.printErrorMessage(e.getMessage());
                quotesifyLogger.log(Level.WARNING, e.getMessage());
            }
        }
    }

    /**
     * Deletes a category from all books and quotes.
     *
     * @param oldCategory Category to be deleted.
     */
    private void deleteCategoryInBooksAndQuotes(String oldCategory) {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        bookList.filterByCategory(oldCategory).getList().forEach(book -> book.getCategories().remove(oldCategory));
        quoteList.filterByCategory(oldCategory).getList().forEach(quote -> quote.getCategories().remove(oldCategory));
    }
}
