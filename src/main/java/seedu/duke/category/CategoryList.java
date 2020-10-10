package seedu.duke.category;

import seedu.duke.exception.QuotesifyException;
import seedu.duke.lists.QuotesifyList;

import java.util.ArrayList;

public class CategoryList extends QuotesifyList<Category> {
    private ArrayList<Category> categories = super.getList();

    public CategoryList() {
        super(new ArrayList<>());
    }

    public CategoryList(ArrayList<Category> categories) {
        super(categories);
    }

    public boolean doesCategoryExist(String name) {
        for (Category category : categories) {
            if (category.getCategoryName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Category getCategoryByName(String name) throws NullPointerException {
        for (Category category : categories) {
            if (category.getCategoryName().equals(name)) {
                return category;
            }
        }
        throw new NullPointerException("\"" + name + "\" does not exist!");
    }

    @Override
    public void add(Category category) {
        categories.add(category);
    }

    @Override
    public void delete(int index) {
        categories.remove(index);
    }

    @Override
    public String toString() {
        String list = "";
        int index = 0;
        for (Category category : categories) {
            list += String.format("%d. %s\n", ++index, category.toString());
        }
        return list;
    }
}
