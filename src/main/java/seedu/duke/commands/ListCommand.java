package seedu.duke.commands;

import seedu.duke.category.Category;
import seedu.duke.category.CategoryList;
import seedu.duke.lists.ListManager;
import seedu.duke.ui.TextUi;

public class ListCommand extends Command {
    private String type;
    private String information;

    public ListCommand(String arguments) {
        String[] details = arguments.split(" ", 2);

        // if user did not provide arguments, let details[0] be empty string
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
            CategoryList categoryList = (CategoryList) listManager.getList(ListManager.CATEGORY_LIST);
            listCategories(categoryList, ui);
            break;
        default:
        }
    }

    private void listCategories(CategoryList categoryList, TextUi ui) {
        if ((information.isEmpty())) {
            listAllCategories(categoryList, ui);
        } else {
            listCategory(categoryList, ui);
        }
    }

    private void listAllCategories(CategoryList categoryList, TextUi ui) {
        ui.printAllCategories(categoryList);
    }

    private void listCategory(CategoryList categoryList, TextUi ui) {
        try {
            ui.printCategory(categoryList.getCategoryByName(information));
        } catch (NullPointerException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
