package seedu.quotesify.category;

import org.json.simple.JSONArray;
import seedu.quotesify.book.BookList;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.lists.QuotesifyList;
import seedu.quotesify.quote.QuoteList;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a list of categories.
 */
public class CategoryList extends QuotesifyList<Category> {
    private ArrayList<Category> categories = super.getList();

    /**
     * Default constructor for category list.
     */
    public CategoryList() {
        super(new ArrayList<>());
    }

    /**
     * Constructor for category list with existing list of categories.
     * @param categories array list of categories
     */
    public CategoryList(ArrayList<Category> categories) {
        super(categories);
    }

    /**
     * Checks if a given category exists in the list.
     *
     * @param name category name
     * @return true if it exists, false otherwise
     */
    public boolean isExistingCategory(String name) {
        assert name != null;
        for (Category category : categories) {
            if (category.getCategoryName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a category if it exists.
     *
     * @param name category name
     * @return category object
     * @throws QuotesifyException if category does not exist
     */
    public Category getCategoryByName(String name) throws QuotesifyException {
        assert name != null;
        for (Category category : categories) {
            if (category.getCategoryName().equals(name)) {
                return category;
            }
        }
        throw new QuotesifyException("Category [" + name + "] does not exist!");
    }

    /**
     * Updates the book list and quote list of all categories.
     */
    public void updateListsInAllCategories() {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);

        for (Category category : categories) {
            category.setBookList(bookList.filterByCategory(category.getCategoryName()));
            category.setQuoteList(quoteList.filterByCategory(category.getCategoryName()));
        }
    }

    /**
     * Updates the book list and quote list of a specified category.
     *
     * @param category existing category object
     */
    public void updateListsInCategory(Category category) {
        assert category != null;
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        category.setBookList(bookList.filterByCategory(category.getCategoryName()));
        category.setQuoteList(quoteList.filterByCategory(category.getCategoryName()));
    }

    /**
     * Finds a category by keyword.
     *
     * @param keyword user specified keyword.
     * @return a list of categories matching the specified keyword.
     */
    public CategoryList findByKeyword(String keyword) {
        ArrayList<Category> list = (ArrayList<Category>) categories.stream()
                .filter(category -> category.getCategoryName().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        return new CategoryList(list);
    }

    /**
     * Removes a specified category.
     *
     * @param category existing category object.
     */
    public void remove(Category category) {
        assert category != null;
        categories.remove(category);
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

    @Override
    public JSONArray toJsonArray() {
        JSONArray list = new JSONArray();
        for (Category category : categories) {
            list.add(category.toJson());
        }
        return list;
    }
}
