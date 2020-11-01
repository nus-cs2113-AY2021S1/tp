package seedu.quotesify.commands.edit;

import seedu.quotesify.book.BookList;
import seedu.quotesify.category.Category;
import seedu.quotesify.category.CategoryList;
import seedu.quotesify.category.CategoryParser;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

public class EditCategoryCommand extends EditCommand {
    public EditCategoryCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        CategoryList categories = (CategoryList) ListManager.getList(ListManager.CATEGORY_LIST);
        editCategory(categories, ui);
    }

    private void editCategory(CategoryList categoryList, TextUi ui) {
        quotesifyLogger.log(Level.INFO, "editing category.");
        try {
            String[] oldAndNewCategories = CategoryParser.getEditParameters(information);
            String oldCategory = oldAndNewCategories[0].toLowerCase();
            String newCategory = oldAndNewCategories[1].toLowerCase();

            if (categoryList.isExistingCategory(newCategory)) {
                throw new QuotesifyException("Category [" + newCategory + "] already exists!");
            }

            Category category = categoryList.getCategoryByName(oldCategory);
            category.setCategoryName(newCategory);
            editCategoryInBooksAndQuotes(oldCategory, newCategory);
            ui.printEditCategory(oldCategory, newCategory);
            quotesifyLogger.log(Level.INFO, "edit category success!");
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.WARNING, e.getMessage());
        }
    }

    public void editCategoryInBooksAndQuotes(String oldCategory, String newCategory) {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        bookList.filterByCategory(oldCategory).getList().forEach(book -> {
            book.getCategories().remove(oldCategory);
            book.getCategories().add(newCategory);
        });

        quoteList.filterByCategory(oldCategory).getList().forEach(quote -> {
            quote.getCategories().remove(oldCategory);
            quote.getCategories().add(newCategory);
        });
    }
}
