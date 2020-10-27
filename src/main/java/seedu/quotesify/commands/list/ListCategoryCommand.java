package seedu.quotesify.commands.list;

import seedu.quotesify.category.CategoryList;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class ListCategoryCommand extends ListCommand {
    public ListCategoryCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        CategoryList categoryList = (CategoryList) ListManager.getList(ListManager.CATEGORY_LIST);
        listCategories(categoryList, ui);
    }

    private void listCategories(CategoryList categoryList, TextUi ui) {
        categoryList.updateListsInAllCategories();
        if ((information.isEmpty())) {
            listAllCategories(categoryList, ui);
        } else {
            listAllInCategory(categoryList, ui);
        }
    }

    private void listAllCategories(CategoryList categoryList, TextUi ui) {
        ui.printAllCategories(categoryList);
    }

    private void listAllInCategory(CategoryList categoryList, TextUi ui) {
        try {
            ui.printAllInCategory(categoryList.getCategoryByName(information.toLowerCase()));
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
