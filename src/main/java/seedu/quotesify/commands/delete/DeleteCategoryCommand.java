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

public class DeleteCategoryCommand extends DeleteCommand {
    public DeleteCategoryCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        CategoryList categories = (CategoryList) ListManager.getList(ListManager.CATEGORY_LIST);
        deleteCategoryFromBookOrQuote(categories, ui);
    }

    private void deleteCategoryFromBookOrQuote(CategoryList categories, TextUi ui) {
        String[] tokens = information.split(" ");
        String[] parameters = CategoryParser.getRequiredParameters(tokens);
        int result = CategoryParser.validateParametersResult(parameters);
        if (result == 1) {
            executeParameters(categories, parameters, ui);
        } else if (result == 0) {
            deleteCategory(categories, parameters[0], ui);
        } else {
            ui.printErrorMessage(ERROR_MISSING_CATEGORY);
        }
    }

    private void executeParameters(CategoryList categoryList, String[] parameters, TextUi ui) {
        try {
            String categoryNames = parameters[0];
            assert !categoryNames.isEmpty() : "category name should not be empty";

            List<String> categories = CategoryParser.parseCategoriesToList(categoryNames);
            for (String categoryName : categories) {
                categoryName = categoryName.toLowerCase();
                Category category = categoryList.getCategoryByName(categoryName);

                String bookTitle = parameters[1];
                String quoteNum = parameters[2];

                deleteCategoryFromBook(category, bookTitle, ui);
                deleteCategoryFromQuote(category, quoteNum, ui);
                categoryList.updateListsInCategory(category);

                if (category.getSize() == 0) {
                    categoryList.remove(category);
                }
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private void deleteCategoryFromBook(Category category, String bookTitle, TextUi ui) {
        // ignore this action if user did not provide book title
        if (bookTitle.isEmpty()) {
            return;
        }

        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        try {
            int bookNum = Integer.parseInt(bookTitle) - 1;
            Book book = bookList.getBook(bookNum);
            ArrayList<String> categories = book.getCategories();

            if (!categories.contains(category.getCategoryName())) {
                String errorMessage = String.format(ERROR_CATEGORY_NOT_IN_BOOK,
                        category.getCategoryName(), book.getTitle());
                throw new QuotesifyException(errorMessage);
            }

            categories.remove(category.getCategoryName());
            ui.printRemoveCategoryFromBook(book.getTitle(), category.getCategoryName());
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage(ERROR_NO_BOOK_FOUND);
        } catch (NumberFormatException e) {
            ui.printErrorMessage(ERROR_INVALID_BOOK_NUM);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private void deleteCategoryFromQuote(Category category, String index, TextUi ui) {
        // ignore this action if user did not provide quote number
        if (index.isEmpty()) {
            return;
        }

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
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage(ERROR_NO_QUOTE_FOUND);
        } catch (NumberFormatException e) {
            ui.printErrorMessage(ERROR_INVALID_QUOTE_NUM);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private void deleteCategory(CategoryList categoryList, String categories, TextUi ui) {
        for (String name : categories.split(" ")) {
            name = name.toLowerCase();
            try {
                Category category = categoryList.getCategoryByName(name);
                deleteCategoryInBooksAndQuotes(name);
                categoryList.remove(category);
                ui.printRemoveCategory(name);
            } catch (QuotesifyException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    private void deleteCategoryInBooksAndQuotes(String oldCategory) {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        bookList.filterByCategory(oldCategory).getList().forEach(book -> book.getCategories().remove(oldCategory));
        quoteList.filterByCategory(oldCategory).getList().forEach(quote -> quote.getCategories().remove(oldCategory));
    }
}
