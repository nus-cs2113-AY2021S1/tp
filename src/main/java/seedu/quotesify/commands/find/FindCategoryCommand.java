package seedu.quotesify.commands.find;

import seedu.quotesify.category.CategoryList;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class FindCategoryCommand extends FindCommand {
    public FindCategoryCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        CategoryList categoryList = (CategoryList) ListManager.getList(ListManager.CATEGORY_LIST);
        findCategories(categoryList, ui);
    }

    private void findCategories(CategoryList categoryList, TextUi ui) {
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
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
