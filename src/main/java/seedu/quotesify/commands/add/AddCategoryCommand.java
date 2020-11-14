package seedu.quotesify.commands.add;

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

import java.util.List;
import java.util.logging.Level;

/**
 * Represents an add category command.
 */
public class AddCategoryCommand extends AddCommand {

    /**
     * Constructor for add category command with user input arguments.
     *
     * @param arguments User input arguments.
     */
    public AddCategoryCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the add category command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        CategoryList categories = (CategoryList) ListManager.getList(ListManager.CATEGORY_LIST);
        addCategoryToBookOrQuote(categories, ui);
    }

    /**
     * Prepares to addition of category to a book, quote, or both.
     *
     * @param categories List of categories.
     * @param ui Ui of the program.
     */
    private void addCategoryToBookOrQuote(CategoryList categories, TextUi ui) {
        try {
            String[] parameters = CategoryParser.getRequiredParameters(information);
            int result = CategoryParser.validateParametersResult(parameters);
            if (result == 1) {
                executeParameters(categories, parameters, ui);
            } else if (result == 0) {
                ui.printErrorMessage(ERROR_MISSING_BOOK_OR_QUOTE);
            } else {
                ui.printErrorMessage(ERROR_MISSING_CATEGORY);
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Executes user specified parameters for add category.
     *
     * @param categoryList List of categories.
     * @param parameters User specified parameters.
     * @param ui Ui of the program.
     */
    private void executeParameters(CategoryList categoryList, String[] parameters, TextUi ui) {
        try {
            String categoryNames = parameters[0];
            assert !categoryNames.isEmpty() : "category name should not be empty";
            String bookNum = parameters[1];
            String quoteNum = parameters[2];
            int bookTagCount = Integer.parseInt(parameters[3]);
            int quoteTagCount = Integer.parseInt(parameters[4]);

            List<String> categories = CategoryParser.parseCategoriesToList(categoryNames);
            for (String categoryName : categories) {
                categoryName = categoryName.toLowerCase();
                CategoryParser.validateCategoryName(categoryName);
                addCategoryToList(categoryList, categoryName);
                Category category = categoryList.getCategoryByName(categoryName);

                if (bookTagCount == 1) {
                    addCategoryToBook(category, bookNum, ui);
                }

                if (quoteTagCount == 1) {
                    addCategoryToQuote(category, quoteNum, ui);
                }
                categoryList.updateListsInCategory(category);
            }
        } catch (QuotesifyException e) {
            quotesifyLogger.log(Level.WARNING, e.getMessage());
            ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Adds a category to the list of categories if it does not exist.
     *
     * @param categories List of categories.
     * @param categoryName Category name.
     */
    private void addCategoryToList(CategoryList categories, String categoryName) {
        if (!categories.isExistingCategory(categoryName)) {
            categories.add(new Category(categoryName));
        }
    }

    /**
     * Adds a category to a book.
     *
     * @param category Category object.
     * @param bookNum Book number.
     * @param ui Ui of the program.
     */
    private void addCategoryToBook(Category category, String bookNum, TextUi ui) {
        // ignore this action if user did not provide book title
        if (bookNum.isEmpty()) {
            ui.printErrorMessage(ERROR_NO_BOOK_NUMBER);
            return;
        }
        assert category != null;
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        try {
            int bookIndex = Integer.parseInt(bookNum) - 1;
            Book book = bookList.getBook(bookIndex);

            if (book.getCategories().contains(category.getCategoryName())) {
                String errorMessage = String.format(ERROR_CATEGORY_EXISTS_IN_BOOK,
                        category.getCategoryName(), book.getTitle());
                throw new QuotesifyException(errorMessage);
            }

            book.getCategories().add(category.getCategoryName());
            ui.printAddCategoryToBook(book.getTitle(), category.getCategoryName());
            quotesifyLogger.log(Level.INFO, "add category to book success");
        } catch (IndexOutOfBoundsException e) {
            quotesifyLogger.log(Level.WARNING, ERROR_NO_BOOK_FOUND);
            ui.printErrorMessage(ERROR_NO_BOOK_FOUND);
        } catch (NumberFormatException e) {
            quotesifyLogger.log(Level.WARNING, ERROR_INVALID_BOOK_NUM);
            ui.printErrorMessage(ERROR_INVALID_BOOK_NUM);
        } catch (QuotesifyException e) {
            quotesifyLogger.log(Level.WARNING, e.getMessage());
            ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Adds a category to a quote.
     *
     * @param category Category object.
     * @param quoteNum Quote number.
     * @param ui Ui of the program.
     */
    private void addCategoryToQuote(Category category, String quoteNum, TextUi ui) {
        // ignore this action if user did not provide quote number
        if (quoteNum.isEmpty()) {
            ui.printErrorMessage(ERROR_NO_QUOTE_NUMBER);
            return;
        }
        assert category != null;
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        try {
            int quoteIndex = Integer.parseInt(quoteNum) - 1;
            Quote quote = quoteList.getList().get(quoteIndex);

            if (quote.getCategories().contains(category.getCategoryName())) {
                String errorMessage = String.format(ERROR_CATEGORY_EXISTS_IN_QUOTE,
                        category.getCategoryName(), quote.getQuote());
                throw new QuotesifyException(errorMessage);
            }

            quote.getCategories().add(category.getCategoryName());
            ui.printAddCategoryToQuote(quote.getQuote(), category.getCategoryName());
            quotesifyLogger.log(Level.INFO, "add category to quote success");
        } catch (IndexOutOfBoundsException e) {
            quotesifyLogger.log(Level.WARNING, ERROR_NO_QUOTE_FOUND);
            ui.printErrorMessage(ERROR_NO_QUOTE_FOUND);
        } catch (NumberFormatException e) {
            quotesifyLogger.log(Level.WARNING, ERROR_INVALID_QUOTE_NUM);
            ui.printErrorMessage(ERROR_INVALID_QUOTE_NUM);
        } catch (QuotesifyException e) {
            quotesifyLogger.log(Level.WARNING, e.getMessage());
            ui.printErrorMessage(e.getMessage());
        }
    }
}
