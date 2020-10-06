package seedu.duke.category;

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

    public Category getCategoryByName(String name) {
        for (Category category : categories) {
            if (category.getCategoryName().equals(name)) {
                return category;
            }
        }
        return null;
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
        return null;
    }
}
