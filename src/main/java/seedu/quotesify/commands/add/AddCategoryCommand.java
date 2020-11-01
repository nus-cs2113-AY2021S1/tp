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

public class AddCategoryCommand extends AddCommand {

    public AddCategoryCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        CategoryList categories = (CategoryList) ListManager.getList(ListManager.CATEGORY_LIST);
        addCategoryToBookOrQuote(categories, ui);
    }

    private void addCategoryToBookOrQuote(CategoryList categories, TextUi ui) {
        try {
            String[] tokens = information.split(" ");
            String[] parameters = CategoryParser.getRequiredParameters(tokens);
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

    private void executeParameters(CategoryList categoryList, String[] parameters, TextUi ui) {
        try {
            String categoryNames = parameters[0];
            assert !categoryNames.isEmpty() : "category name should not be empty";

            List<String> categories = CategoryParser.parseCategoriesToList(categoryNames);
            for (String categoryName : categories) {
                categoryName = categoryName.toLowerCase();
                addCategoryToList(categoryList, categoryName);
                Category category = categoryList.getCategoryByName(categoryName);

                String bookNum = parameters[1];
                String quoteNum = parameters[2];

                addCategoryToBook(category, bookNum, ui);
                addCategoryToQuote(category, quoteNum, ui);
                categoryList.updateListsInCategory(category);
            }
        } catch (QuotesifyException e) {
            quotesifyLogger.log(Level.WARNING, e.getMessage());
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

    private void addCategoryToQuote(Category category, String quoteNum, TextUi ui) {
        // ignore this action if user did not provide quote number
        if (quoteNum.isEmpty()) {
            return;
        }

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
