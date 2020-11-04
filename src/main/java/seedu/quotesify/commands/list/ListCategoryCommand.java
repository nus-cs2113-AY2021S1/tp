package seedu.quotesify.commands.list;

import seedu.quotesify.category.CategoryList;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

/**
 * Represents the list category command.
 */
public class ListCategoryCommand extends ListCommand {
    /**
     * Constructor for list category with user specified arguments.
     *
     * @param arguments user specified arguments.
     */
    public ListCategoryCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the list category command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        CategoryList categoryList = (CategoryList) ListManager.getList(ListManager.CATEGORY_LIST);
        listCategories(categoryList, ui);
    }

    /**
     * Prepares to list categories.
     *
     * @param categoryList list of categories.
     * @param ui Ui of the program.
     */
    private void listCategories(CategoryList categoryList, TextUi ui) {
        categoryList.updateListsInAllCategories();
        if ((information.isEmpty())) {
            listAllCategories(categoryList, ui);
        } else {
            listAllInCategory(categoryList, ui);
        }
    }

    /**
     * List all existing categories.
     *
     * @param categoryList list of categories
     * @param ui Ui of the program
     */
    private void listAllCategories(CategoryList categoryList, TextUi ui) {
        ui.printAllCategories(categoryList);
    }

    /**
     * List all items in the specified category.
     *
     * @param categoryList list of categories
     * @param ui Ui of the program
     */
    private void listAllInCategory(CategoryList categoryList, TextUi ui) {
        try {
            ui.printAllInCategory(categoryList.getCategoryByName(information.toLowerCase()));
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
