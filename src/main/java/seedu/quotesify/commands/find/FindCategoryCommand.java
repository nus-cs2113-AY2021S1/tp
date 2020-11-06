package seedu.quotesify.commands.find;

import seedu.quotesify.category.CategoryList;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

/**
 * Represents the find category command.
 */
public class FindCategoryCommand extends FindCommand {
    /**
     * Constructor for find category command with user specified arguments.
     * @param arguments User specified arguments.
     */
    public FindCategoryCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the find category command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        CategoryList categoryList = (CategoryList) ListManager.getList(ListManager.CATEGORY_LIST);
        findCategories(categoryList, ui);
    }

    /**
     * Find categories by keyword.
     *
     * @param categoryList List of categories.
     * @param ui Ui of the program.
     */
    private void findCategories(CategoryList categoryList, TextUi ui) {
        quotesifyLogger.log(Level.INFO, "finding categories by keyword.");
        try {
            String keyword = information.trim();
            if (keyword.isEmpty()) {
                throw new QuotesifyException(ERROR_MISSING_KEYWORD);
            }

            CategoryList searchResult = categoryList.findByKeyword(keyword);
            if (searchResult.getList().isEmpty()) {
                throw new QuotesifyException(ERROR_NO_MATCHING_CATEGORY);
            }
            ui.printMatchingCategories(searchResult, keyword);
            quotesifyLogger.log(Level.INFO, "found matching categories!");
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.INFO, e.getMessage());
        }
    }
}
